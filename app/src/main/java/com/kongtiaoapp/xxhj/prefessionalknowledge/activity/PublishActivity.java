package com.kongtiaoapp.xxhj.prefessionalknowledge.activity;

import android.Manifest;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.afinal.HttpMethod;
import com.kongtiaoapp.xxhj.afinal.ReceiverAction;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.presenter.PublishPresenter;
import com.kongtiaoapp.xxhj.mvp.view.PublishView;
import com.kongtiaoapp.xxhj.ui.ImagePicker.CropImageView;
import com.kongtiaoapp.xxhj.ui.ImagePicker.GlideImageLoader;
import com.kongtiaoapp.xxhj.ui.ImagePicker.ImageGridActivity;
import com.kongtiaoapp.xxhj.ui.ImagePicker.ImageItem;
import com.kongtiaoapp.xxhj.ui.ImagePicker.ImagePicker;
import com.kongtiaoapp.xxhj.ui.ImagePicker.ImagePreviewDelActivity;
import com.kongtiaoapp.xxhj.utils.Base64Utils;
import com.kongtiaoapp.xxhj.utils.BaseTools;
import com.kongtiaoapp.xxhj.utils.HintDialogFragment;
import com.kongtiaoapp.xxhj.utils.ImageUtils;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luoye on 2016-6-20.
 * 发布动态页面
 */
public class PublishActivity extends BaseActivity<PublishPresenter, PublishView> implements View.OnClickListener, PublishView, AdapterView.OnItemClickListener, HintDialogFragment.DialogFragmentCallback {

    public static final int IMAGE_ITEM_ADD = -1;
    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;

    private ImageAdapter adapter;
    private ArrayList<ImageItem> selImageList = new ArrayList<>(); // 当前选择的所有图片
    private int maxImgCount = 9; // 允许选择图片最大数

    private GridView gridview;
    private TextView tv_publish;

    private String content;
    private EditText et_content;
    private String imageUrls;
    private StringBuffer sb;
    private List list;
    private int item_with;


    @Override
    protected int initContentView() {
        return R.layout.activity_publish;
    }

    @Override
    protected void initView() {
        //   manager_permission();
        enter_paint();


    }

    private void initImagePicker() {
        ImagePicker imagePicker = ImagePicker.getInstance();
//		imagePicker.setMultiMode(true);//设置是否为多选模式
        imagePicker.setImageLoader(new GlideImageLoader()); // 设置图片加载器
        imagePicker.setShowCamera(true); // 显示拍照按钮
        imagePicker.setCrop(false); // 允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true); // 是否按矩形区域保存
        imagePicker.setSelectLimit(maxImgCount); // 选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE); // 裁剪框的形状
        imagePicker.setFocusWidth(400); // 裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(400); // 裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000); // 保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000); // 保存文件的高度。单位像素
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected PublishPresenter getPresenter() {
        return new PublishPresenter();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            // 添加图片返回
            if (data != null && requestCode == REQUEST_CODE_SELECT) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data
                        .getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                selImageList.addAll(images);
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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_publish:
                showProgressDialog();
                content = et_content.getText().toString();
                list = getImage2Base64();
                send();
                break;

            default:
                break;
        }
    }

    private void send() {
        try {

            JSONObject result = new JSONObject();
            result.put("uid", App.sp.getUid());
            result.put("msg", content);
            JSONArray resObj = new JSONArray(list);
            result.put("img", resObj);
            String string = "{" + "\"" + "method" + "\"" + ":" + "\"" + HttpMethod.PUBLISHESMESSAGE + "\"" + "," + "\"" + "reqobj" + "\"" + ":" + result.toString() + "}";
            presenter.send(this, string);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    private List getImage2Base64() {
        list = new ArrayList();
        if (selImageList.size() > 0) {
            for (int i = 0; i < selImageList.size(); i++) {

                try {
                    String str = ImageUtils.compress(selImageList.get(i).path, PublishActivity.this);
                    String imagePath = Base64Utils.encodeBase64File(str);


//                    String imagePath = Base64Utils.encodeBase64File(selImageList.get(i).path);
                    list.add(imagePath);
                } catch (Exception e) {
                    e.printStackTrace();
                    dismissProgressDialog();
                }
            }
            return list;
        } else {
            return null;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position == parent.getAdapter().getCount() - 1 && position != 8) {
            // 打开选择,本次允许选择的数量
            manager_permission();

        } else {
            ToastUtils.showToast(this,selImageList.get(position).path);
            Intent intentPreview = new Intent(this,
                    ImagePreviewDelActivity.class);
            intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS,
                    (ArrayList<ImageItem>) selImageList);
            intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION,
                    position);
            startActivityForResult(intentPreview, REQUEST_CODE_PREVIEW);
        }
    }

    private void manager_permission() {
        if (PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) && PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            //has permission, do operation directly
            getPain();//进入图库或者
        } else {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.CAMERA) || ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
                DialogFragment newFragment = HintDialogFragment.newInstance(R.string.hint_description_title,
                        R.string.hint_description_why_we_need_the_permission,
                        1000);
                newFragment.show(getFragmentManager(), HintDialogFragment.class.getSimpleName());
            } else {


                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE},
                        1000);

            }
        }
    }

    private void enter_paint() {
        initImagePicker();
        item_with = BaseTools.getWindowsWidth(this)
                - BaseTools.dip2px(this, 15 * 2 + 5 * 2);
        item_with = item_with / 3;
        tv_publish = (TextView) findViewById(R.id.tv_publish);
        tv_publish.setOnClickListener(this);
        et_content = (EditText) findViewById(R.id.et_content);
        gridview = (GridView) findViewById(R.id.gridview);
        gridview.setSelector(new ColorDrawable(Color.TRANSPARENT));
        selImageList = new ArrayList<>();
        adapter = new ImageAdapter(this, 0, selImageList);
        gridview.setAdapter(adapter);
        gridview.setOnItemClickListener(this);
    }

    private void getPain() {
        ImagePicker.getInstance().setSelectLimit(
                maxImgCount - selImageList.size());
        Intent intent = new Intent(this, ImageGridActivity.class);
        startActivityForResult(intent, REQUEST_CODE_SELECT);
    }


    @Override
    public void doPositiveClick(int requestCode) {
        if (1000 == requestCode) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE},
                    1000);
        }
    }

    @Override
    public void doNegativeClick(int requestCode) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1000: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {

                    getPain();

                } else {
                    //拒绝进行相关的提醒
                }
                return;
            }
        }
    }

    class ImageAdapter extends ArrayAdapter<ImageItem> {

        private List<ImageItem> list;
        public ImageAdapter(Context context, int resource,
                            List<ImageItem> objects) {
            super(context, resource, objects);
            this.list = objects;
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
            // TODO Auto-generated method stub
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

        @Override
        public View getView(final int position, View convertView,
                            ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_published_grida,
                        parent, false);
                holder = new ViewHolder();
                holder.image = (ImageView) convertView
                        .findViewById(R.id.item_grida_image);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            if (getItemViewType(position) == 0) {
                holder.image.setBackgroundResource(R.drawable.selector_image_add);
            } else {
                ImagePicker
                        .getInstance()
                        .getImageLoader()
                        .displayImage(PublishActivity.this, getItem(position).path, holder.image,
                                0, 0);
            }

            return convertView;
        }

        public class ViewHolder {


            public ImageView image;
        }
    }
    @Override
    public void setText(Object text) {
        ToastUtils.showToast(this, getString(R.string.enegry_publish_succeesd));
        Intent mIneten = new Intent();
        mIneten.setAction(ReceiverAction.MOMENTS_FRESH);
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
