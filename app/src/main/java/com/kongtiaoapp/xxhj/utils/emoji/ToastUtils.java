package com.kongtiaoapp.xxhj.utils.emoji;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.R;

/**
 * Created by xxhj_g on 2016/11/28.  吐丝
 */
public class ToastUtils extends Toast {

    private static View inflate = null;
    private static TextView txt_toast;

    /**
     * Construct an empty Toast object.  You must call {@link #setView} before you
     * can call {@link #show}.
     * @param context The context to use.  Usually your {@link Application}
     *                or {@link Activity} object.
     */
    public ToastUtils(Context context) {
        super(context);

    }

    public static void showToast(Context context, String value) {
        Toast toast = Toast.makeText(context, value, Toast.LENGTH_SHORT);
        if (inflate == null) {
            inflate = LayoutInflater.from(context).inflate(R.layout.toast_view, null);
            txt_toast = ((TextView) inflate.findViewById(R.id.txt_toast));
        }
        txt_toast.setText(value);
        txt_toast.setTextColor(context.getResources().getColor(R.color.toast_color));
        toast.setView(inflate);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public static void showToast(Context context, String value, int color, String t) {
        Toast toast = Toast.makeText(context, value, Toast.LENGTH_SHORT);
        if (inflate == null) {
            inflate = LayoutInflater.from(context).inflate(R.layout.toast_view, null);
            txt_toast = ((TextView) inflate.findViewById(R.id.txt_toast));
        }
        txt_toast.setText(value);
        txt_toast.setTextColor(color);
        toast.setView(inflate);
        // toast.setGravity(Gravity.BOTTOM, 0, 0);
        toast.show();
    }
    public static void showToast( String value) {
        Toast toast = Toast.makeText(App.application, value, Toast.LENGTH_SHORT);
        if (inflate == null) {
            inflate = LayoutInflater.from(App.application).inflate(R.layout.toast_view, null);
            txt_toast = ((TextView) inflate.findViewById(R.id.txt_toast));
        }
        txt_toast.setText(value);
        toast.setView(inflate);
        // toast.setGravity(Gravity.BOTTOM, 0, 0);
        toast.show();
    }

    public static void showToast(Context context, String value, int location) {
        Toast toast = Toast.makeText(context, value, Toast.LENGTH_SHORT);
        if (inflate == null) {
            inflate = LayoutInflater.from(context).inflate(R.layout.toast_view, null);
            txt_toast = ((TextView) inflate.findViewById(R.id.txt_toast));
        }
        txt_toast.setText(value);
        txt_toast.setTextSize(17);
        toast.setView(inflate);
        if (location == 1) {
            toast.setGravity(Gravity.CENTER, 0, 0);
        } else if (location == 2) {
            toast.setGravity(Gravity.TOP, 0, 0);

        }
        toast.show();
    }

}
