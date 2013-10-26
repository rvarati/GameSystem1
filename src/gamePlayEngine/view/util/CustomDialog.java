package gamePlayEngine.view.util;

import gamePlayEngine.controller.GameController;
import gamePlayEngine.controller.GameState;
import gamePlayEngine.model.gameElement.ReadBehavior;
import gamePlayEngine.model.gameElement.graphic.Prop;
import gamePlayEngine.model.gameElement.player.reward.Reward;
import gamePlayEngine.model.gamemodel.GameModel;
import gamePlayEngine.util.Util;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CustomDialog extends JDialog implements ActionListener {
	private JPanel myPanel = null;
	private JButton okButton = null;
	private boolean answer = false;
	private Prop prop;
	private GameState gameState;

	public boolean getAnswer() {
		return answer;
	}

	// TODO Need to move this code from the constructor.
	public CustomDialog(JPanel panel, boolean modal, Prop currentProp,
			GameState gameState) {
		// super(frame, modal);
		myPanel = new JPanel();
		this.prop = currentProp;
		this.gameState = gameState;
		getContentPane().add(myPanel);

		// Read the Behavior tag of the game element and do accordingly.

		ReadBehavior readBehavior = new ReadBehavior(currentProp.getBehavior());

		String toDisplay = currentProp.getText();
		if (readBehavior.getModel().trim().equals("Reward")) {
			if (readBehavior.getAction().trim().equals("GetPoints")) {
				Reward reward = GameModel.getGameModelObject().getCharacter()
						.getReward();
				System.out.println("Points are" + reward.getPoints());
				toDisplay += reward.getPoints();

			}
		}
		System.out.println("To Display is" + toDisplay);
		myPanel.add(new JLabel(toDisplay));
		currentProp.getBehavior();
		okButton = new JButton("OK");
		okButton.addActionListener(this);
		myPanel.add(okButton);
		pack();
		if (currentProp.getColor() == null)
			myPanel.setBackground(new Color(255, 255, 255));
		else
			myPanel.setBackground(Util.StringToColor(currentProp.getColor()
					.toString()));

		setLocationRelativeTo(panel);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (okButton == e.getSource()) {
			System.err.println("User chose OK.");
			if (prop != null) {
				String next = prop.getNext();
				System.out.println("Next to display is" + prop.getNext());

				// Start the nextscreen

				if (next.contains("screen")) {
					GameController.startNextScreen(next, gameState);
				}
				answer = true;
				setVisible(false);
			}
		}
	}

}
