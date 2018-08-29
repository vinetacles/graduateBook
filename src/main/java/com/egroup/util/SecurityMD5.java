package com.egroup.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecurityMD5 {
	public String encryptWords(String info) throws NoSuchAlgorithmException {
		// MD5算法生成MessageDigest
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		// info資料轉乘byte並加密
		md5.update(info.getBytes());
		// 完成計算得到result
		byte[] resultBytes = md5.digest();
		// 把結果轉換成string，可用string格式進行處理
		SecurityMD5 resulString = new SecurityMD5();
		return resulString.binToString(resultBytes);
	}

	// binary轉換成string
	public String binToString(byte[] words) {
		final StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < words.length; ++i) {
			final byte b = words[i];
			final int value = (b & 0x7F) + (b < 0 ? 128 : 0);
			buffer.append(value < 16 ? "0" : "");
			buffer.append(Integer.toHexString(value));
		}

		return buffer.toString();
	}

	public void getMD5() throws NoSuchAlgorithmException {
		String aAString = encryptWords("xauc3954");
		System.out.println("The MD5 is : " + aAString);
	}

}
