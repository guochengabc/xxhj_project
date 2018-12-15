package com.kongtiaoapp.xxhj.prefessionalknowledge.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.activites.ImageScaleActivity;
import com.kongtiaoapp.xxhj.adapter.PostPinglunAdapter;
import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.afinal.ReceiverAction;
import com.kongtiaoapp.xxhj.bean.GetPostList;
import com.kongtiaoapp.xxhj.bean.PostPinglun;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.presenter.PostDetailPresenter;
import com.kongtiaoapp.xxhj.mvp.view.PostDetailView;
import com.kongtiaoapp.xxhj.ui.NineGridView.NetworkImageAdapter;
import com.kongtiaoapp.xxhj.ui.NineGridView.NineGridView;
import com.kongtiaoapp.xxhj.ui.dialog.SlideUpDialog;
import com.kongtiaoapp.xxhj.ui.view.ClickTextView;
import com.kongtiaoapp.xxhj.ui.view.DoubleButtonDialog;
import com.kongtiaoapp.xxhj.ui.view.MyListView;
import com.kongtiaoapp.xxhj.utils.BaseTools;
import com.kongtiaoapp.xxhj.utils.GlideCircleTransform;
import com.kongtiaoapp.xxhj.utils.TimeUtils;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Luoye on 2016-8-2.
 * 说明:讨论区详情页面
 */
public class PostDetailActivity extends BaseActivity<PostDetailPresenter, PostDetailView> implements PostDetailView, PostPinglunAdapter.PinglunListener {
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
    @BindView(R.id.txt_title_talk_detail)
    TextView txt_title_talk_detail;
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
    @BindView(R.id.iv_setting)//删除帖子按钮是否可见
            ImageView ivSetting;
    @BindView(R.id.rl_content)
    RelativeLayout rlContent;
    private GetPostList.Post post;
    private String userID;//发表动态人的ID
    private String themeID;//这条动态的ID
    private String content;//评论内容
    private PostPinglunAdapter adapter;
    private List mList = new ArrayList();
    private String answerId;
    private String toUid;
    private String deleteAnswerID;
    private boolean praise;
    private boolean isFavorite;
    private SlideUpDialog dialog;
    private View view;

    @Override
    protected int initContentView() {
        Intent intent = getIntent();
        post = (GetPostList.Post) intent.getSerializableExtra("post");
        return R.layout.activity_post_detail;
    }

    @Override
    protected void initView() {
        if (post != null) {
            userID = post.getUserId();
            if (userID.equals(App.sp.getUid())) {
                ivSetting.setVisibility(View.VISIBLE);
            }
            themeID = post.getThemeId();
            Glide.with(mContext).load(ConstantValue.URL + post.getAvatarUrl()).transform(new GlideCircleTransform(mContext)).crossFade().into(ivHead);
            tvName.setText(post.getUserName());
            tvTime.setText(TimeUtils.getStandardDate(post.getCreateTime()));
            txt_title_talk_detail.setText(post.getTitle());
            tvContent.setText(post.getContent());
            NetworkImageAdapter adapter = new NetworkImageAdapter(App.application, post.getImageUrl());
            ivPicture.setAdapter(adapter);
            final String[] arr = (String[]) post.getImageUrl().toArray(new String[post.getImageUrl().size()]);
            ivPicture.setOnImageClickListener(new NineGridView.OnImageClickListener() {
                @Override
                public void onImageCilcked(int position, View view) {
                    startActivity(new Intent(mContext, ImageScaleActivity.class).putExtra("urls", arr).putExtra("currentItem", position));
//                ImageScaleActivity.instance.overridePendingTransition(R.anim.activity_translate_in,0);
                }
            });
            tvPraise.setText(post.getWelldoneNumber());
            if ("1".equals(post.getIsWelldone())) {
                //已赞
                tvPraise.setSelected(true);
            } else {
                tvPraise.setSelected(false);
            }
            praise = "1".equals(post.getIsWelldone());
            tvPraise.setSelected(praise);

            if ("1".equals(post.getIsCollection())) {
                tvFavorite.setSelected(true);
            } else {
                tvFavorite.setSelected(false);
            }
            isFavorite = "1".equals(post.getIsCollection());
        }
        adapter = new PostPinglunAdapter(mList, mContext);
        adapter.setOperationListener(this);
        lvList.setAdapter(adapter);

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        getPinglun();//获得评论模块
    }

    @Override
    protected PostDetailPresenter getPresenter() {
        return new PostDetailPresenter();
    }

    //获得评论
    private void getPinglun() {
        presenter.getPinglun(this, post.getThemeId());
    }

    private void comment() {
        List<String> list = new ArrayList<>();
        list.add(themeID);
        list.add(content);
        list.add(answerId);
        list.add(toUid);
        presenter.comment(this, list);
    }

    /**
     * 删除帖子
     */
    private void deleteMoments() {
        presenter.deleteMoments(this, themeID);
    }

    private void deletePinglun(String deleteAnswerID) {
        presenter.deletePinglun(this, deleteAnswerID);
    }

    private void favorite() {
        String isfavorite = isFavorite ? "0" : "1";
        List<String> list = new ArrayList<>();
        list.add(post.getThemeId());
        list.add(isfavorite);
        presenter.favorite(this, list);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.rl_zan, R.id.tv_favorite, R.id.tv_share, R.id.btn_comment, R.id.iv_setting, R.id.rl_content})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_zan://赞
                praise();
                break;
            case R.id.tv_favorite://收藏
                favorite();
                break;
            case R.id.tv_share://分享
                break;
            case R.id.btn_comment://提交评论
                content = edtComment.getText().toString();
                if (TextUtils.isEmpty(content)) {
                    ToastUtils.showToast(this, getString(R.string.enegry_comment_no_null));
                    return;
                }
                BaseTools.closeKeybord(edtComment, mContext);
                comment();
                break;
            case R.id.iv_setting:
                showMeunDialog();
                break;
            case R.id.rl_content:
                toUid = "";
                answerId = "";
                edtComment.setHint("评论");
                edtComment.requestFocus();
                break;
        }
    }

    private void praise() {
        String ispraise = praise ? "0" : "1";
        List<String> list = new ArrayList<>();
        list.add(post.getThemeId());
        list.add(ispraise);
        presenter.praise(this, list);
    }

    public void showMeunDialog() {
        if (view==null){

        view = View.inflate(this, R.layout.dialog_moments_menu, null);
        dialog = new SlideUpDialog(this) {

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
                dialog.cancelDialog();
                dialog.dismiss();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.cancelDialog();
                dialog.dismiss();
            }
        });
        }
        if (dialog.isShowing()){
                dialog.dismiss();
        }else{
            dialog.showDialog();
        }
    }

    @Override
    public void pingLun(int position, PostPinglun.CommentsList item) {
        answerId = "";
        edtComment.setHint("评论");
        edtComment.requestFocus();
        answerId = item.getAnswerId();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    @Override
    public void comment(int position, PostPinglun.CommentsList.CommentsBean item) {
        answerId = "";
        toUid = "";
        PostPinglun.CommentsList items = (PostPinglun.CommentsList) mList.get(position);
        if (!App.sp.getUid().equals(item.getUserId())) {
            edtComment.setHint("回复" + item.getUserName() + ":");
            edtComment.requestFocus();
            answerId = items.getAnswerId();
            toUid = item.getUserId();
        } else {
            answerId = items.getAnswerId();
            edtComment.setHint("评论");
            edtComment.requestFocus();
        }
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);

    }

    @Override
    public void longTimeclick(String uid, String answerId) {
        if (App.sp.getUid().equals(uid)) {
            ShowDoubleButtonDialog("删除评论?", 2);
            deleteAnswerID = answerId;
        }
    }

    @Override
    protected void doubleButtonDialogCallBack(DoubleButtonDialog dialog) {
        super.doubleButtonDialogCallBack(dialog);
        deletePinglun(deleteAnswerID);
        dialog.dismiss();
    }

    @OnClick({R.id.iv_back})
    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void getPinglun(Object response) {
        PostPinglun pinglun = (PostPinglun) response;
        mList.clear();
        mList.addAll(pinglun.getResobj());
//                    adapter = new PostPinglunAdapter(mList, mContext);
        lvList.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        tvPinglunNum.setText("(" + mList.size() + ")");
    }

    @Override
    public void comment_View(Object data) {
        ToastUtils.showToast(this, getString(R.string.enegry_comments_succeed));
        getPinglun();
        edtComment.setText("");
    }

    @Override
    public void deleteMoments_View(Object data) {
        ToastUtils.showToast(this, getString(R.string.enegry_delete_succeed));
        Intent mIneten = new Intent();
        mIneten.setAction(ReceiverAction.POST_FRESH);
        sendBroadcast(mIneten);
        finish();
    }

    @Override
    public void deletePinglun_View(Object data) {
        App.sp.setRefresh("yes");
        ToastUtils.showToast(this, getString(R.string.enegry_delete_succeed));
        getPinglun();
    }

    @Override
    public void favoritet_View() {
        App.sp.setRefresh("yes");
        Intent intent = new Intent(ReceiverAction.POST_FRESH);
        sendBroadcast(intent);
        if (isFavorite) {
            isFavorite = false;
            post.setIsCollection("0");
            tvFavorite.setSelected(false);
            ToastUtils.showToast(this, getString(R.string.enegry_cancel_favorite));
        } else {
            isFavorite = true;
            post.setIsCollection("1");
            tvFavorite.setSelected(true);
            ToastUtils.showToast(this, getString(R.string.enegry_favorite_succeed));
        }
    }

    @Override
    public void praise_View() {
        Intent intent = new Intent(ReceiverAction.POST_FRESH);
        sendBroadcast(intent);
        int number = Integer.valueOf(TextUtils.isEmpty(post.getWelldoneNumber()) ? "0" : post.getWelldoneNumber()).intValue();
        if (praise) {
            post.setIsWelldone("0");
            post.setWelldoneNumber(String.valueOf(number - 1));
            praise = false;
            tvPraise.setSelected(false);
            tvPraise.setText(String.valueOf(number - 1));
        } else {
            post.setIsWelldone("1");
            post.setWelldoneNumber(String.valueOf(number + 1));
            praise = true;
            tvPraise.setSelected(true);
            tvPraise.setText(String.valueOf(number + 1));
        }
    }

    @Override
    public void setText(Object text) {
        //当评论为空时，走的该方法
        mList.clear();
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
        if (mList != null) {
            mList.clear();
            mList = null;
        }
    }

}
