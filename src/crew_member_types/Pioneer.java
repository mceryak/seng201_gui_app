package crew_member_types;

import game_objects.crew_member.CrewMember;

/**
 * This class is for creation of Pioneer CrewMember type
 */
public class Pioneer extends CrewMember {

	// int associated with Barter's ability
	public static final int abilityIdentifier = 2;

	/**
	 * create new Pioneer
	 * 
	 * @param name String name of this pioneer
	 */
	public Pioneer(String name) {
		super(name, "Pioneer", 100);
	}

	@Override
	public boolean callNewDay() {
		return super.newDay(1, 1, 1.2);
	}

	@Override
	public String showSuperPower() {
		return "   ABILITY :: Better odds of finding a spaceship part. Hunger increases at a 20% faster rate, decreases \n              when finding an item and resets when finding a spaceship part";
	}

	@Override
	public int getAbilityIdentifier() {
		return abilityIdentifier;
	}

}
