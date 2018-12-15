package com.kongtiaoapp.xxhj.utils;

import android.util.Log;

/**
 * log管理类
 * 
 * @author Administrator
 *
 */
public class LogUtil {
	private static boolean isDebug = true;// 在app代码Debug时置为true
//	private static boolean isDebug = false;// 在app代码完成后置为false

	/** d级别log tag:TAG  msg:消息*/
	public static void d(String tag, String msg) {
		if (isDebug) {
			Log.d(tag, msg);
		}
	}
	
	/** d级别log object:类名   msg:消息*/
	public static void d(Object object, String msg) {
		if (isDebug) {
			Log.d(object.getClass().getSimpleName(), msg);
		}
	}

	/** e级别log tag:TAG  msg:消息*/
	public static void e(String tag, String msg) {
		if (isDebug) {
			Log.e(tag, msg);
		}
	}

	/** e级别log object:类名   msg:消息*/
	public static void e(Object object, String msg) {
		if (isDebug) {
			Log.e(object.getClass().getSimpleName(), msg);
		}
	}

	/** i级别log tag:TAG  msg:消息*/
	public static void i(String tag, String msg) {
		if (isDebug) {
			Log.i(tag, msg);
		}
	}

	/** i级别log object:类名   msg:消息*/
	public static void i(Object object, String msg) {
		if (isDebug) {
			Log.i(object.getClass().getSimpleName(), msg);
		}
	}
	
	/** v级别log tag:TAG  msg:消息*/
	public static void v(String tag, String msg) {
		if (isDebug) {
			Log.i(tag, msg);
		}
	}

	/** v级别log object:类名   msg:消息*/
	public static void v(Object object, String msg) {
		if (isDebug) {
			Log.v(object.getClass().getSimpleName(), msg);
		}
	}
	
	/** w级别log tag:TAG  msg:消息*/
	public static void w(String tag, String msg) {
		if (isDebug) {
			Log.i(tag, msg);
		}
	}

	/** w级别log object:类名   msg:消息*/
	public static void w(Object object, String msg) {
		if (isDebug) {
			Log.w(object.getClass().getSimpleName(), msg);
		}
	}
}
