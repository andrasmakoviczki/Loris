package edu.elte.spring.loris.backend.util;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.io.FeedException;

import edu.elte.spring.loris.backend.entity.Channel;
import edu.elte.spring.loris.backend.entity.FeedEntry;
import edu.elte.spring.loris.backend.rssHandle.rssFeedDownload;
import edu.elte.spring.loris.backend.service.ChannelServiceImpl;
import edu.elte.spring.loris.backend.service.FeedEntryServiceImpl;

public class StreamingChannel {
	private static final Logger logger = LoggerFactory.getLogger(StreamingChannel.class);

	ChannelServiceImpl channelService;
	List<Channel> channelList;

	public StreamingChannel() {
		this.channelService = new ChannelServiceImpl();
		this.channelList = channelService.listChannel();
	}

	/*
	 * @Async
	 * 
	 * @Scheduled(fixedDelay = 10000)
	 */
	public void demoServiceMethod() {
		for (Channel c : channelService.listChannel()) {
			rssFeedDownload feeder;
			try {
				feeder = new rssFeedDownload(c.getLink());
				FeedEntryServiceImpl feedEntryServie = new FeedEntryServiceImpl();
				if (c.getLastUpdate() == null || c.getLastUpdate().before(feeder.getFeed().getPublishedDate())) {
					c.setPublishDate(feeder.getFeed().getPublishedDate());
					c.setLastUpdate(new Date());
					channelService.updateChannel(c);

					for (Object seo : feeder.getFeed().getEntries()) {
						SyndEntryImpl se = (SyndEntryImpl) seo;
						FeedEntry fe = feeder.FeedEntryBuild(se);

						feedEntryServie.insertFeedEntry(fe);
					}
				}
				logger.info(c.toString());
			} catch (IllegalArgumentException | FeedException | IOException e) {
				e.printStackTrace();
			}
		}

		channelList = channelService.listChannel();
	}

	// @Async
	@Scheduled(fixedDelay = 10000)
	public void JobServerServiceMethod() {

		// try {
		// StringBuilder result = new StringBuilder();
		// URL url = new URL("http://localhost:8090/contexts");
		// HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		// conn.setRequestMethod("GET");
		// BufferedReader rd = new BufferedReader(new
		// InputStreamReader(conn.getInputStream()));
		// String line;
		// while ((line = rd.readLine()) != null) {
		// result.append(line);
		// }
		// rd.close();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		CloseableHttpClient client = HttpClients.createDefault();
		HttpResponse response;

		// context post
		HttpPost post;// = new
						// HttpPost("http://localhost:8090/contexts/myContext");

		/*
		 * try { response = client.execute(post);
		 * logger.info(response.toString()); } catch (Exception e1) {
		 * e1.printStackTrace(); }
		 */

		// context get
		// HttpGet request = new HttpGet("http://localhost:8090/contexts");
		//
		// try {
		// response = client.execute(request);
		// JSONArray myObject = new
		// JSONArray(EntityUtils.toString(response.getEntity()));
		// logger.info(myObject.get(0).toString());
		// } catch (Exception e) { //
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		// jar upload - multipart
		// try {
		// String s =
		// this.getClass().getResource("/resources/pi-0.0.1-SNAPSHOT.jar").getPath();
		//
		// File p = new File(s);
		// post = new HttpPost("http://localhost:8090/jars/newjar2");
		//
		// // MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		// // //builder.addBinaryBod
		// // builder.addBinaryBody("new", p,
		// // ContentType.APPLICATION_OCTET_STREAM,"new.jar");
		// // HttpEntity multipart = builder.build();
		// // post.setEntity(multipart);
		//
		// FileEntity fileEntity = new FileEntity(p,
		// ContentType.APPLICATION_OCTET_STREAM);
		// fileEntity.setChunked(true);
		// post.setEntity(fileEntity);
		//
		// response = client.execute(post);
		//
		// logger.info(response.getStatusLine().toString());
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		// start new job

		try {
			post = new HttpPost(
					new URL("http://localhost:8090/jobs?appName=newjar&classPath=pi.JavaSparkPi&context=my-low-latency-context&sync=true").toURI());
			ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
			postParameters.add(new BasicNameValuePair("pi.arg", "10"));
			post.setEntity(new UrlEncodedFormEntity(postParameters));
			response = client.execute(post);
			
			
			JSONObject myObject = new JSONObject(EntityUtils.toString(response.getEntity()));
			//for (Object object : myObject) {
				logger.info(myObject.toString());
			//}
			
			logger.info(response.getStatusLine().toString());
		} catch (Exception e) { //
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
