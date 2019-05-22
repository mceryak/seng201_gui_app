package main.GUIGame;

import java.util.ArrayList;

import game_objects.Crew;
import game_objects.actions.SearchPlanet;
import game_objects.crew_member.CrewMember;

/*
 * This class contains the method that performs necessary operations at the start of each new day.
 * This includes modifying crew members' attributes and setting up a random event.
 */
public class NewDayEvents {

	/**
	 * Modify all crew members' attributes. Create a random event. Check if any crew
	 * member has died. If any, report and remove from crew.
	 * 
	 * @param crew Player's crew
	 * @return boolean true if game over, false if game on
	 */
	public static boolean startDay(Crew crew) {

		if (crew.numAlive() > 1) { // Crew has enough members to fly to new planet.

			// New day, new planet. Haven't found the piece hidden here yet.
			SearchPlanet.foundSpaceBusPart = false;

		} else { // only 1 crew member. can't fly to new planet. game over.

			return true;

		}

		// list of dead crew members to be removed from the crew
		ArrayList<CrewMember> fallen = new ArrayList<>();

		// daily modification of health, tiredness, and hunger
		for (CrewMember cm : crew.getCrewMembers().keySet()) {
			if (!cm.callNewDay()) { // crew member has died
				fallen.add(cm);
			}
		}

		// kill all Crew Members in fallen by removing them from the crew
		for (CrewMember cm : fallen)
			crew.killCrewMember(cm);

		// game continues
		return false;
	}

}