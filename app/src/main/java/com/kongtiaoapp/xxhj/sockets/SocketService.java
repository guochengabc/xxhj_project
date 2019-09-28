package com.kongtiaoapp.xxhj.sockets;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

public class SocketService extends Service {
    public SocketService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        //注册广播
        IntentFilter filter = new IntentFilter("com.kongtiaoapp.xxhj.sockets.SocketBroadCastReicever");
        SocketBroadCastReicever receiver = new SocketBroadCastReicever();
        registerReceiver(receiver, filter, "com.kongtiaoapp.xxhj.gc", null);
        Log.i("fffffffffffffffffffff","======SocketService========");
        super.onCreate();
    }
}
