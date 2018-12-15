package com.kongtiaoapp.xxhj.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PagerSlidingAdapter extends FragmentPagerAdapter{
	private String[] tabs ;
	private Fragment[] fragments;
	public PagerSlidingAdapter(FragmentManager fm,String[] strings,Fragment[] fragments) {
		super(fm);
		this.tabs = strings;
		this.fragments = fragments;

	}

	/**
	 * 返回每页对应的fragment的对象
	 */
	@Override
	public Fragment getItem(int position) {
		return  fragments[position];
	}
	
	/**
	 * 返回有多少页
	 */
	@Override
	public int getCount() {
		return tabs.length;
	}

	/**
	 * 返回每页对应的标题
	 */
	@Override
	public CharSequence getPageTitle(int position) {
		return tabs[position];
	}
	
//	/**
//	 * 根据不同的位置返回不同的图片
//	 */
//	@Override
//	public int getPageIconResId(int position) {
//		return R.drawable.ic_download;
//	}
}
