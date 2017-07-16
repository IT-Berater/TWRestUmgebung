package de.wenzlaff.umgebung.resource;

import org.restlet.resource.Get;

public interface Mindmap {

	@Get
	public MindmapModell getMindmap();
}
