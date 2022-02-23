package it.univpm.OOPProject_TMF.model;

/**
 * Classe per le location di tipo Country che estende location con un counter e un array delle città di quel paese
 *  
 * @author Federico Mennecozzi
 */
public class Stats extends Location  {

    private int count;
    private String[] town;
    
    public Stats(Location loc,int c) {
        super(loc);
        this.count=c;
    }
    
    public void setCount(int i) {
        this.count=i;
    }
    
    public int getCount() {
        return count;
    }
    
    public String[] getTown() {
        return town;
    }
    
    public void setTown(String[] town) {
        this.town = town;
    }
    
}