package edu.elte.spring.loris.backend.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import edu.elte.spring.loris.backend.entity.FeedEntry;
import edu.elte.spring.loris.backend.entity.Topic;
import edu.elte.spring.loris.backend.service.FeedEntryService;
import edu.elte.spring.loris.backend.service.FeedEntryServiceImpl;

public class JobServer {
	private static final Logger logger = LoggerFactory.getLogger(JobServer.class);

	CloseableHttpClient client;
	HttpResponse response;
	FeedEntryService feService;

	JobServer() {
		feService = new FeedEntryServiceImpl();
	}

	public void ListContextSB() {

		try {
			URL url = new URL("http://localhost:8090/contexts");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");

			StringBuilder result = new StringBuilder();
			BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
			rd.close();

			logger.info("StringBuilder: " + result.toString());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void ListContext() {
		// context get
		client = HttpClients.createDefault();
		HttpGet request = new HttpGet("http://localhost:8090/contexts");
		JSONArray contexts;
		try {
			response = client.execute(request);
			contexts = new JSONArray(EntityUtils.toString(response.getEntity()));

			logger.info("JSONArray");
			for (Object c : contexts) {
				logger.info(c.toString());
			}
		} catch (Exception e) { //
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void CreateContext() {
		client = HttpClients.createDefault();
		String contextName = "myContext";

		HttpPost post = new HttpPost("http://localhost:8090/contexts/" + contextName + "?dependent-jar-uris=file:///home/andris/jars/bundle.jar");

		try {
			response = client.execute(post);
			logger.info(response.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void JarUpload() {
		// jar upload - multipart
		client = HttpClients.createDefault();
		try {
			String uploadFilePath = this.getClass().getResource("/resources/ScalaPi-0.0.1-SNAPSHOT.jar").getPath();
			//String uploadFilePath = this.getClass().getResource("/resources/pi-0.0.1-SNAPSHOT.jar").getPath();
			String appName = "newjar";

			File uploadFile = new File(uploadFilePath);
			HttpPost post = new HttpPost("http://localhost:8090/jars/" + appName);

			// MultipartEntityBuilder builder = MultipartEntityBuilder.create();
			// //builder.addBinaryBod
			// builder.addBinaryBody("new", p,
			// ContentType.APPLICATION_OCTET_STREAM,"new.jar");
			// HttpEntity multipart = builder.build();
			// post.setEntity(multipart);

			FileEntity fileEntity = new FileEntity(uploadFile, ContentType.APPLICATION_OCTET_STREAM);
			fileEntity.setChunked(true);
			post.setEntity(fileEntity);

			response = client.execute(post);
			logger.info("file upload:" + response.getStatusLine().toString());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void StartJob() {
		// start new job
		client = HttpClients.createDefault();
		try {
			HttpPost post = new HttpPost(
					new URL("http://localhost:8090/jobs?appName=newjar&classPath=edu.elte.spring.loris.spark.model.Lda&context=myContext&sync=true&timeout=300")
							.toURI());
			
			ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
			/*postParameters.add(new BasicNameValuePair("pi.arg", "10"));
			post.setEntity(new UrlEncodedFormEntity(postParameters));*/
			
			response = client.execute(post);

			//eredmény
			String s = EntityUtils.toString(response.getEntity());
			logger.info(response.getStatusLine().toString());
			logger.info(s);
			
			JSONObject fullResult = new JSONObject(StringEscapeUtils.unescapeJava(s).replace("\"[", "[").replace("]\"", "]"));
			JSONArray resultBody = fullResult.getJSONArray("result");
			
			List<Topic> topics = new ArrayList<>();
			
			for(int i = 0; i < resultBody.length();++i){
				JSONObject rowIdTopicPair = resultBody.getJSONObject(i);
				String rowId = rowIdTopicPair.getString("rowId");
				JSONArray topicArray = rowIdTopicPair.getJSONArray("topic");// getJSONArray("topic");
				
				FeedEntry fe = new FeedEntry();
				fe = feService.findFeedEntry(rowId);
				
				for(int j = 0; j < topicArray.length();++j){
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
				logger.info("Updated" + fe.toString());
			}
			
		} catch (Exception e) { //
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// @Async
	@Scheduled(fixedDelay = 60000)
	public void JobServerServiceMethod() {
		CreateContext();
		ListContext();
		JarUpload();
		StartJob();
		//logger.info("asd");
	}
}
