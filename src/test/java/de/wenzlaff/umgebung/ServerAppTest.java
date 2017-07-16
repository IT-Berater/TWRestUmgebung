package de.wenzlaff.umgebung;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;
import org.restlet.resource.ClientResource;

import de.wenzlaff.umgebung.resource.Mindmap;
import de.wenzlaff.umgebung.resource.MindmapModell;
import de.wenzlaff.umgebung.resource.Umgebung;
import de.wenzlaff.umgebung.resource.Version;

/**
 * Testklasse für den Server.
 * 
 * @author Thomas Wenzlaff
 *
 */
public class ServerAppTest {

	@BeforeClass
	public static void startServer() throws Exception {
		StandaloneServer.start();
	}

	@Test
	public void testGetUmgebungVersion() throws Exception {

		String hostUrl = StandaloneServer.HOST + "/umgebung/version";
		System.out.println("Anfrage von: " + hostUrl);

		ClientResource clientResource = new ClientResource(hostUrl);

		Version resource = clientResource.wrap(Version.class);

		String vers = resource.getVersion();

		System.out.println(vers);

		assertEquals("0.0.1", vers);
	}

	@Test
	public void testGetUmgebungService() throws Exception {

		String hostUrl = StandaloneServer.HOST + "/umgebung/service";
		System.out.println("Anfrage von: " + hostUrl);

		ClientResource clientResource = new ClientResource(hostUrl);

		Umgebung resource = clientResource.wrap(Umgebung.class);

		String vers = resource.getUmgebung();

		System.out.println(vers);
		assertTrue(vers.length() > 10);
	}

	/**
	 * Benötigt org.restlet.ext.jackson in der pom.xml
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetUmgebungMindmap() throws Exception {

		String hostUrl = StandaloneServer.HOST + "/umgebung/mindmap";
		System.out.println("Anfrage von: " + hostUrl);

		ClientResource clientResource = new ClientResource(hostUrl);

		Mindmap resource = clientResource.wrap(Mindmap.class);

		MindmapModell m = resource.getMindmap();

		if (m == null) {
			System.err.println("Es fehlt evl. die org.restlet.ext.jackson unterstüzung in der pom.xml");
			return;
		}

		assertEquals("0.0.1", m.getVersion());

		System.out.println("Mindmap Version: " + m.getVersion());
		System.out.println("Mindmap Name: " + m.getName());
		System.out.println("Erstellungs Datum: " + m.getErstellungsDatum());
		System.out.println(m);

	}

}
