package com.kongtiaoapp.xxhj.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.kongtiaoapp.xxhj.R;

import java.util.List;

/**
 * Created by sks on 2016/3/4.
 */
public class MyPageFragmentAdapter extends PagerAdapter {
    private List<Integer> list;
    private Context context;
    private ImageView imageView;
    public MyPageFragmentAdapter(List<Integer> list, Context context) {
        this.list=list;
        this.context=context;




    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        // TODO Auto-generated method stub
        View layout=View.inflate(context, R.layout.viewpager_item,null);
        imageView= (ImageView) layout.findViewById(R.id.img_page);
        imageView.setImageResource(list.get(position % list.size()));
        container.removeView(imageView);
        container.addView(imageView);

/*
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (NetworkUtils.checkNetwork(context)){

                        Intent intent=new Intent(context,NewsDetailActivity.class);
                        intent.putExtra("news_id",list.get(position % list.size()).getId());
                        intent.putExtra("viewpager","yes");//是否veiwpager传过去的
                        context.startActivity(intent);
                    }else {
                        Toast.makeText(context, "联网才可以进入详情页", Toast.LENGTH_SHORT).show();
                    }


                }
            });*/

        return imageView;
    }
    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        // TODO Auto-generated method stub
        return arg0==arg1;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // TODO Auto-generated method stub
        container.removeView((View) object);
    }
}

