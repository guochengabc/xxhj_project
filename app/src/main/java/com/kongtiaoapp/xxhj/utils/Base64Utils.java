/*
 * Copyright 2002-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.kongtiaoapp.xxhj.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/**
 * A simple utility class for Base64 encoding and decoding that delegates to Android's
 * {@link Base64} class.
 *
 * @author Juergen Hoeller
 * @author Roy Clarkson
 * @since 1.0
 */
public abstract class Base64Utils {

	private static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

	private static final Base64Delegate delegate = new AndroidBase64Delegate();


	/**
	 * Base64-encode the given byte array.
	 * @param src the original byte array (may be {@code null})
	 * @return the encoded byte array (or {@code null} if the input was {@code null})
	 */
	public static byte[] encode(byte[] src) {
		return delegate.encode(src);
	}

	/**
	 * Base64-encode the given byte array to a String.
	 * @param src the original byte array (may be {@code null})
	 * @return the encoded byte array as a UTF-8 String
	 * (or {@code null} if the input was {@code null})
	 */
	public static String encodeToString(byte[] src) {
		if (src == null) {
			return null;
		}
		if (src.length == 0) {
			return "";
		}
		String result;
		try {
			result = new String(delegate.encode(src), DEFAULT_CHARSET.displayName());
		} catch (UnsupportedEncodingException e) {
			// should not happen, UTF-8 is always supported
			throw new IllegalStateException(e);
		}
		return result;
	}

	/**
	 * Base64-decode the given byte array.
	 * @param src the encoded byte array (may be {@code null})
	 * @return the original byte array (or {@code null} if the input was {@code null})
	 */
	public static byte[] decode(byte[] src) {
		return delegate.decode(src);
	}

	/**
	 * Base64-decode the given byte array from an UTF-8 String.
	 * @param src the encoded UTF-8 String (may be {@code null})
	 * @return the original byte array (or {@code null} if the input was {@code null})
	 * @deprecated in favor of {@link #decodeFromString(String)}
	 */
	@Deprecated
	public static byte[] decode(String src) {
		return decodeFromString(src);
	}

	/**
	 * Base64-decode the given byte array from an UTF-8 String.
	 * @param src the encoded UTF-8 String (may be {@code null})
	 * @return the original byte array (or {@code null} if the input was {@code null})
	 * @since 2.0
	 */
	public static byte[] decodeFromString(String src) {
		if (src == null) {
			return null;
		}
		if (src.length() == 0) {
			return new byte[0];
		}
		byte[] result;
		try {
			result = delegate.decode(src.getBytes(DEFAULT_CHARSET.displayName()));
		} catch (UnsupportedEncodingException e) {
			// should not happen, UTF-8 is always supported
			throw new IllegalStateException(e);
		}
		return result;
	}


	private interface Base64Delegate {

		byte[] encode(byte[] src);

		byte[] decode(byte[] src);
	}


	private static class AndroidBase64Delegate implements Base64Delegate {

		public byte[] encode(byte[] src) {
			if (src == null || src.length == 0) {
				return src;
			}
			return Base64.encode(src, Base64.DEFAULT | Base64.NO_WRAP);
		}

		public byte[] decode(byte[] src) {
			if (src == null || src.length == 0) {
				return src;
			}
			return Base64.decode(src, Base64.DEFAULT | Base64.NO_WRAP);
		}
	}

	/**
	 * 将base64字符串转化为Bitmap图片
	 * @param base64	字符串
     * @return	Bitmap对象
     */
	public static Bitmap getBitmapFor64(String base64){
		byte[] imageByte = Base64.decode(base64, Base64.DEFAULT);
		Bitmap bitmap = BitmapFactory.decodeByteArray(imageByte, 0, imageByte.length);
		return bitmap;
	}

	public static String encodeBase64File(String path) throws Exception {
		File file = new File(path);
		FileInputStream inputFile = new FileInputStream(file);
		byte[] buffer = new byte[(int)file.length()];
		inputFile.read(buffer);
		inputFile.close();
		return Base64.encodeToString(buffer, Base64.DEFAULT);
	}

	/**
	 * 给图片分配适量的内存  防止内存溢出
	 */
    public static Bitmap createImageUtils(String filePath){
        Bitmap bitmap = null;
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, opts);
        opts.inSampleSize = computeSampleSize(opts, -1, 800*800);
        opts.inJustDecodeBounds = false;

        try {
            bitmap = BitmapFactory.decodeFile(filePath, opts);
        }catch (Exception e) {
            // TODO: handle exception
        }
        return bitmap;
    }
	public static Bitmap comp(Bitmap image) {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		if(null!=image){
			image.compress(Bitmap.CompressFormat.JPEG, 50, baos);

		}
		if( baos.toByteArray().length / 1024>1024) {//判断如果图片大于1M,进行压缩避免在生成图片（BitmapFactory.decodeStream）时溢出
			baos.reset();//重置baos即清空baos
			if(null!=image)
				image.compress(Bitmap.CompressFormat.JPEG, 50, baos);//这里压缩50%，把压缩后的数据存放到baos中

		}
		ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
		BitmapFactory.Options newOpts = new BitmapFactory.Options();
		//开始读入图片，此时把options.inJustDecodeBounds 设回true了
		newOpts.inJustDecodeBounds = true;
		Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
		newOpts.inJustDecodeBounds = false;
		int w = newOpts.outWidth;
		int h = newOpts.outHeight;
		//现在主流手机比较多是1920*1080分辨率，所以高和宽我们设置为
		float hh = 1280;//这里设置高度为800f
		float ww = 720;//这里设置宽度为480f
		//缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
		int be = 1;//be=1表示不缩放
		if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放
			be = (int) (newOpts.outWidth / ww);
		} else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放
			be = (int) (newOpts.outHeight / hh);
		}
		if (be <= 0)
			be = 1;
		newOpts.inSampleSize = be;//设置缩放比例
		//重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
		isBm = new ByteArrayInputStream(baos.toByteArray());
		bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
		return bitmap;//compressImage(bitmap);//压缩好比例大小后再进行质量压缩
	}
	//质量压缩
    public static Bitmap compressImage(Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        if(null!=image)
            image.compress(Bitmap.CompressFormat.JPEG, 40, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 60;
        while ( baos.toByteArray().length / 1024>100) {  //循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset();//重置baos即清空baos
            if(null!=image)
                image.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;//每次都减少10
        }

        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片
        try {
            isBm.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }
    public static int computeSampleSize(BitmapFactory.Options options, int minSideLength, int maxNumOfPixels) {
        int initialSize = computeInitialSampleSize(options, minSideLength, maxNumOfPixels);
        int roundedSize;
        if (initialSize <= 8) {
            roundedSize = 1;
            while (roundedSize < initialSize) {
                roundedSize <<= 1;
            }
        } else {
            roundedSize = (initialSize + 7) / 8 * 8;
        }
        return roundedSize;
    }

    private static int computeInitialSampleSize(BitmapFactory.Options options, int minSideLength, int maxNumOfPixels) {
        double w = options.outWidth;
        double h = options.outHeight;
        int lowerBound = (maxNumOfPixels == -1) ? 1 : (int) Math.ceil(Math.sqrt(w * h / maxNumOfPixels));
        int upperBound = (minSideLength == -1) ? 128 :(int) Math.min(Math.floor(w / minSideLength), Math.floor(h / minSideLength));
        if (upperBound < lowerBound) {
            // return the larger one when there is no overlapping zone.
            return lowerBound;
        }
        if ((maxNumOfPixels == -1) && (minSideLength == -1)) {
            return 1;
        } else if (minSideLength == -1) {
            return lowerBound;
        } else {
            return upperBound;
        }
    }

	/**
	 　　* 将bitmap转换成base64字符串
	 　　*
	 　　* @param bitmap
	 　　* @return base64 字符串
	 　　*/
	public static String bitmaptoString(Bitmap bitmap, int bitmapQuality) {
// 将Bitmap转换成字符串
		String string = null;
		ByteArrayOutputStream bStream = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.PNG, bitmapQuality, bStream);
		byte[] bytes = bStream.toByteArray();
		string = Base64.encodeToString(bytes, Base64.DEFAULT);
		return string;
	}
}