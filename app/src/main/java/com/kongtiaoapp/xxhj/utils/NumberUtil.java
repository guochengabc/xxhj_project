package com.kongtiaoapp.xxhj.utils;

import org.json.JSONArray;
import org.json.JSONException;

import java.math.BigDecimal;
import java.util.List;

public class NumberUtil {

	/**
	 * 提供精确的加法运算。
	 *
	 * @param v1
	 *            被加数
	 * @param v2
	 *            加数
	 * @return 两个参数的和
	 */
	public static double add(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.add(b2).doubleValue();
	}

	/**
	 * 提供精确的减法运算。
	 *
	 * @param v1
	 *            被减数
	 * @param v2
	 *            减数
	 * @return 两个参数的差
	 */
	public static double sub(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.subtract(b2).doubleValue();
	}

	/**
	 * 提供精确的乘法运算。
	 *
	 * @param v1
	 *            被乘数
	 * @param v2
	 *            乘数
	 * @return 两个参数的积
	 */
	public static double mul(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.multiply(b2).doubleValue();
	}

	/**
	 * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到 小数点以后10位，以后的数字四舍五入。
	 *
	 * @param v1
	 *            被除数
	 * @param v2
	 *            除数
	 * @return 两个参数的商
	 */
	public static double div(double v1, double v2) {
		return div(v1, v2, 10);
	}

	/**
	 * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
	 *
	 * @param v1
	 *            被除数
	 * @param v2
	 *            除数
	 * @param scale
	 *            表示表示需要精确到小数点以后几位。
	 * @return 两个参数的商
	 */
	public static double div(double v1, double v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 提供精确的小数位四舍五入处理。
	 *
	 * @param v
	 *            需要四舍五入的数字
	 * @param scale
	 *            小数点后保留几位
	 * @return 四舍五入后的结果
	 */
	public static double round(double v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(Double.toString(v));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 提供精确的小数位四舍五入处理。
	 *
	 * @param v
	 *            需要四舍五入的数字
	 * @return 四舍五入后的结果，默认2位
	 */
	public static double round(double v) {
		BigDecimal b = new BigDecimal(Double.toString(v));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, 2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 获取jsonarray的平均值
	 *
	 * @param arr
	 * @return
	 */
	public static double getAvgValue(JSONArray arr) {
		double avgValue = 0;
		int length = 0;
		double value = 0;
		if (arr != null && arr.length() > 0) {
			for (int i = 0; i < arr.length(); i++) {
                try {
                    value = arr.getJSONObject(i).optDouble("V", 0);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (value > 0) {
					avgValue = add(avgValue, value);
					length++;
				}
			}
			if (length > 0) {
				avgValue = avgValue / length;
			}
		}
		return round(avgValue);
	}

	/**
	 * 获取list和
	 *
	 * @param coll
	 * @return
	 */
	public static double getListSum(List<String> coll) {
		double sum = 0;
		if (coll != null) {
			for (int i = 0; i < coll.size(); i++) {
				if (Double.parseDouble(coll.get(i)) > 0) {
					sum = add(sum, Double.parseDouble(coll.get(i)));
				}
			}
		}
		return sum;
	}

	/**
	 * 获取jsonarray的总和
	 *
	 * @param arr
	 * @return
	 */
	public static double getSumValue(JSONArray arr) {
		double sumValue = 0;
		double value = 0;
		if (arr != null && arr.length() > 0) {
			for (int i = 0; i < arr.length(); i++) {
                try {
                    value = arr.getJSONObject(i).optDouble("V", 0);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                sumValue = add(sumValue, value);
			}
		}
		return sumValue;
	}

}
