package main.GUIPregame;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import main.GUIManager;
import main.GUIGame.DayWindow;

/**
 * this class displays rules of the game
 */
public class RulesWindow {

	// currently visible frame
	private JFrame frame;

	// gui controller
	private GUIManager man;

	/**
	 * Launch the application.
	 */
	public RulesWindow(GUIManager manager) {
		man = manager;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					initialize();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * dispose frame
	 */
	public void closeWindow() {
		frame.dispose();
	}

	/**
	 * @return 0 to start game, 1 to view rules
	 */
	public void finishedWindow() {
		man.closeRulesWindow(this);
	}

	/**
	 * Create the application.
	 */
	public RulesWindow() {
		initialize();
	}

	/**
	 * show rules as labels on the front panel
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
			}
		});
		btnBack.setBounds(332, 516, 117, 29);
		frame.getContentPane().add(btnBack);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(102, 0, 0));
		panel_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel_1.setBounds(74, 67, 639, 408);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(6, 6, 627, 392);
		panel_1.add(panel);
		panel.setBackground(new Color(102, 51, 0));
		panel.setLayout(null);

		JLabel lblRules = new JLabel("Rules");
		lblRules.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		lblRules.setForeground(new Color(255, 204, 153));
		lblRules.setBackground(new Color(255, 255, 255));
		lblRules.setBounds(19, 70, 128, 24);
		panel.add(lblRules);

		JLabel lblMissionFindAll = new JLabel(
				"MISSION: Find all missing pieces of your spacebus so your crew can make it home");
		lblMissionFindAll.setForeground(new Color(255, 204, 153));
		lblMissionFindAll.setBounds(19, 16, 567, 31);
		panel.add(lblMissionFindAll);

		JLabel lblEachCrew = new JLabel("1. Each crew member gets 2 actions per day");
		lblEachCrew.setForeground(new Color(255, 204, 153));
		lblEachCrew.setBounds(54, 106, 567, 31);
		panel.add(lblEachCrew);

		JLabel lblCrew = new JLabel("2. 2 crew members MUST use one action to pilot the ship each day");
		lblCrew.setForeground(new Color(255, 204, 153));
		lblCrew.setBounds(54, 138, 567, 31);
		panel.add(lblCrew);

		JLabel lblClickOn = new JLabel("3. Click on characters to view potential actions");
		lblClickOn.setForeground(new Color(255, 204, 153));
		lblClickOn.setBounds(54, 170, 567, 31);
		panel.add(lblClickOn);

		JLabel lblOnly = new JLabel("4. Only 1 spacebus part can be found on each planet");
		lblOnly.setForeground(new Color(255, 204, 153));
		lblOnly.setBounds(54, 201, 567, 31);
		panel.add(lblOnly);

		JLabel lblWatchHunger = new JLabel("5. Watch hunger and tiredness levels. They affect health levels");
		lblWatchHunger.setForeground(new Color(255, 204, 153));
		lblWatchHunger.setBounds(54, 233, 567, 31);
		panel.add(lblWatchHunger);

		JLabel lblYouCan = new JLabel("6. You can move onto the next day whenever you choose");
		lblYouCan.setForeground(new Color(255, 204, 153));
		lblYouCan.setBounds(54, 264, 567, 31);
		panel.add(lblYouCan);

		JLabel lblRandom = new JLabel("7. 1 random (bad) event will occur each time you hit the next day button");
		lblRandom.setForeground(new Color(255, 204, 153));
		lblRandom.setBounds(54, 297, 567, 31);
		panel.add(lblRandom);

		JLabel lblWhenA = new JLabel("8. When a crew member's health reaches 0, they can no longer perform actions");
		lblWhenA.setForeground(new Color(255, 204, 153));
		lblWhenA.setBounds(54, 328, 567, 31);
		panel.add(lblWhenA);

		// background image

		JLabel lblBackgroundImage = new JLabel("");
		lblBackgroundImage.setIcon(new ImageIcon(DayWindow.class.getResource("/" + "images/universe1.jpg")));
		lblBackgroundImage.setBounds(0, 0, 800, 578);
		frame.getContentPane().add(lblBackgroundImage);

	}

}
