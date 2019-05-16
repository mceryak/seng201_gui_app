package crew_member_types;

import game_objects.crew_member.CrewMember;

/**
 * This class is for creation of Chef CrewMember type
 */
public class Chef extends CrewMember {

	// int associated with Chef's ability
	public static final int abilityIdentifier = 4;

	/**
	 * create Chef type
	 * 
	 * @param name String name of this chef
	 */
	public Chef(String name) {
		super(name, "Chef", 100);
	}

	@Override
	public boolean callNewDay() {
		return super.newDay(1, 1, 1.2);
	}

	@Override
	public String showSuperPower() {
		return "   ABILITY :: 1 random food item spawns in your inventory every other day";
	}

	@Override
	public int getAbilityIdentifier() {
		return abilityIdentifier;
	}

}
