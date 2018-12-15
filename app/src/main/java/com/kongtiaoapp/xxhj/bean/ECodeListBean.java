package com.kongtiaoapp.xxhj.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by G_XXHJ on 2018/3/29.
 */

public class ECodeListBean extends RBResponse {


    /**
     * code : 40000
     * resobj : [{"DeviceId":"002ac93d7f6b483d9563abac51a2ba84","Name":"1号冷冻水泵","Type":"ldsbcs"},{"DeviceId":"007965af26a04fd4bdaff32da83d5df7","Name":"2#软水装置","Type":"rhszzcs"},{"DeviceId":"0e25a22657ab4544a0b2163a347d2030","Name":"医院供热支路","Type":"grzlcs"},{"DeviceId":"1a95ae67e99748b3a07d3b9d52138972","Name":"4号供热支路","Type":"grzlcs"},{"DeviceId":"1e3c423eaa404679a24c8b7f3cc54f4e","Name":"1号集水器","Type":"jsqcs"},{"DeviceId":"200cdfcfb3744853a8e2a6c2797425b5","Name":"民生直燃机冷却水泵","Type":"lqsbcs"},{"DeviceId":"2a5cba8ef7c5443bbb9881f52ba7529e","Name":"2号冷冻支路","Type":"ldzlcs"},{"DeviceId":"2be02205766d435591be8a84deb706ca","Name":"2号冷机冷却水泵","Type":"lqsbcs"},{"DeviceId":"2ecf8b7989234112b45337179848b4d2","Name":"蓝标直燃机","Type":"zrjcs"},{"DeviceId":"310a5ccc78a94f59b561f3e70cfda62f","Name":"1号空调泵","Type":"rsxhbcs"},{"DeviceId":"386ab432dc9040479e7bfd57aed0917e","Name":"3号冷冻支路","Type":"ldzlcs"},{"DeviceId":"38970d193e974ff89d04faf506126c0f","Name":"1号冷机冷却水泵","Type":"lqsbcs"},{"DeviceId":"3cb3e0855b344774ac6d65a3ee237e94","Name":"园区供热支路","Type":"grzlcs"},{"DeviceId":"3d4a930939a14c4491d64ea4f05f3c1a","Name":"锅炉1号热水循环泵","Type":"rsxhbcs"},{"DeviceId":"3fcc6651f4dc40c6843ebaef6cfd8bac","Name":"1号供热支路","Type":"grzlcs"},{"DeviceId":"4a3e73e50fb34a82894bed7b208162b8","Name":"1号锅炉","Type":"glcs"},{"DeviceId":"57612e746e324634a00b54880a3f76e0","Name":"2号供热支路","Type":"grzlcs"},{"DeviceId":"5800e283407b4065b3a80cf2d5cafde3","Name":"1号冷冻支路","Type":"ldzlcs"},{"DeviceId":"5be32d14de7b4e5286ac62faad67bb4f","Name":"5号冷却塔","Type":"lqtcs"},{"DeviceId":"5f62cc12eaca4d129689c498271e5953","Name":"民生直燃机","Type":"zrjcs"},{"DeviceId":"65dec05ffad3405abd6ecf75e7266871","Name":"2号冷却塔","Type":"lqtcs"},{"DeviceId":"6beda906a2234e0dadccdab660dbea53","Name":"暖气1号板换","Type":"bhcs"},{"DeviceId":"6cfde2108efa4c5292a101cc0f3f1ef8","Name":"6号冷却塔","Type":"lqtcs"},{"DeviceId":"7606905e0226494f8b2b0ca0ebc50626","Name":"2号约克冷机","Type":"ljcs"},{"DeviceId":"7a730b7f139343b7afb0cabe8460f7c4","Name":"暖气2号板换","Type":"bhcs"},{"DeviceId":"7ebdbb78d93044b6929fb7e65d788704","Name":"4号板换","Type":"bhcs"},{"DeviceId":"8021adcd2c3c4436877f670787626f55","Name":"3号板换","Type":"bhcs"},{"DeviceId":"85a61bc455e84777b2a3980e7d9172a0","Name":"1号板换","Type":"bhcs"},{"DeviceId":"8a95617932b3401585ab99c7c7c6f5a5","Name":"3号供热支路","Type":"grzlcs"},{"DeviceId":"8dc37010422f4661804b729f622c5acd","Name":"2号板换","Type":"bhcs"},{"DeviceId":"9e54b6045bce4791bd4ea10d7e5f90d7","Name":"空调1号板换","Type":"bhcs"},{"DeviceId":"b5ab49b39dd346e3b60b1bc511cb2ce3","Name":"4号冷却塔","Type":"lqtcs"},{"DeviceId":"b8ad2fadb3d44ad88b48f2d3aa21db9e","Name":"3号冷冻水泵","Type":"ldsbcs"},{"DeviceId":"beca07bd4f064c1f9f0f413054a10bb6","Name":"1号约克冷机","Type":"ljcs"},{"DeviceId":"c3142f4f60434200bfeec39fe9be487f","Name":"4号冷冻支路","Type":"ldzlcs"},{"DeviceId":"cbc096e9a5f04f21a3096f83cc38eaaa","Name":"2号冷冻水泵","Type":"ldsbcs"},{"DeviceId":"ce7a19085c8146b89b2b9326914dba57","Name":"蓝标供热支路","Type":"grzlcs"},{"DeviceId":"d17ecfee94d84b448bd55f007e71670a","Name":"暖气1号泵","Type":"rsxhbcs"},{"DeviceId":"d5a11eb1676f499585ab404254aa4bb9","Name":"3号冷却塔","Type":"lqtcs"},{"DeviceId":"d692de4043e1458abc7e2f9c53e8b055","Name":"1#软水装置","Type":"rhszzcs"},{"DeviceId":"d7b70442b7784d2998c73da1844803d8","Name":"3号冷却水泵","Type":"ldsbcs"},{"DeviceId":"d7cfce25f6574c2d84643ff106ec86af","Name":"暖气2号热水循环泵","Type":"rsxhbcs"},{"DeviceId":"e1455719715a41c3b52c6febdab21059","Name":"锅炉2号热水循环泵","Type":"rsxhbcs"},{"DeviceId":"e19543a5d5a348ceb50cdcf8aa88f32d","Name":"4号空调泵","Type":"rsxhbcs"},{"DeviceId":"e3d9b831762a405d8be0438a3fb511e9","Name":"蓝标直燃机冷却水泵","Type":"lqsbcs"},{"DeviceId":"e5af17beea14469087135b45e39518a9","Name":"3号空调泵","Type":"rsxhbcs"},{"DeviceId":"e7307e6d36674370a0d0fe6f0881f29b","Name":"空调2号板换","Type":"bhcs"},{"DeviceId":"e8aafd78f37a4c8b97d0978ba7c01e83","Name":"2号空调泵","Type":"rsxhbcs"},{"DeviceId":"e8aafd78f37a4c8b97d0978ba7c554pl","Name":"锅炉循环泵","Type":"rsxhbcs"},{"DeviceId":"ed322d3170e541b187ede1dfeeb2aba3","Name":"4号冷冻水泵","Type":"ldsbcs"},{"DeviceId":"ee760365a8a14338b2fa9b3f485ff167","Name":"2号锅炉","Type":"glcs"},{"DeviceId":"f164bf00640a4a5782396e733c599c04","Name":"1号冷却塔","Type":"lqtcs"},{"DeviceId":"f171f3a64b974d38b7e05c34e23b24a5","Name":"暖气供热支路","Type":"grzlcs"},{"DeviceId":"f3e5f87cd80c458a9e529b5e51741c38","Name":"补水泵","Type":"bsbcs"}]
     */

    private List<ResobjBean> resobj;

    public List<ResobjBean> getResobj() {
        return resobj;
    }

    public void setResobj(List<ResobjBean> resobj) {
        this.resobj = resobj;
    }

    public static class ResobjBean implements Serializable {
        /**
         * DeviceId : 002ac93d7f6b483d9563abac51a2ba84
         * Name : 1号冷冻水泵
         * Type : ldsbcs
         */

        private String DeviceId;
        private String SensorId;
        private String Name;
        private String Type;
        private String Flag;

        public String getSensorId() {
            return SensorId;
        }

        public void setSensorId(String sensorId) {
            SensorId = sensorId;
        }

        public String getFlag() {
            return Flag;
        }

        public void setFlag(String flag) {
            Flag = flag;
        }

        public String getDeviceId() {
            return DeviceId;
        }

        public void setDeviceId(String DeviceId) {
            this.DeviceId = DeviceId;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public String getType() {
            return Type;
        }

        public void setType(String Type) {
            this.Type = Type;
        }
    }
}
