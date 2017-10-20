package de.wenzlaff.umgebung.resource;

import java.io.Serializable;
import java.util.Date;

/**
 * Das Daten Model für die Mindmap.
 * 
 * @author Thomas Wenzlaff
 */
public class MindmapModell implements Serializable {

	private static final long serialVersionUID = 1L;

	private String version = "0.0.1";

	private String name;

	private Date erstellungsDatum;

	public MindmapModell() {
		super();
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getErstellungsDatum() {
		return erstellungsDatum;
	}

	public void setErstellungsDatum(Date erstellungsDatum) {
		this.erstellungsDatum = erstellungsDatum;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MindmapModell [");
		if (version != null) {
			builder.append("version=");
			builder.append(version);
			builder.append(", ");
		}
		if (name != null) {
			builder.append("name=");
			builder.append(name);
			builder.append(", ");
		}
		if (erstellungsDatum != null) {
			builder.append("erstellungsDatum=");
			builder.append(erstellungsDatum);
		}
		builder.append("]");
		return builder.toString();
	}

}
