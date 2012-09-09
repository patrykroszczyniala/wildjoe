package pl.pepuch.wildjoe.helpers;

public class Math {

	public static int factorial(int n) {
		if (n==0) {
			return 1;
		}
		else {
			return n* factorial(n-1);
		}
	}
	
}
