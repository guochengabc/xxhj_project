package com.kongtiaoapp.xxhj.sockets;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class SocketBroadCastReicever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //abortBroadcast();//优先级高的广播截断优先级低的广播
      //  String sockets = intent.getStringExtra("sockets");
        Log.i("ffffffffffffff","====接收到广播==Yes======");
    }
}
