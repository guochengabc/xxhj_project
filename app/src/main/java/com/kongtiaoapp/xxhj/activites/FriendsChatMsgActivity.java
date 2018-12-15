package com.kongtiaoapp.xxhj.activites;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.adapter.ChatAdapter;
import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.bean.ChatMsg;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.presenter.FriendsChatMsgPresenter;
import com.kongtiaoapp.xxhj.mvp.view.FriendsChatMsgView;
import com.kongtiaoapp.xxhj.ui.refreshui.RotationHeader;
import com.kongtiaoapp.xxhj.ui.refreshui.SpringView;
import com.kongtiaoapp.xxhj.utils.DateUtils;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by ASUS-PC on 2016-10-19 10:34:34.
 * 好友聊天面
 */
public class FriendsChatMsgActivity extends BaseActivity<FriendsChatMsgPresenter,FriendsChatMsgView> implements FriendsChatMsgView {

    @ViewInject(R.id.friendsMsg_listView)
    private ListView mListView;

    @ViewInject(R.id.springView)
    private SpringView springView;

    @ViewInject(R.id.iv_chat_name)
    private TextView tvTitle;

    @ViewInject(R.id.edt_comment)
    private EditText dtComment;

    @ViewInject(R.id.btn_comment)
    private Button btComment;

    private ChatAdapter adapter;
    private List mList = new ArrayList();

    private int currentPage = 1;
    private boolean isRefresh = false;
    private String friendId;

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case ConstantValue.SHUAXIN_SUCESS:
                    getDataForServers();
                    springView.onFinishFreshAndLoad();
                    break;
                case ConstantValue.JIAZAI_SUCESS:
                    getDataForServers();
                    springView.onFinishFreshAndLoad();
                    break;

                default:
                    break;
            }
        }
    };
    private String comment;


    private void getDataForServers() {
        List<String> list=new ArrayList<>();
        list.add(friendId);
        list.add(currentPage+"");
        presenter.onResume(this,list);
    }

    private void comment() {
        List<String> list=new ArrayList<>();
        list.add(friendId);
        list.add(comment);
        presenter.commentMsg(this,list);
    }

    @Override
    protected int initContentView() {
        friendId = getIntent().getStringExtra("friendId");
        return R.layout.activity_friends_chat;
    }


    @Override
    protected void initView() {
        x.view().inject(this);

        springView.setHeader(new RotationHeader(mContext));
//        springView.setFooter(new RotationFooter(mContext));
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                new Thread(() -> {
                    isRefresh = true;
//                        currentPage = 1;
                    handler.sendEmptyMessage(ConstantValue.SHUAXIN_SUCESS);
                }).start();
            }

            @Override
            public void onLoadmore() {
                new Thread(() -> {
                    isRefresh = false;
                    currentPage++;
                    handler.sendEmptyMessage(ConstantValue.JIAZAI_SUCESS);
                }).start();
            }
        });
    }

    @Override
    protected void initListener() {
        btComment.setOnClickListener(v -> {
            comment = dtComment.getText().toString().trim();
            if (TextUtils.isEmpty(comment)) {
                ToastUtils.showToast(this, getString(R.string.content_no_null));
                return;
            }
            comment();
        });
    }



    @Override
    protected void initData() {
        adapter = new ChatAdapter(this, mList);
        mListView.setAdapter(adapter);

        getDataForServers();
    }

    @Override
    protected FriendsChatMsgPresenter getPresenter() {
        return new FriendsChatMsgPresenter();
    }

    @Override
    public void setList(Object response) {
        currentPage++;
        ChatMsg data = (ChatMsg) response;

        List newList = data.getResobj();
        Collections.reverse(newList);
        newList.addAll(mList);
        mList.clear();
        mList.addAll(newList);
        adapter.notifyDataSetChanged();
        if (!isRefresh) {
            mListView.setSelection(adapter.getCount() - 1);
        }
    }

    @Override
    public void setText(Object text) {
        ChatMsg chat = new ChatMsg();
        ChatMsg.ChatMessage message = chat.new ChatMessage();
        message.setUserId(App.sp.getUid());
        message.setAvatarUrl(App.sp.getHeadPath());
        message.setCreateTime(String.valueOf(DateUtils.dateToUnixTimestamp()));
        message.setMessage(comment);

        dtComment.setText("");
        mList.add(message);
        adapter.notifyDataSetChanged();
        mListView.setSelection(adapter.getCount() - 1);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mList!=null){
            mList.clear();
            mList=null;
        }
    }
}
