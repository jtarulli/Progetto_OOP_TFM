package it.univpm.OOPProject_TMF.model;

/**
 * Questa classe gestisce gli oggetti di tipo Location
 * 
 * @author Federico Mennecozzi
 */
public class Location {

	private String name;
	private PlaceType placeType;
	private String url;
	private Integer parentid;
	private String country;
	private Integer woeid;
	private String countryCode;

	public Location() {
		super ();
	}
	public Location(Location l) {
		this.name=l.getName();
		this.placeType=l.getPlaceType();
		this.url=l.getUrl();
		this.parentid=l.getParentid();
		this.country=l.getCountry();
		this.woeid=l.getWoeid();
		this.countryCode=l.getCountryCode();
	}	
	public String getName() {
		return name;
	}	
	public void setName(String name) {
		this.name = name;
	}	
	public PlaceType getPlaceType() {
		return placeType;
	}	
	public void setPlaceType(PlaceType placeType) {
		this.placeType = placeType;
	}	
	public String getUrl() {
		return url;
	}	
	public void setUrl(String url) {
		this.url = url;
	}	
	public Integer getParentid() {
		return parentid;
	}	
	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}	
	public String getCountry() {
		return country;
	}	
	public void setCountry(String country) {
		this.country = country;
	}	
	public Integer getWoeid() {
		return woeid;
	}	
	public void setWoeid(Integer woeid) {
		this.woeid = woeid;
	}	
	public String getCountryCode() {
		return countryCode;
	}	
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

}