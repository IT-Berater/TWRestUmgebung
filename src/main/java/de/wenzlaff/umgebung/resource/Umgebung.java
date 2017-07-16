package de.wenzlaff.umgebung.resource;

import org.restlet.resource.Get;

/**
 * Interface für die Umgebung.
 * 
 * @author Thomas Wenzlaff
 *
 */
public interface Umgebung {

	@Get
	String getUmgebung();

}