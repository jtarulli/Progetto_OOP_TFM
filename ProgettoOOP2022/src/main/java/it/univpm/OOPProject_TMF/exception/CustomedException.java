package it.univpm.OOPProject_TMF.exception;

/**
 * La classe CustomedException gestisce le eccezioni per il file location.txt o per input errato
 * 
 * @author Federico Mennecozzi
 */
public class CustomedException extends  Exception {

	private static final long serialVersionUID = 1L;

	public CustomedException(String message) {
		super(message);
		System.out.print("Bad Data Input");
	}

}
