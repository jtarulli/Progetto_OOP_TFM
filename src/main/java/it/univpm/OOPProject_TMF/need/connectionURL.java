package it.univpm.OOPProject_TMF.need;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Attraverso la classe connectionURL avviene il collegamento con l'url, ottendendo così la jSONString
 * 
 * @author Matteo Forti
 */
public class connectionURL {
	
	private String url;

	/**
	 * Costruttore della classe
	 * 
	 * @param url
	 */
	public connectionURL(String url) {
		super();
		this.url = url;
	}
	
	/**
	 * Il metodo getJSON genera la connessione con l'url
	 * 
	 * @return jSONstring
	 */
	@SuppressWarnings("resource")
	public String getJSON(){
		String jSONString=""; 
		try {
			jSONString= new Scanner(new URL(url).openStream(), "UTF-8").useDelimiter("\\A").next();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jSONString;
	}
	
}
