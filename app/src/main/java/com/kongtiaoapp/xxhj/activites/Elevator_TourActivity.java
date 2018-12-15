package com.kongtiaoapp.xxhj.activites;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Elevator_TourActivity extends Activity {
    @BindView(R.id.txt_create_workorder)//生成工单
    TextView txt_create_workorder;
    @BindView(R.id.edt_exception)
    EditText edt_exception;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elevator__tour);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back,R.id.txt_create_workorder})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.txt_create_workorder:
                if (!TextUtils.isEmpty(edt_exception.getText().toString())){
                    Intent intent=new Intent();
                }
                break;
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
