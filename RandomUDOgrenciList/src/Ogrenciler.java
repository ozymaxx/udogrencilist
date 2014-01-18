import java.util.*;

/**
 * @(#)Ogrenciler.java
 *
 *
 * @author 
 * @version 1.00 2013/6/6
 */


public class Ogrenciler {
	
	final int VARSAYILAN_OGRENCI_SAYISI_LISEBASI = 6;
	
	ArrayList<Ogrenci> ogrenciler;
	ArrayList<ArrayList<Ogrenci>> karmaGruplar;
	ArrayList<Lise> liseler;

    public Ogrenciler() {
    	ogrenciler = new ArrayList<Ogrenci>();
    	liseler = new ArrayList<Lise>();
    	karmaGruplar = null;
    }
    
    public boolean karildiMi() {
    	return karmaGruplar != null;
    }
    
    public void addOgrenci( Ogrenci ogr) {
    	if ( ogr != null) {
    		ogrenciler.add( ogr);
    	}
    }
    
    public void addLise( Lise l) {
    	if ( l != null) {
    		liseler.add( l);
    	}
    }
    
    public String getLiseAd( Ogrenci o) {
    	for ( int i = 0; i < liseler.size(); i++) {
    		Lise l = liseler.get( i);
    		if ( o.getLiseNo() == l.getNo() ) {
    			return l.getAd();
    		}
    	}
    	
    	return "<belirtilmemis lise adi>";
    }
    
    public ArrayList<ArrayList<Ogrenci>> shuffleStudents( int grupElemanSayisi) {
    	ArrayList[] liseOgrencileri = new ArrayList[liseler.size()];
    	for ( int i = 0; i < liseler.size(); i++) {
    		liseOgrencileri[i] = new ArrayList<Ogrenci>();
    	}
    	for ( int i = 0; i < liseler.size(); i++) {
    		for ( int j = 0; j < ogrenciler.size(); j++) {
    			Ogrenci o = ogrenciler.get( j);
    			if ( liseler.get( i).getNo() == o.getLiseNo() ) {
    				liseOgrencileri[i].add( o);
    			}
    		}
    	}
    	for ( int i = 0; i < liseler.size(); i++) {
    		Collections.shuffle( liseOgrencileri[i]);
    	}
    	karmaGruplar = new ArrayList<ArrayList<Ogrenci>>();
    	for ( int i = 0; i < ogrenciler.size() / grupElemanSayisi + 1; i++) {
    		karmaGruplar.add( new ArrayList<Ogrenci>() );
    	}
    	int curList = 0;
    	int maxEleman = 0;
		if ( liseOgrencileri.length > 0) {
			maxEleman = liseOgrencileri[0].size();
			for ( int j = 1; j < liseOgrencileri.length; j++) {
				if ( liseOgrencileri[j].size() > maxEleman) {
					maxEleman = liseOgrencileri[j].size();
				}
			}
		}
		else {
			maxEleman = VARSAYILAN_OGRENCI_SAYISI_LISEBASI;
		}
		for ( int k = 0; k < maxEleman; k++) {
			for ( int x = 0; x < liseOgrencileri.length; x++) {
				if ( liseOgrencileri[x].size() > k) {
					Ogrenci ogr = ( Ogrenci) liseOgrencileri[x].get( k);
					if ( ogr != null ) {
						karmaGruplar.get( curList).add( ogr);
					}
					if ( karmaGruplar.get( curList).size() == grupElemanSayisi) {
						curList++;
					}
				}
			}
		}
		
		return karmaGruplar;
    }
}