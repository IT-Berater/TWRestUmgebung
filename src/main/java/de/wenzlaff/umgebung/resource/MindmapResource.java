package de.wenzlaff.umgebung.resource;

import java.util.Date;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class MindmapResource extends ServerResource implements Mindmap {

	@Override
	@Get
	public MindmapModell getMindmap() {
		MindmapModell m = new MindmapModell();

		m.setName("Test Mindmap");
		m.setVersion("0.0.1");
		m.setErstellungsDatum(new Date());
		return m;
	}

}
