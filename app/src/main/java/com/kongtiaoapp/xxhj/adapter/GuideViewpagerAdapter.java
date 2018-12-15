package com.kongtiaoapp.xxhj.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.activites.MyGuideActivity;
import com.kongtiaoapp.xxhj.login_register.activity.LoginActivity;

import java.util.ArrayList;
import java.util.List;


public class GuideViewpagerAdapter extends PagerAdapter {

    private Context context;
    private List<Integer> imgList = new ArrayList<Integer>();

    public GuideViewpagerAdapter(Context context, List<Integer> imgList) {
        this.context = context;
        this.imgList = imgList;
    }

    @Override
    public int getCount() {
        return imgList.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = View.inflate(context, R.layout.item_guide_viewpager, null);
        ImageView img = (ImageView) view
                .findViewById(R.id.img_spalash_background);
        ImageButton btn_start = (ImageButton) view.findViewById(R.id.img_start);
//		ImageLoader.getInstance().displayImage(imgList.get(position), img,
//				ImageLoaderHelper.initOption(R.drawable.default_image));
        img.setImageResource(imgList.get(position));
        if (position == imgList.size() - 1) {
            btn_start.setVisibility(View.VISIBLE);

            Animation animation = AnimationUtils.loadAnimation(// 设置进入按钮的动画
                    context, R.anim.first_time_in_anim);
            btn_start.startAnimation(animation);

            btn_start.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, LoginActivity.class);
                    App.sp.setFirstLogoin(false);
                    context.startActivity(intent);
                    MyGuideActivity activity = (MyGuideActivity) context;
                    activity.finish();
                }
            });
        } else {
            btn_start.setVisibility(View.INVISIBLE);
            btn_start.setOnClickListener(null);
        }
        container.addView(view);
        return view;
    }

}
