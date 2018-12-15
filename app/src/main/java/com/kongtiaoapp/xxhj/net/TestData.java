package com.kongtiaoapp.xxhj.net;

import java.util.ArrayList;

/**
 * Created by Luoye on 2016-6-20.
 */
public class TestData {

    public TestData() {

    }

    private static ArrayList<Moment> moments = new ArrayList<>(9);
    public static final String[] urls = new String[]{
            "http://img.blog.csdn.net/20151115004147583",
            "http://img.blog.csdn.net/20151115004214075",
            "http://img.blog.csdn.net/20151115004236274",
            "http://img.blog.csdn.net/20151115004258230",
            "http://img.blog.csdn.net/20151115004316107",
            "http://img.blog.csdn.net/20151115004348219",
            "http://img.blog.csdn.net/20151115004411358",
            "http://img.blog.csdn.net/20151115004442588",
            "http://img.blog.csdn.net/20151115004502212"
    };

    public static ArrayList<Moment> getData() {

        for (int i = 0; i < 9; i++) {
            Moment moment = new Moment();
            String[] address = new String[(i + 1)];
            for (int j = 0; j <= i; j++) {
                address[j] = urls[j];
            }
            moment.address = address;
            moments.add(moment);
        }
        return moments;
    }

    public static class Moment {
        public String[] address;
    }
}
