package com.littlejie.demo;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

/**
 * Copyright (c) 2018, Bongmi
 * All rights reserved
 * Author: lishengjie@bongmi.com.
 */
public class TitleBar extends DemoLinearLayout {

    private TextView tvTitle;

    public TitleBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        View view = View.inflate(context, R.layout.layout_titlebar, this);
        tvTitle = (TextView) view.findViewById(R.id.tv_title);
    }
}
