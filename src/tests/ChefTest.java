package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import crew_member_types.Chef;

class ChefTest {

	private Chef c;

	@BeforeEach
	void setUp() throws Exception {
		c = new Chef("Bob");
	}

	@Test
	void testContructors() {
		assertEquals("Bob", c.getName());
		assertEquals("Chef", c.getType());
		assertEquals(c.getHunger(), 0);
		assertEquals(c.getHealth(), 100);
		assertEquals(c.getTiredness(), 0);
		assertTrue(c.isAlive());
	}

	@Test
	void testNewDay() {
		assertEquals(c.getHealth(), 100);
		assertEquals(c.getHunger(), 0);
		assertEquals(c.getTiredness(), 0);
		c.callNewDay();
		assertEquals(c.getHealth(), 94);
		assertEquals(c.getHunger(), 18);
		assertEquals(c.getTiredness(), 15);
		c.callNewDay();
		assertEquals(c.getHealth(), 81);
		assertEquals(c.getHunger(), 36);
		assertEquals(c.getTiredness(), 30);
		c.callNewDay();
		c.callNewDay();
		c.callNewDay();
		assertEquals(c.getHealth(), 3);
		assertEquals(c.getHunger(), 90);
		assertEquals(c.getTiredness(), 75);
		c.callNewDay();
		assertEquals(c.getHealth(), 0);
		assertEquals(c.getHunger(), 100);
		assertEquals(c.getTiredness(), 90);
		assertFalse(c.isAlive());
	}

}
