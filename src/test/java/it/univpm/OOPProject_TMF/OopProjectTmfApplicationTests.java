package it.univpm.OOPProject_TMF;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import it.univpm.OOPProject_TMF.connection.Connection;
import it.univpm.OOPProject_TMF.exception.CustomedException;

/**
 * Questa classe presenta la funzione di controllo della correttezza dei parametri mediante test
 * 
 * @author Matteo Forti
 */
@SpringBootTest(classes=Connection.class)
class OopProjectTmfApplicationTests {

	private Connection conn;
	private String countryCode;
	private double distance;

	@BeforeEach
	void setUp() {
		conn = new Connection();
	}

	@SuppressWarnings("unlikely-arg-type")
	@Test
	void TestWrongDistance() throws CustomedException {
		distance = -2;
		equals(conn.getClosestLocation("Montappone", distance));
	}

	@SuppressWarnings("unlikely-arg-type")
	@Test
	void TestOkCC() throws CustomedException {
		countryCode = "IT";
		equals(conn.getTrendsCC(countryCode));
	}

	@SuppressWarnings("unlikely-arg-type")
	@Test
	void TestWrongLengthCC() throws CustomedException {
		countryCode = "ITA";
		equals(conn.getTrendsCC(countryCode));
	}

	@SuppressWarnings("unlikely-arg-type")
	@Test
	void TestNotAvailableCC() throws CustomedException {
		countryCode = "MP";
		equals(conn.getTrendsCC(countryCode));
	}
}
