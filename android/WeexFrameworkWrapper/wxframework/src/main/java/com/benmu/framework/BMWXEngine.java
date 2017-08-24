package com.benmu.framework;

import android.app.Application;

import com.benmu.framework.extend.adapter.DefaultWXHttpAdapter;
import com.benmu.framework.extend.adapter.DefaultWXImageAdapter;
import com.benmu.framework.http.BMPersistentCookieStore;
import com.benmu.framework.manager.impl.CustomerEnvOptionManager;
import com.benmu.framework.utils.SharePreferenceUtil;
import com.taobao.weex.InitConfig;
import com.taobao.weex.WXSDKEngine;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.cookie.CookieJarImpl;
import com.zhy.http.okhttp.log.LoggerInterceptor;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by Carry on 2017/8/23.
 */

public class BMWXEngine {

    public static void initialize(Application context, BMInitConfig initConfig) {
        initConing(initConfig);
        engineStart(context);
        registerBMComponentsAndModules(context);
        initHttpClient(context);
        initInterceptor(context);
    }

    private static void initInterceptor(Application context) {
        if (!SharePreferenceUtil.getNotFirstLauncher(context)) {
            SharePreferenceUtil.setInterceptorActive(context, true);
        }
    }

    private static void initHttpClient(Application context) {
        CookieJarImpl cookieJar = new CookieJarImpl(new BMPersistentCookieStore
                (context));
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS).cookieJar(cookieJar)
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);
    }

    private static void registerBMComponentsAndModules(Application context) {
        CustomerEnvOptionManager.init(context);
        CustomerEnvOptionManager.registerComponents(CustomerEnvOptionManager.getComponents());
        CustomerEnvOptionManager.registerModules(CustomerEnvOptionManager.getModules());
    }

    private static void engineStart(Application app) {
        WXSDKEngine.initialize(app,
                new InitConfig.Builder()
                        .setImgAdapter(new DefaultWXImageAdapter()).setHttpAdapter(new
                        DefaultWXHttpAdapter(app))
                        .build()
        );
    }

    private static void initConing(BMInitConfig initConfig) {
        if (initConfig == null) return;
        if (initConfig.getmEnvs() != null) {
            initEnv(initConfig.getmEnvs());
        }
    }

    private static void initEnv(HashMap<String, String> Env) {
        CustomerEnvOptionManager.registerCustomConfig(Env);
    }

}
