package main.GUIGame.space_outpost;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import game_objects.actions.ActionSet;
import game_objects.inventory.Inventory;
import main.GUIGame.StoryLine;

/**
 * This class creates the space outpost
 */
public class SpaceOutpostMenu {

	// image of city wok outpost
	private JLabel outpost;

	// display of discount if one exists
	private JLabel discount;

	// back opaque panel covering up whole screen
	private JPanel menuPnlBack;

	// image of menu
	private JLabel menu;

	// clickable label that closes space outpost menu
	private JLabel menuClose;

	// list of all items on the menu
	private ArrayList<MenuItem> menuItems;

	// player's inventory
	private Inventory inventory;

	// var storing whether this menu is open, so non-associated buttons are disabled
	private boolean isOpen;

	// var storing whether a barter type exists in the crew
	private boolean hasBarter;

	/**
	 * Create the Space outpost menu
	 * 
	 * @param invntry       player's inventory
	 * @param nextDayButton Next day button to be disabled when menu open
	 * @param hasBarter     var storing whether crew has a barter type in it
	 */
	public SpaceOutpostMenu(Inventory invntry, JButton nextDayButton, boolean hasBarter) {

		this.hasBarter = hasBarter;

		// create menu items
		MenuForSale mfs = new MenuForSale(hasBarter);
		menuItems = mfs.getMenu();
		isOpen = false; // starts out closed

		inventory = invntry;
		discount = new JLabel("20% off!");
		discount.setBounds(630, 160, 70, 20);
		discount.setForeground(new Color(249, 255, 0));

		outpost = new JLabel("");
		outpost.setIcon(new ImageIcon(SpaceOutpostMenu.class.getResource("/images_sp/outpost_195x82.png")));
		outpost.setBounds(530, 165, 201, 88);
		outpost.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) { // change image to show it is clickable
				if (!isOpen)
					outpost.setIcon(new ImageIcon(
							SpaceOutpostMenu.class.getResource("/images_sp/outpost_selected_201x88.png")));
			}

			@Override
			public void mouseExited(MouseEvent e) { // change image back to normal
				outpost.setIcon(new ImageIcon(SpaceOutpostMenu.class.getResource("/images_sp/outpost_195x82.png")));
			}

			@Override
			public void mouseClicked(MouseEvent e) { // open up menu
				viewMenu();
				nextDayButton.setEnabled(false);
			}
		});

		menu = new JLabel("");
		menu.setIcon(new ImageIcon(SpaceOutpostMenu.class.getResource("/images_sp/menu1_548x653.png")));
		menu.setBounds(328, 121, 545, 653);

		menuPnlBack = new JPanel();
		menuPnlBack.setBackground(new Color(40, 0, 0, 220));
		menuPnlBack.setBounds(0, 0, 1200, 800);

		menuClose = new JLabel("Close Menu");
		menuClose.setBounds(780, 730, 80, 15);
		menuClose.setForeground(new Color(200, 100, 100));
		menuClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) { // change color to show it is clickable
				menuClose.setForeground(new Color(55, 5, 5));
			}

			@Override
			public void mouseExited(MouseEvent e) { // change color back to normal
				menuClose.setForeground(new Color(200, 100, 100));

			}

			@Override
			public void mouseClicked(MouseEvent e) { // close menu
				closeMenu();
				nextDayButton.setEnabled(true);
			}

		});

		closeMenu();

	}

	/**
	 * add all components to the frame
	 */
	public void render(JFrame frame) {
		for (MenuItem mi : menuItems)
			mi.render(frame, inventory);
		frame.getContentPane().add(discount);
		frame.getContentPane().add(menuClose);
		frame.getContentPane().add(menu);
		frame.getContentPane().add(menuPnlBack);
		frame.getContentPane().add(outpost);
//		frame.getContentPane().add(label);
	}

	/**
	 * hide all components associated with the menu
	 */
	private void closeMenu() {
		ActionSet.getInstance().closePanel();
		isOpen = false;
		for (MenuItem mi : menuItems)
			mi.hide();
		menuClose.setVisible(false);
		menuPnlBack.setVisible(false);
		menu.setVisible(false);
		discount.setVisible(false);
	}

	/**
	 * show all components associated with the menu
	 */
	private void viewMenu() {
		discount.setVisible(hasBarter);
		StoryLine.updateLabel("Visiting City Wok Space Outpost");
		isOpen = true;
		ActionSet.getInstance().openPanel();
		for (MenuItem mi : menuItems)
			mi.show();
		menuClose.setVisible(true);
		menuPnlBack.setVisible(true);
		menu.setVisible(true);
	}

}
