package de.wenzlaff.umgebung;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.restlet.resource.ClientResource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import de.wenzlaff.umgebung.resource.Mindmap;
import de.wenzlaff.umgebung.resource.MindmapModell;
import de.wenzlaff.umgebung.resource.Umgebung;
import de.wenzlaff.umgebung.resource.Version;

/**
 * Testklasse für den Server.
 * 
 * @author Thomas Wenzlaff
 */
@DisplayName("REST Server Testklasse")
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

	@Performance
	@DisplayName("Mehrfach Anfragen Service wenzlaff.de")
	@RepeatedTest(value = 5, name = "Wiederholungs Lauf {currentRepetition} von {totalRepetitions}")
	public void wiederholungenTesten(TestInfo testInfo, RepetitionInfo repetitionInfo) {
		String hostUrl = StandaloneServer.HOST + "/umgebung/service";
		System.out.println("Anfrage von: " + hostUrl);

		ClientResource clientResource = new ClientResource(hostUrl);

		Umgebung resource = clientResource.wrap(Umgebung.class);

		String vers = resource.getUmgebung();

		assertTrue(vers.length() > 10);

		System.out.println("Display Klasse: " + testInfo.getTestClass()); // Display Klasse: Optional[class
																			// de.wenzlaff.umgebung.ServerAppTest]
		System.out.println("Display Name: " + testInfo.getDisplayName()); // Display Name: Wiederholungs Lauf 1 von 1
		System.out.println("Display Methode: " + testInfo.getTestMethod()); // Display Methode: Optional[public void
																			// de.wenzlaff.umgebung.ServerAppTest.wiederholungenTesten(org.junit.jupiter.api.TestInfo)]
		System.out.println("Wiederholungen: " + repetitionInfo.getCurrentRepetition() + " von "
				+ repetitionInfo.getTotalRepetitions());
	}

	/**
	 * Braucht: <dependency> <groupId>org.junit.jupiter</groupId>
	 * <artifactId>junit-jupiter-params</artifactId>
	 * <version>${junit.jupiter.version}</version> <scope>test</scope> </dependency>
	 */
	@Performance
	@DisplayName("Parameter Test mit unterschiedlichen URLs wenzlaff.de")
	@ParameterizedTest(name = "Testfall Nr. {index} mit URL Argument [{arguments}]")
	@ValueSource(strings = { "/umgebung/service", "/umgebung/mindmap", "/umgebung/version" })
	public void parameterTesten(String hostServiceUrl) {

		String hostUrl = StandaloneServer.HOST + hostServiceUrl;
		ClientResource clientResource = new ClientResource(hostUrl);
		Umgebung resource = clientResource.wrap(Umgebung.class);

		String vers = resource.getUmgebung();
		assertTrue(vers.length() > 4);
	}

	@Performance
	@DisplayName("CSV Flugdaten validierung")
	@ParameterizedTest(name = "Test Nr. {index} mit Summe: {1} und UID: {0} ")
	@CsvFileSource(resources = "/flug-data.csv")
	void flugDatenDateiTest(String uid, int summe) {

		assertNotNull(uid, "Da fehlt eine UID");
		assertTrue(summe > 2000, "Das sind zu wenig Flugzeuge!");
	}
}
