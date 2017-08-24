package com.benmu.framework.event;

import android.content.Context;
import android.text.TextUtils;

import com.benmu.framework.constant.WXConstant;
import com.benmu.framework.event.browse.EventBrowse;
import com.benmu.framework.event.camera.EventCamera;
import com.benmu.framework.event.http.EventFetch;
import com.benmu.framework.event.modal.EventAlert;
import com.benmu.framework.event.modal.EventConfirm;
import com.benmu.framework.event.modal.EventShowLoading;
import com.benmu.framework.event.modal.EventToast;
import com.benmu.framework.event.router.EventBack;
import com.benmu.framework.event.router.EventCall;
import com.benmu.framework.event.router.EventGetBackParams;
import com.benmu.framework.event.router.EventGetParams;
import com.benmu.framework.event.router.EventOpen;
import com.benmu.framework.event.router.EventRefresh;
import com.benmu.framework.event.router.EventToMap;
import com.benmu.framework.event.router.EventWebView;
import com.benmu.framework.event.shorage.EventDeleteData;
import com.benmu.framework.event.shorage.EventGetData;
import com.benmu.framework.event.shorage.EventRemoveData;
import com.benmu.framework.event.shorage.EventSetData;
import com.benmu.framework.manager.ManagerFactory;
import com.benmu.framework.manager.impl.dispatcher.DispatchEventManager;
import com.benmu.framework.model.WeexEventBean;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import com.taobao.weex.bridge.JSCallback;

import java.util.ArrayList;

/**
 * Created by Carry on 2017/8/23.
 */

public class DispatchEventCenter {
    private static DispatchEventCenter mInstance = new DispatchEventCenter();

    private DispatchEventCenter() {
    }

    public static DispatchEventCenter getInstance(Bus bus) {
        return mInstance;
    }


    public void register() {
        ManagerFactory.getManagerService(DispatchEventManager.class).getBus().register(this);
    }

    public void unregister() {
        ManagerFactory.getManagerService(DispatchEventManager.class).getBus().unregister(this);
    }


    @Subscribe
    public void onWeexEvent(WeexEventBean weexEventBean) {
        if (weexEventBean == null) return;
        Context context = weexEventBean.getContext();
        if (context == null) return;
        String params = weexEventBean.getJsParams();
        switch (weexEventBean.getKey()) {
            case WXConstant.WXEventCenter.EVENT_PAYBYWECHAT:
                if (TextUtils.isEmpty(params)) return;
                break;
            case WXConstant.WXEventCenter.EVENT_OPEN:
                if (TextUtils.isEmpty(params)) return;
                new EventOpen().open(params, context, weexEventBean.getJscallback());
                break;
            case WXConstant.WXEventCenter.EVENT_GETPARAMS:
                new EventGetParams().getParams(context, weexEventBean.getJscallback());
                break;
            case WXConstant.WXEventCenter.EVENT_BACK:
                new EventBack().back(params, context, weexEventBean.getJscallback());
                break;
            case WXConstant.WXEventCenter.EVENT_GETBACKPARAMS:
                new EventGetBackParams().getBackParams(context, weexEventBean.getJscallback());
                break;
            case WXConstant.WXEventCenter.EVENT_REFRESH:
                new EventRefresh().refresh(context, weexEventBean.getJscallback());
                break;
            case WXConstant.WXEventCenter.EVENT_TOMAP:
                new EventToMap().toMap(params, context, weexEventBean.getJscallback());
                break;
            case WXConstant.WXEventCenter.EVENT_TOWEBVIEW:
                new EventWebView().toWebView(params, context);
                break;
            case WXConstant.WXEventCenter.EVENT_CALL:
                new EventCall().call(params, context);
                break;
            case WXConstant.WXEventCenter.EVENT_SETDATA:
                new EventSetData().setData(context, weexEventBean.getParamsList(), weexEventBean
                        .getJscallback());
                break;
            case WXConstant.WXEventCenter.EVENT_GETDATA:
                new EventGetData().getData(context, weexEventBean.getParamsList(), weexEventBean
                        .getJscallback());
                break;
            case WXConstant.WXEventCenter.EVENT_DELETEDATA:
                new EventDeleteData().deleteData(context, weexEventBean.getParamsList(),
                        weexEventBean.getJscallback());
                break;
            case WXConstant.WXEventCenter.EVENT_REMOVEDATA:
                new EventRemoveData().removeData(context, weexEventBean.getJscallback());
                break;
            case WXConstant.WXEventCenter.EVENT_BROWSERIMG:
                if (TextUtils.isEmpty(params)) return;
                new EventBrowse().open(params, context);
                break;

            case WXConstant.WXEventCenter.EVENT_MODAL_ALERT:
                if (TextUtils.isEmpty(params)) return;
                JSCallback modal_alert_callback = weexEventBean.getJscallback();
                new EventAlert().alert(params, modal_alert_callback, context);
                break;
            case WXConstant.WXEventCenter.EVENT_MODAL_CONFIRM:
                if (TextUtils.isEmpty(params)) return;
                ArrayList<JSCallback> callbacks = weexEventBean.getCallbacks();
                if (callbacks == null && callbacks.size() < 2) return;
                new EventConfirm().confirm(params, callbacks.get(0), callbacks.get(1), context);
                break;
            case WXConstant.WXEventCenter.EVENT_MODAL_SHOWLOADING:
                if (TextUtils.isEmpty(params)) return;
                JSCallback modal_showloading_callback = weexEventBean.getJscallback();
                new EventShowLoading().showLoading(params, modal_showloading_callback, context);
                break;
            case WXConstant.WXEventCenter.EVENT_MODAL_TOAST:
                if (TextUtils.isEmpty(params)) return;
                new EventToast().toast(params, context);
                break;
            case WXConstant.WXEventCenter.EVENT_FETCH:
                new EventFetch().fetch(params, context, weexEventBean.getJscallback());
                break;
            case WXConstant.WXEventCenter.EVENT_CAMERA_UPLOADIMAGE:
                new EventCamera().uploadImage(params, context, weexEventBean.getJscallback());
                break;

            case WXConstant.WXEventCenter.EVENT_CAMERA:
                JSCallback jscallback = weexEventBean.getJscallback();
                if (jscallback == null) return;
                new EventCamera().scan(jscallback, context);
                break;


//            case WXConstant.WXEventCenter.EVENT_RIGHTITEM:
//                new EventRightItem().setRightItem(params, weexEventBean.getJscallback());
//                break;
//            case WXConstant.WXEventCenter.EVENT_LEFTITEM:
//                new EventLeftItem().setLeftItem(params, weexEventBean.getJscallback());
//                break;
//            case WXConstant.WXEventCenter.EVENT_CENTERITEM:
//                new EventCenterItem().setCenterItem(params, weexEventBean.getJscallback());
//                break;
//            case WXConstant.WXEventCenter.EVENT_NAVIGATIONINFO:
//                new EventNavigationInfo().setNavigationInfo(params, context, weexEventBean
//                        .getJscallback());
//                break;

//            case WXConstant.WXEventCenter.EVENT_COPYSTRING:
//                if (TextUtils.isEmpty(params)) return;
//                new EventCopyString().copyString(context, params, weexEventBean.getJscallback());
//                break;
//            case WXConstant.WXEventCenter.EVENT_GETCID:
//                new EventGetClientId().getClientId(context, weexEventBean.getJscallback());
//                break;

//            case WXConstant.WXEventCenter.EVENT_WATERMARKER:
//                new EventWaterMarker().add(params,context);
//                break;

        }
    }


}
