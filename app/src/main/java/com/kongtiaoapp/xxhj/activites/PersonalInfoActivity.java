package com.kongtiaoapp.xxhj.activites;

import android.Manifest;
import android.app.DialogFragment;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.bean.UserInfo;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.presenter.PersonInfoPresenter;
import com.kongtiaoapp.xxhj.mvp.view.PersonInfoView;
import com.kongtiaoapp.xxhj.ui.ImagePicker.ImageGridActivity;
import com.kongtiaoapp.xxhj.ui.ImagePicker.ImageItem;
import com.kongtiaoapp.xxhj.ui.ImagePicker.ImagePicker;
import com.kongtiaoapp.xxhj.ui.address.AddressInitTask1;
import com.kongtiaoapp.xxhj.ui.dialog.SlideUpDialog;
import com.kongtiaoapp.xxhj.utils.Base64Utils;
import com.kongtiaoapp.xxhj.utils.GlideCircleTransform;
import com.kongtiaoapp.xxhj.utils.HintDialogFragment;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by ASUS-PC on 2016/6/17.
 * 个人信息页面
 */
public class PersonalInfoActivity extends BaseActivity<PersonInfoPresenter, PersonInfoView> implements PersonInfoView, HintDialogFragment.DialogFragmentCallback {

    @ViewInject(R.id.personalInfo_iv_avatar)
    private ImageView mAvatar;

    @ViewInject(R.id.personalInfo_tv_nickName)
    private TextView mNickName;//昵称

    @ViewInject(R.id.personalInfo_tv_gender)
    private TextView mGender;//性别

    @ViewInject(R.id.personalInfo_tv_city)
    public TextView mCity;//城市

    @ViewInject(R.id.personalInfo_tv_company)
    private TextView mCompany;//工作单位

    @ViewInject(R.id.personalInfo_tv_college)
    private TextView mCollege;//毕业院校

    @ViewInject(R.id.personalInfo_tv_interestNum)
    private TextView mInterestNum;//兴趣爱好字数

    @ViewInject(R.id.personalInfo_et_interestInfo)
    private EditText mInterestInfo;//兴趣爱好内容
    private String gender;
    private String name;
    public String city;
    private String company;
    private String school;
    private String interest;
    private UserInfo.ResobjBean entity;
    private static final int maxImgCount = 1;
    public static final int REQUEST_CODE_SELECT = 101;

    private List<ImageItem> pictures = new ArrayList<ImageItem>();
    private String pictureUrl = "";
    public int CAMERA = 1000;
    public static final int NO_CAMERA = 1000;

    @Event(value = {R.id.iv_back, R.id.personalInfo_tv_save, R.id.personalInfo_lin_avatar,
            R.id.personalInfo_rl_nickName, R.id.personalInfo_rl_gender, R.id.personalInfo_rl_city,
            R.id.personalInfo_rl_company, R.id.personalInfo_rl_college})
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.iv_back://返回按钮
                close_key();
                finish();
                break;
            case R.id.personalInfo_tv_save://保存按钮
//                showToast(TAG, "保存按钮");
                interest = mInterestInfo.getText().toString().trim();
                upDataUserInfo();
                break;
            case R.id.personalInfo_lin_avatar://头像点击栏
//                showToast(TAG, "头像");
                // 打开选择,本次允许选择的数量   什么a
                //管理相机权限      //
                manager_permission();
                break;
            case R.id.personalInfo_rl_nickName://昵称点击栏
//                showToast(TAG, "昵称");
                startActivityForResult(
                        new Intent(mContext, AlterNameActivity.class).putExtra(
                                "name", mNickName.getText().toString()), 100);
                break;
            case R.id.personalInfo_rl_gender://性别点击栏
//                showToast(TAG, "性别");
                showGenderDialog();
                break;
            case R.id.personalInfo_rl_city://城市点击栏
//                showToast(TAG, "城市");
                new AddressInitTask1(this, true, "0").execute("北京", "北京");
                break;
            case R.id.personalInfo_rl_company://工作单位点击栏    先不开放这个接口
/*//                showToast(TAG, "工作单位");
                startActivityForResult(
                        new Intent(mContext, AlterCompanyActivity.class).putExtra(
                                "company", mCompany.getText().toString()), 200);*/
                break;
            case R.id.personalInfo_rl_college://毕业院校点击栏
//                showToast(TAG, "毕业院校");
                startActivityForResult(
                        new Intent(mContext, AlterSchoolActivity.class).putExtra(
                                "school", mCollege.getText().toString()), 300);
                break;
        }
    }

    private void manager_permission() {
        if (PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(PersonalInfoActivity.this, Manifest.permission.CAMERA) && PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(PersonalInfoActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            //has permission, do operation directly
            enter_paint();//进入图库或者
        } else {

            if (ActivityCompat.shouldShowRequestPermissionRationale(PersonalInfoActivity.this,
                    Manifest.permission.CAMERA) || ActivityCompat.shouldShowRequestPermissionRationale(PersonalInfoActivity.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
                DialogFragment newFragment = HintDialogFragment.newInstance(R.string.hint_description_title,
                        R.string.hint_description_why_we_need_the_permission,
                        CAMERA);
                newFragment.show(getFragmentManager(), HintDialogFragment.class.getSimpleName());
            } else {
                ActivityCompat.requestPermissions(PersonalInfoActivity.this,
                        new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE},
                        CAMERA);

            }
        }
    }

    private void enter_paint() {
        //单选模式
        ImagePicker.getInstance(false).setSelectLimit(
                1);
        Intent intent = new Intent(this, ImageGridActivity.class);
        startActivityForResult(intent, REQUEST_CODE_SELECT);


    }

    private void upDataUserInfo() {
        List<String> list = new ArrayList<>();
        list.add(pictureUrl);
        list.add(name);
        list.add(gender);
        list.add(city);
        list.add(school);
        list.add(interest);
        presenter.upPersonInfo(this, list);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

    }


    @Override
    protected int initContentView() {
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        return R.layout.activity_personal_info;
    }

    @Override
    protected void initView() {
        x.view().inject(this);
        entity = (UserInfo.ResobjBean) getIntent().getSerializableExtra("user");

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        mInterestNum.setText(mInterestInfo.getText().toString().length() + "/50");//刚进入页面要把字数显示出来
        mInterestInfo.addTextChangedListener(watcher);
//            Log.e(TAG,sp.getUid());
//            UserInfoEntity entity = AirDb.db.findById(UserInfoEntity.class, App.sp.getUid());
        //设置头像
        if (entity == null) {
            finish();
        }
        if (entity.getAvatarUrl() != null) {
//                mAvatar.setImageBitmap(Base64Utils.getBitmapFor64(entity.getAvatar()));
            Glide.with(mContext).load(ConstantValue.URL + entity.getAvatarUrl()).placeholder(R.mipmap.default_head).transform(new GlideCircleTransform(mContext)).crossFade().into(mAvatar);
//                Glide.with(mContext).load(ConstantValue.URL + entity.getAvatar()).transform(new GlideCircleTransform(mContext)).crossFade().into(mAvatar);
        }
        //设置昵称
        if (entity.getUserName() != null) {
            mNickName.setText(entity.getUserName());
        }
        //设置城市
        if (entity.getCity() != null) {
            mCity.setText(entity.getCity());
        }
        //设置毕业院校
        if (entity.getSchool() != null) {
            mCollege.setText(entity.getSchool());
        }
        //设置兴趣爱好
        if (entity.getInterest() != null) {
            mInterestInfo.setText(entity.getInterest());
        }
        if (!TextUtils.isEmpty(entity.getGender())) {
            mGender.setText(entity.getGender());
        }
        if (!TextUtils.isEmpty(entity.getOrgName())) {
            mCompany.setText(entity.getOrgName());
        }

    }

    @Override
    protected PersonInfoPresenter getPresenter() {
        return new PersonInfoPresenter();
    }

    /**
     * 兴趣爱好判断输入多少字符
     */
    private TextWatcher watcher = new TextWatcher() {
        private CharSequence temp;
        private int editStart;
        private int editEnd;

        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {
            temp = s;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            editStart = mInterestInfo.getSelectionStart();
            editEnd = mInterestInfo.getSelectionEnd();
            mInterestNum.setText(temp.length() + "/50");
            if (temp.length() > 50) {
                ToastUtils.showToast(PersonalInfoActivity.this, getString(R.string.person_over_limit));
                s.delete(editStart - 1, editEnd);
                int tempSelection = editStart;
                mInterestInfo.setText(s);
                mInterestInfo.setSelection(tempSelection);
            }
        }
    };

    /**
     * 显示选择性别对话框
     */
    private void showGenderDialog() {
        final View view = LayoutInflater.from(this).inflate(
                R.layout.dialog_gerenxinxi_sex, null);
        final SlideUpDialog dialog = new SlideUpDialog(this) {
            @Override
            public View initView() {
                return view;
            }
        };
        LinearLayout nan = (LinearLayout) view
                .findViewById(R.id.dialog_genrenxinxi_sex_nan);// 男
        LinearLayout nv = (LinearLayout) view
                .findViewById(R.id.dialog_genrenxinxi_sex_nv);// 女
        Button cancel = (Button) view
                .findViewById(R.id.dialog_gerenxinxi_sex_btn_cancel);// 取消按钮
        nan.setOnClickListener(v -> {
            gender = "男";
            mGender.setText("男");
            dialog.dismissDialog();
        });
        nv.setOnClickListener(v -> {
            gender = "女";
            mGender.setText("女");
            dialog.dismissDialog();
        });
        cancel.setOnClickListener(v -> {
            // 取消对话框
            dialog.cancelDialog();
        });
        dialog.showDialog();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        switch (requestCode) {
            case 100:
                if (resultCode == RESULT_OK) {
                    name = data.getStringExtra("name");
                    mNickName.setText(name);
                }
                break;
            case 200:
                company = data.getStringExtra("company");
                mCompany.setText(company);
                break;
            case 300:
                school = data.getStringExtra("school");
                mCollege.setText(school);
                break;
            case REQUEST_CODE_SELECT:
                if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
                    try {
                        pictures = (ArrayList<ImageItem>) data
                                .getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                        pictureUrl = Base64Utils.encodeBase64File(pictures.get(0).path);
                        Glide.with(this)                             //配置上下文
                                .load(Uri.fromFile(new File(pictures.get(0).path)))      //设置图片路径(fix #8,文件名包含%符号 无法识别和显示)
                                .error(R.mipmap.default_head)           //设置错误图片
                                .placeholder(R.mipmap.default_head)     //设置占位图片
                                .transform(new GlideCircleTransform(mContext))//设置圆形显示
                                .diskCacheStrategy(DiskCacheStrategy.ALL)//缓存全尺寸
                                .into(mAvatar);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                break;
            default:
                break;
        }
    }



    @Override
    public void doPositiveClick(int requestCode) {
        if (CAMERA == requestCode) {
            ActivityCompat.requestPermissions(PersonalInfoActivity.this,
                    new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE},
                    NO_CAMERA);
        }
    }

    @Override
    public void doNegativeClick(int requestCode) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case NO_CAMERA: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {

                    enter_paint();

                } else {
                    //拒绝进行相关的提醒
                }
                return;
            }
        }
    }

    @Override
    public void setText(Object text) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (pictures != null) {
            pictures.clear();
            pictures = null;
        }
    }
}
