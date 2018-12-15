package com.kongtiaoapp.xxhj.net.okhttp;

import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.utils.NetworkUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by xxhj_g on 2016/12/20.
 */
public class HttpsUtils_Certificater {
    public static final int cacheSize = 50 * 1024 * 1024; // 50 MiB
    public static SSLSocketFactory sslSocketFactory;
    public static File httpCacheDirectory = null;
    private static Cache cache = null;

    /**
     * getTrustSslSocketFactory信任所有证书
     */
    public static OkHttpClient getclient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(3000, TimeUnit.SECONDS)//设置连接超时时间
                .readTimeout(3000,TimeUnit.SECONDS)
                .writeTimeout(3000,TimeUnit.SECONDS)
                .sslSocketFactory(getTrustSslSocketFactory())
                .hostnameVerifier(getHostnameVerifier())
                .addInterceptor(getInterceptor())
                .cache(cache);
        return builder.build();
    }


    public static Interceptor getInterceptor() {

        if (httpCacheDirectory == null) {
            httpCacheDirectory = new File(App.application.getCacheDir(), "responses");
        }
        if (cache == null) {

            cache = new Cache(httpCacheDirectory, cacheSize);
        }
        Interceptor interceptor = chain -> {

            CacheControl.Builder cacheBuilder = new CacheControl.Builder();
            cacheBuilder.maxAge(3, TimeUnit.SECONDS);//最多3次访问一个接口
            cacheBuilder.maxStale(365, TimeUnit.DAYS);//控制缓存过期时间
            CacheControl cacheControl = cacheBuilder.build();
            Request request = chain.request();
            if (!NetworkUtils.checkNetwork(App.application)) {
                //设置缓存时间为10秒，并移除了pragma消息头，移除它的原因是因为pragma也是控制缓存的一个消息头属性
                request = request.newBuilder().removeHeader("pragma")
                        .header("Cache-Control", "max-age=60*60*24*5")
                        .cacheControl(cacheControl)
                        .build();
            }
            Response originalResponse = chain.proceed(request);
            if (NetworkUtils.checkNetwork(App.application)) {
                int maxAge = 0; // read from cache
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public ,max-age=" + maxAge)
                        .build();
            } else {
                int maxStale = 60 * 60 * 60 * 28;
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .build();
            }
        };
        return interceptor;
    }

    /*信任所有证书*/
    public static SSLSocketFactory getTrustSslSocketFactory() {
        try {
            try {
                SSLContext sslContext = SSLContext.getInstance("TLS");
                sslContext.init(null, getTrustManager(), null);
                sslSocketFactory = sslContext.getSocketFactory();
            } catch (GeneralSecurityException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sslSocketFactory;
    }

    /**
     * 验证相关证书 这个以后需要相关
     */
    public static SSLSocketFactory getValidateSslSocketFactory() {
        try {
            InputStream inputStream = App.application.getResources().getAssets().open("xxhj_client.cer"); // 得到证书的输入流
            X509TrustManager trustManager = null;//以流的方式读入证书
            try {
                trustManager = trustManagerForCertificates(inputStream);
                SSLContext sslContext = SSLContext.getInstance("TLS");
                sslContext.init(null, new TrustManager[]{trustManager}, null);
                sslSocketFactory = sslContext.getSocketFactory();
            } catch (GeneralSecurityException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sslSocketFactory;
    }

    private static X509TrustManager trustManagerForCertificates(InputStream in)
            throws GeneralSecurityException {
        CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
        Collection<? extends Certificate> certificates = certificateFactory.generateCertificates(in);
        if (certificates.isEmpty()) {
            throw new IllegalArgumentException("expected non-empty set of trusted certificates");
        }
        // Put the certificates a key store.
        char[] password = "password".toCharArray(); // Any password will work.
        KeyStore keyStore = newEmptyKeyStore(password);
        int index = 0;
        for (Certificate certificate : certificates) {
            String certificateAlias = Integer.toString(index++);
            keyStore.setCertificateEntry(certificateAlias, certificate);
        }

        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(
                KeyManagerFactory.getDefaultAlgorithm());
        keyManagerFactory.init(keyStore, password);
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(
                TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init(keyStore);
        TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
        if (trustManagers.length != 1 || !(trustManagers[0] instanceof X509TrustManager)) {
            throw new IllegalStateException("Unexpected default trust managers:"
                    + Arrays.toString(trustManagers));
        }
        return (X509TrustManager) trustManagers[0];
    }


    /**
     * 添加password
     *
     * @param password
     * @return
     * @throws GeneralSecurityException 要处理异常
     */
    public static KeyStore newEmptyKeyStore(char[] password) throws GeneralSecurityException {
        try {
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType()); // 这里添加自定义的密码，默认
            InputStream in = null; // By convention, 'null' creates an empty key store.
            keyStore.load(in, password);
            return keyStore;
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    /**
     * 信任所有证书必不可少的方法
     */

    public static TrustManager[] getTrustManager() {
        final TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                    }

                    @Override
                    public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                    }

                    @Override
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return new java.security.cert.X509Certificate[]{};
                    }
                }
        };
        return trustAllCerts;
    }

    /**
     * 忽略证书验证
     *
     * @param
     * @return
     */
    public static HostnameVerifier getHostnameVerifier() {
        HostnameVerifier hostnameVerifier = new HostnameVerifier() {
            @Override
            public boolean verify(String s, SSLSession sslSession) {
                return true;
            }
        };
        return hostnameVerifier;
    }
}
