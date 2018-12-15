package com.kongtiaoapp.xxhj.db;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;
/**
 * Created by Luoye on 2016-9-15.
 * 说明:
 */
@Table(name = "project")
public class ProjectTable {
    @Column(name = "id", isId = true, autoGen = true)
    private int id;
    @Column(name = "projectId")
    private String projectId;
    @Column(name = "deviceId")
    private String deviceId;
    @Column(name = "name")
    private String name;
    @Column(name = "isSave")
    private boolean isSave;//是否保存过
    @Column(name = "info")
    private String info;
    public boolean isSave() {
        return isSave;
    }

    public void setSave(boolean save) {
        isSave = save;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
