package com.kongtiaoapp.xxhj.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.activites.ZhenDuanMsgActivity;
import com.kongtiaoapp.xxhj.bean.GetHomeMes;
import com.kongtiaoapp.xxhj.mvp.base.BaseFragment;
import com.kongtiaoapp.xxhj.mvp.presenter.MessagePresenter;
import com.kongtiaoapp.xxhj.mvp.view.MessageView;
import com.kongtiaoapp.xxhj.utils.TimeUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 消息界面的Framgent
 * Created by Administrator on 2016-6-8.
 */
public class MessageFragment extends BaseFragment<MessagePresenter, MessageView> implements MessageView {

  /*  @BindView(R.id.fragmentMsg_tv_myFriends)
    TextView mMyFriends;//我的好友*/

    @BindView(R.id.fragmentMsg_tv_zdTime)
    TextView mZdTime;//诊断时间

    @BindView(R.id.fragmentMsg_tv_zdMsgNum)
    TextView mZdMsgNum;//诊断消息数量

    @BindView(R.id.fragmentMsg_tv_zdInfo)
    TextView mZdInfo;//诊断内容

    @BindView(R.id.fragmentMSg_tv_gzTime)
    TextView mGzTime;//故障时间

    @BindView(R.id.fragmentMsg_tv_gzMsgNum)
    TextView mGzMsgNum;//故障消息数量

    @BindView(R.id.fragmentMSg_tv_gzInfo)
    TextView mGzInfo;//故障内容

    @BindView(R.id.fragmentMSg_tv_wbTime)
    TextView mWbTime;//维保时间

    @BindView(R.id.fragmentMsg_tv_wbMsgNum)
    TextView mWbMsgNum;//维保消息数量

    @BindView(R.id.fragmentMSg_tv_wbInfo)
    TextView mWbInfo;//维保内容

    @BindView(R.id.fragmentMSg_tv_hyTime)
    TextView mHyTime;//好友时间

    @BindView(R.id.fragmentMsg_tv_hyMsgNum)
    TextView mHyMsgNum;//好友消息数量

    @BindView(R.id.fragmentMSg_tv_hyInfo)
    TextView mHyInfo;//好友内容
    @BindView(R.id.fragmentMSg_tv_dutyTime)
    TextView fragmentMSg_tv_dutyTime;//值班时间
    @BindView(R.id.fragmentMsg_tv_dutyMsgNum)
    TextView fragmentMsg_tv_dutyMsgNum;//值班消息数量
    @BindView(R.id.fragmentMSg_tv_dutyInfo)
    TextView fragmentMSg_tv_dutyInfo;//值班内容
    private boolean isresume = true;
    private List<GetHomeMes.HomeMessage> mList = new ArrayList<GetHomeMes.HomeMessage>();
    private MyReceiver myReceiver = new MyReceiver();
    private boolean isFirst_dialog = true;

    @Override
    protected View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_msg, null);
        return view;
    }

    @Override
    public void initData() {
        IntentFilter intentFilter = new IntentFilter("UI");
        mActivity.registerReceiver(myReceiver, intentFilter);
    }

    @Override
    public void onResume() {
        super.onResume();
        getDataForServers();
    }


    private void getDataForServers() {
        presenter.onResume(mActivity, isFirst_dialog);
        isresume = false;
        if (isFirst_dialog) {
            isFirst_dialog = !isFirst_dialog;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public MessagePresenter getPresenter() {
        return new MessagePresenter();
    }

    //, R.id.fragmentMsg_rl_hy   好友消息  暂时先隐藏了
    @OnClick({R.id.fragmentMsg_rl_zd, R.id.fragmentMsg_rl_gz, R.id.fragmentMsg_rl_wb, R.id.fragmentMsg_rl_duty})
    public void onClick(View view) {
        switch (view.getId()) {
         /*   case R.id.fragmentMsg_tv_myFriends://我的好友
                Intent intent = new Intent(getContext(), MyFriendsActivity.class);
                startActivity(intent);
                break;*/
            case R.id.fragmentMsg_rl_zd://诊断
                goActivity(ZhenDuanMsgActivity.class, 1);
                break;
            case R.id.fragmentMsg_rl_gz://故障
                goActivity(ZhenDuanMsgActivity.class, 2);
                break;
            case R.id.fragmentMsg_rl_wb://维保
                goActivity(ZhenDuanMsgActivity.class, 3);
                break;
            case R.id.fragmentMsg_rl_duty://值班消息
                goActivity(ZhenDuanMsgActivity.class, 5);
                break;
        /*    case R.id.fragmentMsg_rl_hy://好友
                Intent hyIntent = new Intent(getContext(), HaoYouMsgActivity.class);
                startActivity(hyIntent);
                break;*/
        }
    }

    /**
     * 页面跳转方法
     *
     * @param cls  需要跳转到的页面
     * @param type 区分页面 1诊断 2故障 3维保
     */
    private void goActivity(Class cls, int type) {
        Intent intent = new Intent(getActivity(), cls);
        intent.putExtra("type", type);
        startActivity(intent);
    }


    @Override
    public void setList(Object data) {
        GetHomeMes info = (GetHomeMes) data;
        mList = info.getResobj();
        Log.i("ffffffffffffffffff","====消息====="+mList.toString()+"====个数====="+mList.size());
        for (GetHomeMes.HomeMessage message : mList) {
            if ("A".equals(message.getCategory())) {
                //A 诊断消息
                mZdInfo.setText(message.getNewMessage());
                if (!TextUtils.isEmpty(message.getCounts())) {
                    mZdMsgNum.setVisibility(View.VISIBLE);
                    mZdMsgNum.setText(message.getCounts());
                }else {
                    mZdMsgNum.setVisibility(View.GONE);
                }
                if (!TextUtils.isEmpty(message.getSendTime())) {
                    mZdTime.setVisibility(View.VISIBLE);
                    mZdTime.setText(TimeUtils.getStandardDate(message.getSendTime()));
                }
            } else if ("B".equals(message.getCategory())) {
                //B 故障信息
                mGzInfo.setText(message.getNewMessage());
                if (!TextUtils.isEmpty(message.getCounts())) {
                    mGzMsgNum.setVisibility(View.VISIBLE);
                    mGzMsgNum.setText(message.getCounts());
                }else{
                    mGzMsgNum.setVisibility(View.GONE);
                }
                if (!TextUtils.isEmpty(message.getSendTime())) {
                    mGzTime.setVisibility(View.VISIBLE);
                    mGzTime.setText(TimeUtils.getStandardDate(message.getSendTime()));
                }
            } else if ("G".equals(message.getCategory())) {
                //C 维保信息
                mWbInfo.setText(message.getNewMessage());
                if (!TextUtils.isEmpty(message.getCounts())) {
                    mWbMsgNum.setVisibility(View.VISIBLE);
                    mWbMsgNum.setText(message.getCounts());
                }else{
                    mWbMsgNum.setVisibility(View.GONE);
                }
                if (!TextUtils.isEmpty(message.getSendTime())) {
                    mWbTime.setVisibility(View.VISIBLE);
                    mWbTime.setText(TimeUtils.getStandardDate(message.getSendTime()));
                }
            } else if ("D".equals(message.getCategory())) {
                //D 好友消息
                mHyInfo.setText(message.getNewMessage());
                if (!TextUtils.isEmpty(message.getCounts())) {
                    mHyMsgNum.setVisibility(View.VISIBLE);
                    mHyMsgNum.setText(message.getCounts());
                }else{
                    mHyMsgNum.setVisibility(View.GONE);
                }
                if (!TextUtils.isEmpty(message.getSendTime())) {
                    mHyTime.setVisibility(View.VISIBLE);
                    mHyTime.setText(TimeUtils.getStandardDate(message.getSendTime()));
                }
//                            mHyMsgNum.setText(message.getCounts());
            } else if ("E".equals(message.getCategory())) {
                //E 值班消息
                fragmentMSg_tv_dutyInfo.setText(message.getNewMessage());
                if (!TextUtils.isEmpty(message.getCounts())) {
                    fragmentMsg_tv_dutyMsgNum.setVisibility(View.VISIBLE);
                    fragmentMsg_tv_dutyMsgNum.setText(message.getCounts());
                }else{
                    fragmentMsg_tv_dutyMsgNum.setVisibility(View.GONE);
                }
                if (!TextUtils.isEmpty(message.getSendTime())) {
                    fragmentMSg_tv_dutyTime.setVisibility(View.VISIBLE);
                    fragmentMSg_tv_dutyTime.setText(TimeUtils.getStandardDate(message.getSendTime()));
                }
//                            mHyMsgNum.setText(message.getCounts());
            }

        }
    }

    @Override
    public void list_null() {
        setViewInVisible();
    }

    @Override
    public void error() {
        setViewInVisible();
    }

    private void setViewInVisible() {
        mZdMsgNum.setVisibility(View.INVISIBLE);
        mGzMsgNum.setVisibility(View.INVISIBLE);
        mWbMsgNum.setVisibility(View.INVISIBLE);
        mHyTime.setVisibility(View.INVISIBLE);
        fragmentMsg_tv_dutyMsgNum.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setText(Object data) {

    }

    public class MyReceiver extends BroadcastReceiver {

        private String receiverS;

        @Override
        public void onReceive(Context context, Intent intent) {
            receiverS = intent.getStringExtra("ReceiverS");
            if (receiverS.equals("UI")) {
                getDataForServers();
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mActivity.unregisterReceiver(myReceiver);
    }
}
