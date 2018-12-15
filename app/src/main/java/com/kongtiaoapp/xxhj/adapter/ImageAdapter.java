package com.kongtiaoapp.xxhj.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.ui.ImagePicker.CropImageView;
import com.kongtiaoapp.xxhj.ui.ImagePicker.GlideImageLoader;
import com.kongtiaoapp.xxhj.ui.ImagePicker.ImageItem;
import com.kongtiaoapp.xxhj.ui.ImagePicker.ImagePicker;
import com.kongtiaoapp.xxhj.utils.Base64Utils;

import java.util.List;

/**
 * Created by xxhj_g on 2017/7/29.
 */

public class ImageAdapter extends ArrayAdapter<ImageItem> {
    private List<ImageItem> list;
    private Context context;
    public ImageAdapter(Context context, int resource,
                        List<ImageItem> objects) {
        super(context, resource, objects);
        this.list = objects;
        this.context=context;
        initImagePicker();
    }

    @Override
    public int getCount() {
        if (list == null || list.size() == 0) {
            return 1;
        }
        if (list.size() >= 9) {
            return 9;
        }
        return list.size() + 1;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == list.size()) {
            return 0;
        } else {
            return 1;
        }
    }
    private void initImagePicker() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        //		imagePicker.setMultiMode(true);//设置是否为多选模式
        imagePicker.setImageLoader(new GlideImageLoader()); // 设置图片加载器
        imagePicker.setShowCamera(true); // 显示拍照按钮
        imagePicker.setCrop(false); // 允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true); // 是否按矩形区域保存
        imagePicker.setSelectLimit(9); // 选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE); // 裁剪框的形状
        imagePicker.setFocusWidth(400); // 裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(440); // 裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(800); // 保存文件的宽度。单位像素
        imagePicker.setOutPutY(800); // 保存文件的高度。单位像素
    }
    @Override
    public View getView(final int position, View convertView,
                        ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_published_grida,
                    parent, false);
            holder = new ViewHolder();
            holder.image = (ImageView) convertView
                    .findViewById(R.id.item_grida_image);
            holder.txt_image= ((TextView) convertView.findViewById(R.id.txt_image));
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //            ImageView item = ViewHolder.get(convertView, R.id.image);
        //            ViewGroup.LayoutParams layoutParams = item.getLayoutParams();
        //            layoutParams.height = item_with * 8 / 11;
        //            layoutParams.width = item_with;
        //            item.setLayoutParams(layoutParams);
        if (getItemViewType(position) == 0) {
            holder.image.setBackgroundResource(R.drawable.selector_image_add);
        } else {
                /*ImagePicker
                        .getInstance()
                        .getImageLoader()
                        .displayImage(PublishPostActivity.this, getItem(position).path, holder.image,
                                0, 0);*/
            holder.txt_image.setVisibility(View.GONE);
            Bitmap imageUtils = Base64Utils.createImageUtils(getItem(position).path);
            holder.image.setImageBitmap(Base64Utils.comp(imageUtils));

        }

        return convertView;
    }

    public class ViewHolder {
        public ImageView image;
        public TextView txt_image;
    }
}
