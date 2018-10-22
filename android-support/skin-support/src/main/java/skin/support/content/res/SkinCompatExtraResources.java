package skin.support.content.res;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;

import skin.support.SkinCompatManager;

/**
 * 加载除皮肤包之外的资源包，目前只支持string资源
 * Copyright (c) 2018, Bongmi
 * All rights reserved
 * Author: lishengjie@bongmi.com.
 */
public class SkinCompatExtraResources {

    private static volatile SkinCompatExtraResources sInstance;
    private Resources mResources;
    private String mSkinPkgName = "";
    private String mSkinName = "";
    private SkinCompatManager.SkinLoaderStrategy mStrategy;
    private boolean isDefaultSkin = true;

    private SkinCompatExtraResources() {
    }

    public static SkinCompatExtraResources getInstance() {
        if (sInstance == null) {
            synchronized (SkinCompatExtraResources.class) {
                if (sInstance == null) {
                    sInstance = new SkinCompatExtraResources();
                }
            }
        }
        return sInstance;
    }

    public void reset() {
        reset(SkinCompatManager.getInstance().getStrategies().get(SkinCompatManager.SKIN_LOADER_STRATEGY_NONE));
    }

    public void reset(SkinCompatManager.SkinLoaderStrategy strategy) {
        mResources = SkinCompatManager.getInstance().getContext().getResources();
        mSkinPkgName = "";
        mSkinName = "";
        mStrategy = strategy;
        isDefaultSkin = true;
    }

    public void setupSkin(Resources resources, String pkgName, String skinName, SkinCompatManager.SkinLoaderStrategy strategy) {
        if (resources == null || TextUtils.isEmpty(pkgName) || TextUtils.isEmpty(skinName)) {
            reset(strategy);
            return;
        }
        mResources = resources;
        mSkinPkgName = pkgName;
        mSkinName = skinName;
        mStrategy = strategy;
        isDefaultSkin = false;
    }

    private int getTargetResId(Context context, int resId) {
        try {
            String resName = null;
            if (mStrategy != null) {
                resName = mStrategy.getTargetResourceEntryName(context, mSkinName, resId);
            }
            if (TextUtils.isEmpty(resName)) {
                resName = context.getResources().getResourceEntryName(resId);
            }
            String type = context.getResources().getResourceTypeName(resId);
            return mResources.getIdentifier(resName, type, mSkinPkgName);
        } catch (Exception e) {
            // 换肤失败不至于应用崩溃.
            return 0;
        }
    }

    private String getSkinString(Context context, int resId) {
        if (!SkinCompatManager.getInstance().isContextSkinEnable(context)) {
            return context.getResources().getString(resId);
        }
        if (!isDefaultSkin) {
            int targetResId = getTargetResId(context, resId);
            if (targetResId != 0) {
                return mResources.getString(targetResId);
            }
        }
        return context.getResources().getString(resId);
    }

    public static String getString(Context context, int resId) {
        return getInstance().getSkinString(context, resId);
    }

}
