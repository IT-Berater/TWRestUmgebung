package de.wenzlaff.umgebung.resource;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

/**
 * Liefert die Systemumgebung.
 * 
 * @author Thomas Wenzlaff
 *
 */
public class UmgebungResource extends ServerResource implements Umgebung {

	@Override
	@Get
	public String getUmgebung() {
		return "Umgebung:\n" + System.getProperties().toString().replaceAll(",", "\n");
	}

}
