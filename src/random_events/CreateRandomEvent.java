package random_events;

import java.util.ArrayList;

import crew_member_types.Pilot;
import game_objects.Crew;
import game_objects.RandomEvent;
import game_objects.crew_member.CrewMember;
import main.GUIGame.DayWindow;

/**
 * This class gets called each time the player goes to next day. A random event
 * is performed
 */
public class CreateRandomEvent {

	/**
	 * create 1 of three random events, or no event if pilot is flying the spacebus
	 * 
	 * @param crew player's chosen crew
	 * @return String to update StoryLine with
	 */
	public String create(Crew crew) {

		// list of dead crew members to be removed from the crew
		ArrayList<CrewMember> fallen = new ArrayList<>();

		String ret;
		boolean isPilot = false;
		for (CrewMember cm : DayWindow.pilots) {
			if (cm.getAbilityIdentifier() == Pilot.abilityIdentifier) {
				isPilot = true;
			}
		}

		// If there is a Pilot flying the spacebus, there is a 20% chance that no random
		// event will occur
		if (isPilot && (int) (Math.random() * 5) == 0) {

			// Odds passed. No random event occurred.
			ret = "Hooray! Your pilots managed to dodge the pirates, asteroids, and plague!";

		} else {

			// Odds failed. Choose 1 of: Alien Pirates, Space Plague, Asteroid Belt
			RandomEvent[] events = new RandomEvent[] { new AlienPirates(), new SpacePlague(), new AsteroidBelt() };
			RandomEvent event = events[(int) (Math.random() * 3)];
			ret = event.causeChaos(crew);

			// if the event was SpacePlague, we need to check if it killed anyone
			if (event instanceof SpacePlague) {
				for (CrewMember cm : crew.getCrewMembers().keySet()) {
					if (!cm.isAlive()) { // crew member died
						fallen.add(cm);
						return ret + cm.getName() + " died. ";
					}
				}
				// remove dead crew members from crew
				for (CrewMember cm : fallen)
					crew.killCrewMember(cm);
				if (crew.getCrewMembers().size() == 0) { // whole crew has died. game over.
					return "You're entire crew has died from the plague. Unable to continue.";

				}
			}
		}
		return ret;
	}

}