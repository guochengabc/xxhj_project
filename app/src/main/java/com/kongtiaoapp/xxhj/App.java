package com.kongtiaoapp.xxhj;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.kongtiaoapp.xxhj.db.AirDb;
import com.kongtiaoapp.xxhj.db.XUtil;
import com.kongtiaoapp.xxhj.utils.ImageLoaderHelper;
import com.kongtiaoapp.xxhj.utils.SpUtils;

import org.xutils.DbManager;
import org.xutils.x;

import im.fir.sdk.FIR;

/**
 * ━━━━ Code is far away from ━━━━━━
 * 　　  () 　　　  ()
 * 　　  ( ) 　　　( )
 * 　　  ( ) 　　　( )
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　┻　　　┃
 * 　　┗━┓　　　┏━┛
 * 　　　　┃　　　┃
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 * ━━━━ bug with the XYY protecting━━━
 * <p>
 * Created by Seny on 2015/12/1.
 */
public class App extends Application {

    /**
     * 全局Context，方便引用
     */
    public static App application;

    /**
     * 初始化SP&EDIT
     */
    public static SpUtils sp;
    protected static DbManager db;

    @Override
    public void onCreate() {
        super.onCreate();

        application = this;
        ImageLoaderHelper.initImageLoader(this);//初始化ImageLoader
        FIR.init(this);
        x.Ext.init(this);
        x.Ext.setDebug(true);
        AirDb.getInstance().CreateLocalDB(this);//创建本地数据库
        db = getDb();
        //初始化通用的SP&EDIT
        sp = new SpUtils(this);
        //  LeakCanary.install(this);
    }

    public static DbManager getDb() {
        DbManager.DaoConfig daoConfig = XUtil.getDaoConfig();
        db = x.getDb(daoConfig);
        return db;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
