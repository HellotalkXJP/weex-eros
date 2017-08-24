package com.benmu.framework.manager.impl;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

import com.benmu.framework.R;
import com.benmu.framework.adapter.router.DefaultRouterAdapter;
import com.benmu.framework.constant.Constant;
import com.benmu.framework.manager.Manager;
import com.benmu.framework.model.MedicalMapModel;
import com.benmu.framework.model.RouterModel;
import com.benmu.framework.model.TitleModel;
import com.benmu.framework.model.WebViewParamBean;
import com.benmu.framework.utils.RouterUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Carry on 2017/8/7. manage router
 */

public class RouterManager extends Manager {
//    public static final String ANIMATION_PUSH = "PUSH";
//    public static final String ANIMATION_PRESENT = "PRESENT";
//    public static final String ANIMATION_TRANSLATION = "TRANSLATION";
//    private static Map<String, List<String>> mCustomerStacks = new HashMap<>();
//
//
//    /**
//     * 设置自定义栈
//     *
//     * @param stackName 栈名
//     * @param stack     传入的栈
//     */
//    public static void setCustomerStack(String stackName, List<String> stack) {
//        if (mCustomerStacks != null) {
//            mCustomerStacks.put(stackName, stack);
//        }
//    }
//
//    /**
//     * 得到自定义栈
//     *
//     * @param stackName 栈名
//     */
//
//    public static List<String> getCustomerSatckByName(String stackName) {
//        if (mCustomerStacks != null) {
//            return mCustomerStacks.get(stackName);
//        }
//
//        return new ArrayList<>();
//    }
//
//    public static void startActivity(Activity activity, RouterModel router, String openCategory) {
//        Uri uri = Uri.parse(router.url);
//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        String scheme = uri.getScheme();
//        if (TextUtils.equals("tel", scheme)) {
//
//        } else if (TextUtils.equals("sms", scheme)) {
//
//        } else if (TextUtils.equals("mailto", scheme)) {
//
//        } else if (TextUtils.equals("http", scheme) ||
//                TextUtils.equals("https",
//                        scheme)) {
//            intent.putExtra("isLocal", false);
//            intent.addCategory(openCategory);
//        } else if (TextUtils.equals("file", scheme)) {
//            intent.putExtra("isLocal", true);
//            intent.addCategory(openCategory);
//        } else {
//            intent.addCategory(openCategory);
//            uri = Uri.parse(new StringBuilder("http:").append(router.url).toString());
//        }
//        intent.putExtra(Constant.ROUTERPARAMS, router);
//        intent.setData(uri);
//        activity.startActivity(intent);
//        if (ANIMATION_PUSH.equals(router.animateType)) {
//            activity.overridePendingTransition(R.anim.right_in, R.anim.view_stay);
//        } else if (ANIMATION_PRESENT.equals(router.animateType)) {
//            activity.overridePendingTransition(R.anim.bottom_in, R.anim.view_stay);
//        } else if (ANIMATION_TRANSLATION.equals(router.animateType)) {
//            activity.overridePendingTransition(R.anim.left_in, R.anim.view_stay);
//        } else {
//            activity.overridePendingTransition(R.anim.right_in, R.anim.view_stay);
//        }
//
//    }
//
//
//    public static void startLogin(Activity activity, String login_url, String openCategory,
//                                  TitleModel title) {
//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        Uri uri = Uri.parse(login_url);
//        String scheme = uri.getScheme();
//        if (TextUtils.equals("tel", scheme)) {
//
//        } else if (TextUtils.equals("sms", scheme)) {
//
//        } else if (TextUtils.equals("mailto", scheme)) {
//
//        } else if (TextUtils.equals("http", scheme) ||
//                TextUtils.equals("https",
//                        scheme)) {
//            intent.putExtra("isLocal", false);
//            intent.addCategory(openCategory);
//        } else if (TextUtils.equals("file", scheme)) {
//            intent.putExtra("isLocal", true);
//            intent.addCategory(openCategory);
//        } else {
//            intent.addCategory(openCategory);
//            uri = Uri.parse(new StringBuilder("http:").append(login_url).toString());
//        }
//        intent.putExtra(Constant.ROUTERPARAMS, new RouterModel(null, ANIMATION_PRESENT, null, 0,
//                title, false));
//        intent.setData(uri);
//        activity.startActivity(intent);
//        activity.overridePendingTransition(R.anim.bottom_in, R.anim.view_stay);
//    }
//
//
//    public static void toHome(Activity activity, String homeCategory) {
//        /**清除缓存栈**/
//        RouterUtil.clearTillBottom();
//        /**回到首页**/
//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        intent.addCategory(homeCategory);
//        intent.setPackage(activity.getPackageName());
//        activity.startActivity(intent);
//    }

    public static void finish(Activity activity) {
        //TODO
//        if (activity != null) {
//            AbstractWeexActivity abs = (AbstractWeexActivity) activity;
//            RouterUtil.remove(activity);
//            if (abs.mLoading != null && abs.mLoading.isShowing() && !abs.isFinishing()) {
//                abs.mLoading.dismiss();
//            }
//            String type = abs.mRouterParams.animateType;
//            if (type == null) {
//                type = ANIMATION_PUSH;
//            }
//            abs.closeActivity();
//            if (ANIMATION_PUSH.equals(type)) {
//                abs.overridePendingTransition(R.anim.view_stay, R.anim.right_out);
//            } else if (ANIMATION_PRESENT.equals(type)) {
//                abs.overridePendingTransition(R.anim.view_stay, R.anim.bottom_out);
//            }else if(ANIMATION_TRANSLATION.equals(type)){
//                abs.overridePendingTransition(R.anim.view_stay, R.anim.left_out);
//            }
//        }
    }


    public void dialing(final Context context, final String params) {
        //TODO
        DefaultRouterAdapter.getInstance().dialing(context, params);
    }

    public static void startActivity(Context context, Intent intent) {
        context.startActivity(intent);
    }

//    public static void openMap(Context context, MedicalMapModel params, String mapCategory,
//                               TitleModel titleModel) {
//        if (params != null) {
//            String title = params.getTitle();
//            Intent intent = new Intent(Intent.ACTION_VIEW);
//            intent.addCategory(mapCategory);
//            intent.putExtra(Constant.ROUTERPARAMS, new RouterModel(null, ANIMATION_PUSH, null, 0,
//                    titleModel, false));
//            intent.putExtra(Constant.MAPVIEW_PARAMS, params);
//            context.startActivity(intent);
//        }
//    }


    public boolean open(Context context, String params) {
        return !(context == null || TextUtils.isEmpty(params)) && DefaultRouterAdapter
                .getInstance().open(context, params);
    }

    public boolean back(Context context, String params) {
        return !(context == null || TextUtils.isEmpty(params)) && DefaultRouterAdapter
                .getInstance().back(context, params);
    }

    public RouterModel getParams(Context context) {
        return DefaultRouterAdapter.getInstance().getParams(context);
    }

    //TODO
    public boolean refresh(Context context) {
        return context != null && DefaultRouterAdapter.refresh(context);

    }

    //TODO
    public void toWebView(Context context, String params) {
        DefaultRouterAdapter.getInstance().toWebView(context, params);
    }
}
