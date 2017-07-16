package de.wenzlaff.umgebung.resource;

import org.restlet.resource.Get;

/**
 * Interface für die Version.
 * 
 * @author Thomas Wenzlaff
 *
 */
public interface Version {

	@Get
	public String getVersion();

}