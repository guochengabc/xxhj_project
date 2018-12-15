package com.kongtiaoapp.xxhj.activites;

import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.bean.GetFriendInfo;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.presenter.MsgFriendDetailsPresenter;
import com.kongtiaoapp.xxhj.mvp.view.MsgFriendDetailsView;
import com.kongtiaoapp.xxhj.utils.DensityUtils;
import com.kongtiaoapp.xxhj.utils.GlideCircleTransform;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * 好友消息详情
 */
public class MsgFriendDetailsActivity extends BaseActivity<MsgFriendDetailsPresenter,MsgFriendDetailsView> implements MsgFriendDetailsView {

    @ViewInject(R.id.friendDetails_iv_more)
    private ImageView mMore;//右上角三个点->更多

    @ViewInject(R.id.friendDetails_iv_avatar)
    private ImageView mAvatar;//头像

    @ViewInject(R.id.friendDetails_tv_nickName)
    private TextView mNickName;//昵称

    @ViewInject(R.id.friendDetails_tv_gender)
    private TextView mGender;//性别

    @ViewInject(R.id.friendDetails_tv_interest)
    private TextView mInterest;//兴趣爱好

    @ViewInject(R.id.friendDetails_tv_age)
    private TextView mAge;//年龄

    @ViewInject(R.id.friendDetails_tv_city)
    private TextView mCity;//城市

    @ViewInject(R.id.friendDetails_tv_company)
    private TextView mCompany;//工作单位

    @ViewInject(R.id.friendDetails_tv_college)
    private TextView mCollege;//毕业院校

    private PopupWindow popupWindow;
    private String friendId;
    private GetFriendInfo.FriendInfo friendInfo;

    private boolean IsShield;//0 未被屏蔽 1 屏蔽
    private boolean IsFriend;//0 不是好友 1 是好友
    private boolean isApply = false;//是否为好友申请

    @Event(value = {R.id.iv_back, R.id.friendDetails_iv_more})
    private void getEvent(View v) {
        switch (v.getId()) {
            case R.id.iv_back://返回按钮
                finish();
                break;
            case R.id.friendDetails_iv_more://右上角三个点->更多
                showPopup();
                break;
            default:
                break;
        }
    }

    @Override
    protected int initContentView() {
        friendId = getIntent().getStringExtra("friendId");
        isApply = getIntent().getBooleanExtra("isApply",false);
        return R.layout.activity_msg_friend_details;
    }

    @Override
    protected void initView() {
        x.view().inject(this);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        presenter.onResume(this,friendId);
    }

    @Override
    protected MsgFriendDetailsPresenter getPresenter() {
        return new MsgFriendDetailsPresenter();
    }


    private void PingBi(String action) {
        List<String> list=new ArrayList<>();
        list.add(friendId);
        list.add(action);
        presenter.PingBi(this,list);
    }


    /**
     * 右上角弹出的popupWindow
     */
    @SuppressWarnings("deprecation")
    private void showPopup() {
        View view = LayoutInflater.from(this).inflate(
                R.layout.popup_friend_details_more, null);
        popupWindow = new PopupWindow(view, DensityUtils.dp2px(this, 120),
                DensityUtils.dp2px(this, 100));
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(getResources().getDrawable(
                R.drawable.more_popup_bg));
        popupWindow.setAnimationStyle(-1);
        popupWindow.showAsDropDown(mMore, DensityUtils.dp2px(this, -85), DensityUtils.dp2px(this, -10));

        TextView pingBi = (TextView) view.findViewById(R.id.tv_pingBi);//屏蔽好友
        pingBi.setOnClickListener(popupListener);
        TextView delete = (TextView) view.findViewById(R.id.tv_delete);//删除好友
        delete.setOnClickListener(popupListener);

        if (isApply) {
            pingBi.setText("同意添加");
            delete.setText("取消");
        } else {
            if (IsFriend) {
                if (IsShield) {
                    pingBi.setText("取消屏蔽");
                } else {
                    pingBi.setText("屏蔽好友");
                }
                delete.setText("删除好友");
            } else {
                pingBi.setText("添加好友");
                delete.setText("取消");
            }
        }


        // popupwindow弹出时周围背景变黑
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.4f;
        getWindow().setAttributes(lp);

        // 监听popupwindow消失时，周围背景恢复
        popupWindow.setOnDismissListener(new OnDismissListener() {

            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });

    }

    private View.OnClickListener popupListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.tv_pingBi:
                    if (isApply) {
                        presenter.apply(MsgFriendDetailsActivity.this,friendId);
                    } else {
                        if (IsFriend) {
                            if (IsShield) {
                                //showToast(TAG, "取消屏蔽");
                                PingBi("0");
                            } else {
                                // showToast(TAG, "屏蔽好友");
                                PingBi("1");
                            }
                        } else {
//                            showToast(TAG, "添加好友");
                            presenter.addFriend(MsgFriendDetailsActivity.this,friendId);
                            presenter.addFriend(MsgFriendDetailsActivity.this,friendId);
                        }
                    }

                    popupWindow.dismiss();
                    break;
                case R.id.tv_delete:
                    if (isApply) {
                    } else {
                        if (IsFriend) {
//                            showToast(TAG, "删除好友");
                           presenter.delete(MsgFriendDetailsActivity.this,friendId);
                        } else {
//                            showToast(TAG, "取消");
                        }

                    }
                    popupWindow.dismiss();
                    break;
            }
        }
    };

    @Override
    public void PingBi_View(Object data) {

    }

    @Override
    public void delete_View(Object data) {

    }

    @Override
    public void addFriend_View(Object data) {

    }

    @Override
    public void apply_View(Object data) {

    }

    @Override
    public void setText(Object response) {
        GetFriendInfo data = (GetFriendInfo) response;
        if (data != null) {
            friendInfo = data.getResobj();
            if (friendInfo != null) {
                Glide.with(this).load(ConstantValue.URL + friendInfo.getAvatarUrl()).placeholder(R.mipmap.default_head).transform(new GlideCircleTransform(this)).crossFade().into(mAvatar);
                mNickName.setText(friendInfo.getUserName());
                mGender.setText(friendInfo.getGender());
                mInterest.setText(friendInfo.getInterest());
//                    mAge.setText(friendInfo.get);
                mCity.setText(friendInfo.getCity());
                mCompany.setText(friendInfo.getOrgName());
                mCollege.setText(friendInfo.getSchool());

                IsShield = "0".equals(friendInfo.getIsShield()) ? false : true;
                IsFriend = "0".equals(friendInfo.getIsFriend()) ? false : true;
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
