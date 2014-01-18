import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.io.*;
import java.awt.event.*;

/**
 * @(#)DosyaSecimPanel.java
 *
 *
 * @author 
 * @version 1.00 2013/6/6
 */


public class DosyaSecimPanel extends JPanel{
	
	final int MAX_GRUP_SAYISI = 30;
	
	JLabel ogrencilerDosyaPath;
	JLabel liselerDosyaPath;
	
	JButton ogrencilerDosyaSec;
	JButton liselerDosyaSec;
	JButton karma;
	
	String liselerDosyaYeri;
	String ogrencilerDosyaYeri;
	
	JComboBox grupSayisiSecim;
	
	JPanel liseSecimi;
	JPanel ogrenciSecimi;
	JPanel grupSayisiSecimi;
	
	File currentOgrenciler;
	File currentLiseler;

    public DosyaSecimPanel() {
    	setLayout( new BoxLayout( this, BoxLayout.Y_AXIS) );
    	
    	liseSecimi = new JPanel();
    	liseSecimi.setLayout( new BoxLayout( liseSecimi, BoxLayout.X_AXIS) );
    	
    	liseSecimi.add( new JLabel( "Adým 1: Liselerin olduðu dosyayý seçiniz ") );
    	liselerDosyaSec = new JButton( "Dosya Seç...");
    	liselerDosyaSec.addActionListener( new DosyaSecListener() );
    	
    	liselerDosyaYeri = "";
    	liselerDosyaPath = new JLabel( liselerDosyaYeri);
    	
    	liseSecimi.add( liselerDosyaSec);
    	liseSecimi.add( liselerDosyaPath);
    	add( liseSecimi);
    	
    	ogrenciSecimi = new JPanel();
    	ogrenciSecimi.setLayout( new BoxLayout( ogrenciSecimi, BoxLayout.X_AXIS) );
    	ogrenciSecimi.add( new JLabel( "Adým 2: Öðrencilerin olduðu dosyayý seçiniz ") );
    	
    	ogrencilerDosyaSec = new JButton( "Dosya Seç...");
    	ogrencilerDosyaSec.addActionListener( new DosyaSecListener() );
    	
    	ogrencilerDosyaYeri = "";
    	ogrencilerDosyaPath = new JLabel( ogrencilerDosyaYeri);
    	
    	ogrenciSecimi.add( ogrencilerDosyaSec);
    	ogrenciSecimi.add( ogrencilerDosyaPath);
    	add( ogrenciSecimi);
    	
    	grupSayisiSecimi = new JPanel();
    	grupSayisiSecimi.setLayout( new BoxLayout( grupSayisiSecimi, BoxLayout.X_AXIS) );
    	grupSayisiSecimi.add( new JLabel( "Adým 3: Öðrencileri kaçarlý gruplara rastgele ayýrmak istersiniz? ") );
    	
    	Integer[] grupSayilari = new Integer[ MAX_GRUP_SAYISI ];
    	
    	for ( int i = 0; i < MAX_GRUP_SAYISI; i++) {
    		grupSayilari[i] = i + 1;
    	}
    	
    	grupSayisiSecim = new JComboBox( grupSayilari);
    	
    	if ( MAX_GRUP_SAYISI > 4) {
    		grupSayisiSecim.setSelectedIndex(5);
    	}
    	else if ( MAX_GRUP_SAYISI > 1) {
    		grupSayisiSecim.setSelectedIndex(1);
    	}
    	else {
    		grupSayisiSecim.setSelectedIndex(0);
    	}
    	
    	grupSayisiSecimi.add( grupSayisiSecim);
    	add( grupSayisiSecimi);
    	
    	karma = new JButton( "Adým 4: Karma Listeyi Oluþtur!");
    	karma.addActionListener( new DosyaSecListener() );
    	add( karma);
    	
    	File currentOgrenciler = null;
    	File currentLiseler = null;
    }
    
    private class DosyaSecListener implements ActionListener {
    	public void actionPerformed( ActionEvent e) {
    		Object comp = e.getSource();
    		
    		if ( comp instanceof JButton && (JButton) comp == ogrencilerDosyaSec) {
    			JFileChooser chooser = new JFileChooser();
    			int result = chooser.showOpenDialog( null);
    			
    			if ( result == JFileChooser.APPROVE_OPTION) {
    				currentOgrenciler = chooser.getSelectedFile();
    				ogrencilerDosyaYeri = currentOgrenciler.getAbsolutePath().toString();
    				ogrencilerDosyaPath.setText( ogrencilerDosyaYeri);
    			}
    		} 
    		else if ( comp instanceof JButton && (JButton) comp == liselerDosyaSec) {
    			JFileChooser chooser = new JFileChooser();
    			int result = chooser.showOpenDialog( null);
    			
    			if ( result == JFileChooser.APPROVE_OPTION) {
    				currentLiseler = chooser.getSelectedFile();
    				liselerDosyaYeri = currentLiseler.getAbsolutePath().toString();
    				liselerDosyaPath.setText( liselerDosyaYeri);
    			}
    		}
    		else if ( comp instanceof JButton && (JButton) comp == karma) {
    			try {
    				if ( currentOgrenciler != null && currentLiseler != null) {
	    				Ogrenciler ogrenciListe = new Ogrenciler();
	    				Scanner scan = new Scanner( currentOgrenciler);
	    				
	    				String line = null;
	    				String[] parts = null;
	    				while ( scan.hasNext() ) {
	    					line = scan.nextLine();
	    					parts = line.split( "\t");
	    					ogrenciListe.addOgrenci( new Ogrenci( parts[0], Integer.parseInt( parts[1]) ) );
	    				}
	    				
	    				scan = new Scanner( currentLiseler);
	    				while ( scan.hasNext() ) {
	    					line = scan.nextLine();
	    					parts = line.split( "\t");
	    					ogrenciListe.addLise( new Lise( parts[1], Integer.parseInt( parts[0]) ) );
	    				}
	    				
	    				ArrayList<ArrayList<Ogrenci>> karilmisListe = ogrenciListe.shuffleStudents( (Integer)grupSayisiSecim.getSelectedItem() );
	    				JFileChooser saver = new JFileChooser();
	    				saver.setDialogTitle( "Karma grup listesinin kaydedileceði yeri seçiniz");
	    				int result = saver.showSaveDialog( null);
	    				
	    				if ( result == JFileChooser.APPROVE_OPTION) {
	    					File fileToSave = new File( saver.getSelectedFile().getAbsolutePath().toString() + ".html" );
	    					
	    					if ( !fileToSave.exists() ) {
	    						fileToSave.createNewFile();
	    					}
	    					
	    					String content = "<?xml version=\"1.0\"?>";
	    					content += "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/dtd/xhtml1-transitional.dtd\">";
	    					content += "<html xmlns=\"http://www.w3.org/1999/xhtml\">";
	    					content += "<head><meta http-equiv=\"Content-Type\" content=\"text/HTML; charset=iso-8859-9;\" /><title>Karýlmýþ Liste - ÜD</title></head>";
	    					content += "<body><a href=\"#\" onclick=\"print();\">Sayfayý Yazdýr</a>";
	    					content += "<h2>Karýlmýþ Liste - ÜD</h2>";
	    					for ( int l = 1; l <= karilmisListe.size(); l++) {
	    						content += "<h3>Grup " + l + "</h3>";
	    						content += "<table>";
	    						content += "<tr><th>Öðrenci Adý</th><th>Lise Adý</th></tr>";
	    						for ( int m = 0; m < karilmisListe.get( l - 1).size(); m++) {
	    							Ogrenci o = karilmisListe.get( l - 1).get( m);
	    							content += "<tr>";
	    							content += "<td>" + o.getAdSoyad() + "</td>";
	    							content += "<td>" + ogrenciListe.getLiseAd( o) + "</td>";
	    							content += "</tr>";
	    						}
	    						content += "</table><br /><br />";
	    					}
	    					content += "</body></html>";
	    					
	    					FileOutputStream fop = new FileOutputStream( fileToSave);
	    					byte[] contentBytes = content.getBytes();
	    					fop.write( contentBytes);
	    					fop.flush();
	    					fop.close();
	    					
	    					JOptionPane.showMessageDialog( null, "Karma liste dosyasý baþarýyla oluþturuldu!\nDosyanýn bilgisayarýnýzdaki yeri: " + fileToSave.getAbsolutePath().toString() );
	    				}
	    			} 
	    			else {
	    				JOptionPane.showMessageDialog( null, "Öðrenciler ve liseler dosyasýný henüz seçmemiþsiniz!", "Hata!", JOptionPane.ERROR_MESSAGE);
	    			}
    			} catch ( Exception exc) {
    				exc.printStackTrace();
    				JOptionPane.showMessageDialog( null, exc.toString() );
    			}
    		}
    	} 
    }
}