package com.example.skin;

import skin.support.SkinCompatManager;

/**
 * Copyright (c) 2018, Bongmi
 * All rights reserved
 * Author: lishengjie@bongmi.com.
 */
public class CustomNoneLoader extends CustomAssetsCardLoader {

  @Override
  public int getType() {
    return SkinCompatManager.SKIN_LOADER_STRATEGY_NONE;
  }
}
