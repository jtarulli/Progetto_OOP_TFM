package it.univpm.OOPProject_TMF.connection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import it.univpm.OOPProject_TMF.exception.CustomedException;
import it.univpm.OOPProject_TMF.model.Coordinates;
import it.univpm.OOPProject_TMF.model.Location;
import it.univpm.OOPProject_TMF.model.LocationWithCoordinates;
import it.univpm.OOPProject_TMF.model.Metadata;
import it.univpm.OOPProject_TMF.model.Stats;
import it.univpm.OOPProject_TMF.need.Distance;
import it.univpm.OOPProject_TMF.need.connectionURL;
import it.univpm.OOPProject_TMF.need.getCoordinates;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Connection è la classe che contiene le implementazioni delle chiamate effettive alle varie API e i calcoli necessari a ottenere le statistiche, usando i metodi presenti nel Need
 * @see it.univpm.OOPProject_TMF.need
 * @author Jacopo Tarulli
 * @author Matteo Forti 
 * @author Federico Mennecozzi
 */

public class Connection {

	private static List<Location> trendsAvailable;
	private static List<Location> trendsClosest;
	private String url;
	private static List<Metadata> metadata;

	/**
	 * Metodo per leggere e restituire il JSON della chiamata all'API di Twitter GetTrendsAvailable
	 * @author Jacopo Tarulli
	 */
	public List<Location> getTrendsAvailable() {
		url = "https://wd4hfxnxxa.execute-api.us-east-2.amazonaws.com/dev/api/1.1/trends/available.json";
		try {
			JSONArray jsonList = (JSONArray) JSONValue.parseWithException(new connectionURL(url).getJSON());

			trendsAvailable = new ObjectMapper().readValue(jsonList.toString(), new TypeReference<List<Location>>() {
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
		return trendsAvailable;
	}

	/**
	 * Metodo per leggere e restituire il JSON della chiamata all'API di Twitter GetTrendsClosest, usando come input le location
	 * presenti nel file location.txt
	 * @author Jacopo Tarulli
	 */
	public List<Location> getTrendsClosest() {
		String urlResponse;
		JSONObject obj;
		String listaAppoggio = "[";
		getCoordinates coordinatesList = new getCoordinates("location.txt");
		try {
			for (Coordinates coordinata : coordinatesList.getCoordinatesRequest()) {
				url = "https://wd4hfxnxxa.execute-api.us-east-2.amazonaws.com/dev/api/1.1/trends/closest.json?lat="
						+ coordinata.getLat() + "&long=" + coordinata.getLon();
				urlResponse = new connectionURL(url).getJSON();
				obj = (JSONObject) JSONValue.parseWithException(urlResponse.substring(1, (urlResponse.length() - 1)));
				if(obj.get("errors")!=null)
					throw new CustomedException(obj.toString());
				listaAppoggio += obj + ",";
			}
			listaAppoggio = listaAppoggio.substring(0, listaAppoggio.length() - 1) + "]";
			JSONArray jsonTrends = (JSONArray) JSONValue.parseWithException(listaAppoggio);
			trendsClosest = new ObjectMapper().readValue(jsonTrends.toString(), new TypeReference<List<Location>>() {
			});

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return trendsClosest;


	}


	/**
	 * Metodo che usando GetTrendsClosest calcola la distanza tra una città data in input e quelle del file location.txt
	 * @param placeName
	 * @author Matteo Forti
	 */
	public List<LocationWithCoordinates> getLocation(String placeName) throws CustomedException{

		List<LocationWithCoordinates> list = new ArrayList<LocationWithCoordinates>();
		getTrendsClosest();
		String[] placeCoord = new getCoordinates().getLatLong(placeName); // place punto di riferimento
		String[] tmp;
		Check check = new Check();
		Distance d = new Distance();
		for (Location loc : trendsClosest) {
			check.lowerCase(loc);
			if (loc.getPlaceType().getPlaceTypeName().equals("Town")) {
				tmp = new getCoordinates().getLatLong(loc.getName() + ", " + loc.getCountryCode()); //
				LocationWithCoordinates l = new LocationWithCoordinates(loc);
				l.getPlace().setLat(Double.parseDouble(tmp[0]));
				l.getPlace().setLon(Double.parseDouble(tmp[1]));
				l.setDistance(d.distanza(placeCoord[0], placeCoord[1], tmp[0], tmp[1]));
				list.add(l);
			}
		}
		return list;
	}


	/**
	 * Metodo che restituisce le locations del JSON trendsAvailable entro una certa distanza in km da un paese,
	 * entrambi dati in input. A causa della stessa struttura, può richiedere un paio di minuti per restituire
	 * i dati 
	 * @param placeName
	 * @param distance
	 * @author Jacopo Tarulli
	 * @author Matteo Forti
	 */
	public List<LocationWithCoordinates> getClosestLocation(String placeName, double distance) throws CustomedException {

		if (distance<0 ) throw new CustomedException ("La distanza non può essere negativa");

		List<LocationWithCoordinates> list = new ArrayList<LocationWithCoordinates>();
		getTrendsAvailable();
		String[] placeCoord = new getCoordinates().getLatLong(placeName); // place punto di riferimento
		String[] tmp;
		Check check = new Check();
		Distance d = new Distance();
		for (Location loc : trendsAvailable) {
			check.lowerCase(loc);
			if (loc.getPlaceType().getPlaceTypeName().equals("Town")) {
				tmp = new getCoordinates().getLatLong(loc.getName() + ", " + loc.getCountryCode());
				LocationWithCoordinates l = new LocationWithCoordinates(loc);
				l.getPlace().setLat(Double.parseDouble(tmp[0]));
				l.getPlace().setLon(Double.parseDouble(tmp[1]));
				l.setDistance(d.distanza(placeCoord[0], placeCoord[1], tmp[0], tmp[1]));
				if(l.getDistance()< distance) list.add(l);
			}
		}

		Collections.sort(list, new Comparator<LocationWithCoordinates>() {
			public int compare(LocationWithCoordinates l1, LocationWithCoordinates l2) {
				return l1.getDistance() < l2.getDistance() ? -1 : (l1.getDistance() > l2.getDistance()) ? 1 : 0;
			}
		});

		return list;

	}

	/**
	 * Il metodo getTrendsCC fornisce una lista con le caratteristiche della location corrispondente al countryCode inserito in input
	 * 
	 * @param countryCode
	 * @author Federico Mennecozzi
	 * @author Matteo Forti
	 */
	public List<Location> getTrendsCC(String countryCode) throws CustomedException {
		getTrendsAvailable();
		Check check = new Check();
		check.checkCC(countryCode);
		Iterator<Location> it = trendsAvailable.iterator();
		List<Location> list = new ArrayList<Location>();
		while (it.hasNext()) {
			Location tmp = it.next();
			check.lowerCase(tmp);
			if (tmp.getCountryCode() != null) {
				if (tmp.getCountryCode().equals(countryCode) && tmp.getName() != tmp.getCountry()) {
					if (tmp.getPlaceType().getPlaceTypeName().equals("Town"))
					{
						list.add(tmp);
					}
				}
			}
		}
		if(list.isEmpty()) throw new CustomedException("CountryCode non presente o non esistente");
		Collections.sort(list, new Comparator<Location>() {
			public int compare(Location l1, Location l2) {
				return l1.getName().compareToIgnoreCase(l2.getName()) ;
			}
		});
		return list;
	}

	/**
	 * Il metodo getMetadata restituisce una lista di metadati, ovvero le informazioni di ogni tipo di dato visualizzato
	 * 
	 * @author Federico Mennecozzi
	 */
	@SuppressWarnings("unchecked")
	public List<Metadata> getMetadata() {
		metadata = new JSONArray();
		metadata.add(new Metadata("name", "name", "String"));
		metadata.add(new Metadata("PlaceType", "placeType", "Object"));
		metadata.add(new Metadata("placeTypeCode", "code", "Integer"));
		metadata.add(new Metadata("placeTypeName", "name", "String"));
		metadata.add(new Metadata("url", "url", "String"));
		metadata.add(new Metadata("parentid", "parentid", "Integer"));
		metadata.add(new Metadata("country", "country", "String"));
		metadata.add(new Metadata("woeid", "woeid", "Integer"));
		metadata.add(new Metadata("countryCode", "countryCode", "String"));

		return metadata;
	}

	/**
	 * Il metodo getStats restituisce una classifica dei paesi con più location per trend
	 * 
	 * @author Federico Mennecozzi
	 */
	public List<Stats> getStats() {
		getTrendsAvailable();
		Iterator<Location> it = trendsAvailable.iterator();
		List<Stats> StatsList = new ArrayList<Stats>();

		int i = 0;

		while (it.hasNext()) {
			Location tmp = it.next();
			if (tmp.getPlaceType().getPlaceTypeName().equals("Country")) {
				Stats stats = new Stats(tmp, i);
				StatsList.add(stats);
			}
		}

		for (Stats var : StatsList) {
			String appoggio = "";
			i = 0;
			for (Location trend : trendsAvailable) {
				if (trend.getCountryCode() != null)
					if (trend.getCountryCode().equals(var.getCountryCode()) && trend.getName() != trend.getCountry()) {

						if (trend.getPlaceType().getPlaceTypeName().equals("Town")) {
							appoggio += trend.getName() + ",";
							i++;
						}
						var.setCount(i);
					}

			}

			var.setTown(appoggio.split(","));
		}

		Collections.sort(StatsList, new Comparator<Stats>() {

			public int compare(Stats o1, Stats o2) {
				return o1.getCount() > o2.getCount() ? -1 : (o1.getCount() < o2.getCount()) ? 1 : 0;

			}
		});

		return StatsList;

	}

}
