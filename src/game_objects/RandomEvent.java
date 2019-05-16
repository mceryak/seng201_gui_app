package game_objects;

/*
 * At the start of each day, a random event occurs, with each one implementing this interface.
 */
public interface RandomEvent {

	/*
	 * Does something against the desires of the player
	 * 
	 * @param crew All events will affect the crew's inventory or members somehow
	 */
	public abstract String causeChaos(Crew crew);

}
