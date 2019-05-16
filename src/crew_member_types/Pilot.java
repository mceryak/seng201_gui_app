package crew_member_types;

import game_objects.crew_member.CrewMember;

/**
 * This class is for creation of Pilot CrewMember type
 */
public class Pilot extends CrewMember {

	// int associated with Barter's ability
	public static final int abilityIdentifier = 5;

	/**
	 * create new Pilot
	 * 
	 * @param name String name of this pilot
	 */
	public Pilot(String name) {
		super(name, "Pilot", 100);
	}

	@Override
	public boolean callNewDay() {
		return super.newDay(1, .8, 1);
	}

	@Override
	public String showSuperPower() {
		return "   ABILITY :: Provides 20% chance of no random event occurring. Tiredness increases at a 20% lesser rate";
	}

	@Override
	public int getAbilityIdentifier() {
		return abilityIdentifier;
	}

}
