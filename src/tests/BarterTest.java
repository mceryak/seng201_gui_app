package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import crew_member_types.Barter;

class BarterTest {

	private Barter m;

	@BeforeEach
	void setUp() throws Exception {
		m = new Barter("Steve");
	}

	@Test
	void testConstructor() {
		assertEquals("Steve", m.getName());
		assertEquals("Barter", m.getType());
		assertEquals(m.getHunger(), 0);
		assertEquals(m.getHealth(), 100);
		assertEquals(m.getTiredness(), 0);
		assertTrue(m.isAlive());
	}

	@Test
	void testNewDay() {
		m.callNewDay();
		assertEquals(m.getHealth(), 94);
		assertEquals(m.getHunger(), 15);
		assertEquals(m.getTiredness(), 15);
		m.callNewDay();
		assertEquals(m.getHealth(), 82);
		assertEquals(m.getHunger(), 30);
		assertEquals(m.getTiredness(), 30);
		m.callNewDay();
		assertEquals(m.getHealth(), 64);
		assertEquals(m.getHunger(), 45);
		assertEquals(m.getTiredness(), 45);
		assertTrue(m.isAlive());
		m.callNewDay();
		m.callNewDay();
		m.callNewDay();
		assertEquals(m.getHealth(), 0);
		assertEquals(m.getHunger(), 90);
		assertEquals(m.getTiredness(), 90);
		assertFalse(m.isAlive());
		m.callNewDay();
		assertEquals(m.getHealth(), 0);
		assertEquals(m.getHunger(), 100);
		assertEquals(m.getTiredness(), 100);
		assertFalse(m.isAlive());
		m.callNewDay();
		m.callNewDay();
		assertEquals(m.getHealth(), 0);
		assertEquals(m.getHunger(), 100);
		assertEquals(m.getTiredness(), 100);
		assertFalse(m.isAlive());
	}

}
