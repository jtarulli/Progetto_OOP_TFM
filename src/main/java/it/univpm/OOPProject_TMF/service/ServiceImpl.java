package it.univpm.OOPProject_TMF.service;

import it.univpm.OOPProject_TMF.connection.Connection;
import it.univpm.OOPProject_TMF.exception.CustomedException;
import it.univpm.OOPProject_TMF.model.Location;
import it.univpm.OOPProject_TMF.model.LocationWithCoordinates;
import it.univpm.OOPProject_TMF.model.Metadata;
import it.univpm.OOPProject_TMF.model.Stats;

import java.util.List;

/**
 * Implementazione del Service con tutte le chiamate ai metodi del Connection
 * 
 * @author Jacopo Tarulli
 */
@org.springframework.stereotype.Service
public class ServiceImpl implements Service {
	
	private Connection db = new Connection();
	@Override
	public List<Metadata> MetadataList() {

		return db.getMetadata();
	}
	@Override
	public List<Location> DataList() {

		return db.getTrendsClosest();
	}
	@Override
	public List<Location> TrendsAvailable() {

		return db.getTrendsAvailable();
	}
	@Override
	public List<LocationWithCoordinates> DataList(String placeName, double distance) throws CustomedException {
		return db.getTrendsClosestWithDistance(placeName, distance);
	}
	@Override
	public List<Location> DataListCC(String countryCode) throws CustomedException {
		return db.getTrendsCC(countryCode);
	}
	@Override
	public List<Stats> Stats() {
		return db.getStats();
	}
}
