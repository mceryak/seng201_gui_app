package game_objects.spacebus;

import java.util.HashSet;

import game_objects.actions.Action;
import game_objects.actions.ActionSet;
import game_objects.crew_member.CrewMember;
import main.GUIGame.StoryLine;

/**
 * Object representing crew's space-bus
 */
public class SpaceBus {

	// var representing health of the space-bus. when 0, game is over
	private int shieldHealth;

	// amount of missing pieces that needs to be found by the player
	private int missingPieces;

	// name of the space-bus
	private String name;

	// GUI representation of the space-bus
	private SpaceBusGui gui;

	// provides clickable label for crew member to perform the action of repairing
	// the space-bus
	private Action repairShip;

	/**
	 * Consructor for SpaceBus
	 * 
	 * @param name         Name given to space-bus when player sets up crew
	 * @param piecesToFind Number of pieces player needs to find to win
	 */
	public SpaceBus(String name, int piecesToFind) {

		this.gui = new SpaceBusGui(piecesToFind, name);
		this.name = name;
		this.missingPieces = piecesToFind;
		this.shieldHealth = 100;

		repairShip = new Action("Repair Space-bus") {

			@Override
			public void performAction(CrewMember cm) {

				// deselect the current crew member after performing an action
				super.performAction(cm);

				// update story line panel
				StoryLine.updateLabel(cm.getName() + " repaired the shields");

				// boost shield health
				repairShield();
			}

			@Override
			public boolean shouldShowAction(HashSet<Integer> blackList) {

				// show if shields need repaired and crew member does not have to be a pilot
				return shieldHealth < 100 && !blackList.contains(4);
			}

		};

		// set location just below space-bus' shield health status bar
		repairShip.setBounds(1050, 647, 120, 15);

		// add to all actions
		ActionSet.getInstance().addAction(repairShip);
	}

	/**
	 * @return GUI object for space-bus
	 */
	public SpaceBusGui getGui() {
		return this.gui;
	}

	/**
	 * @return number of pieces to player needs to find
	 */
	public int getMissingPieces() {
		return this.missingPieces;
	}

	/**
	 * 1 less piece to find, update GUI object
	 */
	public void findPiece() {
		gui.findPiece();
		--this.missingPieces;
	}

	/**
	 * shield health of space-bus is lowered, min = 0
	 * 
	 * @param damage Amount shield health is lowered by
	 * @return The amount of damage the space-bus actually took
	 */
	public int takeDamage(int damage) {
		damage = this.shieldHealth - damage < 1 ? this.shieldHealth : damage;
		this.shieldHealth -= damage;
		gui.adjustShieldHealth(this.shieldHealth);
		return damage;
	}

	/**
	 * boost shield health, caps at 100
	 */
	public void repairShield() {
//		this.shieldHealth += isMechanic ? 50 : 30;
		this.shieldHealth += 30;
		this.shieldHealth = this.shieldHealth > 100 ? 100 : this.shieldHealth;
		gui.adjustShieldHealth(this.shieldHealth);
	}

	/**
	 * @return Current shield health of the space-bus
	 */
	public int getShieldHealth() {
		return this.shieldHealth;
	}

	/**
	 * @return Name of space-bus given by player
	 */
	public String getName() {
		return this.name;
	}
}
