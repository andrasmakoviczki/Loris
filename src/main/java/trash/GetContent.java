package trash;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

public class GetContent {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	String str;
	ThreadPoolTaskExecutor executor;

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public GetContent() {
		//int timeout = 20000;
		//String url = "http://index.hu/24ora/rss/default/";

		str = new String();
		// executor = new FeedRefreshExecutor("pool", 1, 50);
		executor = new ThreadPoolTaskExecutor();
		// System.out.println("hello");
		// String str = "CommaFeed/2.3.0-SNAPSHOT (https://www.commafeed.com)";
	}

	public void exec() {
		
		  executor.setCorePoolSize(5);
		  executor.afterPropertiesSet();
		executor.execute(new Runnable() {
			@Override
			public void run() {
				int i = 0;
			   while (i != 10) {
				try {	
					Thread.sleep(5000);
					logger.info("loop: " + i);
					i = i+1;
//					// return "Task finished";
//					HttpGetter g = new HttpGetter();
//					String url = "http://index.hu/24ora/rss/default/";
//					int timeout = 20000;
//					HttpResultí res = g.getBinary(url, timeout);
//					if (res != null) {
//						byte[] xml = res.getContent();
//						Charset encoding = FeedUtils.guessEncoding(xml);
//						String so = xml.toString();
//						String xmlString = FeedUtils.trimInvalidXmlCharacters(new String(xml, encoding));
//						System.out.println(xmlString.substring(0, 10));
//						Thread.sleep(3000);
//					} else {
//						try {
//							Thread.sleep(15000);
//						} catch (InterruptedException e) {
//						}
					}
				 catch (Exception e) {
				}
			}
			 }
		}); //anonym method
	}
}
