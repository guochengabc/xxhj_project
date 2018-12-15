package com.kongtiaoapp.xxhj.db;

import org.xutils.DbManager;

/**
 * Created by Luoye on 2016-9-15.
 * 说明:
 */
public class XUtil {
    static DbManager.DaoConfig daoConfig;
    public static DbManager.DaoConfig getDaoConfig(){
//        File file=new File(Environment.getExternalStorageDirectory().getPath());
        if(daoConfig==null){
            daoConfig=new DbManager.DaoConfig()
                    .setDbName("AirProjects.db")
//                    .setDbDir(file)
                    .setDbVersion(1)
                    .setAllowTransaction(true)
                    .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                        @Override
                        public void onUpgrade(DbManager db, int oldVersion, int newVersion) {

                        }
                    });
        }
        return daoConfig;
    }
}
