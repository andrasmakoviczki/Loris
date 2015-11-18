package edu.elte.spring.loris.backend.util;

import java.util.List;

import org.apache.http.NameValuePair;

public class ParsedURL {
	String scheme;
	String username;
	String password;
	String host;
	String port;
	String path;
	List<NameValuePair> query;
	String fragment;

	public ParsedURL(String scheme, String username, String password, String host, String port, String path,
			List<NameValuePair> query, String fragment) {
		this.scheme = scheme;
		this.username = username;
		this.password = password;
		this.host = host;
		this.port = port;
		this.path = path;
		this.query = query;
		this.fragment = fragment;
	}

	public String getScheme() {
		return scheme;
	}

	public String getUsername() {
		return username;
	}

	public ParsedURL() {
	}

	public String getPassword() {
		return password;
	}

	public String getHost() {
		return host;
	}

	public String getPort() {
		return port;
	}

	public String getPath() {
		return path;
	}

	public List<NameValuePair> getQuery() {
		return query;
	}

	public String getFragment() {
		return fragment;
	}

	public void setScheme(String scheme) {
		this.scheme = scheme;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setQuery(List<NameValuePair> query) {
		this.query = query;
	}

	public void setFragment(String fragment) {
		this.fragment = fragment;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ParsedURL [scheme=");
		builder.append(scheme);
		builder.append(", username=");
		builder.append(username);
		builder.append(", password=");
		builder.append(password);
		builder.append(", host=");
		builder.append(host);
		builder.append(", port=");
		builder.append(port);
		builder.append(", path=");
		builder.append(path);
		builder.append(", query=");
		builder.append(query);
		builder.append(", fragment=");
		builder.append(fragment);
		builder.append("]");
		return builder.toString();
	}
	
	
}
