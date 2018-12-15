package com.kongtiaoapp.xxhj.activites;

import android.Manifest;
import android.app.DialogFragment;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.adapter.GuideViewpagerAdapter;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.presenter.MyGuidePresenter;
import com.kongtiaoapp.xxhj.mvp.view.MyGuideView;
import com.kongtiaoapp.xxhj.utils.BaseTools;
import com.kongtiaoapp.xxhj.utils.HintDialogFragment;

import java.util.ArrayList;
import java.util.List;


/**
 * 引导页
 *
 * @author feiyue
 */
public class MyGuideActivity extends BaseActivity<MyGuidePresenter, MyGuideView> implements OnClickListener, HintDialogFragment.DialogFragmentCallback, MyGuideView {

    private ViewPager viewpager;
    private GuideViewpagerAdapter adapter;
    private List<Integer> imgList = new ArrayList<Integer>();
    private LinearLayout dotlayout;
    private TextView txt_first;
    private final int CAMERAS = 1000;

    @Override
    protected int initContentView() {
        return R.layout.activity_myguide;
    }

    @Override
    protected void initView() {
        viewpager = (ViewPager) findViewById(R.id.viewpager_spalash);
        dotlayout = (LinearLayout) findViewById(R.id.ll_dotlayout);
        txt_first = ((TextView) findViewById(R.id.txt_first));
        //管理相机权限
        manager_permission();
    }

    private void manager_permission() {
        if (PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                && PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                && PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                && PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                && PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)) {
            //has permission, do operation directly  Manifest.permission.CALL_PHONE  电话权限
            txt_first.setVisibility(View.GONE);
            setData();
        } else {
            Log.i(TAG, "申请权限");
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.CAMERA) || ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE) || ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_PHONE_STATE)
                    || ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    || ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.CALL_PHONE)) {
                DialogFragment newFragment = HintDialogFragment.newInstance(R.string.hint_description_title,
                        R.string.hint_description_why_we_need_the_permission,
                        CAMERAS);
                newFragment.show(getFragmentManager(), HintDialogFragment.class.getSimpleName());
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CALL_PHONE},
                        CAMERAS);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case CAMERAS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED
                        && grantResults[2] == PackageManager.PERMISSION_GRANTED && grantResults[3] == PackageManager.PERMISSION_GRANTED
                        && grantResults[4] == PackageManager.PERMISSION_GRANTED) {
                    txt_first.setVisibility(View.GONE);
                    setData();
                } else {
                    //拒绝进行相关的提醒
                    manager_permission();
                }
                return;
            }
        }
    }

    @Override
    protected void initListener() {

    }


    protected void initData() {

    }

    @Override
    protected MyGuidePresenter getPresenter() {
        return new MyGuidePresenter();
    }

    private void setData() {
        imgList.add(R.mipmap.guid_1);
//        imgList.add(R.drawable.guid_2);
//        imgList.add(R.drawable.guid_3);
//        imgList.add(R.drawable.guid_4);
        adapter = new GuideViewpagerAdapter(this, imgList);
        viewpager.setAdapter(adapter);
        viewpager.setCurrentItem(0);
        initDot();
        updateDot();
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                updateDot();
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub

            }
        });

    }

    private void initDot() {
        for (int i = 0; i < imgList.size(); i++) {
            ImageView view = new ImageView(this);
            LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT);
            params.rightMargin = BaseTools.dip2px(this, 7);
            params.leftMargin = BaseTools.dip2px(this, 7);
            view.setLayoutParams(params);
            view.setImageResource(R.mipmap.running_dot_normal);
            dotlayout.addView(view);
        }
    }

    private void updateDot() {
        int currentPage = viewpager.getCurrentItem();
        // 更新点
        // 遍历所有的点，当点的位置和currentPage相当的时候，则设置为可用，否则是禁用
        for (int i = 0; i < dotlayout.getChildCount(); i++) {
            ImageView imageView = (ImageView) dotlayout.getChildAt(i);
            if (i == currentPage)
                imageView.setImageResource(R.mipmap.running_dot_selector);
            else
                imageView.setImageResource(R.mipmap.running_dot_normal);
        }
    }


    @Override
    public void onClick(View v) {
    }

    @Override
    public void doPositiveClick(int requestCode) {
        if (CAMERAS == requestCode) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CALL_PHONE},
                    CAMERAS);
            Log.i(TAG, "fffffffffff========77777777777777777");
        }
    }

    @Override
    public void doNegativeClick(int requestCode) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (imgList != null) {
            imgList.clear();
            imgList = null;
        }
    }

    @Override
    public void setText(Object data) {

    }
}
