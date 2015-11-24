package edu.elte.spring.loris.backend.util;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import edu.elte.spring.loris.backend.entity.FeedEntry;
import edu.elte.spring.loris.backend.entity.Topic;
import edu.elte.spring.loris.backend.service.FeedEntryService;

public class JobServer {
	private static final Logger logger = LoggerFactory.getLogger(JobServer.class);

	CloseableHttpClient client;
	HttpResponse response;

	@Autowired
	FeedEntryService feService;

	JobServer() {
	}

	public List<String> ListContext(String scheme, String host, Integer port)
			throws URISyntaxException, JSONException, ParseException, IOException {
		client = HttpClients.createDefault();

		URI uri = new URIBuilder().setScheme(scheme).setHost(host).setPort(port).setPath("/contexts").build();
		HttpGet request = new HttpGet(uri);

		response = client.execute(request);
		logger.info("ListContext status:" + response.getStatusLine().toString());

		JSONArray contexts = new JSONArray(EntityUtils.toString(response.getEntity()));
		List<String> context = new ArrayList<>();

		for (Object object : contexts) {
			if (object instanceof String) {
				context.add((String) object);
			}
		}
		return context;
	}

	public void CreateContext(String scheme, String host, Integer port, String context, String jarPath)
			throws URISyntaxException, ClientProtocolException, IOException {
		client = HttpClients.createDefault();

		URI uri = new URIBuilder().setScheme(scheme).setHost(host).setPort(port).setPath("/contexts/" + context)
				.addParameter("dependent-jar-uris", jarPath).build();
		HttpPost post = new HttpPost(uri);
		response = client.execute(post);
		logger.info("CreateContext status:" + response.getStatusLine().toString());
	}

	public void JarUpload(String scheme, String host, Integer port, String programPath, String appName)
			throws ClientProtocolException, IOException, URISyntaxException {
		client = HttpClients.createDefault();
		String uploadFilePath = this.getClass().getResource(programPath).getPath();
		File uploadFile = new File(uploadFilePath);

		URI uri = new URIBuilder().setScheme(scheme).setHost(host).setPort(port).setPath("/jars/" + appName).build();
		HttpPost post = new HttpPost(uri);

		FileEntity fileEntity = new FileEntity(uploadFile, ContentType.APPLICATION_OCTET_STREAM);
		fileEntity.setChunked(true);
		post.setEntity(fileEntity);

		response = client.execute(post);
		logger.info("JarUpload status:" + response.getStatusLine().toString());
	}

	public String StartJob(String scheme, String host, Integer port, Map<String, String> paramters,
			Map<String, String> postParameters) throws URISyntaxException, ClientProtocolException, IOException {

		client = HttpClients.createDefault();

		URIBuilder uriBuilder = new URIBuilder().setScheme(scheme).setHost(host).setPort(port).setPath("/jobs");

		for (Entry<String, String> e : paramters.entrySet()) {
			uriBuilder.addParameter(e.getKey(), e.getValue());
		}

		URI uri = uriBuilder.build();
		HttpPost post = new HttpPost(uri);
		ArrayList<NameValuePair> postParameterList = new ArrayList<NameValuePair>();

		for (Entry<String, String> e : postParameters.entrySet()) {
			postParameterList.add(new BasicNameValuePair(e.getKey(), e.getValue()));
		}

		post.setEntity(new UrlEncodedFormEntity(postParameterList));

		response = client.execute(post);
		logger.info("StartJob status:" + response.getStatusLine().toString());
		
		if(response.getStatusLine().getStatusCode() != 200){
			logger.warn("JobServer can't finished the process!");
			return null;
		}

		return EntityUtils.toString(response.getEntity());
	}

	public void ProcessJSON(String json) {
		JSONObject fullResult = new JSONObject(
				StringEscapeUtils.unescapeJava(json).replace("\"[", "[").replace("]\"", "]"));

		JSONArray resultBody = fullResult.getJSONArray("result");

		List<Topic> topics = new ArrayList<>();

		for (int i = 0; i < resultBody.length(); ++i) {
			JSONObject rowIdTopicPair = resultBody.getJSONObject(i);
			String rowId = rowIdTopicPair.getString("rowId");
			JSONArray topicArray = rowIdTopicPair.getJSONArray("topic");

			FeedEntry fe = feService.findFeedEntry(rowId);

			for (int j = 0; j < topicArray.length(); ++j) {
				Topic topic = new Topic();
				JSONObject topicPair = topicArray.getJSONObject(j);

				topic.setFeedEntry(rowId);
				topic.setTopicName(topicPair.getString("topicName"));
				topic.setTopicValue(topicPair.getDouble("topicValue"));

				topics.add(topic);
			}

			fe.setLabeled(true);
			fe.setTopic(topics);

			feService.updateFeedEntry(fe);
		}
		logger.info("JSON processing successful");
	}

	@Scheduled(fixedDelay = 300000)
	public void JobServerServiceMethod() {

		String scheme = "http";
		String host = "localhost";
		Integer port = 8090;
		String contextName = "lorisContext";
		String jarPath = "file:///home/andris/jars/bundle.jar";
		String appPath = "/resources/ScalaPi-0.0.1-SNAPSHOT.jar";
		String appName = "lorisSpark";
		String classPath = "edu.elte.spring.loris.spark.model.Lda";
		String sync = "true";
		String timeOut = "10000";

		Map<String, String> appParameters = new HashMap<>();
		appParameters.put("appName", appName);
		appParameters.put("classPath", classPath);
		appParameters.put("context", contextName);
		appParameters.put("sync", sync);
		appParameters.put("timeout", timeOut);

		Map<String, String> appArguments = new HashMap<>();

		try {
			List<String> contexts = ListContext(scheme, host, port);

			if (!contexts.contains(contextName)) {
				CreateContext(scheme, host, port, contextName, jarPath);
			}

			JarUpload(scheme, host, port, appPath, appName);
			String result = StartJob(scheme, host, port, appParameters, appArguments);

			if (result != null) {
				ProcessJSON(result);
			}
		} catch (JSONException | ParseException | URISyntaxException | IOException e) {
			e.printStackTrace();
		}

	}
}
