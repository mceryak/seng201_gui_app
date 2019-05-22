package game_objects.inventory;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

import game_objects.Item;

/**
 * This class is for the animation of an object being added to the inventory
 */
public class ItemAdded {

	// amount added or subtracted
	private JLabel amt;

	// image of item
	private JLabel icon;

	// location when not visible
	private int offscreenx;

	// location when visible
	private int onscreenx;

	// amount of time label will be visible on screen for
	private int timeOnScreen;

	/**
	 * Constructor for ItemAdded
	 * 
	 * @param thisFrame Frame to render on
	 */
	public ItemAdded() {

		offscreenx = 1200;
		onscreenx = 1100;

		amt = new JLabel("");
		amt.setBounds(offscreenx, 110, 110, 60); // start off screen
		amt.setFont(new Font("Krungthep", Font.PLAIN, 15));

		icon = new JLabel("");
		icon.setBounds(offscreenx + 35, 110, 54, 52);

	}

	public void render(JFrame frame) {
		frame.getContentPane().add(amt);
		frame.getContentPane().add(icon);
	}

	/**
	 * change image icon to match the item being added to inventory
	 * 
	 * @param item   Item to be added
	 * @param amount Quantity of item
	 */
	public void setNewItem(Item item, int amount) {

		icon.setIcon(item.getIcon());
		amt.setText((amount > 0 ? "+" : "") + amount);

		int delay = 100; // milliseconds
		timeOnScreen = 0;
		ActionListener taskPerformer = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {

				if (timeOnScreen < 9) { // moving onto screen
					int newX = amt.getX() - 10; // moving 10 px at a time
					if (newX > onscreenx) { // move until reaches onscreenx coordinate
						amt.setLocation(newX, 110);
						icon.setLocation(newX + 35, 110);
					} else {
						timeOnScreen++;
					}
				} else { // moving off screen
					int newX = amt.getX() + 10;
					if (newX <= offscreenx) {
						amt.setLocation(newX, 110);
						icon.setLocation(newX + 35, 110);
					} else { // off screen, stop timer
						((Timer) evt.getSource()).stop();
					}
				}
			}
		};
		new Timer(delay, taskPerformer).start();
	}
}
