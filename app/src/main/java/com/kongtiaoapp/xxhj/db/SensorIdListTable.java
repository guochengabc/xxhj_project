package com.kongtiaoapp.xxhj.db;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by Luoye on 2016-9-15.  能源录入里面的设备id的表 sensorId
 * 说明:
 */
@Table(name = "sensorId")
public class SensorIdListTable {
    @Column(name = "id", isId = true, autoGen = true)
    private int id;
    @Column(name = "isSave")
    private boolean isSave;//是否保存过
    @Column(name = "sensorId")
    private String sensorId;
    @Column(name = "projectId")
    private String projectId;

    public boolean isSave() {
        return isSave;
    }

    public void setSave(boolean save) {
        isSave = save;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSensorId() {
        return sensorId;
    }

    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}
