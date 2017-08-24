package com.benmu.framework.extend.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.benmu.framework.R;
import com.benmu.framework.utils.ImageUtil;
import com.benmu.framework.utils.WXCommonUtil;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.taobao.weex.WXEnvironment;
import com.taobao.weex.adapter.IWXImgLoaderAdapter;
import com.taobao.weex.common.WXImageStrategy;
import com.taobao.weex.dom.ImmutableDomObject;
import com.taobao.weex.dom.WXImageQuality;
import com.taobao.weex.ui.component.WXImage;
import com.taobao.weex.ui.view.WXImageView;

/**
 * Created by Carry on 2017/8/23.
 */

public class DefaultWXImageAdapter implements IWXImgLoaderAdapter {

    private static final String PLACEHOLDER_DEFAULT = "default";
    private Bitmap mErrorBitmap;

    public DefaultWXImageAdapter() {
    }


    private boolean validatePlaceHolder(WXImageStrategy strategy) {
        if (strategy == null) return false;
        String type = strategy.placeHolder;
        return !TextUtils.isEmpty(type) && type.endsWith(PLACEHOLDER_DEFAULT);
    }

    @Override
    public void setImage(final String url, final ImageView view,
                         WXImageQuality quality, final WXImageStrategy strategy) {
        if (url == null) return;
        Glide.with(WXEnvironment.getApplication()).load(url).diskCacheStrategy(DiskCacheStrategy
                .ALL).into(new
                BMGlideDrawableImageTarget
                (view, strategy, validatePlaceHolder(strategy), url));
    }

    private Bitmap getErrorBitmap(Context context) {
        if (mErrorBitmap == null) {
            mErrorBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable
                    .place_holder);
        }
        return mErrorBitmap;
    }

    private void handleError(WXImageView imageView) {
        WXImage component = imageView.getComponent();
        if (component == null) return;
        ImmutableDomObject domObject = component.getDomObject();
        if (domObject == null) return;
        Bitmap bitmap = getErrorBitmap(imageView.getContext());
        if (domObject.getLayoutWidth() > 0 && domObject.getLayoutHeight() > 0) {
            int target = Math.min((int) (domObject.getLayoutWidth()), (int) (domObject
                    .getLayoutHeight())) / 2;
            Bitmap zoomBitmap = ImageUtil.zoomImage(bitmap, target, target);
            if (zoomBitmap != null) {
                imageView.drawErrorBitmap(zoomBitmap);
            }
        }
    }


    private class BMGlideDrawableImageTarget extends GlideDrawableImageViewTarget {
        private WXImageStrategy mCurrentStrategy;
        private boolean mHasHolder;
        private String mCurrentUrl;

        BMGlideDrawableImageTarget(ImageView view, WXImageStrategy strategy, boolean
                hasHolder, String url) {
            super(view);
            this.mCurrentStrategy = strategy;
            this.mHasHolder = hasHolder;
            this.mCurrentUrl = url;
        }

        @Override
        public void onLoadStarted(Drawable placeholder) {
            super.onLoadStarted(placeholder);
            if (view != null) {
                if (view instanceof WXImageView && mHasHolder) {
                    WXImageView wxImageView = (WXImageView) view;
                    int[] wh = WXCommonUtil.getComponentWH(wxImageView.getComponent());
                    wxImageView.showLoading(wh[0], wh[1]);
                }
            }
        }

        @Override
        public void onLoadFailed(Exception e, Drawable errorDrawable) {
            super.onLoadFailed(e, errorDrawable);
            if (view instanceof WXImageView && mHasHolder) {
                ((WXImageView) view).hideLoading();
                handleError((WXImageView) view);
            }

            if (mCurrentStrategy != null && mCurrentStrategy.getImageListener() != null) {
                mCurrentStrategy.getImageListener().onImageFinish(mCurrentUrl, view, true, null);
            }
        }

        @Override
        public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable>
                animation) {
            super.onResourceReady(resource, animation);
            if (view != null) {
                if (view instanceof WXImageView && mHasHolder) {
                    ((WXImageView) view).hideLoading();
                }
            }
            if (mCurrentStrategy != null && mCurrentStrategy.getImageListener() != null) {
                mCurrentStrategy.getImageListener().onImageFinish(mCurrentUrl, view, true, null);
            }
        }
    }
}
