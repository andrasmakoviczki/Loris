package edu.elte.spring.loris.model.test;

import edu.elte.spring.loris.model.rssStream.rssFeedDownload;

public class DemoServiceXmlConfig
{
    public void demoServiceMethod()
    {  	
    	rssFeedDownload r = new rssFeedDownload("http://index.hu/24ora/rss/default/");
		r.fetcher();
    }
}
