package com.kongtiaoapp.xxhj.prefessionalknowledge.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.adapter.ImageAdapter;
import com.kongtiaoapp.xxhj.afinal.ReceiverAction;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.presenter.PublishPostPresenter;
import com.kongtiaoapp.xxhj.mvp.view.PublishPostView;
import com.kongtiaoapp.xxhj.ui.ImagePicker.ImageGridActivity;
import com.kongtiaoapp.xxhj.ui.ImagePicker.ImageItem;
import com.kongtiaoapp.xxhj.ui.ImagePicker.ImagePicker;
import com.kongtiaoapp.xxhj.ui.ImagePicker.ImagePreviewDelActivity;
import com.kongtiaoapp.xxhj.ui.view.NoScrollGridView;
import com.kongtiaoapp.xxhj.utils.Base64Utils;
import com.kongtiaoapp.xxhj.utils.BaseTools;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Luoye on 2016-9-13.
 * 说明:发帖页面
 */
public class PublishPostActivity extends BaseActivity<PublishPostPresenter, PublishPostView> implements AdapterView.OnItemClickListener, PublishPostView {
    @BindView(R.id.tv_publish)
    TextView tvPublish;
    @BindView(R.id.et_title)
    EditText etTitle;
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.gridview)
    NoScrollGridView gridview;

    public static final int IMAGE_ITEM_ADD = -1;
    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;

    private ImageAdapter adapter;
    private ArrayList<ImageItem> selImageList = new ArrayList<>(); // 当前选择的所有图片
    private int maxImgCount = 9; // 允许选择图片最大数
    private String content;
    private EditText et_content;
    private String imageUrls;
    private StringBuffer sb;
    private List list = new ArrayList();
    private int item_with;
    private String title;


    @Override
    protected int initContentView() {
        return R.layout.activity_publish_post;
    }

    @Override
    protected void initView() {
        item_with = BaseTools.getWindowsWidth(this)
                - BaseTools.dip2px(this, 15 * 2 + 5 * 2);
        item_with = item_with / 3;
        et_content = (EditText) findViewById(R.id.et_content);
        gridview.setSelector(new ColorDrawable(Color.TRANSPARENT));
        adapter = new ImageAdapter(this, 0, selImageList);
        gridview.setAdapter(adapter);
        gridview.setOnItemClickListener(this);
    }


    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected PublishPostPresenter getPresenter() {
        return new PublishPostPresenter();
    }

    @OnClick({R.id.tv_publish, R.id.iv_back})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_publish:
                title = etTitle.getText().toString();
                content = et_content.getText().toString();

                if (!TextUtils.isEmpty(title) && !TextUtils.isEmpty(content)) {
                    send();
                } else if (TextUtils.isEmpty(title)) {
                    ToastUtils.showToast(this, "标题不能为空哦");
                } else if (TextUtils.isEmpty(content)) {
                    ToastUtils.showToast(this, "内容不能为空哦");
                }
                break;
            case R.id.iv_back:
                finish();
                break;
            default:
                break;
        }


    }

    private void send() {
          /*  JSONObject result = new JSONObject();
            result.put("uid", App.sp.getUid());
            result.put("title", title);
            result.put("content", content);
            if (list.size() > 0) {
                JSONArray resObj = new JSONArray(list);
                result.put("img", resObj);
            }
            String string = "{" + "\"" + "method" + "\"" + ":" + "\"" + HttpMethod.PUBLISHDISCUSSTHEME + "\"" + "," + "\"" + "reqobj" + "\"" + ":" + result.toString() + "}";*/
        List<String> list = new ArrayList<>();
        list.add(title);
        list.add(content);
        List<File> listImage = new ArrayList<>();
        for (int i = 0; i < selImageList.size(); i++) {
          //  listImage.add(new File(selImageList.get(i).path));
           // Log.i("fffffffffff", "路径==" + selImageList.get(0).path);
            list.add(selImageList.get(i).path);

        }

       presenter.onResume(this, list);
       /* RequestParams params = new RequestParams(ConstantValue.HTTP_URL);
        params.setMultipart(true);//设置表单传送
        params.setCancelFast(true);//设置可以立即被停止
        Map<String, String> map = new LinkedHashMap<>();
        map.put("content", content);
        params.addBodyParameter("image", new File(String.valueOf(listImage.get(0))), "multipart/form-data");
        params.addBodyParameter("token", App.sp.getToken());
        params.addBodyParameter("para", Encode_params.NoToken_Encodeparms(ParamJson.map2Json_param(HttpMethod.PUBLISHDISCUSSTHEME, map)));

        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });*/
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            // 添加图片返回
            if (data != null && requestCode == REQUEST_CODE_SELECT) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data
                        .getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                selImageList.addAll(images);//回传过来的
                adapter.notifyDataSetChanged();
            }
        } else if (resultCode == ImagePicker.RESULT_CODE_BACK) {
            // 预览图片返回
            if (data != null && requestCode == REQUEST_CODE_PREVIEW) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data
                        .getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
                selImageList.clear();
                selImageList.addAll(images);
                adapter.notifyDataSetChanged();
            }
        }
        // getImage2Base64();
    }

    private List getImage2Base64() {
        if (selImageList.size() > 0) {
            for (int i = 0; i < selImageList.size(); i++) {
                try {
                    String imagePath = Base64Utils.bitmaptoString(Base64Utils.comp(Base64Utils.createImageUtils(selImageList.get(i).path)), 40);
                    Log.i("ffffffffff", "======回传过来的图片======" + imagePath);
                    list.add(imagePath);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return list;
        } else {
            return null;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position == parent.getAdapter().getCount() - 1 && position != 8) {
            // 打开选择,本次允许选择的数量
            ImagePicker.getInstance().setSelectLimit(
                    maxImgCount - selImageList.size());
            Intent intent = new Intent(this, ImageGridActivity.class);
            startActivityForResult(intent, REQUEST_CODE_SELECT);
        } else {
            ToastUtils.showToast(this, selImageList.get(position).path);
            Intent intentPreview = new Intent(this,
                    ImagePreviewDelActivity.class);
            intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS,
                    (ArrayList<ImageItem>) selImageList);
            intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION,
                    position);
            startActivityForResult(intentPreview, REQUEST_CODE_PREVIEW);
        }
    }


    @Override
    public void setText(Object response) {
        ToastUtils.showToast(this, getString(R.string.enegry_publish_succeesd));
        App.sp.setRefresh("yes");
        Intent mIneten = new Intent();
        mIneten.setAction(ReceiverAction.POST_FRESH);
        sendBroadcast(mIneten);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (selImageList != null) {
            selImageList.clear();
            selImageList = null;
        }
    }
}
