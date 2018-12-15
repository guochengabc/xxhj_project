package com.kongtiaoapp.xxhj.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.InspectionBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by G_XXHJ on 2018/4/29.
 */

public class InspectionAdapter extends BaseAdapter {
    List<InspectionBean.ResobjBean.InspBean> list;
    private Context context;
    private Map<Integer, Integer> mapClick = new HashMap<>();
    public InspectionAdapter(List<InspectionBean.ResobjBean.InspBean> list, Context context) {
        this.list = list;
        this.context = context;
        for (int i = 0; i < list.size(); i++) {
            mapClick.put(i, 0);
        }

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolders holder = null;
        if (convertView == null) {
//            convertView =  inflater.inflate(R.layout.whatever, parent, false);
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.item_inspection, parent, false);
            holder = new ViewHolders(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolders) convertView.getTag();
        }
        holder.txt_inspect.setText(list.get(position).getValue());
        ViewHolders finalHolder = holder;
        holder.line_switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //对2求余=1 为off    0为on
                if (mapClick!=null){
                    int integer = mapClick.get(position);
                    int isnorMal = ++integer;

                    mapClick.remove(position);
                    mapClick.put(position, isnorMal);
                    if (isnorMal%2==0){
                        finalHolder.line_switch.setBackgroundResource(R.drawable.shape_circle_open);
                        finalHolder.img_switch_on.setVisibility(View.VISIBLE);
                        finalHolder.img_switch_off.setVisibility(View.GONE);
                        finalHolder.txt_switch.setText("正常");
                    }else if (isnorMal%2==1){
                        finalHolder.line_switch.setBackgroundResource(R.drawable.shape_circle_off);
                        finalHolder.img_switch_on.setVisibility(View.GONE);
                        finalHolder.img_switch_off.setVisibility(View.VISIBLE);
                        finalHolder.txt_switch.setText("异常");
                    }
                }

            }
        });
        if (mapClick.get(position)%2==0){
            finalHolder.line_switch.setBackgroundResource(R.drawable.shape_circle_open);
            finalHolder.img_switch_on.setVisibility(View.VISIBLE);
            finalHolder.img_switch_off.setVisibility(View.GONE);
            finalHolder.txt_switch.setText("正常");

        }
        if (mapClick.get(position)%2==1){
            finalHolder.line_switch.setBackgroundResource(R.drawable.shape_circle_off);
            finalHolder.img_switch_on.setVisibility(View.GONE);
            finalHolder.img_switch_off.setVisibility(View.VISIBLE);
            finalHolder.txt_switch.setText("异常");

        }
        return convertView;
    }
    public List<Map<String,String>> getListInsp(){

        List<Map<String,String>> data = new ArrayList<>();
        data.clear();

        for (int i = 0; i < mapClick.size(); i++) {
            Map<String,String> map=new LinkedHashMap<>();
            if (mapClick.get(i)%2==0){
             map.put("state","ON");
            }else if (mapClick.get(i)%2==1){
                map.put("state","OFF");
            }
            data.add(map);
        }

        return  data;
    }

    public class ViewHolders {
        @BindView(R.id.txt_inspect)
        TextView txt_inspect;
        @BindView(R.id.line_switch)
        LinearLayout line_switch;
        @BindView(R.id.img_switch_off)
        ImageView img_switch_off;
        @BindView(R.id.txt_switch)
        TextView txt_switch;
        @BindView(R.id.img_switch_on)
        ImageView img_switch_on;

        ViewHolders(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
