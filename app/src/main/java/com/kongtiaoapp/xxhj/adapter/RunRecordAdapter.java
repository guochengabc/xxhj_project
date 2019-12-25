package com.kongtiaoapp.xxhj.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RunRecordAdapter extends BaseAdapter {
    private List<String> list;
    private Context context;
;

    public RunRecordAdapter(Context context, List<String> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
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
        ViewHolder holder = null;
        if (convertView == null) {
//            convertView =  inflater.inflate(R.layout.whatever, parent, false);
            convertView = LayoutInflater.from(App.application).inflate(
                    R.layout.item_run_record, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        String content = list.get(position) == null ? "未知" : list.get(position);

        if (position==0||position==1){
            holder.line_down.setVisibility(View.GONE);
            holder.line_time.setVisibility(View.VISIBLE);
            if (content.equals("")){
                holder.line_time.setVisibility(View.GONE);
            }
            holder.tv_time.setText(content);
        }else{
            holder.line_time.setVisibility(View.GONE);
            holder.line_down.setVisibility(View.VISIBLE);
            holder.iv_commit.setImageResource(content.contains("开机") ? R.mipmap.openj : R.mipmap.closej);
            holder.tv_runRecord.setText(content);
        }

        return convertView;
    }

    public void setList(List<String> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    static class ViewHolder {
        @BindView(R.id.line_time)
        LinearLayout line_time;
        @BindView(R.id.tv_time)
        TextView tv_time;
        @BindView(R.id.tv_runRecord)
        TextView tv_runRecord;
        @BindView(R.id.iv_commit)
        ImageView iv_commit;
        @BindView(R.id.tv_stastic)
        TextView tv_stastic;
@BindView(R.id.line_down)
        LinearLayout line_down;
        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
