package com.kongtiaoapp.xxhj.activites;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.presenter.FeedBackPresenter;
import com.kongtiaoapp.xxhj.mvp.view.FeedBackView;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS-PC on 2016/6/17.
 * 意见反馈页面
 */
public class FeedBackActivity extends BaseActivity<FeedBackPresenter, FeedBackView> implements FeedBackView {

    @ViewInject(R.id.feedback_et_info)
    private EditText mInfo;//建议反馈内容
    @ViewInject(R.id.feedback_et_contact)
    private EditText etContact;

    @Event(value = {R.id.iv_back, R.id.feedback_tv_send})
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.iv_back://返回按钮
                close_key();
                finish();
                break;
            case R.id.feedback_tv_send://发送按钮
                String content = mInfo.getText().toString();
                String contact = etContact.getText().toString();
                if (TextUtils.isEmpty(contact.trim())) {
                    ToastUtils.showToast(this, getString(R.string.please_contact));
                    return;
                }
                if (TextUtils.isEmpty(content.trim())) {
                    ToastUtils.showToast(this, getString(R.string.please_feedback_shoulder));
                    return;
                }
                List<String> list = new ArrayList<>();
                list.add(contact);
                list.add(content);
                presenter.onResume(this, list);
                break;
        }
    }

    @Override
    protected int initContentView() {
        return R.layout.activity_feedback;
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

    }

    @Override
    protected FeedBackPresenter getPresenter() {
        return new FeedBackPresenter();
    }

    @Override
    public void setText(Object text) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
