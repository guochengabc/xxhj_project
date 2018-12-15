package com.kongtiaoapp.xxhj.bean;

/**
 * Created by Luoye on 2016-9-13.
 * 说明:
 */
public class GetWeather extends RBResponse {

    /**
     * Rainy : {"code":"101","txt":"多云"}
     * Pm25 : 104
     * OutTemp :
     * InTemp :
     * Humidity : 64
     * City : 北京市
     * Temp : 32
     * Windy : {"dir":"南风","deg":"180","sc":"5-6","spd":"33"}
     */

    private WeatherInfo resobj;

    public WeatherInfo getResobj() {
        return resobj;
    }

    public void setResobj(WeatherInfo resobj) {
        this.resobj = resobj;
    }

    public static class WeatherInfo {
        /**
         * code : 101
         * txt : 多云
         */
        private RainyBean Rainy;
        private String Pm25;
        private String OutTemp;
        private String InTemp;
        private String Humidity;
        private String City;
        private String Temp;
        private String InPm25;

        public String getInPm25() {
            return InPm25;
        }

        public void setInPm25(String inPm25) {
            InPm25 = inPm25;
        }

        /**
         * dir : 南风
         * deg : 180
         * sc : 5-6
         * spd : 33
         */

        private WindyBean Windy;
        // CO2浓度
        private String InCO2;
        // 空气质量
        private String Qlty;

        public String getInCO2() {
            return InCO2;
        }

        public void setInCO2(String inCO2) {
            InCO2 = inCO2;
        }

        public String getQlty() {
            return Qlty;
        }

        public void setQlty(String qlty) {
            Qlty = qlty;
        }

        public RainyBean getRainy() {
            return Rainy;
        }

        public void setRainy(RainyBean Rainy) {
            this.Rainy = Rainy;
        }

        public String getPm25() {
            return Pm25;
        }

        public void setPm25(String Pm25) {
            this.Pm25 = Pm25;
        }

        public String getOutTemp() {
            return OutTemp;
        }

        public void setOutTemp(String OutTemp) {
            this.OutTemp = OutTemp;
        }

        public String getInTemp() {
            return InTemp;
        }

        public void setInTemp(String InTemp) {
            this.InTemp = InTemp;
        }

        public String getHumidity() {
            return Humidity;
        }

        public void setHumidity(String Humidity) {
            this.Humidity = Humidity;
        }

        public String getCity() {
            return City;
        }

        public void setCity(String City) {
            this.City = City;
        }

        public String getTemp() {
            return Temp;
        }

        public void setTemp(String Temp) {
            this.Temp = Temp;
        }

        public WindyBean getWindy() {
            return Windy;
        }

        public void setWindy(WindyBean Windy) {
            this.Windy = Windy;
        }

        public static class RainyBean {
            private String code;
            private String txt;
            private String icon;

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getTxt() {
                return txt;
            }

            public void setTxt(String txt) {
                this.txt = txt;
            }
        }

        public static class WindyBean {
            private String dir;
            private String deg;
            private String sc;
            private String spd;

            public String getDir() {
                return dir;
            }

            public void setDir(String dir) {
                this.dir = dir;
            }

            public String getDeg() {
                return deg;
            }

            public void setDeg(String deg) {
                this.deg = deg;
            }

            public String getSc() {
                return sc;
            }

            public void setSc(String sc) {
                this.sc = sc;
            }

            public String getSpd() {
                return spd;
            }

            public void setSpd(String spd) {
                this.spd = spd;
            }
        }
    }
}
