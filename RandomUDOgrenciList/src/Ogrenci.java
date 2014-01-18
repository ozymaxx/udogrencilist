
/**
 * @(#)Ogrenci.java
 *
 *
 * @author 
 * @version 1.00 2013/6/6
 */

class Ogrenci {
	
	String adSoyad;
	int liseNo;
	
	public Ogrenci( String adSoyad, int liseNo) {
		if ( adSoyad.length() > 0) {
			this.adSoyad = adSoyad;
		}
		else {
			this.adSoyad = "<isimlendirilmemis>";
		}
		this.liseNo = liseNo;
	}
	
	public String getAdSoyad() {
		return adSoyad;
	}
	
	public int getLiseNo() {
		return liseNo;
	}
}
