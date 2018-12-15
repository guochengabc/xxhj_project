package com.kongtiaoapp.xxhj.activites;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.adapter.PinglunAdapter;
import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.afinal.HttpMethod;
import com.kongtiaoapp.xxhj.afinal.ReceiverAction;
import com.kongtiaoapp.xxhj.bean.Moments;
import com.kongtiaoapp.xxhj.bean.Pinglun;
import com.kongtiaoapp.xxhj.bean.PostResult;
import com.kongtiaoapp.xxhj.bean.RBResponse;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.net.HttpLoader;
import com.kongtiaoapp.xxhj.ui.NineGridView.NetworkImageAdapter;
import com.kongtiaoapp.xxhj.ui.NineGridView.NineGridView;
import com.kongtiaoapp.xxhj.ui.dialog.SlideUpDialog;
import com.kongtiaoapp.xxhj.ui.view.ClickTextView;
import com.kongtiaoapp.xxhj.ui.view.DoubleButtonDialog;
import com.kongtiaoapp.xxhj.ui.view.MyListView;
import com.kongtiaoapp.xxhj.ui.view.ToastDialog;
import com.kongtiaoapp.xxhj.utils.BaseTools;
import com.kongtiaoapp.xxhj.utils.GlideCircleTransform;
import com.kongtiaoapp.xxhj.utils.TimeUtils;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Luoye on 2016-8-2.
 * 说明:节能圈详情页面
 */
public class MomentsDetailActivity extends BaseActivity implements HttpLoader.ResponseListener, AdapterView.OnItemClickListener {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.rl_title)
    RelativeLayout rlTitle;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.iv_picture)
    NineGridView ivPicture;
    @BindView(R.id.tv_praise)
    TextView tvPraise;
    @BindView(R.id.tv_favorite)
    TextView tvFavorite;
    @BindView(R.id.tv_share)
    TextView tvShare;
    @BindView(R.id.ll_zan)
    LinearLayout llZan;
    @BindView(R.id.tv_pinglun)
    TextView tvPinglun;
    @BindView(R.id.tv_pinglun_num)
    TextView tvPinglunNum;
    @BindView(R.id.iv_line)
    ImageView ivLine;
    @BindView(R.id.lv_list)
    MyListView lvList;
    @BindView(R.id.edt_comment)
    EditText edtComment;
    @BindView(R.id.btn_comment)
    Button btnComment;
    @BindView(R.id.ll_comment)
    LinearLayout llComment;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_head)
    ImageView ivHead;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.rl_head_layout)
    RelativeLayout rlHeadLayout;
    @BindView(R.id.iv_setting)
    ImageView ivSetting;
    @BindView(R.id.main)
    RelativeLayout main;
    @BindView(R.id.rl_zan)
    RelativeLayout rlZan;
    @BindView(R.id.rl_shoucang)
    RelativeLayout rlShoucang;
    @BindView(R.id.rl_fenxiang)
    RelativeLayout rlFenxiang;
    @BindView(R.id.rl_content)
    RelativeLayout rlContent;
    private Moments.MomentInfo momentInfo;
    private String userID;//发表动态人的ID
    private String messageID;//这条动态的ID
    private String content;//评论内容
    private String toUid;//回复的用户ID
    private PinglunAdapter adapter;
    private List mList = new ArrayList();
    private Pinglun.PinglunInfo item;
    private boolean praise;
    private boolean isFavorite;

    @Override
    protected int initContentView() {
        return R.layout.activity_moments_detail;
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        momentInfo = (Moments.MomentInfo) intent.getSerializableExtra("MomentInfo");
        if (momentInfo != null) {
            userID = momentInfo.getUserId();
            messageID = momentInfo.getMessageId();
            Glide.with(mContext).load(ConstantValue.URL + momentInfo.getAvatarUrl()).placeholder(R.mipmap.default_head).transform(new GlideCircleTransform(mContext)).crossFade().into(ivHead);
            tvName.setText(momentInfo.getUserName());
            tvTime.setText(TimeUtils.getStandardDate(momentInfo.getCreateTime()));
            tvContent.setText(momentInfo.getMessageInfo());
            NetworkImageAdapter adapter = new NetworkImageAdapter(App.application, momentInfo.getImageUrl());
            ivPicture.setAdapter(adapter);
            final String[] arr = (String[]) momentInfo.getImageUrl().toArray(new String[momentInfo.getImageUrl().size()]);
            ivPicture.setOnImageClickListener(new NineGridView.OnImageClickListener() {
                @Override
                public void onImageCilcked(int position, View view) {
                    startActivity(new Intent(mContext, ImageScaleActivity.class).putExtra("urls", arr).putExtra("currentItem", position));
//                ImageScaleActivity.instance.overridePendingTransition(R.anim.activity_translate_in,
//                        0);
                }
            });
            tvPraise.setText(momentInfo.getWelldoneNumber());


            if ("1".equals(momentInfo.getIsWelldone())) {
                //已赞
                tvPraise.setSelected(true);
            } else {
                tvPraise.setSelected(false);
            }
            praise = "1".equals(momentInfo.getIsWelldone());
            tvPraise.setSelected(praise);

            if ("1".equals(momentInfo.getIsCollection())) {
                tvFavorite.setSelected(true);
            } else {
                tvFavorite.setSelected(false);
            }
            isFavorite = "1".equals(momentInfo.getIsCollection());

        }
        adapter = new PinglunAdapter(mList, mContext);
        lvList.setAdapter(adapter);

        lvList.setOnItemClickListener(this);

    }

    @Override
    protected void initListener() {
        lvList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                item = (Pinglun.PinglunInfo) parent.getAdapter().getItem(position);
                if (App.sp.getUid().equals(item.getUserId())) {
//                    ShowToastDialog("是否删除该评论?", 1);
                    ShowDoubleButtonDialog("是否删除该评论?", 100);
                }

                return true;

            }
        });
//        main.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                toUid = "";
//                edtComment.setHint("评论");
//            }
//        });

        edtComment.setOnFocusChangeListener(new View.
                OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // 此处为得到焦点时的处理内容
                } else {
                    // 此处为失去焦点时的处理内容
                    toUid = "";
                    edtComment.setHint("评论");
                }
            }
        });


        rlZan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ispraise = praise ? "0" : "1";
                Map<String, String> map = new HashMap<String, String>();
                map.put("uid", App.sp.getUid());
                map.put("msgId", momentInfo.getMessageId());
                map.put("type", ispraise);
                Map<String, String> params = new HashMap<String, String>();
                params.put(HttpMethod.KEY, map2Json(HttpMethod.WELLDONE, map));

                Log.e(TAG, "WELLDONE:  " + params);
                showProgressDialog();
                HttpLoader.post(ConstantValue.HTTP_URL, params, RBResponse.class, ConstantValue.WELLDONE, MomentsDetailActivity.this);
            }
        });
    }

    @Override
    protected void toastDialogCallBack(ToastDialog dialog) {
        super.toastDialogCallBack(dialog);
        deletePinglun(item.getCommentId());
        dialog.dismiss();
    }

    @Override
    protected void doubleButtonDialogCallBack(DoubleButtonDialog dialog) {
        super.doubleButtonDialogCallBack(dialog);
        if (dialog.getCode() == 100) {
            deletePinglun(item.getCommentId());
            dialog.dismiss();
        }

    }

    @Override
    protected void initData() {
        getPinglun();
    }

    @Override
    protected BasePresenterLpl getPresenter() {
        return null;
    }

    private void getPinglun() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("uid", App.sp.getUid());
        map.put("msgId", momentInfo.getMessageId());
        Map<String, String> params = new HashMap<String, String>();
        params.put(HttpMethod.KEY, map2Json(HttpMethod.GETESCOMMENT, map));

        Log.e(TAG, "GetESMessage:  " + params);
        showProgressDialog();
        HttpLoader.post(ConstantValue.HTTP_URL, params, Pinglun.class, ConstantValue.GETESCOMMENT, this);
    }

    private void comment() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("uid", App.sp.getUid());
        map.put("msgId", messageID);
        map.put("content", content);
        if (!TextUtils.isEmpty(toUid)) {
            map.put("toUid", toUid);
        }
        Map<String, String> params = new HashMap<String, String>();
        params.put(HttpMethod.KEY, map2Json(HttpMethod.PUBLISHESCOMMENT, map));

        Log.e(TAG, "PUBLISHESCOMMENT:  " + params);
        showProgressDialog();
        HttpLoader.post(ConstantValue.HTTP_URL, params, PostResult.class, ConstantValue.PUBLISHESCOMMENT, this);
    }

    private void deleteMoments() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("uid", App.sp.getUid());
        map.put("msgId", messageID);
        Map<String, String> params = new HashMap<String, String>();
        params.put(HttpMethod.KEY, map2Json(HttpMethod.DELETEESMESSAGE, map));

        Log.e(TAG, "PUBLISHESCOMMENT:  " + params);
        showProgressDialog();
        HttpLoader.post(ConstantValue.HTTP_URL, params, PostResult.class, ConstantValue.DELETEESMESSAGE, this);
    }

    private void deletePinglun(String commentId) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("uid", App.sp.getUid());
        map.put("commentId", commentId);
        Map<String, String> params = new HashMap<String, String>();
        params.put(HttpMethod.KEY, map2Json(HttpMethod.DELETEESCOMMENT, map));

        Log.e(TAG, "DELETEESCOMMENT:  " + params);
        showProgressDialog();
        HttpLoader.post(ConstantValue.HTTP_URL, params, PostResult.class, ConstantValue.DELETEESCOMMENT, this);
    }

    private void favorite() {
        String isfavorite = isFavorite ? "0" : "1";
        Map<String, String> map = new HashMap<String, String>();
        map.put("uid", App.sp.getUid());
        map.put("msgId", momentInfo.getMessageId());
        map.put("action", isfavorite);
        map.put("type", ConstantValue.MOMENT);
        Map<String, String> params = new HashMap<String, String>();
        params.put(HttpMethod.KEY, map2Json(HttpMethod.COLLECTMESSAGE, map));

        Log.e(TAG, "WELLDONE:  " + params);
        showProgressDialog();
        HttpLoader.post(ConstantValue.HTTP_URL, params, RBResponse.class, ConstantValue.COLLECTMESSAGE, this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_favorite, R.id.tv_share, R.id.btn_comment, R.id.iv_setting, R.id.rl_content})
    public void onClick(View view) {
        switch (view.getId()) {
//            case R.id.tv_praise://赞
//                break;
            case R.id.rl_content:
                toUid = "";
                edtComment.setHint("评论");
                edtComment.requestFocus();
                break;
            case R.id.tv_favorite://收藏
                favorite();
                break;
            case R.id.tv_share://分享
                break;
            case R.id.btn_comment://提交评论
                content = edtComment.getText().toString();
                if (TextUtils.isEmpty(content)) {
                    ToastUtils.showToast(this, getString(R.string.comments_content_no_null));
                    return;
                }
                BaseTools.closeKeybord(edtComment, mContext);

                comment();
                break;
            case R.id.iv_setting:
                showMeunDialog();
                break;
        }
    }


    @Override
    public void onGetResponseSuccess(int requestCode, RBResponse response) {
        dismissProgressDialog();
        switch (requestCode) {
            case ConstantValue.GETESCOMMENT:
                if (response.getCode() == 40000) {
                    Pinglun pinglun = (Pinglun) response;
                    mList.clear();
                    mList.addAll(pinglun.getResobj());
                    adapter = new PinglunAdapter(mList, mContext);
                    lvList.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    tvPinglunNum.setText("(" + mList.size() + ")");
                } else if (response.getCode() == 40005) {
                    Logger.e(response.getCode() + "");
                    ToastUtils.showToast(this, getString(R.string.enegry_no_comment));
                } else {
                    ToastUtils.showToast(this, getString(R.string.enegry_get_comments_error));
                }
                break;
            case ConstantValue.PUBLISHESCOMMENT:
                PostResult result = (PostResult) response;
                if (response.getCode() == 40000) {
                    ToastUtils.showToast(this, getString(R.string.enegry_comments_succeed));
                    edtComment.clearFocus();
                    getPinglun();
                    edtComment.setText("");
                } else {
                    Logger.e(result.getResult());
                    ToastUtils.showToast(this, getString(R.string.enegry_comments_error));
                }
                break;
            case ConstantValue.DELETEESMESSAGE:
                PostResult resultDelete = (PostResult) response;
                if (response.getCode() == 40000) {
                    ToastUtils.showToast(this, getString(R.string.enegry_delete_succeed));
                    Intent mIneten = new Intent();
                    mIneten.setAction(ReceiverAction.MOMENTS_FRESH);
                    sendBroadcast(mIneten);
                    finish();
                } else {
                    Logger.e(resultDelete.getResult());
                    ToastUtils.showToast(this, getString(R.string.enegry_delete_fail));
                }
                break;
            case ConstantValue.DELETEESCOMMENT:
                if (response.getCode() == 40000) {
                    ToastUtils.showToast(this, getString(R.string.enegry_delete_succeed));
                    getPinglun();
                } else {
                    ToastUtils.showToast(this, getString(R.string.enegry_delete_fail));
                }
                break;
            case ConstantValue.WELLDONE:
                if (response.getCode() == 40000) {

                    int number = Integer.valueOf(TextUtils.isEmpty(momentInfo.getWelldoneNumber()) ? "0" : momentInfo.getWelldoneNumber()).intValue();
                    if (praise) {
                        momentInfo.setIsWelldone("0");
                        momentInfo.setWelldoneNumber(String.valueOf(number - 1));

                        tvPraise.setSelected(false);
                        tvPraise.setText(String.valueOf(number - 1));
                    } else {
                        momentInfo.setIsWelldone("1");
                        momentInfo.setWelldoneNumber(String.valueOf(number + 1));

                        tvPraise.setSelected(true);
                        tvPraise.setText(String.valueOf(number + 1));
                    }
                    initView();
                } else {
                    ToastUtils.showToast(this, getString(R.string.error_net));
                }
                break;
            case ConstantValue.COLLECTMESSAGE:
                if (response.getCode() == 40000) {
                    if (isFavorite) {
                        isFavorite = false;
                        tvFavorite.setSelected(false);
                        ToastUtils.showToast(this, getString(R.string.enegry_cancel_favorite));
                    } else {
                        isFavorite = true;
                        tvFavorite.setSelected(true);
                        ToastUtils.showToast(this, getString(R.string.enegry_favorite_succeed));
                    }
                } else {
                    ToastUtils.showToast(this, getString(R.string.enegry_favorite_fail));
                }
            default:
                break;
        }
    }

    @Override
    public void onGetResponseError(int requestCode, VolleyError error) {
        dismissProgressDialog();
    }

    @Override
    public void onGetResponseFailed(int requestCode, int code) {
        dismissProgressDialog();
    }

    public void showMeunDialog() {
        final View view = View.inflate(this, R.layout.dialog_moments_menu, null);
        final SlideUpDialog dialog = new SlideUpDialog(this) {

            @Override
            public View initView() {
                return view;
            }
        };
        RelativeLayout dialog_delete = (RelativeLayout) view
                .findViewById(R.id.dialog_delete);//
        ClickTextView cancel = (ClickTextView) view
                .findViewById(R.id.dialog_btn_cancel);// 取消按钮

        dialog_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteMoments();
            }


        });

        cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                dialog.cancelDialog();
            }
        });
        dialog.showDialog();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Pinglun.PinglunInfo item = (Pinglun.PinglunInfo) parent.getAdapter().getItem(position);
        toUid = "";
        if (!App.sp.getUid().equals(item.getUserId())) {
            edtComment.setHint("回复" + item.getUserName() + ":");
            edtComment.requestFocus();

            toUid = item.getUserId();
        } else {
            edtComment.setHint("评论");
            edtComment.requestFocus();
        }
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mList != null) {
            mList.clear();
            mList = null;
        }
    }
}
