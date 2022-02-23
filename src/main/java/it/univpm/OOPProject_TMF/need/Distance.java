package it.univpm.OOPProject_TMF.need;

/**
 * La funzione principale della classe Distance è quella di calcolare 
 * la distanza tra due città attraverso l'utilizzo delle loro coordinate
 * 
 * @author Matteo Forti
 */
public class Distance {
	
private double latA, lonA, latB, lonB;
    
	/**
	 * Costruttore della classe
	 */
    public Distance() {
        super();
    }

    /**
     * Costruttore della classe con i parametri delle due città A e B
     * 
     * @param latA
     * @param lonA
     * @param latB
     * @param lonB
     */
    public Distance(double latA, double lonA, double latB, double lonB) {
        super();
        this.latA = latA;
        this.lonA = lonA;
        this.latB = latB;
        this.lonB = lonB;
    }

    /**
     * Attraverso il metodo distanza è possibile calcolare in Km quanto distano due città inserite
     * 
     * @param a0 (latitudine di A)
     * @param a1 (longitudine di A)
     * @param b0 (latitudine di B)
     * @param b1 (longitudine di B)
     * @return d (distanza tra A e B)
     */
    public double distanza(String a0, String a1, String b0, String b1) {

        latA = stringToDouble(a0);
        lonA = stringToDouble(a1);
        latB = stringToDouble(b0);
        lonB = stringToDouble(b1);

        double R = 6371;
        double pigreco = 3.1415927;
        double lat_alfa, lat_beta;
        double lon_alfa, lon_beta;
        double fi;
        double p, d;

        lat_alfa = pigreco * latA / 180;
        lat_beta = pigreco * latB / 180;
        lon_alfa = pigreco * lonA / 180;
        lon_beta = pigreco * lonB / 180;

        fi = Math.abs(lon_alfa - lon_beta);

        p = Math.acos(Math.sin(lat_beta) * Math.sin(lat_alfa) + Math.cos(lat_beta) * Math.cos(lat_alfa) * Math.cos(fi));
        d = p * R;
        return (d);
    }
    
    /**
     * Il metodo stringToDouble restituisce un double a partire da un parametro stringa
     * 
     * @param a
     * @return Double.parseDouble(a);
     */
    private double stringToDouble(String a) {
        return Double.parseDouble(a);
    }

}
