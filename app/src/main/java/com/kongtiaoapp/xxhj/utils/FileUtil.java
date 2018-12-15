/*************************************************************************************************
 * 锟斤拷权锟斤拷锟斤拷 (C)2012,  锟斤拷锟斤拷锟叫匡拷锟窖硷拷锟脚股凤拷锟斤拷锟睫癸拷司 
 * 
 * 锟侥硷拷锟斤拷锟狡ｏ拷FileUtil.java
 * 锟斤拷锟斤拷摘要锟斤拷锟侥硷拷锟斤拷锟斤拷锟斤拷
 * 锟斤拷前锟芥本锟斤拷
 * 锟斤拷         锟竭ｏ拷 hexiaoming
 * 锟斤拷锟斤拷锟斤拷冢锟�2012-12-26
 * 锟睫改硷拷录锟斤拷
 * 锟睫革拷锟斤拷锟节ｏ拷
 * 锟斤拷   锟斤拷  锟脚ｏ拷
 * 锟斤拷   锟斤拷  锟剿ｏ拷
 * 锟睫革拷锟斤拷锟捷ｏ拷
 ************************************************************************************************/
package com.kongtiaoapp.xxhj.utils;

import android.os.Environment;

import java.io.File;

/**
 * 锟斤拷锟斤拷锟斤拷锟斤拷FileUtil
 *  @author hexiaoming
 *  @version  
 */
public class FileUtil {
	
	public static File updateDir = null;
	public static File updateFile = null;
	public static File updateFile_work = null;
	
	public static boolean isCreateFileSucess;

	/** 
	* 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷createFile锟斤拷锟斤拷
	* @param    app_name
	* @return 
	* @see FileUtil
	*/
	public static void createFile(String app_name ) {
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			isCreateFileSucess = true;
			updateDir = new File(Environment.getExternalStorageDirectory()+"/");
			updateFile = new File(updateDir + "/" + app_name + ".apk");
			updateFile_work = new File(updateDir + "/" + "hyw_work" + ".apk");
		}else{
			isCreateFileSucess = false;
		}
	}
}