package gamePlayEngine.util;

import gamePlayEngine.model.gameElement.graphic.Location;
import gamePlayEngine.model.gameElement.graphic.Prop;
import gamePlayEngine.model.gameElement.player.Size;
import gamePlayEngine.view.util.RoundedPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.lang.reflect.Field;

import javax.swing.JPanel;

public class Util {

	// Change the string color to Color object.
	public static Color StringToColor(String col) {
		//Color c=new Color(255,0,0);
		Color c=null;
		 if(col.equals("LIGHT_YELLOW")) {
			 c = new Color(255, 255, 128);
		 }
		 if(col.equals("LIGHT_BLUE")) {
			 c = new Color(32,143, 255);
		 }
		 if(col.equals("LIGHT_TURQUOISE")) {
			 c = new Color(126, 211, 188);
		 }
		 if(col.equals("LIGHT_PURPLE")) {
			 c = new Color(188, 121, 255);
		 }
		 if(col.equals("LIGHT_RED")) {
			 c = new Color(255, 117, 117);
		 }
		 if(col.equals("LIGHT_PINK")) {
			 c = new Color(255, 193, 214);
		 }
		 if(col.equals("LIGHT_ORANGE")) {
			 c = new Color(241, 142, 10);
		 }
		 if(col.equals("LIGHT_PEACH")) {
			 c = new Color(255,206,132);
		 }
		 if(col.equals("RED") ||col.equals("WHITE") || col.equals("YELLOW") || col.equals("BLACK") || 
				 col.equals("GRAY") || col.equals("LIGHT_GRAY") || col.equals("MAGENTA") || 
				 col.equals("BLUE") || col.equals("DARK_GRAY") || col.equals("ORANGE") || 
				 col.equals("PINK") || col.equals("GREEN") || col.equals("CYAN")) 
		 {
			 c = new Color(255, 0, 0);
             	Field field;
             		try {
             				field = Color.class.getField(col);
             				c = (Color) field.get(null);
             		} catch (Exception e) {
             			c = new Color(255, 0, 0);
             		}
		}
		 if(col.equals("PURPLE")) {
			 c = new Color(130, 4, 255);
		 }
		 if(col.equals("TURQUOISE")) {
			 c = new Color(62, 181, 148);
		 }
		 if(col.equals("DARK_YELLOW")) {
			 c = new Color(244, 227, 11);
		 }
		 if(col.equals("DARK_BLUE")) {
			c = new Color(0,0,160);
		 }
		 if(col.equals("DARK_TURQUOISE")) {
				c = new Color(37, 109, 89);
		}
		 if(col.equals("DARK_PURPLE")) {
				c = new Color(64,0,128);
		 }
		 if(col.equals("DARK_RED")) {
				c = new Color(196,0,0);
		 }
		 if(col.equals("DARK_PINK")) {
				c = new Color(232,0,75);
		 }
		 if(col.equals("DARK_ORANGE")) {
				c = new Color(241,142,10);
		 }
		 if(col.equals("DARK_PEACH")) {
				c = new Color(254,163,27);
		 }
		 if(col.equals("MEDIUM_PEACH")) {
				c = new Color(254,182,73);
		 }
		 
		//System.out.println("Inside Util!!");
		//System.out.println(col);
		
		
	/*Field field;
		try {
			field = Color.class.getField(col);
			c = (Color) field.get(null);
		} catch (Exception e) {
			c = new Color(255, 0, 0);
		}
		return c; */
		/*Field field;
		Color clr;
        try {
                field = Color.class.getField(col);
                clr = (Color) field.get();
        } catch (Exception e) {
        	System.out.println("Inside Catch");
                clr = new Color(255, 0, 0);
        }*/
        return c;
	}

	public static Dimension panelDimension(Size size) {
		Dimension dimension = new Dimension();
		switch (size) {
		case SMALL:
			dimension.height = 40;
			dimension.width = 150;
			break;
		case MEDIUM:
			dimension.height = 50;
			dimension.width = 200;
			break;
		case LARGE:
			dimension.height = 100;
			dimension.width = 200;
			break;
		case EXTRA_LARGE:
			dimension.height = 150;
			dimension.width = 200;
			break;
		}
		return dimension;
	}

	public static JPanel panelPosition(Location location,boolean isInformationBox,Prop prop) {
		JPanel jPanel = new JPanel();
		
		//TODO : Need to uncomment this.
		if(isInformationBox){			
			jPanel = new RoundedPanel(prop);
		}
		//check UUL, LLC
		
		switch (location) {
		case UUR:
			jPanel.setBounds(0, 0, 200, 160);
			jPanel.setOpaque(true);
			break;
		case UURC:
			jPanel.setBounds(370, 170, 200, 160);
			jPanel.setOpaque(true);
			break;
		case UUC:
			jPanel.setBounds(370, 0, 200, 160);
			jPanel.setOpaque(true);
			break;
		case UULC:
			jPanel.setBounds(600, 0, 200, 160);
			jPanel.setOpaque(true);
			break;
		case UUL:
			jPanel.setBounds(800, 0, 200, 160);
			jPanel.setOpaque(true);
			break;
			
		case UR:
			jPanel.setBounds(600, 280, 200, 160);
			jPanel.setOpaque(true);
			break;
		case URC:
			jPanel.setBounds(600, 140, 200, 160);
			jPanel.setOpaque(true);
			break;
		case UC:
			jPanel.setBounds(400, 160, 250, 700);
			jPanel.setOpaque(true);
			break;
		case ULC:
			jPanel.setBounds(-45, 180, 200, 160);
			jPanel.setOpaque(true);
			break;
		case UL:
			jPanel.setBounds(200, 160, 250, 700);
			jPanel.setOpaque(true);
			break;
		case R:
			jPanel.setBounds(0, 320, 200, 160);
			jPanel.setOpaque(true);
			break;
		case RC:
			jPanel.setBounds(200, 320, 200, 160);
			jPanel.setOpaque(true);
			break;
		case C:
			jPanel.setBounds(40, 370, 200, 160);
			jPanel.setOpaque(true);
			break;
		case LC:
			jPanel.setBounds(50, 320, 200, 160);
			jPanel.setOpaque(true);
			break;
		case L:
			jPanel.setBounds(800, 320, 200, 160);
			jPanel.setOpaque(true);
			break;
		case DR:
			jPanel.setBounds(0, 480, 200, 160);
			jPanel.setOpaque(true);
			break;
		case DRC:
			jPanel.setBounds(100, 160, 200, 160);
			jPanel.setOpaque(true);
			break;
		case DC:
			jPanel.setBounds(370, 350, 200, 160);
			jPanel.setOpaque(true);
			break;
		case DLC:
			jPanel.setBounds(450, 240, 200, 160);
			jPanel.setOpaque(true);
			break;
		case DL:
			jPanel.setBounds(400, 200, 200, 160);
			jPanel.setOpaque(true);
			break;
		case DDR:
			jPanel.setBounds(0, 640, 200, 160);
			//jPanel.setOpaque(true);
			break;
		case DDRC:
			jPanel.setBounds(200, 640, 200, 160);
			//jPanel.setOpaque(true);
			break;
		case DDC:
			jPanel.setBounds(40, 640, 200, 160);
			//jPanel.setOpaque(true);
			break;
		case DDLC:
			jPanel.setBounds(600, 420, 200, 160);
			//jPanel.setOpaque(true);
			break;
		case DDL:
			jPanel.setBounds(800, 640, 200, 160);
			//jPanel.setOpaque(true);
			break;
		}
		
		if(isInformationBox){
			jPanel.setOpaque(false);	
			System.out.println("Yes. it is in info box!!!");
		}else{
			jPanel.setOpaque(false);
			System.out.println("Not info box!!!");
		}
		return jPanel;
	}
}
