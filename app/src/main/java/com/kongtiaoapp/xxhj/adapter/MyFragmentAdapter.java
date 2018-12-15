package com.kongtiaoapp.xxhj.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by xxhj_g on 2016/11/14.
 */
public class MyFragmentAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> list;

    public MyFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    public MyFragmentAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.list = list;
    }

    public void setList(List<Fragment> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

}
