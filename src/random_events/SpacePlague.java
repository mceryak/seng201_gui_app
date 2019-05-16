package random_events;

import java.util.HashSet;
import java.util.Set;

import game_objects.Crew;
import game_objects.RandomEvent;
import game_objects.crew_member.CrewMember;

/**
 * This class is 1 of 3 possible classes called at the start of each new day
 * after the first. Space Plague has infected one or more members of the crew.
 */
public class SpacePlague implements RandomEvent {

	/**
	 * Choose based on probabilities how many crew members get the plague.
	 * 
	 * @param crew The crew members need to be accessed to modify the hasPlague
	 *             attribute
	 */
	public String causeChaos(Crew crew) {

		String ret = "Space Plague infected your crew over night. ";

		// set of alive and unplaguedcrew members
		Set<CrewMember> victims = new HashSet<>();
		for (CrewMember cm : crew.getCrewMembers().keySet()) {
			if (crew.getCrewMembers().get(cm) && !cm.hasPlague())
				victims.add(cm);
		}

		// set according odds that another crew member will get sick
		boolean makeSick = true;

		// number of crew members given plague
		int numSick = 0;

		while (makeSick && victims.size() > 0) { // potential victims remain

			CrewMember victim = (CrewMember) victims.toArray()[(int) (Math.random() * victims.size())];
			victim.plagued();
			victims.remove(victim);

			numSick++;

			// chance 1 person gets plague: 1/1
			// chance 2 people get plague: 1/4
			// chance 3 people get plague: 1/4 * 1/7 = 1/28
			// chance 4 people get plague: 1/4 * 1/7 * 1/10 = 1/280
			makeSick = (int) (Math.random() * (numSick * 3 + 1)) < 1 ? true : false;
		}

		if (numSick > 0)
			return ret + numSick + " new crew member" + (numSick > 1 ? "s now have" : " now has") + " the plague.";

		return ret + "Everyone already has the plague anyway. No biggie.";
	}
}