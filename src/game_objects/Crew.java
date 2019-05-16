package game_objects;

import java.util.HashMap;
import java.util.HashSet;

import game_objects.crew_member.CrewMember;
import game_objects.inventory.Inventory;
import game_objects.spacebus.SpaceBus;

/**
 * This class creates the crew object for the player
 */
public class Crew {

	// given name of crew
	private String name;

	// crew's spacebus
	private SpaceBus spacebus;

	// set of ints representing which crew member types are in this crew
	private HashSet<Integer> abilityIdentifiers;

	// map of crew member to var containing whether they are alive or dead
	private HashMap<CrewMember, Boolean> crewMembers;

	// crew's inventory
	private Inventory inventory;

	/**
	 * Create the crew
	 * 
	 * @param crewName     String name of crew
	 * @param spaceBusName String name of spaceBus
	 * @param piecesLeft   int amount of pieces left to find to win
	 */
	public Crew(String crewName, String spaceBusName, int piecesLeft) {
		this.name = crewName;
		this.spacebus = new SpaceBus(spaceBusName, piecesLeft);

		this.crewMembers = new HashMap<>();
		this.inventory = new Inventory();

		this.abilityIdentifiers = new HashSet<>();
	}

	/**
	 * get name of the crew
	 * 
	 * @return String name of crew
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * get crew's inventory
	 * 
	 * @return Inventory of crew
	 */
	public Inventory getInventory() {
		return this.inventory;
	}

	/**
	 * get crew's spacebus
	 * 
	 * @return SpaceBus of crew
	 */
	public SpaceBus getShip() {
		return this.spacebus;
	}

	/**
	 * add crew member to crew
	 * 
	 * @param c                 crew member being added
	 * @param abilityIdentifier integer representing which ability that c has
	 */
	public void addCrewMember(CrewMember c, int abilityIdentifier) {
		this.crewMembers.put(c, true);
		abilityIdentifiers.add(abilityIdentifier);
	}

	/**
	 * set val to false in crew member hashmap and remove their ability identifier
	 * from set
	 * 
	 * @param c crew member to be set to dead
	 */
	public void killCrewMember(CrewMember c) {
		this.crewMembers.put(c, false);
		abilityIdentifiers.remove(c.getAbilityIdentifier());
	}

	/**
	 * get number of crew members still alive
	 * 
	 * @return int number of alive crew members
	 */
	public int numAlive() {
		int ret = 0;
		for (CrewMember cm : crewMembers.keySet())
			if (crewMembers.get(cm)) // still alive
				ret++;
		return ret;
	}

	/**
	 * get crew members
	 * 
	 * @return HashMap of crew members and state of life
	 */
	public HashMap<CrewMember, Boolean> getCrewMembers() {
		return this.crewMembers;
	}

	/**
	 * get set of all ability identifiers in the crew
	 * 
	 * @return HashSet of ability identifiers
	 */
	public HashSet<Integer> getAbilityIdentifiers() {
		return abilityIdentifiers;
	}

}
