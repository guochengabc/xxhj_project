package com.kongtiaoapp.xxhj.activites;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;

import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.adapter.Message_AreaAdapter;
import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.bean.CommentsGroupBean;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.presenter.More_CommentsPresenter;
import com.kongtiaoapp.xxhj.mvp.view.More_CommentsView;
import com.kongtiaoapp.xxhj.ui.refreshui.RotationFooter;
import com.kongtiaoapp.xxhj.ui.refreshui.RotationHeader;
import com.kongtiaoapp.xxhj.ui.refreshui.SpringView;
import com.kongtiaoapp.xxhj.ui.view.DoubleButtonDialog;
import com.kongtiaoapp.xxhj.ui.view.ToastDialog;
import com.kongtiaoapp.xxhj.utils.BaseTools;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class More_CommentsActivity extends BaseActivity<More_CommentsPresenter, More_CommentsView> implements More_CommentsView {
    @BindView(R.id.elv_comments)
    ExpandableListView elv_project_detail;
    @BindView(R.id.btn_comments)
    Button btn_comments;
    @BindView(R.id.edt_comments)
    EditText edt_comments;
    @BindView(R.id.sv_project_detail)
    SpringView springView;
    private List<CommentsGroupBean.ResobjBean> list_comment = new ArrayList<>();
    private Message_AreaAdapter adapter;
    private String toUid;
    private int group_position;
    private int child_position;
    private boolean ischild;
    private int currentPage = 1;
    private boolean isRefresh = true;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case ConstantValue.SHUAXIN_SUCESS:
                    getCommentList();
                    springView.onFinishFreshAndLoad();
                    break;
                case ConstantValue.JIAZAI_SUCESS:
                    getCommentList();
                    springView.onFinishFreshAndLoad();

                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected int initContentView() {
        return R.layout.activity_more__comments;
    }

    @Override
    protected void initView() {
        setDataView();
    }

    private void setDataView() {
        springView.setHeader(new RotationHeader(this));
        springView.setFooter(new RotationFooter((this)));
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        isRefresh = true;

                        handler.sendEmptyMessage(ConstantValue.SHUAXIN_SUCESS);
                    }
                }).start();
            }

            @Override
            public void onLoadmore() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        isRefresh = false;
                        currentPage++;
                        handler.sendEmptyMessage(ConstantValue.JIAZAI_SUCESS);
                    }
                }).start();
            }
        });
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        adapter = new Message_AreaAdapter(list_comment, this);
        elv_project_detail.setAdapter(adapter);
        setElvListerner();//设置不可伸缩
        getCommentList();
    }

    @Override
    protected More_CommentsPresenter getPresenter() {
        return new More_CommentsPresenter();
    }

    private void setElvListerner() {
        elv_project_detail.setGroupIndicator(null);
        elv_project_detail.setOnGroupClickListener((expandableListView, view, i, l) -> {
            elv_project_detail.expandGroup(i);
            toUid = "";
            if (!App.sp.getUid().equals(list_comment.get(i).getUserId())) {
                edt_comments.setHint("回复" + (list_comment.get(i).getUserName() + ":"));
                edt_comments.requestFocus();
                toUid = list_comment.get(i).getCommentId();
            } else {
                edt_comments.setHint("评论");
                edt_comments.requestFocus();
            }
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
            return true;
        });
        elv_project_detail.setOnItemLongClickListener((adapterView, view, i, id) -> {
            if (ExpandableListView.getPackedPositionType(id) == ExpandableListView.PACKED_POSITION_TYPE_CHILD) {

                long position = ((ExpandableListView) adapterView).getExpandableListPosition(i);
                group_position = ExpandableListView.getPackedPositionGroup(position);
                child_position = ExpandableListView.getPackedPositionChild(position);
                if (App.sp.getUid().equals(list_comment.get(group_position).getComments().get(child_position).getUserId())) {
                    ShowDoubleButtonDialog("是否删除该评论?", 100);
                    ischild = true;
                }
            } else {
                if (App.sp.getUid().equals(list_comment.get(i).getUserId())) {
                    ShowDoubleButtonDialog("是否删除该评论?", 100);
                    group_position = i;
                    ischild = false;
                }

            }

            return false;
        });
        edt_comments.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                // 此处为得到焦点时的处理内容
            } else {
                // 此处为失去焦点时的处理内容
                toUid = "";
                edt_comments.setHint("评论");
            }
        });
    }

    @Override
    protected void toastDialogCallBack(ToastDialog dialog) {
        super.toastDialogCallBack(dialog);
        if (ischild == true) {
            delete_Comment(list_comment.get(group_position).getComments().get(child_position).getCommentId());
        } else {
            delete_Comment(list_comment.get(group_position).getCommentId());
        }
        dialog.dismiss();
    }

    @Override
    protected void doubleButtonDialogCallBack(final DoubleButtonDialog dialog) {
        if (dialog.getCode() == 100) {
            // Toast.makeText(mContext,"确定删除此足迹",Toast.LENGTH_SHORT).show();
            if (ischild == true) {
                delete_Comment(list_comment.get(group_position).getComments().get(child_position).getCommentId());
            } else {
                delete_Comment(list_comment.get(group_position).getCommentId());
            }
            dialog.dismiss();
        }
    }

    private void delete_Comment(String commentId) {
        presenter.delete_Comments(this, commentId);
        //删除列表  cid
    }

    //获取留言列表
    private void getCommentList() {
        List list = new ArrayList<>();
        list.add(isRefresh);
        list.add(currentPage);
        presenter.onResume(this, list);
    }

    private void initCommit() {
        if (list_comment != null) {
            for (int i = 0; i < adapter.getGroupCount(); i++) {
                elv_project_detail.expandGroup(i, false);
            }
        }
    }

    @OnClick({R.id.btn_comments, R.id.iv_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_comments:
                String content = edt_comments.getText().toString();
                if (TextUtils.isEmpty(content)) {
                    ToastUtils.showToast(this, getString(R.string.comments_content_no_null));
                    return;
                }
                BaseTools.closeKeybord(edt_comments, this);
                commit_comment(content);//提交留言
                break;
            case R.id.iv_back:
                close_key();
                finish();
                break;
            default:
                break;
        }
    }

    //提交留言
    private void commit_comment(String content) {
        List<String> list = new ArrayList<>();
        list.add(content);
        list.add(toUid);
        presenter.commit_Comments(this, list);
    }

    @Override
    public void delete_Comments_View(Object data) {
        ToastUtils.showToast(More_CommentsActivity.this, "删除成功");
        isRefresh = true;
        getCommentList();
    }

    @Override
    public void commit_Comments_View(Object data) {
        ToastUtils.showToast(this, getString(R.string.comments_succeed));
        toUid = "";
        edt_comments.clearFocus();
        getCommentList();//获取留言列表
        edt_comments.setText("");
    }

    @Override
    public void notifyAdapter() {
        if (isRefresh) {
            list_comment.clear();
            adapter.notifyDataSetChanged();
        }
    }

    //获取留言列表
    @Override
    public void setText(Object data) {
        CommentsGroupBean response = (CommentsGroupBean) data;
        if (isRefresh == true) {
            list_comment.clear();
            list_comment = response.getResobj();
            adapter.setList(list_comment);
        } else {
            list_comment.addAll(response.getResobj());
            adapter.setList(list_comment);
        }
        initCommit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(null);
        if (list_comment != null) {
            list_comment.clear();
            list_comment = null;
        }
    }
}
