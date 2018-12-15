package com.kongtiaoapp.xxhj.mvp.module;

import android.app.Activity;

import com.kongtiaoapp.xxhj.mvp.base.BaseModule;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

import java.util.List;

/**
 * Created by xxhj_g on 2017/5/5.
 */
public interface PublishPostModule extends BaseModule {
    void setUploadPictrue(Activity activity, List<String> list, ResponseXXHJListener listener);
}
