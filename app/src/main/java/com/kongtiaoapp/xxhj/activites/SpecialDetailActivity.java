package com.kongtiaoapp.xxhj.activites;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.adapter.SelectionPinglunAdapter;
import com.kongtiaoapp.xxhj.bean.GetSelectionInfo;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.presenter.SpecialDetailPresenter;
import com.kongtiaoapp.xxhj.mvp.view.SpecialDetailView;
import com.kongtiaoapp.xxhj.ui.dialog.SlideUpDialog;
import com.kongtiaoapp.xxhj.ui.view.ClickTextView;
import com.kongtiaoapp.xxhj.ui.view.DoubleButtonDialog;
import com.kongtiaoapp.xxhj.ui.view.MyListView;
import com.kongtiaoapp.xxhj.ui.view.ToastDialog;
import com.kongtiaoapp.xxhj.utils.BaseTools;
import com.kongtiaoapp.xxhj.utils.TimeUtils;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Luoye on 2016-10-10.
 * 说明:专栏详情页
 */
public class SpecialDetailActivity extends BaseActivity<SpecialDetailPresenter, SpecialDetailView> implements SpecialDetailView, AdapterView.OnItemClickListener {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rl_title)
    RelativeLayout rlTitle;
    @BindView(R.id.iv_head)
    ImageView ivHead;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.rl_head_layout)
    RelativeLayout rlHeadLayout;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.tv_praise)
    TextView tvPraise;
    @BindView(R.id.rl_zan)
    RelativeLayout rlZan;
    @BindView(R.id.tv_favorite)
    TextView tvFavorite;
    @BindView(R.id.rl_shoucang)
    RelativeLayout rlShoucang;
    @BindView(R.id.tv_share)
    TextView tvShare;
    @BindView(R.id.rl_fenxiang)
    RelativeLayout rlFenxiang;
    @BindView(R.id.ll_zan)
    LinearLayout llZan;
    @BindView(R.id.line_top)
    View lineTop;
    @BindView(R.id.line_down)
    View lineDown;
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
    @BindView(R.id.main)
    RelativeLayout main;
    @BindView(R.id.rl_content)
    RelativeLayout rlContent;

    private List mList = new ArrayList();
    private SelectionPinglunAdapter adapter;
    private String toUid;
    private GetSelectionInfo.SelectionInfo.CommentsBean item;
    private String content;
    private String sid;
    private boolean isFinish = false;
    private boolean isFavorite;

    @Override
    protected int initContentView() {
        sid = getIntent().getStringExtra("sid");
        //        sid = "e54cfecae5a947d7a03e237ec8d76b0e";
        return R.layout.activity_selection_detail;
    }

    @Override
    protected void initView() {
        initWebView();
    }

    @Override
    protected void initListener() {

        lvList.setOnItemLongClickListener((parent, view, position, id) -> {
            item = (GetSelectionInfo.SelectionInfo.CommentsBean) parent.getAdapter().getItem(position);
            if (App.sp.getUid().equals(item.getUserId())) {
                //                    ShowToastDialog("是否删除该评论?", 1);
                ShowDoubleButtonDialog("是否删除该评论?", 100);
            }

            return true;

        });
        edtComment.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                // 此处为得到焦点时的处理内容
            } else {
                // 此处为失去焦点时的处理内容
                toUid = "";
                edtComment.setHint("评论");
            }
        });

        btnComment.setOnClickListener(v -> {
            content = edtComment.getText().toString();
            if (TextUtils.isEmpty(content)) {
                ToastUtils.showToast(this, getString(R.string.enegry_comment_no_null));
                return;
            }
            BaseTools.closeKeybord(edtComment, mContext);

            comment();
        });

    }

    @OnClick({R.id.iv_back, R.id.iv_setting})
    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_setting:
                showMeunDialog();
                break;
            default:
                break;
        }


    }

    @Override
    protected void toastDialogCallBack(ToastDialog dialog) {
        super.toastDialogCallBack(dialog);
        deletePinglun(item.getCommentId());
        dialog.dismiss();
    }

    /*长按进行监听*/
    @Override
    protected void doubleButtonDialogCallBack(final DoubleButtonDialog dialog) {
        if (dialog.getCode() == 100) {
            // Toast.makeText(mContext,"确定删除此足迹",Toast.LENGTH_SHORT).show();
            deletePinglun(item.getCommentId());
            dialog.dismiss();
        }
    }

    @Override
    protected void initData() {
        adapter = new SelectionPinglunAdapter(mList, mContext);
        lvList.setAdapter(adapter);
        lvList.setOnItemClickListener(this);
        getDataForServers();
    }

    @Override
    protected SpecialDetailPresenter getPresenter() {
        return new SpecialDetailPresenter();
    }

    //"e54cfecae5a947d7a03e237ec8d76b0e"
    private void getDataForServers() {
        presenter.onResume(this, sid);
    }

    private void comment() {
        List<String> list = new ArrayList<>();
        list.add(sid);
        list.add(content);
        list.add(toUid);
        presenter.comment(this, list);
    }

    private void deletePinglun(String commentId) {
        presenter.deletePinglun(this, commentId);
    }

    private void favorite() {
        String isfavorite = isFavorite ? "0" : "1";
        List<String> list = new ArrayList<>();
        list.add(sid);
        list.add(isfavorite);
        presenter.favorite(this, list);
    }

    /**
     * 初始化webview
     */
    protected void initWebView() {

        WebSettings settings = webView.getSettings();
        settings.setAllowFileAccess(true);
        settings.setDatabaseEnabled(true);
        settings.setSupportZoom(true);//设置可以支持缩放
        settings.setBuiltInZoomControls(true);//设置出现缩放工具
        settings.setUseWideViewPort(true);//设置webview推荐使用的窗口
        settings.setLoadWithOverviewMode(true);//设置webview加载的页面的模式
        //settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        String dir = this.getApplicationContext()
                .getDir("database", Context.MODE_PRIVATE).getPath();
        settings.setDatabasePath(dir);
        settings.setDomStorageEnabled(true);
        settings.setGeolocationEnabled(true);
        settings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new myWebClient());

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        GetSelectionInfo.SelectionInfo.CommentsBean item = (GetSelectionInfo.SelectionInfo.CommentsBean) parent.getAdapter().getItem(position);
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

    @OnClick(R.id.rl_content)
    public void onClick() {
        toUid = "";
        edtComment.setHint("评论");
        edtComment.requestFocus();
    }

    @Override
    public void comment_View(Object data) {
        ToastUtils.showToast(this, getString(R.string.enegry_comments_succeed));
        edtComment.clearFocus();
        getDataForServers();
        edtComment.setText("");
    }

    @Override
    public void deletePinglun_View(Object data) {
        ToastUtils.showToast(this, getString(R.string.enegry_delete_succeed));
        getDataForServers();
    }

    @Override
    public void favorite_View(Object data) {
        App.sp.setRefresh("yes");
        if (isFavorite) {
            isFavorite = false;
            ToastUtils.showToast(this, getString(R.string.enegry_cancel_favorite));
        } else {
            isFavorite = true;
            ToastUtils.showToast(this, getString(R.string.enegry_favorite_succeed));
        }
    }

    @Override
    public void setText(Object data) {

    }

    @Override
    public void setList(Object response) {
        GetSelectionInfo.SelectionInfo item = null;
        GetSelectionInfo data = (GetSelectionInfo) response;
        if (data != null) {
            item = data.getResobj();
        }
        if (item == null) {
            return;
        }
        isFavorite = "1".equals(item.getIsCollection());
        if (isFinish) {
            if (item.getComments() != null && item.getComments().size() > 0) {
                mList.clear();
                mList.addAll(item.getComments());
                adapter.notifyDataSetChanged();
                tvPinglunNum.setText("(" + mList.size() + ")");
                return;
            }
            return;
        }

        tvName.setText(item.getTitle());
        if (!TextUtils.isEmpty(item.getCreateTime())) {
            tvTime.setText(TimeUtils.getStandardDate(item.getCreateTime()));
        }

        webView.loadUrl(data.getResobj().getContent());

        //webView.loadData(data.getResobj().getContent(), "text/html; charset=UTF-8", null);

        if (item.getComments() != null && item.getComments().size() > 0) {
            mList.clear();
            mList.addAll(item.getComments());
            adapter.notifyDataSetChanged();
            tvPinglunNum.setText("(" + mList.size() + ")");
        }
        isFinish = true;
    }


    public class myWebClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);

        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {

        }
    }

    public void showMeunDialog() {
        final View view = View.inflate(this, R.layout.dialog_moments_menu, null);
        final SlideUpDialog dialog = new SlideUpDialog(this) {

            @Override
            public View initView() {
                return view;
            }
        };
        TextView tv_name = (TextView) view.findViewById(R.id.tv_name1);
        tv_name.setTextColor(0xff333333);
        if (isFavorite) {
            tv_name.setText("取消收藏");
        } else {
            tv_name.setText("收藏");
        }

        RelativeLayout dialog_delete = (RelativeLayout) view
                .findViewById(R.id.dialog_delete);//
        ClickTextView cancel = (ClickTextView) view
                .findViewById(R.id.dialog_btn_cancel);// 取消按钮

        dialog_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                favorite();
                dialog.cancelDialog();
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
    protected void onDestroy() {
        super.onDestroy();
        if (mList != null) {
            mList.clear();
            mList = null;
        }
    }
}
