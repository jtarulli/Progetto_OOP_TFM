package it.univpm.OOPProject_TMF.connection;

import it.univpm.OOPProject_TMF.exception.CustomedException;
import it.univpm.OOPProject_TMF.model.Location;

/**
* Check controlla e risolve gli errori del Connection
* @author Jacopo Tarulli
* @author Matteo Forti
* @author Federico Mennecozzi
*/

public class Check {
	
	public Check(){
	}
	
	/**
	* Metodo per controllare che il countryCode sia effettivamente di 2 lettere
	* @param cc
	*/
	public void checkCC(String cc ) throws CustomedException {
		if (cc.length()!= 2) throw new CustomedException("Il countryCode deve essere di 2 lettere");
	
	}

	/**
	* Abbiamo notato che il metodo getCoordinates restituisce errore quando legge nel nome di 
	* una località i caratteri ' H' (spazio+H maiuscola). Quindi sostituisce l'H maiuscola con 
	* l'h minuscola nelle 4 location in cui si presenta l'errore (Belo Horizonte, Den Haag, 
	* Port Harcourt e New Haven)
	* @param loc
	*/
	public void lowerCase(Location loc ) throws CustomedException {
		if(	loc.getWoeid() ==455821
				||loc.getWoeid() ==726874
					||	loc.getWoeid() == 1404447
						||		loc.getWoeid() ==2458410) {
		String name = loc.getName().toLowerCase();
		loc.setName(name.substring(0, 1).toUpperCase()+name.substring(1));
		}
	
	}
	
}
