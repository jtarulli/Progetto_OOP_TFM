package it.univpm.OOPProject_TMF.service;


import it.univpm.OOPProject_TMF.exception.CustomedException;
import it.univpm.OOPProject_TMF.model.Location;
import it.univpm.OOPProject_TMF.model.LocationWithCoordinates;
import it.univpm.OOPProject_TMF.model.Metadata;
import it.univpm.OOPProject_TMF.model.Stats;

import java.util.List;

/**
 * Interfaccia per visualizzare i metodi del Service
 * @author jacopotarulli
 *
 */

@org.springframework.stereotype.Service
public interface Service {

	public abstract List<Metadata> MetadataList();
	public abstract List<LocationWithCoordinates> DataList(String placeName)throws CustomedException;
	public abstract List<LocationWithCoordinates> DataList(String placeName, double distance) throws CustomedException;
	public abstract List<Location> DataListCC(String countryCode) throws CustomedException;
	public abstract List<Location> TrendsAvailable();
	public abstract List<Stats> Stats();

}
