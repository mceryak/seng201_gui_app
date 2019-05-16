package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import crew_member_types.Pilot;

class PilotTest {

	private Pilot p;

	@BeforeEach
	void setUp() throws Exception {
		p = new Pilot("Perry");
	}

	@Test
	void testContructors() {
		assertEquals("Perry", p.getName());
		assertEquals("Pilot", p.getType());
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
		assertTrue(p.isAlive());
		p.callNewDay();
		assertEquals(p.getHealth(), 95);
		assertEquals(p.getHunger(), 15);
		assertEquals(p.getTiredness(), 12);
		p.callNewDay();
		assertEquals(p.getHealth(), 85);
		assertEquals(p.getHunger(), 30);
		assertEquals(p.getTiredness(), 24);
		p.callNewDay();
		p.callNewDay();
		p.callNewDay();
		assertEquals(p.getHealth(), 21);
		assertEquals(p.getHunger(), 75);
		assertEquals(p.getTiredness(), 60);
		p.callNewDay();
		assertEquals(p.getHealth(), 0);
		assertEquals(p.getHunger(), 90);
		assertEquals(p.getTiredness(), 72);
		assertFalse(p.isAlive());
		p.callNewDay();
		assertEquals(p.getHealth(), 0);
		assertEquals(p.getHunger(), 100);
		assertEquals(p.getTiredness(), 84);
		assertFalse(p.isAlive());
	}

}
