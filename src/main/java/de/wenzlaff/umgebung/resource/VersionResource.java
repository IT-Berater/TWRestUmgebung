package de.wenzlaff.umgebung.resource;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

/**
 * Beispiel Resource für die Version.
 * 
 * @author Thomas Wenzlaff
 *
 */
public class VersionResource extends ServerResource implements Version {

	@Override
	@Get
	public String getVersion() {
		return "0.0.1";
	}

}
