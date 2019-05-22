package game_objects.crew_member;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import game_objects.Images;
import game_objects.StatusBar;

/**
 * This class creates the GUI representation of a crew member
 */
public class CrewMemberGuiObject {

	// icon of crew member type
	private ImageIcon icon;

	// crew member associated with this object
	private CrewMember cm;

	// image of description of this crew member's ability
	private ImageIcon abilityIcon;

	// name
	private JLabel lblName;

	// label containing abilityIcon
	private JLabel lblAbility;

	// panel behind name
	private JPanel panelName = new JPanel();

	// label containing character box image
	private JLabel lblCharacterBox = new JLabel("");

	// label visible when plagued
	private JLabel lblPlagued;

	// label visible when dead
	private JLabel lblDead;

	// panel behind plague label and dead label
	private JPanel panelPlaguedOrDead;

	// character associated with this crew member
	private Character character;

	// status bars
	private StatusBar healthBar;
	private StatusBar hungerBar;
	private StatusBar tirednessBar;

	/**
	 * Constructor for CrewMemberGuiObject
	 * 
	 * @param cm Crew member associated with this gui object
	 */
	public CrewMemberGuiObject(CrewMember cm) {
		lblAbility = new JLabel("");
		lblPlagued = new JLabel("PLAGUED");
		lblDead = new JLabel("DEAD");
		panelPlaguedOrDead = new JPanel();
		this.cm = cm;
		this.tirednessBar = new StatusBar(new Color(223, 226, 254), new Color(44, 0, 255), 0, "Tiredness"); // blue
		this.healthBar = new StatusBar(new Color(255, 179, 168), new Color(232, 39, 48), cm.getHealth(), "Health"); // red
		this.hungerBar = new StatusBar(new Color(219, 251, 219), new Color(36, 148, 21), 0, "Hunger"); // green

		lblName = new JLabel(cm.getName());

		/* Get the right icon */
		switch (cm.getType()) {
		case "Juggernaut":
			this.icon = Images.juggernautSquare;
			this.character = new Character(cm, Images.juggernautCharacter);
			this.abilityIcon = new ImageIcon(CrewMemberGuiObject.class.getResource("/images_sp/ability_jug_95x96.png"));
			break;
		case "Pioneer":
			this.icon = Images.pioneerSquare;
			this.character = new Character(cm, Images.pioneerCharacter);
			this.abilityIcon = new ImageIcon(
					CrewMemberGuiObject.class.getResource("/images_sp/ability_pioneer_95x96.png"));
			break;
		case "Medic":
			this.icon = Images.medicSquare;
			this.character = new Character(cm, Images.medicCharacter);
			this.abilityIcon = new ImageIcon(
					CrewMemberGuiObject.class.getResource("/images_sp/ability_medic_95x95.png"));
			break;
		case "Pilot":
			this.icon = Images.pilotSquare;
			this.character = new Character(cm, Images.pilotCharacter);
			this.abilityIcon = new ImageIcon(
					CrewMemberGuiObject.class.getResource("/images_sp/ability_pilot_95x96.png"));
			break;
		case "Barter":
			this.icon = Images.barterSquare;
			this.character = new Character(cm, Images.barterCharacter);
			this.abilityIcon = new ImageIcon(
					CrewMemberGuiObject.class.getResource("/images_sp/ability_barter_95x95.png"));
			break;
		case "Chef":
			this.icon = Images.chefSquare;
			this.character = new Character(cm, Images.chefCharacter);
			this.abilityIcon = new ImageIcon(
					CrewMemberGuiObject.class.getResource("/images_sp/ability_chef_95x96.png"));
			break;
		}
	}

	/**
	 * show that crew member has lost an action
	 */
	public void performAction() {
		character.performAction();
	}

	/**
	 * show that crew member has been plagued
	 */
	public void plagued() {
		panelPlaguedOrDead.setVisible(true);
		lblPlagued.setVisible(true);
	}

	/**
	 * show that crew member no longer has plague
	 */
	public void curePlague() {
		panelPlaguedOrDead.setVisible(false);
		lblPlagued.setVisible(false);
	}

	/**
	 * show adjustment of health to new value
	 * 
	 * @param newHealth New health level
	 */
	public void adjustHealth(int newHealth) {
		if (newHealth == 0) { // dead
			lblPlagued.setVisible(false);
			panelPlaguedOrDead.setVisible(true);
			lblDead.setVisible(true);
		}
		this.healthBar.adjustValue(newHealth);
	}

	/**
	 * show adjustment of hunger to new value
	 * 
	 * @param newHunger New hunger level
	 */
	public void adjustHunger(int newHunger) {
		this.hungerBar.adjustValue(newHunger);
	}

	/**
	 * show adjustment of tiredness to new value
	 * 
	 * @param newTiredness New tiredness level
	 */
	public void adjustTiredness(int newTiredness) {
		this.tirednessBar.adjustValue(newTiredness);
	}

	/**
	 * @return box image of crew member type
	 */
	public ImageIcon getIcon() {
		return this.icon;
	}

	/**
	 * @return Character object associated with this crew member
	 */
	public Character getCharacter() {
		return this.character;
	}

	/**
	 * render everything to do with this crew member (icon, health, hunger ...)
	 * 
	 * @param frame JFrame we're rendering to
	 * @param x     x coordinate to render in relation to
	 * @param y     Y coordinate to render in relation to
	 */
	public void render(JFrame frame, int x, int y) {

		// Name label

		lblName.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		lblName.setForeground(new Color(255, 255, 255));
		lblName.setBounds(x + 2, y - 10, 98, 17);
		frame.getContentPane().add(lblName);

		panelName.setBounds(x, y - 10, 98, 17);
		panelName.setBackground(new Color(40, 40, 40, 200));
		frame.getContentPane().add(panelName);

		// plagued/death label

		lblDead.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		lblDead.setBounds(x + 25, y + 40, 60, 20);
		lblDead.setForeground(new Color(251, 16, 4));
		lblDead.setVisible(cm.getHealth() == 0);
		frame.getContentPane().add(lblDead);

		lblPlagued.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		lblPlagued.setForeground(new Color(0, 237, 130));
		lblPlagued.setBounds(x + 5, y + 40, 90, 20);
		lblPlagued.setVisible(cm.hasPlague() && cm.getHealth() > 0);
		frame.getContentPane().add(lblPlagued);

		panelPlaguedOrDead.setBounds(x + 1, y + 38, 95, 22);
		panelPlaguedOrDead.setBackground(new Color(40, 40, 40, 200));
		panelPlaguedOrDead.setVisible(cm.hasPlague() || cm.getHealth() == 0);
		frame.getContentPane().add(panelPlaguedOrDead);

		// Icon

		lblCharacterBox.setIcon(cm.getGuiObject().getIcon());
		lblCharacterBox.setBounds(x - 1, y, 100, 110);
		lblCharacterBox.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblAbility.setVisible(true);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblAbility.setVisible(false);
			}
		});
		frame.getContentPane().add(lblCharacterBox);

		// ability label

		lblAbility.setIcon(abilityIcon);
		lblAbility.setVisible(false);
		lblAbility.setBounds(x + 100, y + 5, 95, 96);
		frame.getContentPane().add(lblAbility);

		// Health Bar

		healthBar.render(frame, x - 1, y + 108);

		// Hunger Bar

		hungerBar.render(frame, x - 1, y + 124);

		// Tiredness Bar

		tirednessBar.render(frame, x - 1, y + 140);

	}
}
