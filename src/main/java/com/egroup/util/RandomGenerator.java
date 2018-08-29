package com.egroup.util;

import java.util.Random;

public class RandomGenerator {
	
	/**
	 * 產生字母數字混和字串
	 * @param length 長度
	 * @return numChar
	 */
	public String getNumChar(int length) {
		// init func
		final Random random = new Random();
		// init variable
		String val = "";
		String charOrNum;
		int choice;
		for (int i = 0; i < length; i++) {
			charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num"; // 输出字母还是数字
			if ("char".equalsIgnoreCase(charOrNum)) { // 字符串
				choice = random.nextInt(2) % 2 == 0 ? 65 : 97; // 取得大写字母还是小写字母
				val += (char) (choice + random.nextInt(26));
			} else if ("num".equalsIgnoreCase(charOrNum)) { // 数字
				val += String.valueOf(random.nextInt(10));
			}
		}
		return val;
	}
	
	/**
	 * 產生數字字串
	 * @param length 長度
	 * @return
	 */
	public String getNum(int length) {
		String val = "";
		final Random random = new Random();		
		for (int i = 0; i < length; i++) {
			val += String.valueOf(random.nextInt(10));
		}
		return val;
	}
}
