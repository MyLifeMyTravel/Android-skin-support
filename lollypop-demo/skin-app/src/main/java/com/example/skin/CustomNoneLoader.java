package com.example.skin;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;

import skin.support.SkinCompatManager;
import skin.support.content.res.SkinCompatExtraResources;
import skin.support.load.SkinNoneLoader;
import skin.support.utils.SkinFileUtils;

/**
 * Copyright (c) 2018, Bongmi
 * All rights reserved
 * Author: lishengjie@bongmi.com.
 */
public class CustomNoneLoader extends SkinNoneLoader {

    @Override
    public String loadResInBackground(Context context, String resName) {
        if (TextUtils.isEmpty(resName)) {
            return resName;
        }
        String resPkgPath = Utils.copySkinFromAssets(context, resName);
        if (SkinFileUtils.isFileExists(resPkgPath)) {
            String pkgName = SkinCompatManager.getInstance().getSkinPackageName(resPkgPath);
            Resources resources = SkinCompatManager.getInstance().getSkinResources(resPkgPath);
            if (resources != null && !TextUtils.isEmpty(pkgName)) {
                SkinCompatExtraResources.getInstance().setupSkin(
                        resources,
                        pkgName,
                        resName,
                        this);
                return resName;
            }
        }
        return super.loadResInBackground(context, resName);
    }

    @Override
    public String getText(Context context, String skinName, int resId) {
        return SkinCompatExtraResources.getString(context, resId);
    }
}
