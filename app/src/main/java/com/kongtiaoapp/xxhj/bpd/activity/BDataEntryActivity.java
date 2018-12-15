package com.kongtiaoapp.xxhj.bpd.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.BPD_DataEntryBean;
import com.kongtiaoapp.xxhj.bpd.fragment.BDataEntryDetailFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BDataEntryActivity extends FragmentActivity {
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.frm_dataEntry)
    FrameLayout frm_dataEntry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bdata_entry);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        if (intent != null) {
            BPD_DataEntryBean.ResobjBean.PowerBean bean = (BPD_DataEntryBean.ResobjBean.PowerBean) intent.getSerializableExtra("bpd");
            tv_title.setText(bean.getName());
            BDataEntryDetailFragment fragment = BDataEntryDetailFragment.getInstance();
            Bundle bundle=new Bundle();
            bundle.putSerializable("bpd",bean);
            fragment.setArguments(bundle);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.frm_dataEntry,fragment).commit();
        }

    }

    @OnClick({R.id.iv_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
