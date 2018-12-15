package com.kongtiaoapp.xxhj.prefessionalknowledge.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.bean.EnergyShowDetail;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.presenter.EnergyShowDetailPresenter;
import com.kongtiaoapp.xxhj.mvp.view.EnergyShowDetailView;
import com.kongtiaoapp.xxhj.utils.TimeUtils;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;
import com.squareup.picasso.Picasso;

import net.robinx.lib.blur.StackBlur;
import net.robinx.lib.blur.utils.BlurUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Luoye on 2016-10-8.
 * 说明:节能展会详情
 */
public class EnergyShowDetailActivity extends BaseActivity<EnergyShowDetailPresenter, EnergyShowDetailView> implements EnergyShowDetailView {

    @BindView(R.id.iv_top_bg)//节能展会详情
    ImageView ivTopBg;
    @BindView(R.id.iv_bottom_bg)
    ImageView ivBottomBg;
    @BindView(R.id.tv_zhuBanFang)
    TextView tvZhuBanFang;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_zhanhui_title)
    TextView tvZhanhuiTitle;
    @BindView(R.id.tv_zhanhui_content)
    TextView tvZhanhuiContent;
    @BindView(R.id.iv_shoucang)
    ImageView ivShoucang;
    @BindView(R.id.iv_share)
    ImageView ivShare;
    private String eid;
    private boolean isFavorite;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    protected int initContentView() {
        eid = getIntent().getStringExtra("eid");
        return R.layout.activity_energy_show_detail;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        presenter.onResume(this, eid);
    }

    @Override
    protected EnergyShowDetailPresenter getPresenter() {
        return new EnergyShowDetailPresenter();
    }

    @OnClick({R.id.iv_shoucang, R.id.iv_share})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_shoucang:
                favorite();
                break;
            case R.id.iv_share:
                break;
        }
    }

    private void favorite() {
        List list = new ArrayList<>();
        list.add(isFavorite);
        list.add(eid);
        presenter.favorite(this, list);
    }

    /**
     * 根据图片的url路径获得Bitmap对象
     *
     * @param url
     * @return
     */
    private Bitmap returnBitmap(String url) {
        URL fileUrl = null;
        Bitmap bitmap = null;

        try {
            fileUrl = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            HttpURLConnection conn = (HttpURLConnection) fileUrl
                    .openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;

    }

    @Override
    public void setList(Object response) {
        EnergyShowDetail data = (EnergyShowDetail) response;
        final EnergyShowDetail.DetailBean detail = data.getResobj();
        if (detail != null) {
            ToastUtils.showToast(this, getString(R.string.enegry_data_succeed));
            Picasso.with(mContext).load(ConstantValue.URL + detail.getFaceUrl()).placeholder(R.mipmap.default_head).into(ivTopBg);

            tvZhuBanFang.setText("主办单位:" + detail.getHost());//主办单位
            if (!TextUtils.isEmpty(detail.getTime())) {
                tvTime.setText("时间:" + TimeUtils.getDateToString(detail.getTime()));//举办时间
            }
            tvAddress.setText("地址:" + detail.getPlace());
            tvZhanhuiTitle.setText(detail.getAbstract());
            tvZhanhuiContent.setText(detail.getExhibitionInfo());

            if ("1".equals(detail.getIsCollection())) {
                ivShoucang.setSelected(true);
            } else {
                ivShoucang.setSelected(false);
            }
            isFavorite = "1".equals(detail.getIsCollection());

            Glide.with(this).load(ConstantValue.URL + detail.getFaceUrl()).asBitmap().into(new SimpleTarget<Bitmap>() {
                @Override
                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                    Bitmap compressedBgBitmap = BlurUtils.compressBitmap(resource, 8);
                    Bitmap blurBgBitmap = StackBlur.blurNativelyPixels(compressedBgBitmap, 8, false);
                    ivBottomBg.setImageBitmap(blurBgBitmap);
                }
            }); //方法中设置asBitmap可以设置回调类型
        }
    }

    @Override
    public void favorites() {
        if (isFavorite) {
            isFavorite = false;
//                        post.setIsCollection("0");
            ivShoucang.setSelected(false);
            ToastUtils.showToast(this, getString(R.string.enegry_cancel_favorite));
        } else {
            isFavorite = true;
//                        post.setIsCollection("1");
            ivShoucang.setSelected(true);
            ToastUtils.showToast(this, getString(R.string.enegry_favorite_succeed));
        }
//                    showToast(TAG,"收藏成功");
    }

    @Override
    public void setText(Object text) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
