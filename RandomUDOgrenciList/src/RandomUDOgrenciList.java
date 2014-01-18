import javax.swing.*;
import java.awt.*;

/**
 * @(#)RandomUDOgrenciList.java
 *
 * RandomUDOgrenciList application
 *
 * @author 
 * @version 1.00 2013/6/6
 */
 
public class RandomUDOgrenciList {
    
    public static void main(String[] args) {
    	JFrame frame = new JFrame( "ÜD Grup Karma Programý");
    	frame.getContentPane().add( new DosyaSecimPanel() );
    	frame.setResizable( true);
    	frame.setLocation(100,100);
    	frame.setPreferredSize( new Dimension( 1000, 150) );
    	frame.setResizable( false);
    	frame.pack();
    	frame.setVisible( true);
    }
}
