package tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import crew_member_types.Medic;
import game_objects.Crew;
import game_objects.crew_member.CrewMember;
import random_events.SpacePlague;

class SpacePlagueTest {

	Crew crew;

	@BeforeEach
	void setUp() throws Exception {
		crew = new Crew("crew-name", "ship-name", 3);
	}

	@Test
	void testGetSick() {

		for (int i = 0; i < 1000; i++) {
			crew = new Crew("crew-name", "ship-name", 3);
			crew.addCrewMember(new Medic("Joey"), Medic.abilityIdentifier);
			crew.addCrewMember(new Medic("Beth"), Medic.abilityIdentifier);
			crew.addCrewMember(new Medic("Andy"), Medic.abilityIdentifier);
			crew.addCrewMember(new Medic("Alex"), Medic.abilityIdentifier);
			new SpacePlague().causeChaos(crew);
			int numSickActual = getActualNumSick(crew);
			assertTrue(numSickActual > 0);
		}

		for (int i = 0; i < 1000; i++) {
			crew = new Crew("crew-name", "ship-name", 3);
			crew.addCrewMember(new Medic("Joey"), Medic.abilityIdentifier);
			crew.addCrewMember(new Medic("Beth"), Medic.abilityIdentifier);
			crew.addCrewMember(new Medic("Andy"), Medic.abilityIdentifier);
			new SpacePlague().causeChaos(crew);
			int numSickActual = getActualNumSick(crew);
			assertTrue(numSickActual > 0);
		}

		for (int i = 0; i < 1000; i++) {
			crew = new Crew("crew-name", "ship-name", 3);
			crew.addCrewMember(new Medic("Joey"), Medic.abilityIdentifier);
			crew.addCrewMember(new Medic("Beth"), Medic.abilityIdentifier);
			new SpacePlague().causeChaos(crew);
			int numSickActual = getActualNumSick(crew);
			assertTrue(numSickActual > 0);
		}
	}

	int getActualNumSick(Crew crew) {
		int numSickActual = 0;
		for (CrewMember cm : crew.getCrewMembers().keySet()) {
			if (cm.hasPlague()) {
				numSickActual++;
			}
		}
		return numSickActual;
	}

}
