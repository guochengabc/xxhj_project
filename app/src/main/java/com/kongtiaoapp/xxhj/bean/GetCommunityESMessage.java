package com.kongtiaoapp.xxhj.bean;

import java.util.List;

/**
 * Created by Luoye on 2016-10-17.
 * 说明:
 */
public class GetCommunityESMessage extends RBResponse {

    private ResobjBean resobj;

    public ResobjBean getResobj() {
        return resobj;
    }

    public void setResobj(ResobjBean resobj) {
        this.resobj = resobj;
    }

    public static class ResobjBean {
        private List<String> A;
        private List<String> B;
        private List<String> C;

        public List<String> getA() {
            return A;
        }

        public void setA(List<String> A) {
            this.A = A;
        }

        public List<String> getB() {
            return B;
        }

        public void setB(List<String> B) {
            this.B = B;
        }

        public List<String> getC() {
            return C;
        }

        public void setC(List<String> C) {
            this.C = C;
        }
    }
}
