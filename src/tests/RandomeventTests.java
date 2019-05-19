package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import crew_member_types.Pioneer;
import food_items.SaltyChocolateBalls;
import game_objects.Crew;
import game_objects.FoodItem;
import game_objects.MedicalItem;
import game_objects.crew_member.CrewMember;
import medical_items.MedPack_Small;
import random_events.AlienPirates;
import random_events.AsteroidBelt;
import random_events.SpacePlague;
import random_events.AlienPiratesOptions.StealFood;
import random_events.AlienPiratesOptions.StealHealing;
import random_events.AlienPiratesOptions.StealMoney;

class RandomeventTests {
	Crew crew;

	@BeforeEach
	void setUp() throws Exception {
		crew = new Crew("crew-name", "ship-name", 3);
	}

	@Test
	void asteroidtest() {
		AsteroidBelt asteroid = new AsteroidBelt();
		assertEquals(crew.getShip().getShieldHealth(), 100);
		asteroid.causeChaos(crew);
		assertEquals(crew.getShip().getShieldHealth(), 50);
		asteroid.causeChaos(crew);
		assertEquals(crew.getShip().getShieldHealth(), 25);
		asteroid.causeChaos(crew);
		assertEquals(crew.getShip().getShieldHealth(), 0);
	}
	
	@Test
	void stealMoneyTest() {
		String sr;
		int amt;
		StealMoney stealmon = new StealMoney();
		sr = stealmon.steal(crew.getInventory());
		sr = sr.substring(0,2);
		amt = Integer.parseInt(sr);
		assertEquals(crew.getInventory().getCCAmount(),100 - amt);
		crew.getInventory().modifyCCAmount(amt-100);
		assertEquals(stealmon.steal(crew.getInventory()), "");
		assertEquals(crew.getInventory().getCCAmount(), 0);

	}
	
	@Test
	void stealFoodTest() {
		StealFood stealfood = new StealFood();
		assertEquals(stealfood.steal(crew.getInventory()), "");
		FoodItem b1 = new SaltyChocolateBalls();
		crew.getInventory().addItem(b1);
		stealfood.steal(crew.getInventory());
		assertEquals(crew.getInventory().getFoodItems().size(), 0);
	}
	
	@Test
	void stealMedTest() {
		StealHealing stealmed = new StealHealing();
		assertEquals(stealmed.steal(crew.getInventory()), "");
		MedicalItem m1 = new MedPack_Small();
		crew.getInventory().addItem(m1);
		stealmed.steal(crew.getInventory());
		assertEquals(crew.getInventory().getMedicalItems().size(), 0);
	}
	
	@Test
	void PrirateTest() {
		AlienPirates pirate = new AlienPirates();
		pirate.causeChaos(crew);
		assertFalse(crew.getInventory().getCCAmount() == 100);
		crew.getInventory().modifyCCAmount(-100);
		assertEquals(pirate.causeChaos(crew), "Alien pirates raided your inventory, but they couldn't find anything to steal, so they insulted you instead.");
	}
	

}
