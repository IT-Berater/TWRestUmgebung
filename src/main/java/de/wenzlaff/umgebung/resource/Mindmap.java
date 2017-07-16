package de.wenzlaff.umgebung.resource;

import org.restlet.resource.Get;

/**
 * Das Interface für die Mindmap.
 * 
 * @author Thomas Wenzlaff
 *
 */
public interface Mindmap {

	@Get
	public MindmapModell getMindmap();
}
