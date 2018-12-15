package com.kongtiaoapp.xxhj.activites;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.presenter.NotePresenter;
import com.kongtiaoapp.xxhj.mvp.view.NoteView;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 便签页面
 */
public class NoteActivity extends BaseActivity<NotePresenter, NoteView> implements NoteView {
    @BindView(R.id.txt_send)
    TextView txt_send;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected int initContentView() {
        return R.layout.activity_note;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        String name = getIntent().getStringExtra("name");
        if (name.equals("0")){
            tv_title.setText(getString(R.string.workorder_note));
        }else if (name.equals("1")){
            tv_title.setText(getString(R.string.workorder));
        }else if (name.equals("2")){
            tv_title.setText(getString(R.string.workorder_task));
        }
    }

    @OnClick({R.id.iv_back, R.id.txt_send,R.id.img_small_right,R.id.img_add_peole})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                close_key();
                finish();
                break;
            case R.id.txt_send:

                break;
            case R.id.img_small_right:
                ToastUtils.showToast(this,"点击选取图片");
                break;
            case R.id.img_add_peole:
                ToastUtils.showToast(this,"选择接收人");
                break;
            default:

                break;
        }
    }

    @Override
    protected NotePresenter getPresenter() {
        return new NotePresenter();
    }

    @Override
    public void setText(Object data) {

    }
}
