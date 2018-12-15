package com.kongtiaoapp.xxhj.mvp.base;

import java.lang.ref.WeakReference;

/**
 * Created by xxhj_g on 2017/4/25.
 */
public abstract class BasePresenterLpl<V extends BaseView,M extends BaseModule> implements BasePresenter{
    private  WeakReference<V> weakReference;
    protected M model;
    protected final int SUCCEDD=40000;//成功回调
    protected final int FAILED=40002;//返回失败，服务器无响应
    protected final int EMPTY=40005;//数据为空
    public void attach(V t) {
        weakReference = new WeakReference<>(t);
        model = getModel();
    }

    public void deAttach() {
        if (weakReference != null) {
            weakReference.clear();
            weakReference = null;
        }
    }

    public boolean isViewAttached() {
        return weakReference != null && weakReference.get() != null;
    }

    public V getView() {
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }
    protected abstract M getModel();


}
