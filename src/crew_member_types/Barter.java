package crew_member_types;

import game_objects.crew_member.CrewMember;

/**
 * This class is for creation of the Barter CrewMember type
 */
public class Barter extends CrewMember {

	// int associated with Barter's ability
	public static final int abilityIdentifier = 1;

	/**
	 * create Barter type
	 * 
	 * @param name String name of Barter
	 */
	public Barter(String name) {
		super(name, "Barter", 100);
	}

	@Override
	public boolean callNewDay() {
		return super.newDay(1, 1, 1);
	}

	@Override
	public String showSuperPower() {
		return "   ABILITY :: 20% off all items in the shop";
	}

	@Override
	public int getAbilityIdentifier() {
		return abilityIdentifier;
	}

}
