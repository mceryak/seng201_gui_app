package tests;

import static org.junit.jupiter.api.Assertions.*;

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
	}

	@Test
	void testMedPack_Large() {
		MedPack_Large m1 = new MedPack_Large();
		assertTrue(m1.equals(new MedPack_Large()));
		assertEquals("Large MedPack", m1.getName());
		assertEquals(20, m1.getPrice());
		assertEquals(40, m1.getHealthRestore());
		assertFalse(m1.curesSpacePlague());
	}

	@Test
	void testSpacePlagueCure() {
		SpacePlagueCure m1 = new SpacePlagueCure();
		assertTrue(m1.equals(new SpacePlagueCure()));
		assertEquals("Space Plague Cure", m1.getName());
		assertEquals(15, m1.getPrice());
		assertEquals(5, m1.getHealthRestore());
		assertTrue(m1.curesSpacePlague());
	}

}
