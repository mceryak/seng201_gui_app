package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.EventQueue;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import crew_member_types.Barter;
import crew_member_types.Chef;
import crew_member_types.Juggernaut;
import crew_member_types.Medic;
import crew_member_types.Pilot;
import crew_member_types.Pioneer;
import food_items.MemberBerries;
import food_items.SaltyChocolateBalls;
import food_items.TenormanChili;
import food_items.Thwizlers;
import game_objects.Crew;
import game_objects.FoodItem;
import game_objects.crew_member.CrewMember;
import medical_items.MedPack_Small;
import medical_items.SpacePlagueCure;

class CrewTest {

	Crew crew;

	@BeforeEach
	void setUp() throws Exception {
		crew = new Crew("crew-name", "ship-name", 3);
	}

	@Test
	void testSetUp() {
		assertTrue(crew.getInventory().getFoodItems().isEmpty());
		assertTrue(crew.getInventory().getMedicalItems().isEmpty());
		assertTrue(crew.getCrewMembers().isEmpty());
		assertEquals(3, crew.getShip().getMissingPieces());
		assertEquals("ship-name", crew.getShip().getName());
		assertEquals(100, crew.getInventory().getCCAmount());
		assertEquals("crew-name", crew.getName());
	}

	@Test
	void testAddMoney() {
		int before = crew.getInventory().getCCAmount();
		crew.getInventory().modifyCCAmount(20);
		int after = crew.getInventory().getCCAmount();
		assertEquals(before + 20, after);
		before = after;
		crew.getInventory().modifyCCAmount(-20);
		after = crew.getInventory().getCCAmount();
		assertEquals(before - 20, after);
	}

	@Test
	void testGetAbilityIdentifiers() {
		CrewMember c1 = new Pioneer("Joe");
		CrewMember c2 = new Juggernaut("Eric");
		CrewMember c3 = new Barter("kyle");
		CrewMember c4 = new Chef("chef");
		crew.addCrewMember(c1, Pioneer.abilityIdentifier);
		crew.addCrewMember(c2, Juggernaut.abilityIdentifier);
		crew.addCrewMember(c3, Barter.abilityIdentifier);
		crew.addCrewMember(c4, Chef.abilityIdentifier);
		assertEquals(4, crew.getAbilityIdentifiers().size());
		assertTrue(crew.getAbilityIdentifiers().contains(Pioneer.abilityIdentifier));
		assertTrue(crew.getAbilityIdentifiers().contains(Juggernaut.abilityIdentifier));
		assertTrue(crew.getAbilityIdentifiers().contains(Barter.abilityIdentifier));
		assertTrue(crew.getAbilityIdentifiers().contains(Chef.abilityIdentifier));
		assertFalse(crew.getAbilityIdentifiers().contains(Medic.abilityIdentifier));
		assertFalse(crew.getAbilityIdentifiers().contains(Pilot.abilityIdentifier));
		crew.killCrewMember(c3);
		assertFalse(crew.getAbilityIdentifiers().contains(Barter.abilityIdentifier));
	}

	@Test
	void testNumAlive() {
		CrewMember c1 = new Pioneer("Joe");
		CrewMember c2 = new Juggernaut("Eric");
		CrewMember c3 = new Barter("kyle");
		CrewMember c4 = new Chef("chef");
		crew.addCrewMember(c1, Pioneer.abilityIdentifier);
		crew.addCrewMember(c2, Juggernaut.abilityIdentifier);
		crew.addCrewMember(c3, Barter.abilityIdentifier);
		crew.addCrewMember(c4, Chef.abilityIdentifier);
		assertEquals(4, crew.numAlive());
		crew.killCrewMember(c2);
		assertEquals(3, crew.numAlive());
		crew.killCrewMember(c4);
		assertEquals(2, crew.numAlive());
		crew.killCrewMember(c1);
		assertEquals(1, crew.numAlive());
		for (CrewMember cm : crew.getCrewMembers().keySet())
			if (cm.getName() == "kyle")
				assertTrue(crew.getCrewMembers().get(cm));
			else
				assertFalse(crew.getCrewMembers().get(cm));
		crew.killCrewMember(c3);
		assertEquals(0, crew.numAlive());
	}

	@Test
	void testAddCrewMember() {
		crew.addCrewMember(new Medic("Bobby"), Medic.abilityIdentifier);
		assertEquals(crew.getCrewMembers().size(), 1);
		assertEquals(crew.getCrewMembers().keySet().iterator().next().getType(), "Medic");
		assertEquals(crew.getCrewMembers().keySet().iterator().next().getName(), "Bobby");
		crew.addCrewMember(new Medic("Alex"), Medic.abilityIdentifier);
		assertEquals(crew.getCrewMembers().size(), 2);
	}

	@Test
	void testBuyFoodItem() {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				FoodItem b1 = new SaltyChocolateBalls();
				FoodItem b2 = new SaltyChocolateBalls();
				FoodItem p1 = new MemberBerries();
				assertEquals(100, crew.getInventory().getCCAmount());
				crew.getInventory().addItem(b1);
				assertTrue(crew.getInventory().getFoodItems().size() == 1);
				crew.getInventory().addItem(b2, b2.getPrice());
				assertTrue(crew.getInventory().getFoodItems().size() == 1);
				HashMap<FoodItem, Integer> hm = crew.getInventory().getFoodItems();
				int quantity = hm.get(b2);
				assertEquals(2, quantity);
				quantity = hm.get(b1);
				assertEquals(2, quantity);
				quantity = hm.get(new SaltyChocolateBalls());
				assertEquals(2, quantity);
				crew.getInventory().addItem(p1, p1.getPrice());
				Object[] pairs = hm.entrySet().toArray();
				quantity = hm.get(b1);
				assertEquals(2, quantity);
				quantity = hm.get(new MemberBerries());
				assertEquals(1, quantity);
				assertEquals(2, pairs.length);
				assertEquals(100 - b2.getPrice() - p1.getPrice(), crew.getInventory().getCCAmount());
			}
		});
	}

	@Test
	void testBuyMedicalItem() throws InterruptedException {
		SpacePlagueCure s1 = new SpacePlagueCure();
		SpacePlagueCure s2 = new SpacePlagueCure();
		MedPack_Small mp = new MedPack_Small();
		assertEquals(100, crew.getInventory().getCCAmount());
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				crew.getInventory().addItem(s1);
				assertEquals(1, crew.getInventory().getMedicalItems().size());
				int quantity = crew.getInventory().getMedicalItems().get(s1);
				assertEquals(1, quantity);
				crew.getInventory().addItem(s2, s2.getPrice());
				assertEquals(1, crew.getInventory().getMedicalItems().size());
				quantity = crew.getInventory().getMedicalItems().get(s1);
				assertEquals(2, quantity);
				quantity = crew.getInventory().getMedicalItems().get(s2);
				assertEquals(2, quantity);
				crew.getInventory().addItem(mp, mp.getPrice());
				assertEquals(2, crew.getInventory().getMedicalItems().size());
				quantity = crew.getInventory().getMedicalItems().get(mp);
				assertEquals(1, quantity);
				quantity = crew.getInventory().getMedicalItems().get(s1);
				assertEquals(2, quantity);
				quantity = crew.getInventory().getMedicalItems().get(s2);
				assertEquals(2, quantity);
				assertEquals(100 - s2.getPrice() - mp.getPrice(), crew.getInventory().getCCAmount());
			}
		});
	}

	@Test
	void testRemoveFoodItem() {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				FoodItem b1 = new TenormanChili();
				FoodItem b2 = new TenormanChili();
				FoodItem p1 = new Thwizlers();
				crew.getInventory().addItem(b1, b1.getPrice());
				crew.getInventory().addItem(b2, b2.getPrice());
				crew.getInventory().addItem(p1, p1.getPrice());
				assertEquals(2, crew.getInventory().getFoodItems().size());
				crew.getInventory().removeItem(new TenormanChili());
				assertEquals(2, crew.getInventory().getFoodItems().size());
				int quantity = crew.getInventory().getFoodItems().get(b2);
				assertEquals(1, quantity);
				crew.getInventory().removeItem(b2);
				assertNull(crew.getInventory().getFoodItems().get(b1));
				assertEquals(1, crew.getInventory().getFoodItems().size());
				crew.getInventory().removeItem(p1);
				assertTrue(crew.getInventory().getFoodItems().isEmpty());
			}
		});
	}

	@Test
	void testRemoveMedicalItem() {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				SpacePlagueCure s1 = new SpacePlagueCure();
				SpacePlagueCure s2 = new SpacePlagueCure();
				MedPack_Small mp = new MedPack_Small();
				crew.getInventory().addItem(s1, s1.getPrice());
				crew.getInventory().addItem(s2, s2.getPrice());
				crew.getInventory().addItem(mp, mp.getPrice());
				assertEquals(2, crew.getInventory().getMedicalItems().size());
				crew.getInventory().removeItem(new SpacePlagueCure());
				assertEquals(2, crew.getInventory().getMedicalItems().size());
				int quantity = crew.getInventory().getMedicalItems().get(s1);
				assertEquals(1, quantity);
				crew.getInventory().removeItem(mp);
				assertEquals(1, crew.getInventory().getMedicalItems().size());
				quantity = crew.getInventory().getMedicalItems().get(new SpacePlagueCure());
				assertEquals(1, quantity);
				assertNull(crew.getInventory().getMedicalItems().get(new MedPack_Small()));
				crew.getInventory().removeItem(s2);
				assertTrue(crew.getInventory().getMedicalItems().isEmpty());
			}
		});
	}

}
