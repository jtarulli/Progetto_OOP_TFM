package it.univpm.OOPProject_TMF.need;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import it.univpm.OOPProject_TMF.exception.CustomedException;
import it.univpm.OOPProject_TMF.model.Coordinates;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

/**
 * Attraverso la classe getCoordinates è possibile conseguire una lista di coordinate mediante il file del JSON
 * 
 * @author Matteo Forti
 *
 */
public class getCoordinates {

    private String sourceFile;
    private List<Coordinates> listCoordinate;

    public getCoordinates(String file) {
        this.sourceFile = file;
    }
    
    public getCoordinates() {
    	
    }
    
    /**
	 * Il metodo getPlaceFromeFile() ricorre al file in input e crea una lista 
	 * di coordinate solo con il nome della città (parametri di latitudine e longitudine vuoti)
	 * 
	 * @throws IOException
	 * @SuppressWarnings("unused")
	 */
	private void getPlaceFromeFile() throws IOException {
        BufferedReader reader = null;
        String line;
        String[] place = null;
        listCoordinate = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader(new File(sourceFile)));
            line = reader.readLine();
            place = line.split(",");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (String string : place) {
            Coordinates c = new Coordinates();
            c.setPlaceName(string);
            listCoordinate.add(c);
        }
    }
    
	/**a
	 * Il metodo getLatLong, mediante il parametro placeName ritorna 
	 * un array con latitudine e longitudine della città interessata
	 * 
	 * @param placeName
	 * @return array long_lat
	 * @throws BadDataInput (errore dell'input)
	 */
    public String[] getLatLong(String placeName) throws CustomedException {
        JSONObject tmp = null;
        JSONArray tmpArray = null;
        String data;
        String long_lat[];
        String jsonString = new connectionURL("https://wd4hfxnxxa.execute-api.us-east-2.amazonaws.com/dev/geo/" + placeName).getJSON();

        try {
            tmp = (JSONObject) JSONValue.parseWithException(jsonString);
            tmpArray = (JSONArray) JSONValue.parseWithException(tmp.get("features").toString());
            if (tmpArray.isEmpty()) throw new CustomedException("No coordinates found! Try another location");
            tmp = (JSONObject) JSONValue.parseWithException(tmpArray.get(0).toString());

        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(tmp==null) throw new CustomedException("tmp è nullo");
        data = tmp.get("center").toString();
        long_lat = data.substring(1, data.length() - 1).split(",");
        if (Double.parseDouble(long_lat[0]) < -180 || Double.parseDouble(long_lat[0]) > 180
                || Double.parseDouble(long_lat[1]) < -180 || Double.parseDouble(long_lat[1]) > 180)
            throw new CustomedException("Error in input coordinates from GEO! Try with an other location");
        return long_lat;
    }

    /**
	 * Il metodo getCoordinatesRequest() ritorna una lista con namePlace e i parametri di latitudine e longitudine
	 * 
	 * @return listCoordinate
	 * @throws MalformedURLException (url sbagliato)
	 * @throws IOException (errore in input)
	 * @throws BadDataInput (errore per input sbagliato)
	 */
    public List<Coordinates> getCoordinatesRequest() throws MalformedURLException, IOException, CustomedException {
        getPlaceFromeFile();

        for (Coordinates coordinata : listCoordinate) {
            String[] tmp=getLatLong(coordinata.getPlaceName());
            coordinata.setLon(Double.parseDouble(tmp[0]));
            coordinata.setLat(Double.parseDouble(tmp[1]));

        }
        return listCoordinate;
    }
 

}
