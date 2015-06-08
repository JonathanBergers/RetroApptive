package com.saxion.nl.retroapptive.communication.data.gatherer.isis;

import com.saxion.nl.retroapptive.communication.data.gatherer.isis.applib.exceptions.JsonParseException;
import com.saxion.nl.retroapptive.communication.data.gatherer.isis.applib.representation.JsonRepr;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;

public class ExtendedHttpClient extends DefaultHttpClient {

	public HttpResponse execute(final HttpMethod httpMethod, final String url, final Map<String, Object> args) throws IOException {
		HttpResponse response = null;
		if (httpMethod == HttpMethod.GET) {
			final HttpGet get = new HttpGet(url);
			get.setHeader("Accept", "*/*");
			response = execute(get);
		} else if (httpMethod == HttpMethod.POST) {
			final HttpPost post = new HttpPost(url);
			post.setHeader("Accept", "*/*");
			post.setHeader("Content-Type", "*/*");
			if (args != null) {
				final Map<String, Map<String, Object>> argmap = new HashMap<>();
				String[] params = {};
				params = args.keySet().toArray(params);

				for (String param : params) {
					Map<String, Object> value = new HashMap<>();
					value.put("value", args.get(param));
					argmap.put(param, value);
				}
				final ObjectMapper objectMapper = new ObjectMapper();
				final String data = objectMapper.writeValueAsString(argmap);

				post.setEntity(new StringEntity(data));
			}

			response = execute(post);
		}else if (httpMethod == HttpMethod.PUT) {
			final HttpPut put = new HttpPut(url);
			put.setHeader("Accept", "*/*");
			put.setHeader("Content-Type", "*/*");
			final ObjectMapper objectMapper = new ObjectMapper();
			final String data = objectMapper.writeValueAsString(args);
			
			put.setEntity(new StringEntity(data));
			response = execute(put);
		}
		return response;
	}

	public HttpResponse executeGET(final String url) throws IOException {
		return execute(HttpMethod.GET, url, null);
	}

	public HttpResponse executePOST(final String url) throws IOException {
		return executePOST(url, null);
	}

	public HttpResponse executePOST(final String url, final Map<String, Object> args) throws IOException {
		return execute(HttpMethod.POST, url, args);
	}

	public <T extends JsonRepr> T executeT(Class<T> t, HttpMethod httpMethod, String url) throws IOException, JsonParseException {
		return executeT(t, httpMethod, url, null);
	}

	public <T extends JsonRepr> T executeT(Class<T> t, HttpMethod httpMethod, String url, Map<String, Object> args) throws IOException, JsonParseException {
		HttpResponse response = execute(httpMethod, url, args);

		try {
			String json = EntityUtils.toString(response.getEntity());
			JsonParser jp = new JsonFactory().createJsonParser(json);
			ObjectMapper objectMapper = new ObjectMapper();
			T representation = objectMapper.readValue(jp, t);
			return representation;
		} catch (Exception e) {
			e.printStackTrace();
			throw new JsonParseException();
		}
	}

}
