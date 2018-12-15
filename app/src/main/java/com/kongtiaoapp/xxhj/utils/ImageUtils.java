package com.kongtiaoapp.xxhj.utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Luoye on 2016-9-20.
 * 说明:图片压缩
 */
public class ImageUtils {
    private static final int MAX_SIZE = 100 * 1024;

    public static String compress(String srcPath, Activity context) {
        String outpath;
        DisplayMetrics dm = new DisplayMetrics();
//	 outpath = getDiskCacheDirPaht(context, "icon");
        File out = new File(context.getExternalFilesDir("image"),getTiem() + "icon.jpg");
        try {
            out.createNewFile();
        } catch (IOException e1) {
// TODO Auto-generated catch block
            e1.printStackTrace();
        }
//	 File file = new File(outpath);
        context.getWindowManager().getDefaultDisplay().getMetrics(dm);
        float hh = dm.heightPixels;
        float ww = dm.widthPixels;
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(srcPath, opts);
        opts.inJustDecodeBounds = false;
        int w = opts.outWidth;
        int h = opts.outHeight;
        int size = 0;
        if (w <= ww && h <= hh) {
            size = 1;
        } else {
            double scale = w >= h ? w / ww : h / hh;
            double log = Math.log(scale) / Math.log(2);
            double logCeil = Math.ceil(log);
            size = (int) Math.pow(2, logCeil);
        }
        opts.inSampleSize = size;
        bitmap = BitmapFactory.decodeFile(srcPath, opts);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int quality = 100;
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);
        System.out.println(baos.toByteArray().length);
        while (baos.toByteArray().length > MAX_SIZE) {
            baos.reset();
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);
            quality -= 20;
            System.out.println(baos.toByteArray().length);
        }
        try {
            if (!out.exists()) {
                out.mkdir();
            }
            baos.writeTo(new FileOutputStream(out));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                baos.flush();
                baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return out.getPath();
    }

    private static String getTiem(){
        java.util.Date currentdate = new java.util.Date();//当前时间
        return String.valueOf(System.currentTimeMillis());
    }
}
