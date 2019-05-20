package main.GUIGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import game_objects.Crew;
import game_objects.actions.ActionSet;

/**
 * this class creates a jpanel that pops up when the game is over
 */
public class GameOver {

	// back panel
	private JPanel backPanel;

	// label stating whether player won or lost
	private JLabel lblWL;

	// label stating player's final score
	private JLabel lblYourScore;

	/**
	 * set up the panel but don't render yet
	 * 
	 * @param frame Currently visible frame
	 * @param ma    MouseAdapter for play again button
	 */
	public GameOver(JFrame frame, MouseAdapter ma) {

		backPanel = new JPanel();
		backPanel.setBackground(new Color(51, 0, 0));
		backPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		backPanel.setBounds(308, 88, 605, 467);
		backPanel.setVisible(false);
		backPanel.setLayout(null);
		frame.getContentPane().add(backPanel);

		// panel in front of back panel

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 153, 153));
		panel.setBounds(18, 18, 567, 430);
		backPanel.add(panel);
		panel.setLayout(null);

		// Game over label

		JLabel lblGameOver = new JLabel("GAME OVER");
		lblGameOver.setBounds(54, 5, 459, 102);
		lblGameOver.setForeground(new Color(153, 0, 0));
		lblGameOver.setFont(new Font("Krungthep", Font.PLAIN, 60));
		panel.add(lblGameOver);

		lblWL = new JLabel("");
		lblWL.setBounds(5, 150, 700, 33);
		lblWL.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		panel.add(lblWL);

		lblYourScore = new JLabel("Your score: ");
		lblYourScore.setBounds(147, 220, 300, 33);
		lblYourScore.setFont(new Font("Lucida Grande", Font.PLAIN, 27));
		panel.add(lblYourScore);

		// play again button

		JButton btnPlayAgain = new JButton("Play again");
		btnPlayAgain.setBounds(264, 307, 107, 29);
		btnPlayAgain.addMouseListener(ma);
		panel.add(btnPlayAgain);

	}

	/**
	 * render game over panel
	 */
	public void render(int daysTaken, int totalDays, Crew crew) {

		// get ship name
		String shipName = crew.getShip().getName();

		// store whether player won in a variable
		boolean didWin = crew.getShip().getMissingPieces() == 0;

		// less crew members in crew gives higher score. less dead gives higher score.
		// more extra days gives higher score. higher shield health gives higher score.
		int finalScore = didWin
				? (int) Math.pow(3, (crew.numAlive() + 1) * (4 - crew.getCrewMembers().size() + 1) - 1)
						+ 300 * (totalDays + 1 - daysTaken) + crew.getShip().getShieldHealth()
				: 0;

		// display score
		lblYourScore.setText(lblYourScore.getText() + finalScore);

		// disable all other buttons
		ActionSet.getInstance().openPanel();

		if (didWin) {
			lblWL.setText("You win! You found all missing pieces of " + shipName + " in " + daysTaken + " days");
		} else {
			lblWL.setText("You lose! You did not find all pieces of " + shipName + " after " + daysTaken + " days");

		}

		// make it visible
		backPanel.setVisible(true);
	}
}
