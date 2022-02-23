package it.univpm.OOPProject_TMF.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Estensione della classe Location, sono stati aggiunti i parametri coordinate e distanza
 * 
 * @author Federico Mennecozzi
 */
public class LocationWithCoordinates extends Location {
	
	@JsonProperty("Coordinates")
	private Coordinates place;
	@JsonProperty("Distance[Km]")
	private double distance;
	
	public LocationWithCoordinates(Location l) {
		super(l);
		place = new Coordinates();
		place.setPlaceName(l.getName());
	}	
	@JsonProperty("Coordinates")
	public Coordinates getPlace() {
		return place;
	}
	@JsonProperty("Coordinates")
	public void setPlace(Coordinates place) {
		this.place = place;
	}
	@JsonProperty("Distance[Km]")
	public double getDistance() {
		return distance;
	}
	@JsonProperty("Distance[Km]")
	public void setDistance(double distance) {
		this.distance = distance;
	}

}
