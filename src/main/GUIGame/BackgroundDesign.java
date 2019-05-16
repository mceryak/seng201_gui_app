package main.GUIGame;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * background design for daywindow
 */
public class BackgroundDesign {

	/**
	 * render the design
	 * 
	 * @param frame   visible frame
	 * @param curDay  current day
	 * @param lastDay last day
	 */
	public static void render(JFrame frame, int curDay, int lastDay) {

		// rubble

		JLabel rubble = new JLabel("");
		rubble.setIcon(new ImageIcon(DayWindow.class.getResource("/images/rubble_100x86.png")));
		rubble.setBounds(frame.getWidth() / 2 + 70, frame.getHeight() / 2 + 100, 105, 90);
		frame.getContentPane().add(rubble);

		// bed

		JLabel bed = new JLabel("");
		bed.setIcon(new ImageIcon(DayWindow.class.getResource("/images_sp/bed_90x74.png")));
		bed.setBounds(frame.getWidth() / 2 - 70, frame.getHeight() / 2 + 100, 90, 74);
		frame.getContentPane().add(bed);

		// planet image

		JLabel lblPlanetImage = new JLabel("");
		lblPlanetImage.setIcon(new ImageIcon(DayWindow.class.getResource("/images/planet1.png")));
		lblPlanetImage.setBounds(frame.getWidth() / 2 - 200, frame.getHeight() / 2 - 250, 500, 500);
		frame.getContentPane().add(lblPlanetImage);

		// Day Label

		JLabel lblDay = new JLabel("Day " + curDay + " / " + lastDay);
		lblDay.setFont(new Font("Krungthep", Font.PLAIN, 21));
		lblDay.setForeground(new Color(255, 255, 255));
		lblDay.setBounds(frame.getWidth() / 2 - 53, 6, 106, 28);
		frame.getContentPane().add(lblDay);

		// background image

		JLabel lblBackgroundImage = new JLabel("");
		lblBackgroundImage.setIcon(new ImageIcon(DayWindow.class.getResource("/images/universe1.jpg")));
		lblBackgroundImage.setBounds(0, 0, frame.getWidth(), frame.getHeight());
		frame.getContentPane().add(lblBackgroundImage);
	}

}
