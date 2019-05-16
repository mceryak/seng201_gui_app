package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import food_items.CremeFraiche;
import food_items.Fingerlings;
import food_items.MemberBerries;
import food_items.SaltyChocolateBalls;
import food_items.TenormanChili;
import food_items.Thwizlers;

class FoodTest {

	@Test
	void testThwizlers() {
		Thwizlers t1 = new Thwizlers();
		assertTrue(t1.equals(new Thwizlers()));
		assertEquals("Thwizlers", t1.getName());
		assertEquals(20, t1.getPrice());
		assertEquals(40, t1.getNutrition());
		assertEquals(5, t1.getRarity());
	}

	@Test
	void testFingerlings() {
		Fingerlings f1 = new Fingerlings();
		assertTrue(f1.equals(new Fingerlings()));
		assertEquals("Fingerlings", f1.getName());
		assertEquals(10, f1.getPrice());
		assertEquals(20, f1.getNutrition());
		assertEquals(10, f1.getRarity());
	}

	@Test
	void testTenormanChili() {
		TenormanChili t1 = new TenormanChili();
		assertTrue(t1.equals(new TenormanChili()));
		assertEquals("Tenorman Chili", t1.getName());
		assertEquals(40, t1.getPrice());
		assertEquals(80, t1.getNutrition());
		assertEquals(5, t1.getRarity());
	}

	@Test
	void testSaltyChocolateBalls() {
		SaltyChocolateBalls s1 = new SaltyChocolateBalls();
		assertTrue(s1.equals(new SaltyChocolateBalls()));
		assertEquals("Salty Choclate Balls", s1.getName());
		assertEquals(5, s1.getPrice());
		assertEquals(20, s1.getNutrition());
		assertEquals(2, s1.getRarity());
	}

	@Test
	void testCremeFraiche() {
		CremeFraiche c1 = new CremeFraiche();
		assertTrue(c1.equals(new CremeFraiche()));
		assertEquals("Creme Fraiche", c1.getName());
		assertEquals(2, c1.getPrice());
		assertEquals(3, c1.getNutrition());
		assertEquals(10, c1.getRarity());
	}

	@Test
	void testMemberBerries() {
		MemberBerries c1 = new MemberBerries();
		assertTrue(c1.equals(new MemberBerries()));
		assertEquals("Member Berries", c1.getName());
		assertEquals(15, c1.getPrice());
		assertEquals(30, c1.getNutrition());
		assertEquals(5, c1.getRarity());
	}

}
