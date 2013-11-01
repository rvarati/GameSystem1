/**
 * Dummy View class implementation - Will be replaced by actual view classes 
 */
package gamePlayEngine.view;

import gamePlayEngine.controller.GameController;
import gamePlayEngine.controller.GameState;
import gamePlayEngine.controller.Message;
import gamePlayEngine.model.gameElement.graphic.If;
import gamePlayEngine.model.gameElement.graphic.Location;
import gamePlayEngine.model.gameElement.graphic.Prop;
import gamePlayEngine.model.gameElement.player.Profile;
import gamePlayEngine.model.gameElement.player.reward.Reward;
import gamePlayEngine.model.gamemodel.GameModel;
import gamePlayEngine.util.Util;
import gamePlayEngine.view.util.ImagePanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
//import org.apache.log4j.Logger;

public class GameViewFrame extends javax.swing.JFrame {

	private static final int JPANELSCENECONSTANT = 100;
	private static final int FRAME_WIDTH = 600;
	private static final int FRAME_HEIGHT = 800;
	private GameController controller;
	private JFrame jFrame;
	private JPanel jPanelScene;
	private static JLayeredPane layeredPane;
	private BufferedImage myPicture;

	public GameViewFrame() {
		System.out.println("view has been initialized");
	}

	public GameController getController() {
		return controller;
	}

	public void setController(GameController controller) {
		this.controller = controller;
	}

	public void viewStartAct() {

		jFrame = new JFrame("Background Example");

		JLabel picLabel = new JLabel();

		picLabel.setSize(this.getWidth(), this.getHeight());
		picLabel.setLayout(new FlowLayout());

		
		jFrame.setLocationRelativeTo(null);
		jFrame.pack();
		jFrame.setVisible(true);
		jFrame.setSize(1000, 800);
		jFrame.setLayout(new BorderLayout());

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		jFrame.setLocation(dim.width / 2 - jFrame.getSize().width / 2,
				dim.height / 2 - jFrame.getSize().height / 2);
		layeredPane = new JLayeredPane();
		jFrame.add(layeredPane, BorderLayout.CENTER);
		layeredPane.setBounds(0, 0, 1000, 800);

	}

	public void setBackgroundImage(String imageName) {
		
		Image image = null;
		try {
			image = ImageIO.read(getClass().getResource(imageName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		jFrame.add(new ImagePanel(image));
		jFrame.setSize(FRAME_HEIGHT, FRAME_WIDTH);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setVisible(true);
	}

	public void displayNext(Prop prop, GameState gameState) {
		if (prop != null) {
			String next = prop.getNext();
			System.out.println("Iniyan ... Next to display is" + prop.getNext());

			if (next != null) {
				if (next.contains("screen")) {
								GameController.startNextScreen(next, gameState);
							
				}
			} else {
				System.out.println("Need to start sequencing");
			}
			setVisible(false);
		}
	}

	
	public void addCheckBox(final Prop currentProp, final GameState gameState,char answerValue) {
		jPanelScene = Util.panelPosition(currentProp.getLocation(), false, currentProp);
		JCheckBox checkBox = new JCheckBox(currentProp.getText());
		System.out.println("Answer Value is : "+ answerValue);
		checkBox.setMnemonic(answerValue);
		checkBox.setSelected(false);		
		checkBox.setBackground(Color.yellow);
		jPanelScene.add(checkBox);
		layeredPane.add(jPanelScene,JPANELSCENECONSTANT);	
		
		checkBox.addMouseListener(new MouseListener(){
			private JTextArea ta = new JTextArea();
			private JScrollPane scrollPane = new JScrollPane();
			public void mouseClicked(MouseEvent arg0) {
				
			}

			
			public void mouseEntered(MouseEvent arg0) {
			
				jPanelScene = Util.panelPosition(currentProp.getLocation(), false, currentProp);				
				ta = new JTextArea(currentProp.getHint(),5,15);				
				ta.setLineWrap(true);					
				ta.setWrapStyleWord(true);
				
				jPanelScene.add(new JLabel("Hint : "));
				
			    jPanelScene.add(ta);
				layeredPane.add(jPanelScene);
			}

			
			public void mouseExited(MouseEvent arg0) {
				System.out.println("Exitting!!!");
				ta.setVisible(false);
				jPanelScene.remove(ta);
			}

			public void mousePressed(MouseEvent arg0) {
			}

			public void mouseReleased(MouseEvent arg0) {
				
			}
			
		});
		
		checkBox.addItemListener(new ItemListener(){

			public void itemStateChanged(ItemEvent e) {
				String[] params = e.paramString().split(",");
				if(((JCheckBox)e.getSource()).getMnemonic() == 'C'){
				gameState.setIsCorrect(true);
				}                
			}			
		});
	}
		
	
	public void addinformationBox(final Prop currentProp, final GameState gameState) {		
		try {			
			jPanelScene = Util.panelPosition(currentProp.getLocation(), true,
					currentProp);
		} catch (NullPointerException e) {
			jPanelScene = Util.panelPosition(Location.C, true, currentProp);
		}
		layeredPane.add(jPanelScene, 200);
				
		displayNext(currentProp, gameState);
	}

	public JFrame createNewFrame(String name, ImagePanel image) {
		JFrame jFrame = new JFrame(name);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		jFrame.setSize(1000, 800);
		jFrame.setLayout(new BorderLayout());

		jFrame.pack();
        jFrame.validate();
		jFrame.setVisible(true);
		return jFrame;

	}

	public static void resetLayeredPane() {
		layeredPane.removeAll();
		layeredPane.revalidate();
		layeredPane.repaint();
		layeredPane.setVisible(true);
	}
		

	public void addImage(final GameState gameState) {

		final Prop prop = (Prop) gameState.getGameElement();
       
        if(prop.getLocation() == null) {
        	System.out.println("location is null");
        }
        
		try{
			System.out.println("Background: " + gameState.getScene().getBackdrop());
			setBackgroundImage(gameState.getScene().getBackdrop());
		}catch(NullPointerException ns){
			System.out.println("Yes.. backdrop is null!!!");
		}
		

		try{
			jPanelScene = Util.panelPosition(prop.getLocation(), false, prop);
			
		}catch(Exception e){
			jPanelScene = Util.panelPosition(Location.C, false, prop);			
		}		
				
		String imageName = ((Prop)gameState.getGameElement()).getType().getTypeName();
		System.out.println("./Resources/"+((Prop)gameState.getGameElement()).getType().getTypeName());
		Image image = null;
		try {
			image = ImageIO.read(getClass().getResource(imageName));
			System.out.println("Reading image from the file");
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		ImagePanel ip = new ImagePanel(image);
		ip.setImage(image);
		ip.setLocation(jPanelScene.getX(), jPanelScene.getY());
		ip.setOpaque(false);
		ip.setSize(250, 400);
	    layeredPane.add(ip);
	   // jFrame.add(layeredPane);
	    //jFrame.setLocationRelativeTo(null);
	    //jFrame.setVisible(true);
		System.out.println("Image done!!");
		Profile profile = GameModel.getGameModelObject().getCharacter().getProfile();
		String name = profile.getName();
		String title = profile.getTitle();
		List<String> degrees = profile.getDegrees();
		StringBuffer s = new StringBuffer("");
		Iterator it = degrees.iterator();
		
		while(it.hasNext()) {
			s.append(it.next());
		}
						
		List<String> skills = profile.getSkills();
		StringBuffer s2 = new StringBuffer("");
		Iterator itr = skills.iterator();
		
		while(itr.hasNext()) {
			s2.append(itr.next());
		}
		int experienceYrs = profile.getExperienceYears();
		String communication = profile.getCommunication();
		String leadership = profile.getLeadership();
		String teamWork = profile.getTeamwork();
		String demoGraphics = profile.getDemographics();
		final String str = "CHARACTER PROFILE";
		final String toDisplay = str+ "\n" + "\r\nName: "+name + "\n" +"\r\nTitle : "+title+ "\n" + "\r\nTeamWork : "+teamWork+
				                 "\n\r\nCommunication : "+communication +"\n" + "\r\nDegrees : "+ s + "\n" + "\r\nSkills : " + s2 +
				                  "\r\nExperience : "+ experienceYrs + "\n" + "\r\nLeadership : "+ leadership + "\n" +
				                   "\r\nTeamwork: " + teamWork;	
		
		ip.addMouseListener(new MouseListener(){
			private JTextArea ta;
			private JScrollPane pane;
			public void mouseClicked(MouseEvent e) {			
			}

			public void mouseEntered(MouseEvent e) {
				System.out.println("Profile Information is : "+toDisplay);
				jPanelScene = Util.panelPosition(Location.UC, false, prop);
				ta = new JTextArea(toDisplay);
				ta.setEditable(false);  // TODO so that it cannot be edited
				ta.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
				ta.setSize(jPanelScene.getWidth(), jPanelScene.getHeight());
				ta.setLineWrap(true);
				ta.setWrapStyleWord(true);
				jPanelScene.add(ta);
				layeredPane.add(jPanelScene);
			}

			public void mouseExited(MouseEvent e) {				
				ta.setVisible(false);
				jPanelScene.remove(ta);							
			}

			public void mousePressed(MouseEvent arg0) {
			}

			public void mouseReleased(MouseEvent arg0) {
			}
			
		});
	}

	public void addButton(final GameState gameState) {

		final Prop prop = (Prop) gameState.getGameElement();
		
		if (prop.getType().getTypeName().equals("Button1")) {
			jPanelScene = Util.panelPosition(prop.getLocation(), false, prop);
			jPanelScene.add(new FadingButtonTF(gameState));
		
			layeredPane.add(jPanelScene, new Integer(0), 100);
		} else if (prop.getType().getTypeName().equals("Button2")) {

			JButton btn = new JButton(prop.getText());
			if (prop.getColor() == null){
				btn.setBackground(new Color(255, 255, 255));
				System.out.println("Inside GameViewFrame if statement");
			}
			else
			{
				btn.setBackground(Util
						.StringToColor(prop.getColor().toString()));

			btn.setFont(new Font(prop.getFont().toString(), Font.PLAIN, Integer
					.parseInt(prop.getTextSize())));
			btn.setPreferredSize(Util.panelDimension(prop.getSize()));
			btn.setBorder(new LineBorder(Color.BLACK, 4));
			System.out.println("Button End Game Created !!!!!");
			jPanelScene = Util.panelPosition(prop.getLocation(), false, prop);
			jPanelScene.add(btn);
			layeredPane.add(jPanelScene, new Integer(0), 100);

			btn.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent arg0) {					
					resetLayeredPane();
				}
			});
			}
		}else if(prop.getType().getTypeName().equals("Button3")){
			JButton btn = new JButton(prop.getText());
			if (prop.getColor() == null){
				btn.setBackground(new Color(255, 255, 255));
				System.out.println("Inside GameViewFrame if statement");
			}
			else
			{
				btn.setBackground(Util
						.StringToColor(prop.getColor().toString()));

			btn.setFont(new Font(prop.getFont().toString(), Font.PLAIN, Integer
					.parseInt(prop.getTextSize())));
			btn.setPreferredSize(Util.panelDimension(prop.getSize()));
			btn.setBorder(new LineBorder(Color.BLACK, 4));			
			jPanelScene = Util.panelPosition(prop.getLocation(), false, prop);
			jPanelScene.add(btn);
			layeredPane.add(jPanelScene, new Integer(0), 100);
		
			btn.addActionListener(new ActionListener() {				
				public void actionPerformed(ActionEvent e) {
					resetLayeredPane();
					System.out.println("Calling Next screen.......................");
					displayNext(prop, gameState);	
				}
			});
			}
		}
		/**
		 * Create panel with Act+Scene+Screen name. and populate with game
		 * elements. Once we create all the panels, link the panels with user
		 * interaction.
		 */

		
	}
}