package com.kongtiaoapp.xxhj.utils;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.activites.AllActivityManager;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
    static  Runnable run;
    static String toastContent="";
   static Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 1001:
                    String str = msg.getData().getString("result");
                    App.sp.setEnvironment(str);
                    mHandler.removeCallbacks(run);
                    if (run!=null){
                        run=null;
                    }
                    AllActivityManager.getInstance().finishAllActivity();
                    System.exit(0);
                    break;
                case 1002:
                    String toastContent= (String) msg.obj;
                    ToastUtils.showToast(App.application, toastContent);
                    break;

                default:
                    break;
            }
        }
    };
    public static void StartThread(){

         run = new Runnable() {
            @Override
            public void run() {
                String ret = DBUtil.QuerySQL();
                Message msg = new Message();
                msg.what = 1001;
                Bundle data = new Bundle();
                try {
                String[] content = new String(ret.getBytes(),"UTF-8").split("===");
                    System.out.println("====取到的==="+ret+"==new String(ret.getBytes(),\"UTF-8\")=="+new String(ret.getBytes(),"UTF-8"));
                toastContent= content[1];
                    data.putString("result", content[0]);
                    msg.setData(data);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                mHandler.sendMessageDelayed(msg,1500);
                Message message=new Message();
                message.what=1002;
                message.obj=toastContent;
                mHandler.sendMessage(message);

            }
        };
        new Thread(run).start();
    }
    private static Connection getSQLConnection(String ip, String user, String pwd, String db) {
        Connection con = null;
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:jtds:sqlserver://" + ip + ":1433/" + db + ";useUnicode=true&characterEncoding=utf-8", user, pwd);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        return con;
    }

    public static String QuerySQL() {
        String result = "";
        String toastContent="";
        try {
            String urlType = App.sp.getUrlType();
            int type = Integer.parseInt(urlType);
            Connection conn = getSQLConnection("123.57.15.76", "sa", "xiaoxihuijv", "KTJN_V2");
            String sql = "select * from AppUrlInfo where Type="+type;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            App.sp.setUrlType(((type+1)%2)+"");
            while (rs.next()) {
                result = rs.getString("BaseUrl")==null?"":rs.getString("BaseUrl");
                toastContent=rs.getString("ToastContent")==null?"":rs.getString("ToastContent");
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            result += "查询数据异常!" + e.getMessage();
        }
        try {
            return result+"==="+new String(toastContent.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();

        }
        return "0===1";
    }

}
