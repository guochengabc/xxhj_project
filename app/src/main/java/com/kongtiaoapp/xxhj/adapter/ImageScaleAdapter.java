package com.kongtiaoapp.xxhj.adapter;


import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.activites.ImageScaleActivity;
import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.utils.LogUtil;

import java.util.List;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;


public class ImageScaleAdapter extends PagerAdapter {
	private List<String> urls;
	private Context context;

	public ImageScaleAdapter(Context context, List<String> urls) {
		super();
		this.urls = urls;
		this.context = context;

	}

	@Override
	public int getCount() {

		return urls.size();
	}

	@Override
	public Object instantiateItem(ViewGroup container, final int position) {
		PhotoView photoView = new PhotoView(context);
		//thumbnail 代表的缩略图，是原图的0.1倍
		Log.i("ffffffffff","==========url==88=="+ConstantValue.URL +  urls.get(position));
		Glide.with(context).load(ConstantValue.URL +  urls.get(position)).placeholder(R.mipmap.default_head).crossFade().into(photoView);
//		Glide.with(context).load(ConstantValue.URL + urls.get(position)).placeholder(R.mipmap.default_head).crossFade().into(photoView);
		container.addView(photoView);
		photoView.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
			@Override
			public void onPhotoTap(View view, float x, float y) {
				ImageScaleActivity.instance.finish();
//				ImageScaleActivity.instance.overridePendingTransition(0,
//						R.anim.activity_translate_out);
				LogUtil.e(this,"图片点击了"+"x===="+ x + "y====="+ y  );

			}
		});
		return photoView;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {

		return arg0 == arg1;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {

		container.removeView((View) object);
	}

}