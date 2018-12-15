package com.kongtiaoapp.xxhj.ui.NineGridView;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.afinal.ConstantValue;

import java.util.List;

/**
 * Created by xing on 11/15/15.
 */
public class NetworkImageAdapter extends DefaultAdapter<String> {

    public NetworkImageAdapter(Context context, List<String> t) {
        super(context, t);
    }

    @Override
    public View getView(int positon, View recycleView) {
        String url = getItem(positon) ;
        ImageView imageView ;

        if (recycleView == null) {
            imageView = generialDefaultImageView() ;
        } else {
            imageView = (ImageView) recycleView;
        }
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//        imageLoader.displayImage(url, imageView, initOption);
        Glide.with(context).load(ConstantValue.URL + url).placeholder(R.mipmap.default_head).crossFade().into(imageView);
        Log.i("ffffffffff","=====NetworkImageAdapter===="+ConstantValue.URL + url);
       // Picasso.with(context).load(ConstantValue.URL + url).placeholder(R.mipmap.default_head).into(imageView);
    /*    Bitmap bitmap = Base64Utils.createImageUtils(ConstantValue.URL + url);
        imageView.setImageBitmap(bitmap);*/
        return imageView;
    }
}
