package main.GUIGame;

import java.awt.Color;
import java.util.concurrent.ExecutionException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingWorker;
import javax.swing.border.BevelBorder;

/**
 * This class creates the dialog panel that states what is happening in the game
 */
public class StoryLine {

	// inner container
	private static JPanel container;

	// outer border panel
	private static JPanel border;

	// first line of text
	private static JLabel line1;

	// second line of text
	private static JLabel line2;

	/**
	 * create the panel and set first dialog text
	 * 
	 * @param frame Currently visible frame
	 */
	public static void initialize(JFrame frame) {

		border = new JPanel();
		border.setBackground(new Color(255, 102, 153));
		border.setBorder(
				new BevelBorder(BevelBorder.LOWERED, new Color(51, 51, 102), new Color(0, 0, 102), null, null));
		border.setBounds(153, frame.getHeight() - 120, 380, 70);
		border.setLayout(null);

		container = new JPanel();
		container.setBackground(new Color(0, 0, 0));
		container.setBounds(6, 6, 368, 58);
		container.setLayout(null);
		border.add(container);

		line1 = new JLabel("It's a new day. Make the most of it.");
		line1.setForeground(new Color(255, 255, 255));
		line1.setBounds(170, frame.getHeight() - 105, 350, 15);
		line2 = new JLabel("");
		line2.setForeground(new Color(255, 255, 255));
		line2.setBounds(170, frame.getHeight() - 82, 350, 15);

		frame.getContentPane().add(line1);
		frame.getContentPane().add(line2);
		frame.getContentPane().add(border);
	}

	/**
	 * When something happens in the game, call this method to put it into words
	 * 
	 * @param text Message about what happened
	 */
	public static void updateLabel(String text) {
		SwingWorker<String[], Void> bgThread = new SwingWorker<>() {

			/**
			 * take var text and see if it needs to be split into two lines.
			 * 
			 * @return String array of size 2. index 0 = first line, index 1 = second line
			 */
			@Override
			protected String[] doInBackground() { // run background thread
				String[] ret = new String[2];
				if (text.length() > 53) { // split into two strings
					int index = 53;
					while (text.charAt(--index) != ' ') // find beginning of word
						;
					ret[0] = text.substring(0, index + 1); // string 1
					ret[1] = text.substring(index + 1); // string 2
				} else { // text is fine as it is
					ret[0] = text;
					ret[1] = "";
				}
				return ret;
			}

			/**
			 * set text according to string array
			 */
			@Override
			protected void done() {
				String[] ret;
				try {
					ret = get();
					line1.setText(ret[0]);
					line2.setText(ret[1]);
				} catch (InterruptedException | ExecutionException e) {
					e.printStackTrace();
				}

			}

		};
		bgThread.execute();
	}

}
