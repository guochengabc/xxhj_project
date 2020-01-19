package com.kongtiaoapp.xxhj.interfaces;

/**
 * 变配电顶部列表专用
 */
public interface BPD_PaintUpdate {
    /**
     * @param deviceIdNew
     * @param paramTypeNew 点击列表获取图表更新设备id和参数类型
     */
    void updatePaint(String deviceIdNew, String paramTypeNew);
}
