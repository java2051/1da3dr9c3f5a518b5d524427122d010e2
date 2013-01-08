package com.honey.core.utils;

import java.util.Random;

/**
 * 随机字符串
 * 
 * @author Administrator
 * 
 */
public class RandomStringUtils {

	private static final Random RANDOM = new Random();

	public RandomStringUtils() {
		super();
	}

	/**
	 * 随机数字
	 * 
	 * @param count
	 * @return
	 */
	public static String randomNumeric(int count) {
		return random(count, 48, 58, null, false, true, RANDOM);
	}

	/**
	 * 随机ascii包含的字符 (包括 数组 字母 和特殊字符) 
	 * @param count
	 * @return
	 */
	public static String randomAscii(int count) {
		return random(count, 33, 127, null, false, false, RANDOM);
	}

	/**
	 * 随机字母
	 * @param count
	 * @return
	 */
	public static String randomAlphabetic(int count) {
		return random(count, 65, 123, null, true, false, RANDOM);
	}

	/**
	 * 产生随机字符
	 * @param count
	 * @param start
	 * @param end
	 * @param chars
	 * @param letters
	 * @param numbers
	 * @param random
	 * @return
	 */
	private static String random(int count, int start, int end, char[] chars, boolean letters, boolean numbers, Random random) {
		if (count <= 0) {
			return "";
		}
		char[] buffer = new char[count];
		int gap = end - start;
		char ch;
		while (count-- != 0) {
			if (chars == null) {
				ch = (char) (random.nextInt(gap) + start);
			} else {
				ch = chars[random.nextInt(gap) + start];
			}
			if (letters && (ch >= 65 && ch <= 90 || ch >= 97 && ch <= 122)
					|| numbers && (ch >= 48 && ch <= 57) || !letters
					&& !numbers) {
				buffer[count] = ch;
			} else {
				count++;
			}
		}
		return new String(buffer);
	}

	public static void main(String[] args) {
		System.out.println(RandomStringUtils.randomAlphabetic(100));
	}
}
