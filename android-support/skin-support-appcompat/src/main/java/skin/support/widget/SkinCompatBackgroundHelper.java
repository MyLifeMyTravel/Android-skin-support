package skin.support.widget;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;

import skin.support.R;
import skin.support.content.res.SkinCompatResources;
import skin.support.content.res.SkinCompatVectorResources;

/**
 * Created by ximsfei on 2017/1/10.
 */

public class SkinCompatBackgroundHelper extends SkinCompatHelper {
    private final View mView;

    private int mBackgroundResId = INVALID_ID;
    private int mBackgroundTintResId = INVALID_ID;

    public SkinCompatBackgroundHelper(View view) {
        mView = view;
    }

    public void loadFromAttributes(AttributeSet attrs, int defStyleAttr) {
        TypedArray a = mView.getContext().obtainStyledAttributes(attrs, R.styleable.SkinBackgroundHelper, defStyleAttr, 0);
        try {
            if (a.hasValue(R.styleable.SkinBackgroundHelper_android_background)) {
                mBackgroundResId = a.getResourceId(
                        R.styleable.SkinBackgroundHelper_android_background, INVALID_ID);
            }
            if (a.hasValue(R.styleable.SkinBackgroundHelper_android_backgroundTint)) {
                mBackgroundTintResId = a.getResourceId(R.styleable.SkinBackgroundHelper_android_backgroundTint, 0);
            } else if (a.hasValue(R.styleable.SkinBackgroundHelper_backgroundTint)) {
                mBackgroundTintResId = a.getResourceId(R.styleable.SkinBackgroundHelper_backgroundTint, 0);
            }
        } finally {
            a.recycle();
        }
        applySkin();
    }

    public void onSetBackgroundResource(int resId) {
        mBackgroundResId = resId;
        // Update the default background tint
        applySkin();
    }

    private void applyBackgroundResource() {
        mBackgroundResId = checkResourceId(mBackgroundResId);
        if (mBackgroundResId == INVALID_ID) {
            return;
        }
        Drawable drawable = SkinCompatVectorResources.getDrawableCompat(mView.getContext(), mBackgroundResId);
        if (drawable != null) {
            int paddingLeft = mView.getPaddingLeft();
            int paddingTop = mView.getPaddingTop();
            int paddingRight = mView.getPaddingRight();
            int paddingBottom = mView.getPaddingBottom();
            ViewCompat.setBackground(mView, drawable);
            mView.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
        }
    }

    private void applyBackgroundTint() {
        mBackgroundTintResId = checkResourceId(mBackgroundTintResId);
        if (mBackgroundTintResId == INVALID_ID) {
            return;
        }
        //Drawable tintDrawable = DrawableCompat.wrap(mView.getBackground()).mutate();
        //DrawableCompat.setTintList(tintDrawable, SkinCompatResources.getInstance().getColorStateList(mBackgroundTintResId));
        //mView.setBackgroundDrawable(tintDrawable);
        ColorStateList colorStateList = SkinCompatResources.getInstance().getColorStateList(mBackgroundTintResId);
        if (colorStateList != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                mView.setBackgroundTintList(colorStateList);
            }
        }
    }

    @Override
    public void applySkin() {
        applyBackgroundResource();
        applyBackgroundTint();
    }
}
