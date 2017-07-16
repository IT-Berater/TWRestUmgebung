package de.wenzlaff.umgebung.resource;

import org.restlet.resource.Get;

public interface Version {

	@Get
	public String getVersion();

}