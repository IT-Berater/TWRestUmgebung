package de.wenzlaff.umgebung;

import org.restlet.Component;
import org.restlet.data.Protocol;

/**
 * Ein Beispiel Standalone Server.
 * 
 * @author Thomas Wenzlaff
 *
 */
public class StandaloneServer {

	public static final Integer PORT = 6969;

	public static final String HOST = "http://localhost:" + PORT;

	public static void main(String[] args) throws Exception {

		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					StandaloneServer.start();
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			}
		});
		t.start();
	}

	/**
	 * Der Server mit der Hauptanwendung.
	 * 
	 * Aufruf über
	 * 
	 * http://localhost:9696/umgebung/
	 * 
	 * http://localhost:9696/umgebung/service
	 * 
	 * http://localhost:9696/umgebung/version
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void start() throws Exception {
		Component component = new Component();
		component.getServers().add(Protocol.HTTP, PORT);

		component.getDefaultHost().attach("/umgebung", new UmgebungsZiele());

		component.start();
	}

}
