package it.univpm.OOPProject_TMF.control;


import it.univpm.OOPProject_TMF.exception.CustomedException;
import it.univpm.OOPProject_TMF.service.Service;
import it.univpm.OOPProject_TMF.service.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller è la classe che traccia le 6 rotte che permettono di ottenere su richiesta statistiche e liste 
 * dal JSON restituito dalla chiamata alle API di Twitter Get Trends Locations
 * 
 * Parametri richiesti: nome città, distanza massima, countryCode
 * 
 * @author Jacopo Tarulli
 *
 */

@RestController
public class Controller {
	@Autowired
	private Service data = new ServiceImpl();

	/**
	 * La rotta /Metadata restituisce la lista dei metadati del JSON trendsAvailable
	 * 
	 * @author Jacopo Tarulli
	 * @see it.univpm.OOPProject_TMF.service
	 */
	@GetMapping("/Metadata")
	public ResponseEntity<Object> getMeta(){
		return new ResponseEntity<>(data.MetadataList(),HttpStatus.OK); 
	}

	/**
	 * La rotta /Data restituisce la location più vicina a quella scritta nel file location.txt tra quelle
	 * presenti nel JSON trendsAvailable. Usa la chiamata API getTrendsClosest
	 * 
	 * @author Jacopo Tarulli
	 * @throws CustomedException 
	 * @see it.univpm.OOPProject_TMF.service
	 */
	@GetMapping("/Locations")
	public ResponseEntity<Object> getLocation(@RequestParam(name = "name", defaultValue = "Montappone") String placeName) throws CustomedException{
		return new ResponseEntity<>(data.DataList(placeName),HttpStatus.OK); 

	}

	/**
	 * La rotta /ClosestLocations restituisce le locations del JSON trendsAvailable entro una certa distanza 
	 * 
	 * @author Jacopo Tarulli
	 * @param placeName (nome della location di riferimento)
	 * @param distance (distanza massima entro la quale cercare le locations)
	 * @throws CustomedException 
	 * @see it.univpm.OOPProject_TMF.service
	 */
	@GetMapping("/ClosestLocations")
	public ResponseEntity<Object> getClosestLocations(@RequestParam(name = "name", defaultValue = "Montappone") String placeName, 
			@RequestParam(name = "distance") double distance) throws CustomedException {
		return new ResponseEntity<>(data.DataList(placeName, distance),HttpStatus.OK); 
	}

	/**
	 * La rotta /CountryCode restituisce le locations del JSON trendsAvailable della nazione specificata dal countryCode
	 * 
	 * @author Jacopo Tarulli
	 * @param cc (countryCode)
	 * @throws CustomedException 
	 * @see it.univpm.OOPProject_TMF.service
	 */
	@GetMapping("/CountryCode")
	public ResponseEntity<Object> getCC(@RequestParam(name = "cc", defaultValue = "IT") String countryCode) throws CustomedException {
		return new ResponseEntity<>(data.DataListCC(countryCode),HttpStatus.OK); 
	}

	/**
	 * La rotta /TrendsAvailable restituisce il JSON trendsAvailable con tutte le location registrate
	 * 
	 * @author Jacopo Tarulli
	 * @see it.univpm.OOPProject_TMF.service
	 */
	@GetMapping("/TrendsAvailable")
	public  ResponseEntity<Object> TrendsAvailable(){

		return new ResponseEntity<>(data.TrendsAvailable(),HttpStatus.OK); 
	}

	/**
	 * La rotta /Stats restituisce la classifica dei paesi con più locations per trend.
	 * 
	 * @author Jacopo Tarulli
	 * @see it.univpm.OOPProject_TMF.service
	 */
	@GetMapping("/Stats")
	public  ResponseEntity<Object> Stats(){

		return new ResponseEntity<>(data.Stats(),HttpStatus.OK); 
	}
}
