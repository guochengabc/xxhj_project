package com.kongtiaoapp.xxhj.utils;



import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 * @author 作者 Luoye
 * @version 创建时间：2016年7月27日 下午4:43:46
 *
 * @description 类说明 :根据时间戳返回时间差
 */
public class TimeUtils {
	/**
	 * 将时间戳转为代表"距现在多久之前"的字符串
	 * 
	 * @param timeStr
	 *            时间戳
	 * @return
	 */
	public static String getStandardDate(String timeStr) {

		StringBuffer sb = new StringBuffer();

		long t = Long.parseLong(timeStr);
//		t = t / 1000;
		long time = System.currentTimeMillis() - (t * 1000);

		long mill = (long) Math.ceil(time / 1000);// 秒前

		long minute = (long) Math.ceil(time / 60 / 1000.0f);// 分钟前

		long hour = (long) Math.ceil(time / 60 / 60 / 1000.0f);// 小时

		long day = (long) Math.ceil(time / 24 / 60 / 60 / 1000.0f);// 天前

		if (day - 1 > 0) {
			sb.append(getDateToString(timeStr));
			return sb.toString();
		} else if (day - 1 > 0) {
			sb.append(day + "天");
		} else if (hour - 1 > 0) {
			if (hour >= 24) {
				sb.append("1天");
			} else {
				sb.append(hour + "小时");
			}
		} else if (minute - 1 > 0) {
			if (minute == 60) {
				sb.append("1小时");
			} else {
				sb.append(minute + "分钟");
			}
		} else if (mill - 1 > 0) {
			if (mill == 60) {
				sb.append("1分钟");
			} else {
				sb.append(mill + "秒");
			}
		} else {
			sb.append("刚刚");
		}
		if (!sb.toString().equals("刚刚")) {
			sb.append("前");
		}
		return sb.toString();
	}

	/* 时间戳转换成字符窜 */
	public static String getDateToString(String str) {
		long time = Long.parseLong(str);
		time = time*1000;
		SimpleDateFormat sf = new SimpleDateFormat("yyyy年MM月dd日");
		Date d = new Date(time);
		String format = sf.format(d);
		return format;

	}
	/* 时间戳转换成字符窜 */
	public static String getDetailTime(String str) {
		long time = Long.parseLong(str);
		time = time*1000;
		SimpleDateFormat sf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
		Date d = new Date(time);
		String format = sf.format(d);
		return format;

	}

	/* 时间戳转换成字符窜 */
	public static String getDetailTimeNoCHN(String str) {
		long time = Long.parseLong(str);
		time = time*1000;
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d = new Date(time);
		String format = sf.format(d);
		return format;

	}

	/* 时间戳转换成字符窜 */
	public static String getMothDay(String str) {
		long time = Long.parseLong(str);
		time = time*1000;
		SimpleDateFormat sf = new SimpleDateFormat("MM月dd日");
		Date d = new Date(time);
		String format = sf.format(d);
		return format;

	}

	/* 时间戳转换成字符窜 */
	public static String getYMR(String str) {
		long time = Long.parseLong(str);
		time = time*1000;
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Date d = new Date(time);
		String format = sf.format(d);
		return format;

	}

}
