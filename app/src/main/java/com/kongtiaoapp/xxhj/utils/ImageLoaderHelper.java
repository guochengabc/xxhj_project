package com.kongtiaoapp.xxhj.utils;

import android.content.Context;

import com.kongtiaoapp.xxhj.R;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

public class ImageLoaderHelper {

	public static void initImageLoader(Context context) {

		// This configuration tuning is custom. You can tune every option, you
		// may tune some of them,
		// or you can create default configuration by
		// ImageLoaderConfiguration.createDefault(this);
		// method.
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				context)
				.threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.discCacheFileNameGenerator(new Md5FileNameGenerator())
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				.writeDebugLogs()
				// Remove for release app
				.memoryCache(new WeakMemoryCache()).threadPoolSize(5)
				.defaultDisplayImageOptions(initOption(R.mipmap.ic_launcher))
				.build();
		// Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(config);
	}

	public static DisplayImageOptions initOption(int sourceid) {
		return new DisplayImageOptions.Builder().showImageForEmptyUri(sourceid)
				.showImageOnFail(sourceid).showImageOnLoading(sourceid)
				.cacheInMemory(true).cacheOnDisc(true).considerExifParams(true)
				.build();
	}

	public static DisplayImageOptions initCilcleOption(int sourceid) {
		return new DisplayImageOptions.Builder().showImageForEmptyUri(sourceid)
				.showImageOnFail(sourceid).cacheInMemory(true)
				.cacheOnDisc(true).considerExifParams(true)
				.displayer(new RoundedBitmapDisplayer(360)).build();
	}
}
