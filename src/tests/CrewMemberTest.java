package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import crew_member_types.Juggernaut;
import crew_member_types.Medic;
import food_items.Fingerlings;
import food_items.Thwizlers;
import game_objects.FoodItem;
import game_objects.crew_member.CrewMember;
import medical_items.MedPack_Large;
import medical_items.MedPack_Small;
import medical_items.SpacePlagueCure;

class CrewMemberTest {

	CrewMember m1;
	CrewMember j1;

	@BeforeEach
	void setUp() throws Exception {
		m1 = new Medic("Juice");
		j1 = new Juggernaut("Betty");
	}

	@Test
	void testEquals() {
		CrewMember c1 = new Medic("Joey");
		CrewMember c2 = new Medic("Joe");
		CrewMember c3 = new Juggernaut("Dave");
		assertTrue(c1.equals(c1));
		assertFalse(c1.equals(c2));
		assertFalse(c1.equals(c3));
		assertTrue(c1.equals(new Medic("Joey")));
	}

	@Test
	void testGetters() {
		assertEquals("Juice", m1.getName());
		assertEquals(100, m1.getHealth());
		assertEquals(2, m1.getActionsLeft());
	}

	@Test
	void testEatFood() {
		FoodItem bread = new Thwizlers();
		FoodItem pudding = new Fingerlings();
		m1.callNewDay();
		assertEquals(15, m1.getHunger());
		assertEquals(40, bread.getNutrition());
		m1.eatFood(bread);
		assertEquals(0, m1.getHunger());
		m1.callNewDay();
		assertEquals(15, m1.getHunger());
		m1.eatFood(pudding);
		assertEquals(0, m1.getHunger());
		m1.callNewDay();
		m1.callNewDay();
		m1.callNewDay();
		m1.callNewDay();
		m1.callNewDay();
		m1.callNewDay();
		assertEquals(90, m1.getHunger());
		m1.heal(new MedPack_Large(), true);
		m1.callNewDay();
		assertEquals(100, m1.getHunger());
	}

	@Test
	void testHealing() {
		SpacePlagueCure spc = new SpacePlagueCure();
		MedPack_Small mps = new MedPack_Small();
		MedPack_Large mpl = new MedPack_Large();
		assertFalse(m1.hasPlague());
		m1.plagued();
		assertTrue(m1.hasPlague());
		assertEquals(90, m1.getHealth());
		m1.callNewDay();
		assertEquals(74, m1.getHealth());
		assertTrue(m1.hasPlague());
		m1.heal(mps, false);
		assertEquals(89, m1.getHealth());
		assertTrue(m1.hasPlague());
		m1.callNewDay();
		assertEquals(67, m1.getHealth());
		m1.callNewDay();
		assertEquals(39, m1.getHealth());
		m1.heal(mps, true);
		assertEquals(57, m1.getHealth());
		assertTrue(m1.hasPlague());
		m1.heal(spc, true);
		assertFalse(m1.hasPlague());
		assertEquals(63, m1.getHealth());
		m1.heal(mpl, false);
		assertEquals(100, m1.getHealth());
		m1.heal(mpl, false);
		assertEquals(100, m1.getHealth());
	}

}
