package com.kongtiaoapp.xxhj.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.afinal.HttpMethod;
import com.kongtiaoapp.xxhj.bean.RBResponse;
import com.kongtiaoapp.xxhj.db.ProjectTable;
import com.kongtiaoapp.xxhj.db.SensorIdListTable;
import com.kongtiaoapp.xxhj.net.okhttp.Encode_params;
import com.kongtiaoapp.xxhj.net.okhttp.Get_NOActivity_Task;
import com.kongtiaoapp.xxhj.net.okhttp.ParamJson;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;
import org.xutils.DbManager;
import org.xutils.ex.DbException;
import java.util.HashMap;
import java.util.Map;


/**
 * 2018-8-11  By  GC
 */
public class RecordCommitService extends IntentService {
    //以下是数据库管理内容
    private DbManager db;
    private ProjectTable project;
    private SensorIdListTable sensorTable;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            ToastUtils.showToast(getApplicationContext(), "=====");
            Intent intent = (Intent) msg.obj;
            String sensorList = intent.getStringExtra("sensorList");
            String energyInfoList = intent.getStringExtra("energyInfoList");
            Log.i("ffffffffffffff", "====sensorList======" + sensorList + "===energyInfoList====" + energyInfoList);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("sensorList", sensorList);
            map.put("energyInfoList", energyInfoList);
            Map<String, String> params = new HashMap<String, String>();//POSTBATCHENTRYINFO
            params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.POSTBATCHENTRYINFO, map, ""));
            new Get_NOActivity_Task<RBResponse>(getApplicationContext(), RBResponse.class, ConstantValue.HTTP_URLS + Encode_params.YesToken_Encodeparms(params), new ResponseXXHJListener<RBResponse>() {
                @Override
                public void requuestError(int code) {

                }

                @Override
                public void requestSuccess(RBResponse rbResponse) {
                    if (rbResponse.getCode() == 40000) {
                        deleteEnergyTable();//删除能耗记录表
                    }
                }
            }).execute();
        }
    };

    private void deleteEnergyTable() {
        ToastUtils.showToast(getApplicationContext(), "能源录入数据已提交到服务器");
        try {
            sensorTable = db.selector(SensorIdListTable.class).where("projectId", "=", App.sp.getProjectId()).findFirst();
            String sensorId = sensorTable.getSensorId();
            if (sensorId.contains("--")) {
                String[] sendorIdList = sensorId.split(ConstantValue.SPLIT_ENERGY);
                for (int i = 0; i < sendorIdList.length; i++) {
                    project = db.selector(ProjectTable.class).where("projectId", "=", App.sp.getProjectId()).and("deviceId", "=", sendorIdList[i]).findFirst();
                    db.delete(project);
                }
            } else {
                project = db.selector(ProjectTable.class).where("projectId", "=", App.sp.getProjectId()).and("deviceId", "=", sensorId).findFirst();
                db.delete(project);
            }
            db.delete(sensorTable);
        } catch (DbException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public RecordCommitService() {
        super("RecordCommitService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        db = App.getDb();//得到db
        Message message = Message.obtain();
        message.obj = intent;
        handler.sendMessage(message);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("ffffffffff","service已销毁===");
    }
}
