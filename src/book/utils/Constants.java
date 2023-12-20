package book.utils;

import java.util.Random;

public class Constants {
	public static String randomCode(int len) {
		Random r = new Random();
		String s = "";
		for (int i = 0; i < len; i++) {
			s += Math.abs(r.nextInt() % 10);
		}
		return s;
	}
}
