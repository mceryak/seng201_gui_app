package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import medical_items.MedPack_Large;
import medical_items.MedPack_Small;
import medical_items.SpacePlagueCure;

class MedicalItemsTest {

	@Test
	void testMedPack_Small() {
		MedPack_Small m1 = new MedPack_Small();
		assertTrue(m1.equals(new MedPack_Small()));
		assertEquals("Small MedPack", m1.getName());
		assertEquals(10, m1.getPrice());
		assertEquals(15, m1.getHealthRestore());
		assertFalse(m1.curesSpacePlague());
		assertEquals(15, m1.getValue());
	}

	@Test
	void testMedPack_Large() {
		MedPack_Large m1 = new MedPack_Large();
		assertTrue(m1.equals(new MedPack_Large()));
		assertEquals("Large MedPack", m1.getName());
		assertEquals(20, m1.getPrice());
		assertEquals(40, m1.getHealthRestore());
		assertFalse(m1.curesSpacePlague());
		assertEquals(40, m1.getValue());
	}

	@Test
	void testEquals() {
		MedPack_Large m1 = new MedPack_Large();
		MedPack_Large m2 = new MedPack_Large();
		MedPack_Small m3 = new MedPack_Small();
		MedPack_Small m4 = new MedPack_Small();
		assertTrue(m1.equals(m2));
		assertFalse(m1.equals(m3));
		assertFalse(m2.equals(m3));
		assertTrue(m3.equals(m4));
	}

	@Test
	void testSpacePlagueCure() {
		SpacePlagueCure m1 = new SpacePlagueCure();
		assertTrue(m1.equals(new SpacePlagueCure()));
		assertEquals("Space Plague Cure", m1.getName());
		assertEquals(15, m1.getPrice());
		assertEquals(5, m1.getHealthRestore());
		assertTrue(m1.curesSpacePlague());
		assertEquals(5, m1.getValue());
	}

}
