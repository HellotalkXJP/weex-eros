package com.benmu.framework.http;

import com.benmu.framework.BuildConfig;

/**
 * Created by Carry on 2017/8/21.
 */

public class Api {
    public static final String API_BASE_UPLOAD = "https://lev-inf.benmu-health.com/";
    public static final String API_BASE_MTS = BuildConfig.DEBUG ? "http://test4.benmu-health" +
            ".com" : "https://wechat.benmu-health.com";
    public static final String API_UPLOAD = "resource/image/upload.do";
    public static final String API_USEIMAGE = "resource/image/";
    public static final String API_CHECKUPDATE = "/mobile/app/version/checkJSUpdate";
    public static final String API_UPADATE_CID = "/mobile/app/user/updateCid";

    public static final String API_LOGIN = "mobile/app/user/login";
    public static final String API_REPORT_ERROR = "/mobile/wx/conf/log";
    public static final String API_NATIVE_UPDATE = "/mobile/app/version/query/status";
}
