package main.GUIPregame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import main.GUIManager;

public class StartWindow {

	// visible frame
	private JFrame frame;

	// gui controller
	private GUIManager man;

	// determines next window to open, 0 to start game, 1 for rules
	private int nextWindow = 0;

	/**
	 * Constructor for StartWindow
	 */
	public StartWindow(GUIManager manager) {
		man = manager;
		initialize();

		try {
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * dispose frame
	 * 
	 * @return 0 to start game, 1 to view rules
	 */
	public int closeWindow() {
		frame.dispose();
		return nextWindow;
	}

	/**
	 * close this window and load the next
	 */
	public void finishedWindow() {
		man.closeStartWindow(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		// frame

		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 900, 900);
		frame.getContentPane().setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// south park logo

		JLabel logo = new JLabel("SOUTH PARK");
		logo.setFont(new Font("Krungthep", Font.PLAIN, 40));
		logo.setForeground(new Color(255, 255, 255));
//		logo.setIcon(new ImageIcon(StartWindow.class.getResource("/images_sp/logo_400x476px.png")));
		logo.setBounds(frame.getWidth() / 2 - 115, frame.getHeight() / 5, 400, 476);
		frame.getContentPane().add(logo);

		// start game button

		JButton startGameButton = new JButton("START GAME");
		startGameButton.setBounds(frame.getWidth() / 2 - 58, frame.getHeight() - 140, 117, 29);
		startGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
			}
		});

		// view rules button

		JButton rulesButton = new JButton("RULES");
		rulesButton.setBounds(392, 700, 117, 29);
		rulesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nextWindow = 1;
				finishedWindow();
			}
		});
		frame.getContentPane().add(rulesButton);

		// game title

		JLabel lblSpaceExplorer = new JLabel("Space Explorer");
		lblSpaceExplorer.setForeground(new Color(255, 153, 0));
		lblSpaceExplorer.setBounds(frame.getWidth() / 2 - 280, frame.getHeight() / 36, 560, 60);
		lblSpaceExplorer.setFont(new Font("Krungthep", Font.BOLD, 69));
		frame.getContentPane().add(lblSpaceExplorer);
		frame.getContentPane().add(startGameButton);

		// space background image

		JLabel spaceBackground = new JLabel("");
		spaceBackground.setBounds(0, 0, frame.getWidth(), frame.getHeight());
		spaceBackground.setBackground(new Color(0, 255, 0));
		spaceBackground.setIcon(new ImageIcon(StartWindow.class.getResource("/images/universe1.jpg")));
		frame.getContentPane().add(spaceBackground);
	}
}
