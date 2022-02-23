package it.univpm.OOPProject_TMF.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Questa classe gestisce le coordinate (latitudine e longitudine) di una determinata località
 * 
 * @author Federico Mennecozzi
 */
public class Coordinates {
	
	@JsonProperty("latitude")
	private double lat;
	
	@JsonProperty("longitude")
	private double lon;
	@JsonIgnore
	private String placeName;
	
	public Coordinates() {

	}
	public Coordinates(String placeName) {
		super();
		this.placeName = placeName;
	}
	@JsonProperty("latitude")
	public double getLat() {
		return lat;
	
	@JsonProperty("latitude")
	public void setLat(double lat) {
		this.lat = lat;
	}
	@JsonProperty("longitude")
	public double getLon() {
		return lon;
	}
	@JsonProperty("longitude")
	public void setLon(double lon) {
		this.lon = lon;
	}
	@JsonIgnore
	public String getPlaceName() {
		return placeName;
	}
	@JsonIgnore
	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}
	
}
