package pl.pepuch.wildjoe.helpers;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Math class tests
 * @author proszczyniala
 *
 */
public class MathTest {

	@Test
	public void shouldReturnValidInt_factorialTest() {
		assertEquals(1, Math.factorial(0));
		assertEquals(1, Math.factorial(1));
		assertEquals(2, Math.factorial(2));
		assertEquals(6, Math.factorial(3));
		assertEquals(479001600, Math.factorial(12));
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void shouldThrowIndexOutOfBoundsExceptionForTooLargeNumber_factorialTest() {
		Math.factorial(123);
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void shouldThrowIndexOutOfBoundsExceptionForNegativeNumber_factorialTest() {
		Math.factorial(-1);
	}

}
