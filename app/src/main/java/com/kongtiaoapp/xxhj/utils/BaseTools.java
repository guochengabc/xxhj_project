package com.kongtiaoapp.xxhj.utils;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.Selection;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;

@SuppressLint("NewApi")
public class BaseTools {
	/**
	 * 获取手机信息
	 * 
	 * @param context
	 * @return
	 */
	public static String getDeviceInfo(Context context) {

		try {
			org.json.JSONObject json = new org.json.JSONObject();
			android.telephony.TelephonyManager tm = (android.telephony.TelephonyManager) context
					.getSystemService(Context.TELEPHONY_SERVICE);

			String device_id = tm.getDeviceId();

			WifiManager wifi = (WifiManager) context
					.getSystemService(Context.WIFI_SERVICE);

			String mac = wifi.getConnectionInfo().getMacAddress();
			json.put("mac", mac);

			if (TextUtils.isEmpty(device_id)) {
				device_id = mac;
			}

			if (TextUtils.isEmpty(device_id)) {
				device_id = android.provider.Settings.Secure.getString(
						context.getContentResolver(),
						android.provider.Settings.Secure.ANDROID_ID);
			}

			json.put("device_id", device_id);

			return json.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取应用 版本
	 * 
	 * @param context
	 * @return
	 */
	public static int getAppVersion(Context context) {

		try {
			PackageInfo info = context.getPackageManager().getPackageInfo(
					context.getPackageName(), 0);
			return info.versionCode;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return 1;
	}

	public static String getAppVersionName(Context context) {

		try {
			PackageInfo info = context.getPackageManager().getPackageInfo(
					context.getPackageName(), 0);
			return info.versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return "1.0";
	}

	/**
	 * MDS 转化
	 * 
	 * @param key
	 * @return
	 */
	public static String hashKeyForDisk(String key) {
		String cacheKey;
		try {
			final MessageDigest mDigest = MessageDigest.getInstance("MD5");
			mDigest.update(key.getBytes());
			cacheKey = bytesToHexString(mDigest.digest());
		} catch (NoSuchAlgorithmException e) {
			cacheKey = String.valueOf(key.hashCode());
		}
		return cacheKey;
	}

	private static String bytesToHexString(byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			String hex = Integer.toHexString(0xFF & bytes[i]);
			if (hex.length() == 1) {
				sb.append('0');
			}
			sb.append(hex);
		}
		return sb.toString();
	}

	/**
	 * 穿件文件 根据是否有存储卡
	 * 
	 * @param context
	 * @param uniqueName
	 * @return
	 */
	public static File getDiskCacheDir(Context context, String uniqueName) {

		String cachePath;
		if (Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState())
				|| !Environment.isExternalStorageRemovable()) {
			cachePath = context.getExternalCacheDir().getPath();
		} else {
			cachePath = context.getCacheDir().getPath();
		}
		return new File(cachePath + File.separator + uniqueName);
	}

	public static String changeMoblie(String phoneNumber)
			throws RuntimeException {

		if (phoneNumber == null || phoneNumber.length() != 11) {
			throw new RuntimeException("鎵嬫満鍙风爜涓嶅鍝囷紒");
		}

		return phoneNumber.substring(0, 3) + "****"
				+ phoneNumber.substring(7, 11);
	}

	/**
	 * // 小数点左侧每三位一个逗号 保留两位小数
	 * 
	 * @param data
	 * @return
	 */
	public static String formatTosepara(float data) {

		DecimalFormat df = new DecimalFormat("#,###.00");
		return df.format(data);
	}

	public static String formatTosepara(int card_on) {

		DecimalFormat df = new DecimalFormat("#,###.00");
		return df.format(card_on);
	}

	/**
	 * 是否有 SDCARD
	 * 
	 * @return
	 */
	public static boolean isSdCardReady() {

		return Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED);
	}

	/**
	 * 获取手机屏幕宽度
	 * 
	 * @param activity
	 * @return
	 */
	public final static int getWindowsWidth(Activity activity) {

		DisplayMetrics dm = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
		return dm.widthPixels;
	}

	/**
	 * 获取手机屏幕高度
	 * 
	 * @param activity
	 * @return
	 */
	public final static int getWindowsHeigh(Activity activity) {

		DisplayMetrics dm = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
		return dm.heightPixels;
	}

	/**
	 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
	 * 
	 * @param context
	 * @param dpValue
	 * @return
	 */
	public static int dip2px(Context context, float dpValue) {

		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	/**
	 * 像素转化dip
	 * 
	 * @param context
	 * @param pxValue
	 * @return
	 */
	public static int px2dip(Context context, float pxValue) {

		final float scale = context.getResources().getDisplayMetrics().density;

		return (int) (pxValue / scale + 0.5f);

	}

	/**
	 * 将px值转换为sp值，保证文字大小不变
	 * 
	 * @param pxValue
	 * @param fontScale
	 *            （DisplayMetrics类中属性scaledDensity）
	 * @return
	 */
	public static int px2sp(Context context, float pxValue) {

		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (pxValue / fontScale + 0.5f);
	}

	/**
	 * 将sp值转换为px值，保证文字大小不变
	 * 
	 * @param spValue
	 * @param fontScale
	 *            （DisplayMetrics类中属性scaledDensity）
	 * @return
	 */
	public static int sp2px(Context context, float spValue) {

		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (spValue * fontScale + 0.5f);
	}

	/**
	 * 5-20 数字和字母组合
	 * 
	 * @param username
	 * @return
	 */
	public static boolean isUserName(String username) {

		Pattern p = Pattern.compile("^[a-zA-Z][a-zA-Z0-9]{5,20}$");
		Matcher m = p.matcher(username);
		return m.matches();
	}

	public static boolean isMobileNO(String s) {
		return s != null && s.length() == 11 ? true : false;
	}

	/**
	 * 银行卡号是否合法
	 * 
	 * @param number
	 * @return
	 */
	public static boolean isBankCard(String number) {

		try {
			int sumOdd = 0;
			int sumEven = 0;
			int length = number.length();
			int[] wei = new int[length];
			for (int i = 0; i < number.length(); i++) {
				wei[i] = Integer.parseInt(number.substring(length - i - 1,
						length - i));// 浠庢渶鏈竴浣嶅紑濮嬫彁鍙栵紝姣忎竴浣嶄笂鐨勬暟鍊�
			}
			for (int i = 0; i < length / 2; i++) {
				sumOdd += wei[2 * i];
				if ((wei[2 * i + 1] * 2) > 9)
					wei[2 * i + 1] = wei[2 * i + 1] * 2 - 9;
				else
					wei[2 * i + 1] *= 2;
				sumEven += wei[2 * i + 1];
			}
			if ((sumOdd + sumEven) % 10 == 0)
				return true;
			else
				return false;
		} catch (NumberFormatException exception) {
			return false;
		}
	}

	/**
	 * 检查邮箱格式是否正确
	 * 
	 * @param email
	 * @return
	 */
	public static boolean checkEmail(String email) {

		boolean flag = false;
		try {
			String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(email);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}

		return flag;
	}

	/**
	 * file 转 byte[]
	 * 
	 * @param file
	 * @return
	 */
	public static byte[] readFile(File file) {

		RandomAccessFile rf = null;
		byte[] data = null;
		try {
			rf = new RandomAccessFile(file, "r");
			int length = (int) rf.length();
			if (length >= 5 * 1024 * 1024) {
				return null;
			}
			data = new byte[length];
			rf.readFully(data);
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			closeQuietly(rf);
		}
		return data;
	}

	public static void closeQuietly(Closeable closeable) {

		try {
			if (closeable != null) {
				closeable.close();
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	/**
	 * 获取相机内的图片文件
	 * 
	 * @param act
	 * @return
	 */

	public static ArrayList<String> getGalleryPhotos(Activity act) {

		ArrayList<String> galleryList = new ArrayList<String>();
		try {
			final String[] columns = { MediaStore.Images.Media.DATA,
					MediaStore.Images.Media._ID };
			// MediaStore.Audio.Media.DATA;
			final String orderBy = MediaStore.Images.Media._ID;
			Cursor imagecursor = act.managedQuery(
					MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns,
					null, null, orderBy);
			act.getContentResolver().query(
					MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns,
					null, null, orderBy);
			if (imagecursor != null && imagecursor.getCount() > 0) {
				while (imagecursor.moveToNext()) {
					String item = new String();
					int dataColumnIndex = imagecursor
							.getColumnIndex(MediaStore.Images.Media.DATA);
					item = imagecursor.getString(dataColumnIndex);
					galleryList.add(item);
				}
			}
			imagecursor.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Collections.reverse(galleryList);
		return galleryList;
	}

	/**
	 * 银行卡四位加空格
	 * 
	 * @param mEditText
	 */
	public static void bankCardNumAddSpace(final EditText mEditText) {

		// editText.setFilters(new InputFilter[]{new
		// InputFilter.LengthFilter(100)});
		mEditText.addTextChangedListener(new TextWatcher() {

			int beforeTextLength = 0;
			int onTextLength = 0;
			boolean isChanged = false;

			int location = 0;// 璁板綍鍏夋爣鐨勪綅缃�
			private char[] tempChar;
			private StringBuffer buffer = new StringBuffer();
			int konggeNumberB = 0;

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

				beforeTextLength = s.length();
				if (buffer.length() > 0) {
					buffer.delete(0, buffer.length());
				}
				konggeNumberB = 0;
				for (int i = 0; i < s.length(); i++) {
					if (s.charAt(i) == ' ') {
						konggeNumberB++;
					}
				}
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {

				onTextLength = s.length();
				buffer.append(s.toString());
				if (onTextLength == beforeTextLength || onTextLength <= 3
						|| isChanged) {
					isChanged = false;
					return;
				}
				isChanged = true;
			}

			@Override
			public void afterTextChanged(Editable s) {

				if (isChanged) {
					location = mEditText.getSelectionEnd();
					int index = 0;
					while (index < buffer.length()) {
						if (buffer.charAt(index) == ' ') {
							buffer.deleteCharAt(index);
						} else {
							index++;
						}
					}

					index = 0;
					int konggeNumberC = 0;
					while (index < buffer.length()) {
						if ((index == 4 || index == 9 || index == 14 || index == 19)) {
							buffer.insert(index, ' ');
							konggeNumberC++;
						}
						index++;
					}

					if (konggeNumberC > konggeNumberB) {
						location += (konggeNumberC - konggeNumberB);
					}

					tempChar = new char[buffer.length()];
					buffer.getChars(0, buffer.length(), tempChar, 0);
					String str = buffer.toString();
					if (location > str.length()) {
						location = str.length();
					} else if (location < 0) {
						location = 0;
					}

					mEditText.setText(str);
					Editable etable = mEditText.getText();
					try {
						Selection.setSelection(etable, location);
					} catch (Exception e) {
						e.printStackTrace();
					}

					isChanged = false;
				}
			}
		});
	}

	/**
	 * 计算listview 的高度
	 * 
	 * @param listView
	 */
	public static void setListViewHeightBasedOnChildren(ListView listView) {

		ListAdapter listAdapter = listView.getAdapter();
		if (listAdapter == null) {
			// pre-condition
			return;
		}

		int totalHeight = 0;
		for (int i = 0; i < listAdapter.getCount(); i++) {
			View listItem = listAdapter.getView(i, null, listView);
			listItem.measure(0, 0);
			totalHeight += listItem.getMeasuredHeight();
		}

		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight
				+ (listView.getDividerHeight() * (listAdapter.getCount() - 1))
				+ (listView.getPaddingTop() + listView.getPaddingBottom());
		listView.setLayoutParams(params);
	}

	/**
	 * 计算 gridview 高度
	 * 
	 * @param listView
	 * @param column
	 */
	public static void setGridviewHeightBasedOnChildren(GridView listView,
			int column) {
		ListAdapter listAdapter = listView.getAdapter();
		if (listAdapter == null) {
			// pre-condition
			return;
		}
		int line = 0;
		int count = listAdapter.getCount();
		if (count % column == 0) {
			line = count / column;
		} else {
			line = count / column + 1;
		}
		int totalHeight = 0;

		for (int i = 0; i < line; i++) {
			View listItem = listAdapter.getView(i, null, listView);
			listItem.measure(0, 0);
			totalHeight += listItem.getMeasuredHeight();
		}

		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight
				+ (listView.getVerticalSpacing() * (line - 1))
				+ (listView.getPaddingTop() + listView.getPaddingBottom());
		listView.setLayoutParams(params);
	}

	/**
	 * 字节流转换为字符串
	 * 
	 * @param is
	 *            内容输入流
	 * @return String result
	 * @throws IOException
	 */
	public static String readFromStream(InputStream is) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = is.read(buffer)) != -1) {
			baos.write(buffer, 0, len);
		}
		is.close();
		String result = baos.toString();
		baos.close();
		return result;
	}

	/**
	 * 检查是否有网络
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isNetworkConnected(Context context) {
		// TODO Auto-generated method stub
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mNetworkInfo = mConnectivityManager
					.getActiveNetworkInfo();
			if (mNetworkInfo != null) {
				return mNetworkInfo.isAvailable();
			}
		}
		return false;
	}

	/**
	 * 计算view 宽度
	 * 
	 * @param view
	 * @return
	 */
	public static int customMeasureWidth(View view) {
		int w = View.MeasureSpec.makeMeasureSpec(0,
				View.MeasureSpec.UNSPECIFIED);
		int h = View.MeasureSpec.makeMeasureSpec(0,
				View.MeasureSpec.UNSPECIFIED);
		view.measure(w, h);
		int width = view.getMeasuredWidth();
		return width;
	}

	/**
	 * 计算view 高度
	 * 
	 * @param view
	 * @return
	 */

	public static int customMeasureHeight(View view) {
		int w = View.MeasureSpec.makeMeasureSpec(0,
				View.MeasureSpec.UNSPECIFIED);
		int h = View.MeasureSpec.makeMeasureSpec(0,
				View.MeasureSpec.UNSPECIFIED);
		view.measure(w, h);
		int height = view.getMeasuredHeight();
		return height;
	}

	/**
	 * 打卡软键盘
	 * 
	 * @param mEditText
	 * @param mContext
	 */
	public static void openKeybord(EditText mEditText, Context mContext) {
		InputMethodManager imm = (InputMethodManager) mContext
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.showSoftInput(mEditText, InputMethodManager.RESULT_SHOWN);
		imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,
				InputMethodManager.HIDE_IMPLICIT_ONLY);
	}

	/**
	 * 关闭软键盘
	 *
	 */
	public static void closeKeybord(EditText mEditText, Context mContext) {
		InputMethodManager imm = (InputMethodManager) mContext
				.getSystemService(Context.INPUT_METHOD_SERVICE);

		imm.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
	}

	/**
	 * 将名称从后面第一个指定字符截取
	 * 
	 * @param name
	 */
	public static String splitString(String name, String splitChar) {
		return name.substring(0, name.lastIndexOf(splitChar));
	}

	/**
	 * 判断字符串是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpety(String str) {
		return !"".equals(str) && (str != null) ? true : false;
	}

	/**
	 * 获取当前屏幕截图，包含状态栏
	 * 
	 * @param activity
	 * @return
	 */
	public static Bitmap snapShotWithStatusBar(Activity activity) {
		View view = activity.getWindow().getDecorView();
		view.setDrawingCacheEnabled(true);
		view.buildDrawingCache();
		Bitmap bmp = view.getDrawingCache();
		int width = getWindowsWidth(activity);
		int height = getWindowsHeigh(activity);
		Bitmap bp = null;
		bp = Bitmap.createBitmap(bmp, 0, 0, width, height);
		view.destroyDrawingCache();
		return bp;

	}

	/**
	 * 获取当前屏幕截图，不包含状态栏
	 * 
	 * @param activity
	 * @return
	 */
	public static Bitmap snapShotWithoutStatusBar(Activity activity) {
		View view = activity.getWindow().getDecorView();
		view.setDrawingCacheEnabled(true);
		view.buildDrawingCache();
		Bitmap bmp = view.getDrawingCache();
		Rect frame = new Rect();
		activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
		int statusBarHeight = frame.top;

		int width = getWindowsWidth(activity);
		int height = getWindowsHeigh(activity);
		Bitmap bp = null;
		bp = Bitmap.createBitmap(bmp, 0, statusBarHeight, width, height
				- statusBarHeight);
		view.destroyDrawingCache();
		return bp;
	}

	/**
	 * 获得状态栏的高度
	 * 
	 * @param context
	 * @return
	 */
//	public static int getStatusHeight(Context context) {
//
//		int statusHeight = -1;
//		try {
//			Class<?> clazz = Class.forName("com.android.internal.R$dimen");
//			Object object = clazz.newInstance();
//			int height = Integer.parseInt(clazz.getField("status_bar_height")
//					.get(object).toString());
//			statusHeight = context.getResources().getDimensionPixelSize(height);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return statusHeight;
//	}

	/**
	 * Gps是否可用
	 * 
	 * @param mContext
	 * @return
	 */
	public static boolean isGpsEnable(Context mContext) {
		LocationManager locationManager = ((LocationManager) mContext
				.getSystemService(Context.LOCATION_SERVICE));
		return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
	}

	/**
	 * Wifi是否可用
	 * 
	 * @param mContext
	 * @return
	 */
	public static boolean isWifiEnable(Context mContext) {
		WifiManager wifiManager = (WifiManager) mContext
				.getSystemService(Context.WIFI_SERVICE);
		return wifiManager.isWifiEnabled();
	}

	public static Double div(Double dividend, Double divisor) {
		return div(dividend, divisor, 2);
	}

	public static Double div(Double dividend, Double divisor, Integer scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(dividend));
		BigDecimal b2 = new BigDecimal(Double.toString(divisor));
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/** 根据屏幕宽度与密度计算GridView显示的列数， 最少为三列，并获取Item宽度 */
	public static int getImageItemWidth(Activity activity) {
		int screenWidth = activity.getResources().getDisplayMetrics().widthPixels;
		int densityDpi = activity.getResources().getDisplayMetrics().densityDpi;
		int cols = screenWidth / densityDpi;
		cols = cols < 3 ? 3 : cols;
		int columnSpace = (int) (2 * activity.getResources()
				.getDisplayMetrics().density);
		return (screenWidth - columnSpace * (cols - 1)) / cols;
	}

}
