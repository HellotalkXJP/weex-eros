package com.benmu.framework.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import com.benmu.framework.R;
import com.benmu.framework.adapter.router.RouterTracker;
import com.benmu.framework.constant.Constant;
import com.benmu.framework.constant.WXConstant;
import com.benmu.framework.manager.ManagerFactory;
import com.benmu.framework.manager.impl.GlobalEventManager;
import com.benmu.framework.manager.impl.dispatcher.DispatchEventManager;
import com.benmu.framework.model.RouterModel;
import com.benmu.framework.utils.WXCommonUtil;
import com.taobao.weex.IWXRenderListener;
import com.taobao.weex.RenderContainer;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.common.WXRenderStrategy;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Carry on 2017/8/16.
 */

public class AbstractWeexActivity extends AppCompatActivity implements IWXRenderListener,
        RouterTracker.RouterTrackerListener {
    protected RouterModel mRouterParam;
    private WXSDKInstance mWXInstance;
    private ViewGroup mContainer;
    private String mPageUrl;
    private static final String TAG = "AbstractWeexActivity";
    private String mPageName;
    private String mRouterType;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRouterType = GlobalEventManager.TYPE_OPEN;
        Intent data = getIntent();
        initRouterParams(data);
        initUrl(data);
        synRouterStack();
    }

    protected void initRouterParams(Intent data) {

        Serializable serializableExtra = data.getSerializableExtra(Constant.ROUTERPARAMS);
        if (serializableExtra instanceof RouterModel) {
            setRouterParam((RouterModel) serializableExtra);
        }


    }

    protected void initUrl(Intent data) {
        Uri pageUri = data.getData();
        setPageUrl(pageUri.toString());
    }

    public void setRouterParam(RouterModel param) {
        this.mRouterParam = param;
    }

    public RouterModel getRouterParam() {
        return null;
    }

    protected void synRouterStack() {
        if (mRouterParam != null && !TextUtils.isEmpty(mRouterParam.animateType)) {
            if (!Constant.ACTIVITIES_ANIMATION.ANIMATION_PRESENT.equals(mRouterParam.animateType)) {
                onAttach(this);
            } else {
                onAttach(this, getClass().getName());
            }
        }
    }

    public void refresh() {
        createWXInstance();
        renderPage();
    }


    public void setPageUrl(String url) {
        this.mPageUrl = url;
    }


    public String getPageUrl() {
        return mPageUrl;
    }


    protected void renderPage() {
        if (TextUtils.isEmpty(mPageUrl)) {
            return;
        }
        preRender();
        renderPageByURL();
        postRender();
    }

    private void renderPageByURL() {
        WXCommonUtil.throwIfNull(mContainer, new RuntimeException("Can't render page, container " +
                "is" +
                " null"));
        Map<String, Object> options = new HashMap<>();
        options.put(WXSDKInstance.BUNDLE_URL, mPageUrl);
        mWXInstance.renderByUrl(
                getPageName(),
                mPageUrl,
                options,
                null,
                WXRenderStrategy.APPEND_ASYNC);
    }


    public String getPageName() {
        return mPageName == null ? TAG : mPageName;
    }


    public void setPageName(String pageName) {
        this.mPageName = pageName;
    }


    protected void preRender() {

    }

    protected void postRender() {

    }

    protected void createWXInstance() {
        if (mWXInstance != null) {
            destroyWXInstance();
        }
        RenderContainer renderContainer = new RenderContainer(this);
        mContainer.addView(renderContainer);
        mWXInstance.registerRenderListener(this);
        mWXInstance.setRenderContainer(renderContainer);

    }

    protected void destroyWXInstance() {
        if (mWXInstance != null) {
            Intent intent = new Intent(WXConstant.WXEventCenter.EVENT_INSTANCE_DESTORY);
            intent.putExtra("data", mWXInstance.getInstanceId());
            ManagerFactory.getManagerService(DispatchEventManager.class).getBus().post(intent);
            mWXInstance.registerRenderListener(null);
            mWXInstance.registerOnWXScrollListener(null);
            mWXInstance.destroy();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mRouterType = GlobalEventManager.TYPE_BACK;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mWXInstance != null) {
            mWXInstance.onActivityResume();
        }

        if (mWXInstance != null) {
            GlobalEventManager.onViewDidAppear(mWXInstance, mRouterType);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mWXInstance != null) {
            mWXInstance.onActivityStart();
        }

        if (mWXInstance != null) {
            GlobalEventManager.onViewWillAppear(mWXInstance, mRouterType);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mWXInstance != null) {
            mWXInstance.onActivityPause();
        }

        if (mWXInstance != null) {
            GlobalEventManager.onViewWillDisappear(mWXInstance, mRouterType);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mWXInstance != null) {
            mWXInstance.onActivityStop();
        }

        if (mWXInstance != null) {
            GlobalEventManager.onViewDidDisappear(mWXInstance, mRouterType);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mWXInstance != null) {
            mWXInstance.onActivityDestroy();
        }
    }

    @Override
    public void onViewCreated(WXSDKInstance instance, View view) {
        if (view != null && view.getParent() == null) {
            mContainer.addView(view);
        }
        mContainer.requestLayout();
        GlobalEventManager.onViewWillAppear(mWXInstance, mRouterType);
    }

    @Override
    public void onRenderSuccess(WXSDKInstance instance, int width, int height) {
        //do some report
        GlobalEventManager.onViewDidAppear(mWXInstance, mRouterType);
    }

    @Override
    public void onRefreshSuccess(WXSDKInstance instance, int width, int height) {

    }

    @Override
    public void onException(WXSDKInstance instance, String errCode, String msg) {

    }

    @Override
    public void onAttach(Activity activity) {
        RouterTracker.push(activity);
    }

    @Override
    public void onAttach(Activity activity, String activityName) {
        RouterTracker.newInstancePush(activity, activityName);
    }

    @Override
    public void onDetach(Activity activity) {
        if (activity == this) {
            finish();
            if (mRouterParam != null) {
                String type = mRouterParam.animateType;
                if (Constant.ACTIVITIES_ANIMATION.ANIMATION_PUSH.equals(type)) {
                    overridePendingTransition(R.anim.view_stay, R.anim.right_out);
                } else if (Constant.ACTIVITIES_ANIMATION.ANIMATION_PRESENT.equals(type)) {
                    overridePendingTransition(R.anim.view_stay, R.anim.bottom_out);
                } else if (Constant.ACTIVITIES_ANIMATION.ANIMATION_TRANSLATION.equals(type)) {
                    overridePendingTransition(R.anim.view_stay, R.anim.left_out);
                }
            }
        }
    }

    @Override
    public void onDetach(Activity activity, String activityName) {

    }

    @Override
    public void onBackPressed() {
        RouterTracker.popActivity();
    }
}
