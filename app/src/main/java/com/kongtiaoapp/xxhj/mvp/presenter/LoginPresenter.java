package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;
import android.content.Intent;

import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.activites.MainActivity;
import com.kongtiaoapp.xxhj.bean.LoginBean;
import com.kongtiaoapp.xxhj.bean.ProjectListBean;
import com.kongtiaoapp.xxhj.bean.UserInfo;
import com.kongtiaoapp.xxhj.bean.UserInfoEntity;
import com.kongtiaoapp.xxhj.db.AirDb;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.Loginlpl;
import com.kongtiaoapp.xxhj.mvp.view.LoginView;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import org.xutils.ex.DbException;

import java.util.List;

/**
 * Created by xxhj_g on 2017/5/4.
 */
public class LoginPresenter extends BasePresenterLpl<LoginView, Loginlpl> {
    @Override
    protected Loginlpl getModel() {
        return new Loginlpl();
    }

    public void onResume(Activity activity, List list) {
        getModel().getDataForservices(activity, list, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                LoginBean loginBean = (LoginBean) o;
                if (loginBean.getCode() == 40000) {
                    getView().setText(loginBean);
                } else {
                    ToastUtils.showToast(activity, loginBean.getErrormsg());
                }
            }
        });
    }

    public void getUserInfo(Activity activity, String param) {
        getModel().getUserInfo(activity, param, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                UserInfo userInfo = (UserInfo) o;
                if (userInfo.getCode() == 40000) {
                    UserInfo.ResobjBean info = userInfo.getResobj();
                    try {
                        UserInfoEntity entity = new UserInfoEntity();
                        entity.setPhone(info.getPhone());//电话
                        entity.setCredits(info.getCredits());//积分
                        entity.setSuperiorId(info.getSuperiorId());//上级ID
                        entity.setLocation(info.getLocation());//地址
                        entity.setUserName(info.getUserName());//用户名
                        entity.setUserId(param);//uid
                        entity.setCity(info.getCity());//城市
                        entity.setOnLineTime(info.getOnLineTime());//在线时长
                        entity.setEmail(info.getEmail());//邮箱
                        entity.setInterest(info.getInterest());//爱好
                        entity.setOrgId(info.getOrgId());
                        entity.setLevel(info.getLevel());//等级
                        entity.setSchool(info.getSchool());//学校
                        entity.setAvatar(info.getAvatarUrl());//头像
                        entity.setGender(info.getGender());//性别
                        entity.setOrgName(info.getOrgName());
                        AirDb.db.saveOrUpdate(entity);
                        //SP 存储
                        App.sp.setUid(param);
                        App.sp.setHeadPath(info.getAvatarUrl());
                        App.sp.setName(info.getUserName());
                        App.sp.setPhone(info.getPhone());
                        App.sp.setLoginState(true);
                        Intent intent = new Intent(activity, MainActivity.class);
                        intent.putExtra("main", "resume");
                        activity.startActivity(intent);
                        activity.finish();

                    } catch (DbException e) {
                        e.printStackTrace();
                    }

                } else {
                    //   showToast(TAG, "获取个人信息失败");
                    ToastUtils.showToast(activity, userInfo.getErrormsg());
                }

            }
        });
    }
    public void getProjectList(Activity activity){
        getModel().getProjectList(activity, "", new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                ProjectListBean bean= (ProjectListBean) o;
                if (bean.getCode()==SUCCEDD){
                        getView().getProjectList(bean);
                }else if (bean.getCode()==EMPTY){
                    ToastUtils.showToast(activity,activity.getString(R.string.no_data));
                }else{
                    ToastUtils.showToast(activity,bean.getErrormsg());
                }
            }
        });
    }
}
