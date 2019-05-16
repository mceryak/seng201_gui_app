package crew_member_types;

import game_objects.crew_member.CrewMember;

/**
 * This class is for creation of Juggernaut CrewMember type
 */
public class Juggernaut extends CrewMember {

	// int associated with Juggernaut's ability
	public static final int abilityIdentifier = 0;

	/**
	 * create Juggernaut type
	 * 
	 * @param name String name of this juggernaut
	 */
	public Juggernaut(String name) {
		super(name, "Juggernaut", 120);
	}

	@Override
	public boolean callNewDay() {
		return super.newDay(.8, 1, 1);
	}

	@Override
	public String showSuperPower() {
		return "   ABILITY :: 10% greater max health and loses 20% less health in all situations";
	}

	/**
	 * In original method, crew member loses 10 health. Juggernaut loses 20% less.
	 */
	@Override
	public void plagued() {
		super.plagued();
		super.modifyHealth(2);
	}

	@Override
	public int getAbilityIdentifier() {
		return abilityIdentifier;
	}

}
