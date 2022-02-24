package it.univpm.OOPProject_TMF.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Questa classe serve per contenere i due parametri del placeType, codice e nome
 * 
 * @author Federico Mennecozzi
 */
public class PlaceType {
	@JsonProperty("code")
	private int placeTypeCode;

	@JsonProperty("name")
	private String placeTypeName;

	public PlaceType() {
		super();
	}

	public PlaceType(int code, String name) {
		this.placeTypeCode=code;
		this.placeTypeName=name;
	}

	@JsonProperty("code")
	public Integer getPlaceTypeCode() {
		return placeTypeCode;
	}

	@JsonProperty("code")
	public void setPlaceTypeCode(Integer placeTypeCode) {
		this.placeTypeCode = placeTypeCode;
	}

	@JsonProperty("name")
	public String getPlaceTypeName() {
		return placeTypeName;
	}

	@JsonProperty("name")
	public void setPlaceTypeName(String placeTypeName) {
		this.placeTypeName = placeTypeName;
	}

	@Override
	public String toString() {
		return "PlaceType [code=" + placeTypeCode + ", name=" + placeTypeName + "]";
	}


}