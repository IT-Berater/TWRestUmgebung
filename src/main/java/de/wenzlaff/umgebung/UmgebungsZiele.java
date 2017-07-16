package de.wenzlaff.umgebung;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

import de.wenzlaff.umgebung.resource.InfoResource;
import de.wenzlaff.umgebung.resource.MindmapResource;
import de.wenzlaff.umgebung.resource.UmgebungResource;
import de.wenzlaff.umgebung.resource.VersionResource;

/**
 * 
 * @author Thomas Wenzlaff
 *
 */
public class UmgebungsZiele extends Application {

	/**
	 * Erzeugt den root Restlet der alle eingehende Aufrufe bearbeitet.
	 * 
	 * http://localhost:9696/umgebung/
	 * 
	 * http://localhost:9696/umgebung/service
	 * 
	 * http://localhost:9696/umgebung/version
	 * 
	 */
	@Override
	public synchronized Restlet createInboundRoot() {

		Router router = new Router(getContext());

		router.attach("/", InfoResource.class);
		router.attach("/service", UmgebungResource.class);
		router.attach("/version", VersionResource.class);
		router.attach("/mindmap", MindmapResource.class);

		return router;
	}

}
