package de.wenzlaff.umgebung;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.restlet.resource.ClientResource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
@DisplayName("REST Server Testklasse")
@RunWith(JUnitPlatform.class)
public class ServerAppTest {

	@BeforeAll
	public static void startServer() throws Exception {
		StandaloneServer.start();
	}

	@DisplayName("Versions Test")
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
