package de.wenzlaff.umgebung.resource;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class VersionResource extends ServerResource implements Version {

	@Get
	public String getVersion() {
		return "0.0.1";
	}

}
