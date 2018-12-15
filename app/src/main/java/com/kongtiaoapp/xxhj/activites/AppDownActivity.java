package com.kongtiaoapp.xxhj.activites;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.view.View;
import android.widget.ImageView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.bean.VersionBean;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.presenter.AppDownPresenter;
import com.kongtiaoapp.xxhj.mvp.view.AppDownView;
import com.mylhyl.zxing.scanner.encode.QREncode;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by xxhj_g on 2017/5/23.    二维码扫描
 */
public class AppDownActivity extends BaseActivity<AppDownPresenter, AppDownView> implements AppDownView {
    @BindView(R.id.img_code)
    ImageView img_code;

    @Override
    protected int initContentView() {
        return R.layout.activity_app_dowm;
    }

    @Override
    protected void initView() {
        presenter.onResume(this);//获取下载apk链接
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected AppDownPresenter getPresenter() {
        return new AppDownPresenter();
    }

    /*返回上一界面*/
    @OnClick(R.id.iv_back)
    public void onClick(View view) {
        finish();
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public void setText(Object data) {
        VersionBean bean = (VersionBean) data;
        // 二维码中间图标
        final Bitmap centerImage = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
// 生成的二维码图案
//文本类型
        Bitmap bitmap = new QREncode.Builder(this)
                .setColor(getResources().getColor(R.color.theme_color))//二维码颜色
                //.setParsedResultType(ParsedResultType.TEXT)//默认是TEXT类型
                .setContents(ConstantValue.APP_URL + bean.getResobj().getAppUrl())//二维码内容
                .setLogoBitmap(centerImage)//二维码中间logo
                .build().encodeAsBitmap();
        img_code.setImageBitmap(bitmap);
    }
}
