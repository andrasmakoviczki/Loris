package edu.elte.spring.loris.backend.util;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.http.client.utils.URIBuilder;

public class ConnectionCheck {

	public static boolean portIsOpen(String scheme, String host, Integer port, Integer timeout) throws URISyntaxException {
		String u = new URIBuilder().setScheme(scheme).setHost(host).setPort(port).build().toString();
		return portIsOpen(u, timeout);	
	}

	public static boolean portIsOpen(String url, Integer timeout) {
		try {
			URLNormailze uNorm = new URLNormailze();
			URL u = uNorm.normailze(url);

			Socket socket = new Socket();
			if(u.getPort() == -1){
				socket.connect(new InetSocketAddress(u.getHost(), 80),timeout);
			}
			else{
				socket.connect(new InetSocketAddress(u.getHost(), u.getPort()), timeout);
			}
			
			socket.close();

			return true;
		} catch (Exception ex) {
			return false;
		}
	}

}
