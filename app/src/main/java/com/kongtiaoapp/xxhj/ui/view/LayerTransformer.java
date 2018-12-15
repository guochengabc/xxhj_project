package com.kongtiaoapp.xxhj.ui.view;

import android.support.v4.view.ViewPager;
import android.view.View;

import com.nineoldandroids.view.ViewHelper;

public class LayerTransformer implements ViewPager.PageTransformer
{
	private static final float MIN_SCALE = 0.75f;

	public void transformPage(View view, float position)
	{
		int pageWidth = view.getWidth();

		if (position < -1)
		{ // [-Infinity,-1)
			// This page is way off-screen to the left.
			// view.setAlpha(0);
			ViewHelper.setAlpha(view, 0);
		} else if (position <= 0)// a页滑动至b页 ； a页从 0.0 -1 ；b页从1 ~ 0.0
		{ // [-1,0]
			// Use the default slide transition when moving to the left page
			// view.setAlpha(1);
			ViewHelper.setAlpha(view, 1);
			// view.setTranslationX(0);
			ViewHelper.setTranslationX(view, 0);
			// view.setScaleX(1);
			ViewHelper.setScaleX(view, 1);
			// view.setScaleY(1);
			ViewHelper.setScaleY(view, 1);

		} else if (position <= 1)
		{
			ViewHelper.setAlpha(view, 1 - position);

			ViewHelper.setTranslationX(view, pageWidth * -position);

			float scaleFactor = MIN_SCALE + (1 - MIN_SCALE) * (1 - position);
			ViewHelper.setScaleX(view, scaleFactor);
			ViewHelper.setScaleY(view, scaleFactor);

		} else
		{
			ViewHelper.setAlpha(view, 0f);
		}
	}
}