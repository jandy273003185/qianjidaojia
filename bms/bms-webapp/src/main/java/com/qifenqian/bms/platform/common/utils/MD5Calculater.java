package com.qifenqian.bms.platform.common.utils;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 生成MD5码
 * @author Eric
 */
public class MD5Calculater {

	private MessageDigest alg = null;
	private final static int handleBlockSize = 32 * 1024;

	public MD5Calculater() throws NoSuchAlgorithmException {
		init();
	}

	// 使用方法一：对字节数组组合计算

	/**
	 * 初始化
	 */
	public void init() throws NoSuchAlgorithmException {
		alg = MessageDigest.getInstance("MD5");
		alg.reset();
	}

	/**
	 * 更新MD5散列
	 * 
	 * @param bufferData
	 * @param start
	 * @param end
	 */
	public void update(byte[] bufferData, int start, int end) {
		alg.update(bufferData, start, end);
	}

	/**
	 * 获取MD5码
	 * 
	 * @return
	 */
	public String getMD5Result() {
		byte[] hash = alg.digest();
		String md5 = "";
		for (int i = 0; i < hash.length; i++) {
			int v = hash[i] & 0xFF;
			if (v < 16) {
				md5 += "0";
			}
			md5 += Integer.toString(v, 16).toLowerCase(); 
		}
		return md5;
	}

	/**
	 * 使用方法二：直接对文件计算MD5
	 * 
	 * @param fileName
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws IOException
	 */
	public static String getMd5String(String fileName)
			throws NoSuchAlgorithmException, IOException {
		File file = new File(fileName);
		BufferedInputStream bis = null;
		bis = new BufferedInputStream(new FileInputStream(file));
		byte[] buffer = new byte[handleBlockSize];

		MessageDigest alg = MessageDigest.getInstance("MD5");
		alg.reset();
		while (true) {
			int len = bis.read(buffer);
			if (len <= 0) {
				break;
			} else {
				alg.update(buffer, 0, len);
			}
		}
		bis.close();
		
		byte[] hash = alg.digest();
		String md5 = "";
		for (int i = 0; i < hash.length; i++) {
			int v = hash[i] & 0xFF;
			if (v < 16) {
				md5 += "0";
			}
			md5 += Integer.toString(v, 16).toLowerCase();
		}
		
		return md5;
	}
	
	/**
	 * 使用方法二：直接对字符串计算MD5
	 * 
	 * @param tarStr    需计算MD5的字符串
	 * @param encoding  字符串转换为字节流时的编码
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws IOException
	 */
	public static String getMd5String(String tarStr,String encoding)
			throws NoSuchAlgorithmException, IOException {

		byte[] temp = tarStr.getBytes(encoding);
		return getMd5String(temp);
	}
	
	/**
	 * 使用方法二：直接对字节计算MD5
	 * 
	 * @param target    需计算MD5的字节
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws IOException
	 */
	public static String getMd5String(byte[] target)
			throws NoSuchAlgorithmException, IOException {
/*		File file = new File(fileName);
		BufferedInputStream bis = null;
		bis = new BufferedInputStream(new FileInputStream(file));*/

		MessageDigest alg = MessageDigest.getInstance("MD5");
		alg.reset();
		alg.update(target);
/*		while (true) {
			int len = bis.read(buffer);
			if (len <= 0) {
				break;
			} else {
				alg.update(buffer, 0, len);
			}
		}
		//bis.close();
*/		
		byte[] hash = alg.digest();
		String md5 = "";
		for (int i = 0; i < hash.length; i++) {
			int v = hash[i] & 0xFF;
			if (v < 16) {
				md5 += "0";
			}
			md5 += Integer.toString(v, 16).toLowerCase(); //按中心要求将MD5码改为小写 by jack 2010-09-21
		}
		
		return md5;
	}
	
}
