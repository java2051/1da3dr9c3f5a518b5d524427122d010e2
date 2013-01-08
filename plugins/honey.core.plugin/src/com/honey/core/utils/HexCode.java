package com.honey.core.utils;

/**
 * 十六进制编/解码
 * @author Administrator
 *
 */
public class HexCode {

	/** 十六进制使用字符 */
	private final static char[] digits = { '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
	/**
	 * 编码
	 * @param data
	 * @return
	 */
	public final static char[] encodeHex(byte[] data) {
		int l = data.length;
		char[] out = new char[l << 1];
		for (int i = 0, j = 0; i < l; i++) {
			out[j++] = digits[(0xF0 & data[i]) >>> 4];
			out[j++] = digits[0x0F & data[i]];
		}
		return out;
	}

	/**
	 * 解码
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public final static byte[] decodeHex(char[] data) throws Exception {
		int l = data.length;
		if ((l & 0x01) != 0) {
			throw new Exception("odd number of characters.");
		}
		byte[] out = new byte[l >> 1];
		for (int i = 0, j = 0; j < l; i++) {
			int f = Character.digit(data[j++], 16) << 4;
			f = f | Character.digit(data[j++], 16);
			out[i] = (byte) (f & 0xFF);
		}
		return out;
	}
}
