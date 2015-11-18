package edu.elte.spring.loris.backend.util;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.validator.routines.UrlValidator;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicNameValuePair;

public class URLNormailze {

	public URLNormailze() {
		this.parsedURL = new ParsedURL();
	}

	private ParsedURL parsedURL;

	private static final Pattern URL_PATTERN = Pattern
			.compile("((https?|ftp|file)://)?" + "(([-\\w._~!$&'()*+,;=:]*)@)?" + "(([-\\w._~!$&'()*+,;=]*)"
					+ "(:(\\d{1,5}))?)" + "(/[-\\w._~!$&'()*+,;=:@/]*)?" + "(\\?([-\\w._~!$&'()*+,;=:@/?]*))?"
					+ "(#([-\\w._~!$&'()*+,;=:@/?]*))?(.*)*");
	private static final Pattern USERINFO = Pattern.compile("([-\\w._~!$&'()*+,;=]*):([-\\w._~!$&'()*+,;=]*)");
	private static final Pattern QUERY = Pattern.compile("&?([-\\w._~!$'()*+,;:@/?]*)=([-\\w._~!$'()*+,;:@/?]*)");

	private ParsedURL URLParse(String url) {

		ParsedURL p = new ParsedURL();

		if (url == null) {
			return null;
		}

		Matcher urlMatcher = URL_PATTERN.matcher(url);

		if (urlMatcher.find()) {
			p.setScheme(urlMatcher.group(2));
			p.setHost(urlMatcher.group(6));
			p.setPort(urlMatcher.group(8));
			p.setPath(urlMatcher.group(9));
			p.setFragment(urlMatcher.group(13));
		} else {
			return null;
		}

		if (urlMatcher.group(4) != null) {
			Matcher userinfoMatcher = USERINFO.matcher(urlMatcher.group(4));
			if (userinfoMatcher.find()) {
				p.setUsername(userinfoMatcher.group(1));
				p.setPassword(userinfoMatcher.group(2));
			}
		}

		List<NameValuePair> queryList = new ArrayList<>();
		if (urlMatcher.group(11) != null) {
			Matcher queryMatcher = QUERY.matcher(urlMatcher.group(11));

			while (queryMatcher.find()) {
				NameValuePair keyValue = new BasicNameValuePair(queryMatcher.group(1), queryMatcher.group(2));
				queryList.add(keyValue);
			}
		}
		p.setQuery(queryList);

		return p;
	}

	private URI BuildURI() throws URISyntaxException {

		URIBuilder uBuilder = new URIBuilder()
				.setHost(parsedURL.getHost());
		
		if(parsedURL.getScheme() != null){
			uBuilder.setScheme(parsedURL.getScheme());
		}else {
			uBuilder.setScheme("http");
		}
		
		if(parsedURL.getUsername() != null && parsedURL.getPassword() != null){
			uBuilder.setUserInfo(parsedURL.getUsername(), parsedURL.getPassword());
		}
		
		if(!parsedURL.getQuery().isEmpty()){
			uBuilder.addParameters(parsedURL.getQuery());
		}
		
		if(parsedURL.getPath() != null){
			uBuilder.setPath(parsedURL.getPath());
		}

		if(parsedURL.getFragment() != null){
			uBuilder.setFragment(parsedURL.getFragment());
		}
		
		if(parsedURL.getPort() != null){
			uBuilder.setPort(Integer.parseInt(parsedURL.getPort()));
		}

		URI u = uBuilder.build();
		
		return u;
	}

	public URL normailze(String url) throws MalformedURLException, URISyntaxException {

		String lowerUrl = url.toLowerCase();
		parsedURL = URLParse(lowerUrl);
		URI u = BuildURI();
		UrlValidator validator = new UrlValidator();
		URL normalUrl;
		if(validator.isValid(u.toString())){
			normalUrl = u.toURL();
		} else
		{
			throw new URISyntaxException(u.toString(), "Cannot parse ");
		}
		
		return normalUrl;

	}
}