//package com.saxion.nl.retroapptive.communication.data.gatherer.isis;
//
//import com.saxion.nl.retroapptive.communication.data.gatherer.DataGatherer;
//import com.saxion.nl.retroapptive.communication.login.LoginCredentials;
//
//import org.apache.http.auth.AuthScope;
//import org.apache.http.auth.UsernamePasswordCredentials;
//import org.apache.http.client.CredentialsProvider;
//import org.apache.http.impl.client.BasicCredentialsProvider;
//import org.apache.http.impl.client.DefaultHttpClient;
//
//
//
//public class ApacheIsisDataGatherer implements DataGatherer {
//
//	private final DefaultHttpClient httpClient;
//
//	private String host;
//	private LoginCredentials loginCredentials;
//
//	public ApacheIsisDataGatherer() {
//		this.httpClient = new DefaultHttpClient();
//	}
//
//
//	private static ApacheIsisDataGatherer apacheIsisDataGatherer = null;
//
//	public static ApacheIsisDataGatherer getInstance() {
//		if (apacheIsisDataGatherer == null) {
//			apacheIsisDataGatherer = new ApacheIsisDataGatherer();
//		}
//		return apacheIsisDataGatherer;
//	}
//
//	public String getHost() {
//		return host;
//	}
//
//	public void setHost(final String host) {
//		this.host = host;
//	}
//
//	public LoginCredentials getLoginCredentials() {
//		return loginCredentials;
//	}
//
//	public void setLoginCredentials(final LoginCredentials loginCredentials) {
//		this.loginCredentials = loginCredentials;
//		final CredentialsProvider provider = new BasicCredentialsProvider();
//		provider.setCredentials(new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT), new UsernamePasswordCredentials(loginCredentials.getUsername(), loginCredentials.getPassword()));
//		httpClient.setCredentialsProvider(provider);
//	}
////
////	@Override
////	public List<Project> getProjects() {
////		return null;
////	}
////
////	@Override
////	public List<Sprint> getSprints(Project sprint) {
////		return null;
////	}
////
////	@Override
////	public List<Notitie> getNotities(Sprint sprint) {
////
////
//////		String[] args = {};
//////		RORequest r = RORequest.To("http://192.168.0.103:8080/restful/services/ToDoItems/actions/notYetComplete/invoke", Resource.Collection, args);
//////
//////
//////
//////		ROClient.getInstance().executeT(Collection.class, "GET", r, null);
////
//// return  null;
////
////	}
////
////	@Override
////	public List<Notitie> getNotities(Project sprint) {
////		return null;
////	}
////
////
////
////
////}
