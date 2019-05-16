package game_objects;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This class creates a status bar to keep track of some important value
 */
public class StatusBar {

	// light colored bar behind front bar of consistent width of 100px
	private JPanel backBar;

	// dark colored bar with width according to current value (max 100px)
	private JPanel frontBar;

	// label containing value of front bar
	private JLabel LblBarValue;

	// value of front bar
	private int barValue;

	// width of front bar (max of 100px)
	private int frontBarWidth;

	// back bar color
	private Color backColor;

	// front bar color
	private Color frontColor;

	// label describing what this status bar is for
	private JLabel lblDescribe;

	// maximum value for this status bar
	private int max;

	// coordinates
	private int x;
	private int y;

	/**
	 * Create the status bar
	 * 
	 * @param back        Color for back bar
	 * @param front       Color for front bar
	 * @param barValueNum int starting value
	 * @param description String description of status bar
	 */
	public StatusBar(Color back, Color front, int barValueNum, String description) {
		backColor = back;
		frontColor = front;
		lblDescribe = new JLabel(description);
		max = barValueNum > 0 ? barValueNum : 100;
		frontBarWidth = barValueNum > 100 ? 100 : barValueNum;
		barValue = barValueNum;
		LblBarValue = new JLabel("" + barValueNum);
		LblBarValue.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		backBar = new JPanel();
		frontBar = new JPanel();
	}

	/**
	 * adjust value for this status bar, and adjust front bar width accordingly
	 * 
	 * @param newValue int new value for this status bar
	 */
	public void adjustValue(int newValue) { // newvalue / max = w / 100 ... newvalue * 100 = w * max

		// set width proportionally

		frontBarWidth = barValue > 0 ? (int) Math.floor((double) newValue * 100 / max) : newValue;
		frontBar.setBounds(x, y, frontBarWidth, 12);

		// adjust status value label

		barValue = newValue;
		LblBarValue.setText("" + newValue);
		LblBarValue.setBounds(x + 81 - (newValue > 99 ? 7 : 0), y, 25, 12);
	}

	public void render(JFrame frame, int xcoord, int ycoord) {

		// set coords

		x = xcoord;
		y = ycoord;

		// Status value label

		LblBarValue.setBounds(x + 81 - (barValue > 99 ? 7 : 0), y, 25, 12); /* 3 digit number -> 7 pixels to left */
		frame.getContentPane().add(LblBarValue);

		// Front Bar

		frontBar.setBounds(x, y, frontBarWidth, 12);
		frontBar.setBackground(frontColor);
		frame.getContentPane().add(frontBar);

		// Back Bar

		backBar.setBounds(x, y, 100, 12);
		backBar.setBackground(backColor);
		backBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) { // show status bar description
				lblDescribe.setVisible(true);
			}

			@Override
			public void mouseExited(MouseEvent e) { // hide status bar description
				lblDescribe.setVisible(false);
			}
		});
		frame.getContentPane().add(backBar);

		// status bar description

		lblDescribe.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		lblDescribe.setForeground(new Color(255, 255, 255));
		lblDescribe.setBounds(x + 205 > frame.getWidth() ? x - 80 : x + 102, y - 2, 80, 17);
		lblDescribe.setVisible(false);
		frame.getContentPane().add(lblDescribe);

	}
}
