package crew_member_types;

import game_objects.crew_member.CrewMember;

/**
 * This class is for creation of Medic CrewMember type
 */
public class Medic extends CrewMember {

	// int associated with Medic's ability
	public static final int abilityIdentifier = 3;

	/**
	 * create new Medic
	 * 
	 * @param name String name of this medic
	 */
	public Medic(String name) {
		super(name, "Medic", 100);
	}

	@Override
	public boolean callNewDay() {
		return super.newDay(1, 1, 1);
	}

	@Override
	public String showSuperPower() {
		return "   ABILITY :: Healing items restore 20% more health for entire crew";
	}

	@Override
	public int getAbilityIdentifier() {
		return abilityIdentifier;
	}
}
