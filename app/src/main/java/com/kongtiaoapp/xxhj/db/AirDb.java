package com.kongtiaoapp.xxhj.db;


import android.content.Context;

import org.xutils.DbManager;
import org.xutils.x;


public class AirDb {

    private static AirDb airDb;
    public static DbManager db;

    public static synchronized AirDb getInstance() {
        if (airDb == null) {
            airDb = new AirDb();

        }
        return airDb;
    }

    private AirDb() {

    }

    public void CreateLocalDB(Context context) {
        DbManager.DaoConfig daoConfig = new DbManager.DaoConfig();
        daoConfig.setDbName("Air.db");
        daoConfig.setDbVersion(1);
        daoConfig.setAllowTransaction(true);
        daoConfig.setDbOpenListener(new DbManager.DbOpenListener() {

            @Override
            public void onDbOpened(DbManager db) {
                db.getDatabase().enableWriteAheadLogging();
            }
        });
        //数据库升级时方法
        daoConfig.setDbUpgradeListener(new DbManager.DbUpgradeListener() {

            @Override
            public void onUpgrade(DbManager db, int oldVersion,
                                  int newVersion) {
            }
        });

        x.getDb(daoConfig).getDaoConfig();


            db = x.getDb(daoConfig);

    }

}
