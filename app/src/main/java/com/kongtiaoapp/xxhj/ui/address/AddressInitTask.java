package com.kongtiaoapp.xxhj.ui.address;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.alibaba.fastjson.JSON;
import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.activites.ProcjectInfoDetailActivity;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import java.util.ArrayList;

/**
 * 获取地址数据并显示地址选择器
 *
 * @author 李玉江[QQ:1032694760]
 * @version 2015/12/15
 */
public class AddressInitTask extends AsyncTask<String, Void, ArrayList<AddressPicker.Province>> {
    private Activity activity;
    private ProgressDialog dialog;
    private String selectedProvince = "", selectedCity = "", selectedCounty = "";
    private boolean hideCounty = false;

    /**
     * 初始化为不显示区县的模式
     *
     * @param activity
     * @param hideCounty is hide County
     */
    public AddressInitTask(Activity activity, boolean hideCounty) {
        this.activity = activity;
        this.hideCounty = hideCounty;
        dialog = ProgressDialog.show(activity, null, "正在初始化数据...", true, true);
    }

    public AddressInitTask(Activity activity) {
        this.activity = activity;
        dialog = ProgressDialog.show(activity, null, "正在初始化数据...", true, true);
    }

    @Override
    protected ArrayList<AddressPicker.Province> doInBackground(String... params) {
        if (params != null) {
            switch (params.length) {
                case 1:
                    selectedProvince = params[0];
                    break;
                case 2:
                    selectedProvince = params[0];
                    selectedCity = params[1];
                    break;
                case 3:
                    selectedProvince = params[0];
                    selectedCity = params[1];
                    selectedCounty = params[2];
                    break;
                default:
                    break;
            }
        }
        ArrayList<AddressPicker.Province> data = new ArrayList<AddressPicker.Province>();
        try {
            String json = AssetsUtils.readText(activity, "city.json");
            data.addAll(JSON.parseArray(json, AddressPicker.Province.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    protected void onPostExecute(ArrayList<AddressPicker.Province> result) {
        dialog.dismiss();
        if (result.size() > 0) {
            AddressPicker picker = new AddressPicker(activity, result);
            picker.setHideCounty(hideCounty);
            picker.setSelectedItem(selectedProvince, selectedCity, selectedCounty);
            final ProcjectInfoDetailActivity mActivity = (ProcjectInfoDetailActivity) activity;
            picker.setOnAddressPickListener(new AddressPicker.OnAddressPickListener() {
                @Override
                public void onAddressPicked(AddressPicker.Province province, AddressPicker.City city, AddressPicker.County county) {
                    if (county == null) {
//                        Toast.makeText(activity, "province : " + province + ", city: " + city, Toast.LENGTH_LONG).show();
                        mActivity.tvContent2.setText(city.getAreaName());
                        mActivity.cityCode = city.getAreaId();
                    } else {
                        ToastUtils.showToast(activity,"province : " + province + ", city: " + city + ", county: " + county);
                    }
                }
            });
            picker.show();
        } else {
            ToastUtils.showToast(activity,activity.getString(R.string.loading_fail));
        }
    }

}
