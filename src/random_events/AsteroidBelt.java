package random_events;

import game_objects.Crew;
import game_objects.RandomEvent;
import game_objects.spacebus.SpaceBus;

/**
 * This class is 1 of 3 possible classes called at the start of each new day
 * after the first. The crew flies into an asteroid belt
 */
public class AsteroidBelt implements RandomEvent {

	/**
	 * crew's spacebus takes damage
	 */
	@Override
	public String causeChaos(Crew crew) {

		// ship gets destroyed after 3 asteroid belts if never repaired
		SpaceBus ship = crew.getShip();
		int damage = ship.getShieldHealth() / 2;
		damage = damage > 25 ? damage : 25;
		damage = ship.takeDamage(damage);

		return "Your spacebus flew into an asteroid belt last night. ! Shields have taken " + damage + " damage. ";

	}

}
