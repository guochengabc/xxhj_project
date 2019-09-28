package com.kongtiaoapp.xxhj.sockets;

import android.util.Log;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

public class SocketTask implements Runnable {
    @Override
    public void run() {

        try {
            WebSocketClient mClient=new WebSocketClient(new URI("")) {
                @Override
                public void onOpen(ServerHandshake serverHandshake) {

                }

                @Override
                public void onMessage(String s) {

                }

                @Override
                public void onClose(int i, String s, boolean b) {

                }

                @Override
                public void onError(Exception e) {

                }
            };
            Log.i("ffffffffffff","客户端建立连接");
            mClient.connect();
        } catch (URISyntaxException e) {
            e.printStackTrace();
            Log.i("ffffffffffff","客户端连接异常。。。"+e.getMessage());
        }catch (Exception e){
            Log.i("fffffffffff","其他原因报错了");
        }
    }
}
