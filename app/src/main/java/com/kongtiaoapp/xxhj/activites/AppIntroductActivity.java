package com.kongtiaoapp.xxhj.activites;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.presenter.AppIntroductPresenter;
import com.kongtiaoapp.xxhj.mvp.view.AppIntroductView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AppIntroductActivity extends BaseActivity<AppIntroductPresenter, AppIntroductView> implements AppIntroductView {
    @BindView(R.id.webView)
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected int initContentView() {
        return R.layout.activity_app_introduct;
    }

    @Override
    protected void initView() {
        initWebView();
        webView.loadUrl("http://b.xiumi.us/board/v5/2zgwd/34933456");
    }

    private void initWebView() {

        WebSettings settings = webView.getSettings();
        settings.setAllowFileAccess(true);
        settings.setDatabaseEnabled(false);
        settings.setSupportZoom(false);//设置可以支持缩放
        settings.setBuiltInZoomControls(false);//设置出现缩放工具
        settings.setUseWideViewPort(true);//设置webview推荐使用的窗口
        settings.setLoadWithOverviewMode(true);//设置webview加载的页面的模式
//        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        String dir = App.application
                .getDir("database", Context.MODE_PRIVATE).getPath();
        settings.setDatabasePath(dir);
        settings.setDomStorageEnabled(true);
        settings.setGeolocationEnabled(true);
        settings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new myWebClient());
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

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected AppIntroductPresenter getPresenter() {
        return new AppIntroductPresenter();
    }

    @OnClick({R.id.iv_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void setText(Object data) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
