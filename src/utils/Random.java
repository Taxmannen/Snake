package utils;

public class Random {

	public static int integer(int begin, int end) {
		return (int)(Math.random() * (end - begin)) + begin;
	}
}
