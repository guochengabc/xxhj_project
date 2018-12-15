package com.kongtiaoapp.xxhj.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {

	/**
	 * 获取字符串加密
	 * @param string
	 * @return
	 */
	public static String encode(String string) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] bs = md.digest(string.getBytes());
			StringBuilder sb = new StringBuilder();
			for (byte b : bs) {
				int num = b & 0xff;	
				String hex = Integer.toHexString(num);	
				if (hex.length() == 1) {
					sb.append(0);
				}
				sb.append(b);
			}
			return sb.toString();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取文件MD5
	 * @param file
	 * @return
	 * @throws FileNotFoundException
	 */
	public static String encode(File file) throws FileNotFoundException {
		String value = null;
		FileInputStream in = new FileInputStream(file);
		try {
			MappedByteBuffer byteBuffer = in.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, file.length());
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(byteBuffer);
			BigInteger bi = new BigInteger(1, md5.digest());
			value = bi.toString(16);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(null != in) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return value;
	}

	/**
	 * 32位MD5加密
	 * @param sourceStr
     * @return
     */
	public static String MD5(String sourceStr) {
		String result = "";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(sourceStr.getBytes());
			byte b[] = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			result = buf.toString();
			System.out.println("MD5(" + sourceStr + ",32) = " + result);
			System.out.println("MD5(" + sourceStr + ",16) = " + buf.toString().substring(8, 24));
		} catch (NoSuchAlgorithmException e) {
			System.out.println(e);
		}
		return result;
	}

	public static String String2MD5(String sourceStr){
		return MD5Utils.MD5(MD5Utils.MD5(sourceStr));
	}
}
