/**
 * @(#)Lise.java
 *
 *
 * @author 
 * @version 1.00 2013/6/6
 */


public class Lise {
	
	int no;
	String ad;

    public Lise( String ad, int no) {
    	if ( ad.length() > 0) {
    		this.ad = ad;
    	}
    	else {
    		this.ad = "<isimlendirilmemis>";
    	}
    	
    	this.no = no;
    }
    
    public String getAd() {
    	return ad;
    }
    
    public int getNo() {
    	return no;
    }
}