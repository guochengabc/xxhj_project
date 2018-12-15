package com.kongtiaoapp.xxhj.service;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.gson.Gson;
import com.igexin.sdk.GTIntentService;
import com.igexin.sdk.PushConsts;
import com.igexin.sdk.PushManager;
import com.igexin.sdk.message.FeedbackCmdMessage;
import com.igexin.sdk.message.GTCmdMessage;
import com.igexin.sdk.message.GTTransmitMessage;
import com.igexin.sdk.message.SetTagCmdMessage;
import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.activites.AllActivityManager;
import com.kongtiaoapp.xxhj.workorder.activity.EngineerRepairActivity;
import com.kongtiaoapp.xxhj.prefessionalknowledge.activity.HaoYouMsgActivity;
import com.kongtiaoapp.xxhj.login_register.activity.LoginActivity;
import com.kongtiaoapp.xxhj.activites.MainActivity;
import com.kongtiaoapp.xxhj.workorder.activity.WorkOrderActivity;
import com.kongtiaoapp.xxhj.activites.ZhenDuanMsgActivity;
import com.kongtiaoapp.xxhj.duty.activity.ZhiBanSubmitActivity;
import com.kongtiaoapp.xxhj.afinal.UserManegerList;
import com.kongtiaoapp.xxhj.bean.ServiceBean;

/**
 * 继承 GTIntentService 接收来自个推的消息, 所有消息在线程中回调, 如果注册了该服务, 则务必要在 AndroidManifest中声明, 否则无法接受消息<br>
 * onReceiveMessageData 处理透传消息<br>
 * onReceiveClientId 接收 cid <br>
 * onReceiveOnlineState cid 离线上线通知 <br>
 * onReceiveCommandResult 各种事件处理回执 <br>
 */
public class GetInfo_IntentService extends GTIntentService {
    private static final String TAG = "GetuiSdkDemo";

    /**
     * 为了观察透传数据变化.
     */
    private static int cnt;

    public GetInfo_IntentService() {

    }

    @Override
    public void onReceiveServicePid(Context context, int pid) {
        Log.d(TAG, "onReceiveServicePid -> " + pid);
    }

    @Override
    public void onReceiveMessageData(Context context, GTTransmitMessage msg) {
        String appid = msg.getAppid();
        String taskid = msg.getTaskId();
        String messageid = msg.getMessageId();
        byte[] payload = msg.getPayload();
        String pkg = msg.getPkgName();
        String cid = msg.getClientId();

        // 第三方回执调用接口，actionid范围为90000-90999，可根据业务场景执行
        boolean result = PushManager.getInstance().sendFeedbackMessage(context, taskid, messageid, 90001);
        if (payload == null) {
            Intent intent = new Intent(context, ZhiBanSubmitActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            this.getApplicationContext().startActivity(intent);
        } else {
            String data = new String(payload);
            Gson gson = new Gson();
            ServiceBean s = gson.fromJson(data, ServiceBean.class);

            String action = s.getAction();
            Intent intent = new Intent();
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            if (action.equals("ForceOffline")) {
                App.sp.setLoginState(false);
                App.sp.resetSp();
                AllActivityManager.getInstance().finishAllActivity();
                intent.putExtra("quit_login", "quit_login");
                intent.setClass(context, LoginActivity.class);
            } else if (action.equals("OffDuty")) {
                intent.setClass(context, ZhiBanSubmitActivity.class);
            } else if (action.equals("OpenMessage")) {
                intent.putExtra("category", s.getMessage());
                Log.i("fffffffffffff", "======推送=======" + s.getMessage());
                if (s.getMessage().equals("A")) {
                    intent.setClass(context, ZhenDuanMsgActivity.class);
                } else if (s.getMessage().equals("B")) {
                    intent.setClass(context, ZhenDuanMsgActivity.class);
                } else if (s.getMessage().equals("G")) {//工单
                    intent.setClass(context, ZhenDuanMsgActivity.class);
                } else if (s.getMessage().equals("D")) {
                    intent.setClass(context, HaoYouMsgActivity.class);
                } else if (s.getMessage().equals("E")) {
                    intent.setClass(context, ZhenDuanMsgActivity.class);
                }
            } else if (action.equals("OpenRepairInfo")) {

                String[] message = s.getMessage().split("--");
                if (message != null) {
                    if (UserManegerList.WORKORDER_DISP() || UserManegerList.WORKORDER_MANAGER()) {
                        int status = Integer.parseInt(message[1]);
                        String repairContent = "";
                        if (status < 2) {
                            if (message.length == 3) {
                                repairContent = message[2];
                            }else {
                                intent.putExtra("dispatchId", message[0]);
                            }
                            intent.putExtra("ismodify", true);
                            intent.putExtra("contentName", repairContent);
                            intent.setClass(context, WorkOrderActivity.class);
                        } else {
                            intent.putExtra("dispatchId", message[0]);
                            intent.setClass(context, EngineerRepairActivity.class);
                        }
                    } else {
                        intent.putExtra("dispatchId", message[0]);
                        intent.setClass(context, EngineerRepairActivity.class);
                    }

                }


            } else if (action.equals("RefreshRepair")) {
                if (App.sp.getIsMain().equals("true")) {
                    if (App.sp.getIsMainSecond().equals("true")) {
                        intent.putExtra("rStatus", App.sp.getRStatus());
                        intent.setClass(context, MainActivity.class);
                    }
                } else {

                }
            }
            context.startActivity(intent);
        }
    }

    @Override
    public void onReceiveClientId(Context context, String clientid) {
        Log.e(TAG, "onReceiveClientId -> " + "clientid = " + clientid);
    }

    @Override
    public void onReceiveOnlineState(Context context, boolean online) {
        Log.d(TAG, "onReceiveOnlineState -> " + (online ? "online" : "offline"));
    }

    @Override
    public void onReceiveCommandResult(Context context, GTCmdMessage cmdMessage) {
        Log.d(TAG, "onReceiveCommandResult -> " + cmdMessage);
        int action = cmdMessage.getAction();
        if (action == PushConsts.SET_TAG_RESULT) {
            setTagResult((SetTagCmdMessage) cmdMessage);
        } else if ((action == PushConsts.THIRDPART_FEEDBACK)) {
            feedbackResult((FeedbackCmdMessage) cmdMessage);
        }
    }

    private void setTagResult(SetTagCmdMessage setTagCmdMsg) {
        String sn = setTagCmdMsg.getSn();
        String code = setTagCmdMsg.getCode();

        String text = "设置标签失败, 未知异常";
        switch (Integer.valueOf(code)) {
            case PushConsts.SETTAG_SUCCESS:
                text = "设置标签成功";
                break;
            case PushConsts.SETTAG_ERROR_COUNT:
                text = "设置标签失败, tag数量过大, 最大不能超过200个";
                break;
            case PushConsts.SETTAG_ERROR_FREQUENCY:
                text = "设置标签失败, 频率过快, 两次间隔应大于1s且一天只能成功调用一次";
                break;
            case PushConsts.SETTAG_ERROR_REPEAT:
                text = "设置标签失败, 标签重复";
                break;
            case PushConsts.SETTAG_ERROR_UNBIND:
                text = "设置标签失败, 服务未初始化成功";
                break;
            case PushConsts.SETTAG_ERROR_EXCEPTION:
                text = "设置标签失败, 未知异常";
                break;
            case PushConsts.SETTAG_ERROR_NULL:
                text = "设置标签失败, tag 为空";
                break;
            case PushConsts.SETTAG_NOTONLINE:
                text = "还未登陆成功";
                break;
            case PushConsts.SETTAG_IN_BLACKLIST:
                text = "该应用已经在黑名单中,请联系售后支持!";
                break;

            case PushConsts.SETTAG_NUM_EXCEED:
                text = "已存 tag 超过限制";
                break;

            default:
                break;
        }
        Log.d(TAG, "settag result sn = " + sn + ", code = " + code + ", text = " + text);
    }

    private void feedbackResult(FeedbackCmdMessage feedbackCmdMsg) {
        String appid = feedbackCmdMsg.getAppid();
        String taskid = feedbackCmdMsg.getTaskId();
        String actionid = feedbackCmdMsg.getActionId();
        String result = feedbackCmdMsg.getResult();
        long timestamp = feedbackCmdMsg.getTimeStamp();
        String cid = feedbackCmdMsg.getClientId();
        Log.d(TAG, "onReceiveCommandResult -> " + "appid = " + appid + "\ntaskid = " + taskid + "\nactionid = " + actionid + "\nresult = " + result
                + "\ncid = " + cid + "\ntimestamp = " + timestamp);
    }

 /*   private void sendMessage(String data, int what) {
        Message msg = Message.obtain();
        msg.what = what;
        msg.obj = data;

    }*/
}
