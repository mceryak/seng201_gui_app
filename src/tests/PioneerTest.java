package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import crew_member_types.Pioneer;

class PioneerTest {

	private Pioneer p;

	@BeforeEach
	void setUp() throws Exception {
		p = new Pioneer("Johnson");
	}

	@Test
	void testContructors() {
		assertEquals("Johnson", p.getName());
		assertEquals("Pioneer", p.getType());
		assertEquals(p.getHunger(), 0);
		assertEquals(p.getHealth(), 100);
		assertEquals(p.getTiredness(), 0);
		assertTrue(p.isAlive());
	}

	@Test
	void testNewDay() {
		assertEquals(p.getHealth(), 100);
		assertEquals(p.getHunger(), 0);
		assertEquals(p.getTiredness(), 0);
		p.callNewDay();
		assertEquals(p.getHealth(), 94);
		assertEquals(p.getHunger(), 18);
		assertEquals(p.getTiredness(), 15);
		p.callNewDay();
		assertEquals(p.getHealth(), 81);
		assertEquals(p.getHunger(), 36);
		assertEquals(p.getTiredness(), 30);
		p.callNewDay();
		p.callNewDay();
		p.callNewDay();
		assertEquals(p.getHealth(), 3);
		assertEquals(p.getHunger(), 90);
		assertEquals(p.getTiredness(), 75);
		p.callNewDay();
		assertEquals(p.getHealth(), 0);
		assertEquals(p.getHunger(), 100);
		assertEquals(p.getTiredness(), 90);
		assertFalse(p.isAlive());
	}
}
