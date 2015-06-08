package com.saxion.nl.retroapptive.communication.data.gatherer.isis;

import com.saxion.nl.retroapptive.communication.data.gatherer.DataGatherer;
import com.saxion.nl.retroapptive.communication.data.gatherer.isis.ExtendedHttpClient;
import com.saxion.nl.retroapptive.communication.data.gatherer.isis.HttpMethod;
import com.saxion.nl.retroapptive.communication.data.gatherer.isis.applib.exceptions.JsonParseException;
import com.saxion.nl.retroapptive.communication.data.gatherer.isis.applib.representation.Action;
import com.saxion.nl.retroapptive.communication.data.gatherer.isis.applib.representation.ActionResult;
import com.saxion.nl.retroapptive.communication.data.gatherer.isis.applib.representation.Collection;
import com.saxion.nl.retroapptive.communication.data.gatherer.isis.applib.representation.CollectionValue;
import com.saxion.nl.retroapptive.communication.data.gatherer.isis.applib.representation.DomainObject;
import com.saxion.nl.retroapptive.communication.data.gatherer.isis.applib.representation.DomainTypeAction;
import com.saxion.nl.retroapptive.communication.data.gatherer.isis.applib.representation.JsonRepr;
import com.saxion.nl.retroapptive.communication.data.gatherer.isis.applib.representation.Link;
import com.saxion.nl.retroapptive.communication.data.gatherer.isis.applib.representation.ObjectMember;
import com.saxion.nl.retroapptive.communication.data.gatherer.isis.applib.representation.Service;
import com.saxion.nl.retroapptive.communication.login.LoginCredentials;
import com.saxion.nl.retroapptive.model.Item;
import com.saxion.nl.retroapptive.model.Model;
import com.saxion.nl.retroapptive.model.Notitie;
import com.saxion.nl.retroapptive.model.Profiel;
import com.saxion.nl.retroapptive.model.Project;
import com.saxion.nl.retroapptive.model.Sprint;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.JsonNode;

public class ApacheIsisDataGatherer implements DataGatherer {

	public static final String HOST = "http://192.168.2.27:8080/restful/";

	public static void main(String[] args) {
		Model model = Model.getInstance();
		System.out.println(model.login(new LoginCredentials("todoapp-admin", "pass")));
	}

	/*-public static void main(String[] args) {
		ApacheIsisDataGatherer dataGatherer = new ApacheIsisDataGatherer("http://127.0.0.1:8080/restful/");

		dataGatherer.setLoginCredentials(new LoginCredentials("todoapp-admin", "pass"));

		dataGatherer.init();
	}*/

	private final ExtendedHttpClient httpClient;

	private String host;
	private LoginCredentials loginCredentials;

	public ApacheIsisDataGatherer(final String host) {
		this.host = host;
		this.httpClient = new ExtendedHttpClient();
	}

	public String getHost() {
		return host;
	}

	public void setHost(final String host) {
		this.host = host;
	}

	public LoginCredentials getLoginCredentials() {
		return loginCredentials;
	}

	@Override
	public int login(final LoginCredentials loginCredentials) {
		this.loginCredentials = loginCredentials;
		final CredentialsProvider provider = new BasicCredentialsProvider();
		provider.setCredentials(new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT), new UsernamePasswordCredentials(loginCredentials.getUsername(), loginCredentials.getPassword()));
		httpClient.setCredentialsProvider(provider);
		try {
			final HttpResponse response = httpClient.executeGET(getHost());
			EntityUtils.toString(response.getEntity());//consumeQuietly removed...
			return response.getStatusLine().getStatusCode();
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return 503;
		}
	}

	@Override
	public List<Project> getProjects() throws IOException, JsonParseException {
		final ActionResult ar = httpClient.executeT(ActionResult.class, HttpMethod.GET, host + "/services/RetroApptiveService/actions/collectProjects/invoke");
		final List<Link> links = ar.getResult().getValueAsList();
		final List<Project> projectList = new ArrayList<>(links.size());
		for (Link link : links) {
			final DomainObject projectDomainObject = httpClient.executeT(DomainObject.class, HttpMethod.GET, link.getHref());
			final Map<String, ObjectMember> members = projectDomainObject.getMembers();
			final String name = members.get("name").getValue().getTextValue();
			final Project project = new IsisProject(name, link.getHref());
			projectList.add(project);
		}
		return projectList;
	}

	@Override
	public List<Sprint> getSprints(final Project project) throws IOException, JsonParseException {
		if (!(project instanceof IsisProject)) {
			throw new IllegalArgumentException("Project must be an instance of IsisProject, else we can't find the project URL");
		}
		final IsisProject isisProject = (IsisProject) project;
		final String projectURL = isisProject.getProjectURL();
		final String sprintsFetchURL = projectURL + "/collections/collectSprints";
		final Collection sprintsCollection = httpClient.executeT(Collection.class, HttpMethod.GET, sprintsFetchURL);
		final List<CollectionValue> values = sprintsCollection.getValue();
		final List<Sprint> sprintList = new ArrayList<>(values.size());
		for (CollectionValue collectionValue : values) {
			final String title = collectionValue.getTitle();
			int sprintNumber = 0;
			if (title.contains(":")) {
				sprintNumber = Integer.parseInt(title.substring(title.indexOf(":") + 1).trim());
			} else {
				sprintNumber = Integer.parseInt(title);
			}
			final Sprint sprint = new IsisSprint(isisProject, sprintNumber, collectionValue.getHref());
			System.out.println("SPRINT URL: " + collectionValue.getHref());
			sprintList.add(sprint);
		}
		return sprintList;
	}

	@Override
	public List<Item> getItems(final Sprint sprint) throws IOException, JsonParseException {
		if (!(sprint instanceof IsisSprint)) {
			throw new IllegalArgumentException("Sprint must be an instance of IsisSprint, else we can't find the sprint URL");
		}
		final IsisSprint isisSprint = (IsisSprint) sprint;
		final String sprintURL = isisSprint.getSprintURL();
		final String itemsFetchURL = sprintURL + "/collections/collectItems";
		final Collection itemsCollection = httpClient.executeT(Collection.class, HttpMethod.GET, itemsFetchURL);
		final List<CollectionValue> values = itemsCollection.getValue();
		final List<Item> itemList = new ArrayList<>(values.size());
		for (CollectionValue collectionValue : values) {
			// final String title = collectionValue.getTitle();
			final String itemFetchURL = collectionValue.getHref();
			final DomainObject itemObject = httpClient.executeT(DomainObject.class, HttpMethod.GET, itemFetchURL);
			final List<Link> links = itemObject.getLinks();
			for (Link link : links) {
				final Map<String, Map<String, JsonNode>> arguments = link.getArguments();
				if (arguments == null) {
					continue;
				}
				final Map<String, JsonNode> categoryMap = arguments.get("category");
				if (categoryMap == null) {
					continue;
				}
				final String category = categoryMap.get("value").getTextValue();
				Item item = null;
				if (category.equalsIgnoreCase("Note")) {
					item = getNoteFromItem(sprint, link);
				}
				if (item != null) {
					itemList.add(item);
				}
			}
		}
		return itemList;
	}

	private Notitie getNoteFromItem(final Sprint sprint, final Link link) {
		final Map<String, Map<String, JsonNode>> arguments = link.getArguments();
		final String description = arguments.get("description").get("value").getTextValue();
		final String summary = arguments.get("summary").get("value").getTextValue();
		final JsonNode categoryNode = arguments.get("subcategory").get("value");
		final String subcategory = categoryNode == null ? "Other" : categoryNode.getTextValue();
		final boolean isPositive = arguments.get("isPositive").get("value").getBooleanValue();

		final JsonNode profileData = arguments.get("profiel").get("value");
		final String profileURL = profileData.get("href").getTextValue();
		final String profileTitle = profileData.get("title").getTextValue();
		final String profileName;
		if (profileTitle.contains(":")) {
			profileName = profileTitle.substring(profileTitle.indexOf(":") + 1).trim();
		} else {
			profileName = profileTitle;
		}
		final Profiel profile = new IsisProfiel(profileName, profileURL);
		return new IsisNotitie(sprint, description, summary, profile, isPositive, subcategory, link.getHref());
	}

	@Override
	public Map<Sprint, List<Item>> getItems(final Project project) throws IOException, JsonParseException {
		final Map<Sprint, List<Item>> itemsMap = new HashMap<>();
		final List<Sprint> sprints = getSprints(project);
		for (Sprint sprint : sprints) {
			final List<Item> items = getItems(sprint);
			itemsMap.put(sprint, items);
		}
		return itemsMap;
	}

	@Override
	public List<String> getPossibleNoteSubcategories(final Sprint sprint) throws IOException, JsonParseException {
		if (!(sprint instanceof IsisSprint)) {
			throw new IllegalArgumentException("Sprint must be an instance of IsisSprint, else we can't find the sprint URL");
		}
		final IsisSprint isisSprint = (IsisSprint) sprint;
		final String sprintURL = isisSprint.getSprintURL();
		final String createNoteURL = sprintURL + "/actions/createNote";
		System.out.println(createNoteURL);
		final Action action = httpClient.executeT(Action.class, HttpMethod.GET, createNoteURL);
		final Map<String, Map<String, JsonNode>> parameters = action.getParameters();
		final JsonNode arrayNode = parameters.get("subcategory").get("choices");
		final List<String> possibleNoteSubcategories = new ArrayList<>();
		for (JsonNode optionJsonNode : arrayNode) {
			String option = optionJsonNode.getTextValue();
			possibleNoteSubcategories.add(option);
		}
		return possibleNoteSubcategories;
	}

	@Override
	public Notitie createNote(final Sprint sprint, final String description, final String summary, final boolean positive, final String subcategory) throws IOException, JsonParseException {
		if (!(sprint instanceof IsisSprint)) {
			throw new IllegalArgumentException("Sprint must be an instance of IsisSprint, else we can't find the sprint URL");
		}
		final IsisSprint isisSprint = (IsisSprint) sprint;
		final String sprintURL = isisSprint.getSprintURL();
		final String createNoteURL = sprintURL + "/actions/createNote/invoke";
		final Map<String, Object> postArgs = new HashMap<>();
		postArgs.put("description", description);
		postArgs.put("summary", summary);
		postArgs.put("positive", String.valueOf(positive));
		postArgs.put("subcategory", subcategory);
		httpClient.executePOST(createNoteURL, postArgs);
		return new Notitie(isisSprint, description, summary, Model.getInstance().getLocalProfile(), positive, subcategory);
	}

	@Override
	public void deleteNote(final Notitie note) throws Exception {
		if (!(note instanceof IsisNotitie)) {
			throw new IllegalArgumentException("Note must be an instance of IsisNote, else we can't find the note URL");
		}
		final IsisNotitie isisSprint = (IsisNotitie) note;
		final String noteURL = isisSprint.getNotitieURL();
		final String deleteNoteURL = noteURL + "/actions/delete";
		httpClient.executeGET(deleteNoteURL);
	}

}
