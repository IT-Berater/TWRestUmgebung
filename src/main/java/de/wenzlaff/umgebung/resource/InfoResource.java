package de.wenzlaff.umgebung.resource;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

/**
 * http://localhost:9696/umgebung/
 * 
 * @author thomaswenzlaff
 *
 */
public class InfoResource extends ServerResource {

	@Get
	public String getInformationen() {
		return "Folgenden Schnittstellen sind in der Umgebung implementiert:" + "\nhttp://localhost:9696/umgebung"
				+ "\nhttp://localhost:9696/umgebung/service" + "\nhttp://localhost:9696/umgebung/version";
	}

}
