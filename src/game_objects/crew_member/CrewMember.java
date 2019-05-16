package game_objects.crew_member;

import game_objects.FoodItem;
import game_objects.MedicalItem;
import main.GUIGame.DayWindow;
import main.GUIGame.StoryLine;

/**
 * Abstract class for creating a crew member
 */
public abstract class CrewMember {

	// given name
	private String name;

	// chosen type
	private String type;

	// health level
	private int health;

	// max health level
	private int maxHealth;

	// hunger level
	private int hunger;

	// tiredness level
	private int tiredness;

	// number of actions remaining
	private int actionsLeft;

	// gui representation
	private CrewMemberGuiObject gui;

	// var containing whether this crew member has the plague
	private boolean hasPlague;

	// modify these variables to optimize gameplay
	private final int DAILYTIREDNESSGAIN = 15;
	private final int DAILYHUNGERGAIN = 15;
	private final int SLEEPTIREDNESSLOSS = 40;

	/**
	 * Constructor for crew member
	 * 
	 * @param name      Name of this crew member
	 * @param type      Type of this crew member
	 * @param maxHealth Max health of this crew member
	 */
	public CrewMember(String name, String type, int maxHealth) {

		this.name = name;
		this.type = type;

		this.health = maxHealth;
		this.maxHealth = maxHealth;
		this.hunger = 0;
		this.tiredness = 0;
		this.actionsLeft = 2;

		this.hasPlague = false;

		this.gui = new CrewMemberGuiObject(this);
	}

	/**
	 * @return gui representation of this crew member
	 */
	public CrewMemberGuiObject getGuiObject() {
		return this.gui;
	}

	/**
	 * @return integer associating the crew member with a certain ability
	 */
	public abstract int getAbilityIdentifier();

	/**
	 * @return var storing whether this crew member is alive
	 */
	public boolean isAlive() {
		return this.health > 0;
	}

	/**
	 * @return what type of crew member this one is
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * @return name of this crew member
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @return health level of this crew member
	 */
	public int getHealth() {
		return this.health;
	}

	/**
	 * @return hunger level of this crew member
	 */
	public int getHunger() {
		return this.hunger;
	}

	/**
	 * @return tiredness level of this crew member
	 */
	public int getTiredness() {
		return this.tiredness;
	}

	/**
	 * @return how many actions this crew member has left
	 */
	public int getActionsLeft() {
		return this.actionsLeft;
	}

	/**
	 * make crew member lose an action. update DayWindow if out of actions
	 */
	public void performAction() {
		--this.actionsLeft;
		if (this.actionsLeft == 0) {
			DayWindow.haveActions--;
		}
		gui.performAction();
	}

	/**
	 * modify health by specified amount
	 * 
	 * @param amt Specified amount to modify health by
	 */
	public void modifyHealth(int amt) {
		this.health += amt;

		// if health > max, health -> max. if health < 1, health -> 0.
		this.health = this.health > this.maxHealth ? this.maxHealth : this.health < 1 ? 0 : this.health;

		// render change
		this.gui.adjustHealth(this.health);
	}

	/**
	 * modify hunger by specified amount
	 * 
	 * @param amt Specified amount to modify hunger by
	 */
	public void modifyHunger(int amt) {
		this.hunger += amt;

		// if hunger > 100, hunger -> 100. if hunger < 1, hunger -> 0.
		this.hunger = this.hunger > 100 ? 100 : this.hunger < 1 ? 0 : this.hunger;

		// render change
		this.gui.adjustHunger(this.hunger);
	}

	/**
	 * decrease hunger according to value of Food item and update StoryLine label
	 * 
	 * @param f Food item being eaten
	 */
	public void eatFood(FoodItem f) {
		StoryLine.updateLabel(this.name + " filled up his tummy with some " + f.getName());
		this.modifyHunger(-f.getNutrition());
	}

	/**
	 * Heals crew member according to healthRestore attribute of the medical item
	 * Crew member health cannot be greater than max
	 * 
	 * @param m            Medical item used to heal
	 * 
	 * @param crewHasMedic If a medic is in the crew, 20% more health is restored
	 * 
	 * @return int The amount of health restored
	 */
	public int heal(MedicalItem m, boolean crewHasMedic) {

		// amount of health to be restored
		int hr = m.getHealthRestore();

		// restore 20% more if medic is in crew
		hr += crewHasMedic ? (int) (hr * .2) : 0;

		// cap crew member's new health at max
		hr = this.health + hr > this.maxHealth ? this.maxHealth - this.health : hr;

		// modify crew member's health
		this.modifyHealth(hr);

		// cure space plague if crew member has it and the medical item can cure it
		if (m.curesSpacePlague() && this.hasPlague) {
			gui.curePlague();
			this.hasPlague = false;
		}

		if (hr > m.getHealthRestore()) { // extra points restored
			StoryLine.updateLabel(this.name + " restored " + (hr - m.getHealthRestore()) + " extra point(s) with the "
					+ m.getName() + " thanks to a medic in the crew");
		} else
			StoryLine.updateLabel(this.name + " feels healthier after using the " + m.getName());

		return hr;
	}

	/**
	 * reduce tiredness
	 */
	public void sleep() {
		this.tiredness -= SLEEPTIREDNESSLOSS;
		this.tiredness = this.tiredness < 0 ? 0 : this.tiredness;
		gui.adjustTiredness(this.tiredness);
	}

	/**
	 * give plague to this crew member
	 */
	public void plagued() {
		this.hasPlague = true;
		gui.plagued();
		this.modifyHealth(-10);
	}

	/**
	 * remove plague from this crew member
	 */
	public void curePlague() {
		gui.curePlague();
		this.hasPlague = false;
	}

	/**
	 * determine if this crew member has the plague
	 * 
	 * @return boolean storing whether this crew member has the plague
	 */
	public boolean hasPlague() {
		return this.hasPlague;
	}

	/**
	 * Called at the start of each new day to modify attributes of crew member.
	 * Attributes are modified based on values given
	 * 
	 * @param healthMod    Affects how much health is modified
	 * 
	 * @param tirednessMod Affects how much tiredness is modified
	 * 
	 * @param hungerMod    Affects how much hunger is modified
	 * 
	 * @return boolean Returns true if crew member is still alive, otherwise false
	 */
	public boolean newDay(double healthMod, double tirednessMod, double hungerMod) {

		this.tiredness += DAILYTIREDNESSGAIN * tirednessMod;
		this.tiredness = this.tiredness > 100 ? 100 : this.tiredness;

		this.hunger += DAILYHUNGERGAIN * hungerMod;
		this.hunger = this.hunger > 100 ? 100 : this.hunger;

		// -1 health point for every 5 tiredness points
		// -1 health point for every 5 hunger points
		this.health -= (this.tiredness / 5 + this.hunger / 5) * healthMod;

		// loses default 10 health if has plague
		this.health -= this.hasPlague ? 10 * healthMod : 0;

		// If health dropped to 0 or below, crew member is now dead
		if (this.health < 1) {
			this.health = 0;

			// show 0 health in gui
			this.getGuiObject().adjustHealth(0);
			return false;
		}

		// show changes in GUI
		this.getGuiObject().adjustHealth(this.health);
		this.getGuiObject().adjustHunger(this.hunger);
		this.getGuiObject().adjustTiredness(this.tiredness);

		this.actionsLeft = 2;

		return true;
	}

	/*
	 * Check if two crew members are the same. Return true if names are equal.
	 * Different crew members are not allowed to have the same name.
	 */
	@Override
	public boolean equals(Object other) {
		return other instanceof CrewMember && this.name == ((CrewMember) other).name;
	}

	/**
	 * call newDay() with specified modifier values
	 * 
	 * @return boolean storying whether crew member survived
	 */
	public abstract boolean callNewDay();

	/**
	 * describe powers of this crew member type
	 * 
	 * @return String describing powers
	 */
	public abstract String showSuperPower();

}
