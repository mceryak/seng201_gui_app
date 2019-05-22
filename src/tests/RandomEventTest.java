package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import crew_member_types.Medic;
import crew_member_types.Pilot;
import food_items.Fingerlings;
import game_objects.Crew;
import game_objects.RandomEvent;
import main.GUIGame.DayWindow;
import medical_items.MedPack_Small;
import random_events.AlienPirates;
import random_events.AsteroidBelt;
import random_events.CreateRandomEvent;

class RandomEventTest {

	Crew crew;

	@BeforeEach
	void setUp() throws Exception {
		crew = new Crew("CrewName", "ShipName", 6);
	}

	@Test
	void testCreate() {
		DayWindow.pilots = new HashSet<>();
		DayWindow.pilots.add(new Pilot("Jen"));
		crew.addCrewMember(new Pilot("Jen"), Pilot.abilityIdentifier);
		crew.addCrewMember(new Medic("Beth"), Pilot.abilityIdentifier);
		for (int i = 0; i < 20; i++) {
			CreateRandomEvent cre = new CreateRandomEvent();
			assertTrue(cre.create(crew).length() > 0);
		}

	}

	@Test
	void testAsteroidBelt() {
		RandomEvent asteroids = new AsteroidBelt();
		assertEquals(100, crew.getShip().getShieldHealth());
		asteroids.causeChaos(crew);
		assertEquals(50, crew.getShip().getShieldHealth());
		crew.getShip().repairShield();
		assertEquals(80, crew.getShip().getShieldHealth());
		asteroids.causeChaos(crew);
		assertEquals(40, crew.getShip().getShieldHealth());
		asteroids.causeChaos(crew);
		assertEquals(15, crew.getShip().getShieldHealth());
		asteroids.causeChaos(crew);
		assertEquals(0, crew.getShip().getShieldHealth());
	}

	private int getInventorySize() {
		int ret = 0;
		for (int i : crew.getInventory().getFoodItems().values()) {
			ret += i;
		}
		for (int i : crew.getInventory().getMedicalItems().values()) {
			ret += i;
		}
		return ret;
	}

	@Test
	void testAlienPirates() {
		RandomEvent pirates = new AlienPirates();

		for (int i = 0; i < 9; i++) {
			crew.getInventory().addItem(new Fingerlings());
			crew.getInventory().addItem(new MedPack_Small());
		}

		for (int i = 0; i < 20; i++) {
			int inventorySize = getInventorySize();
			int coinsCount = crew.getInventory().getCCAmount();
			pirates.causeChaos(crew);
			assertTrue(getInventorySize() < inventorySize || crew.getInventory().getCCAmount() < coinsCount);
		}
	}

}
