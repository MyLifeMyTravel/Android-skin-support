package skin.support.widget;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;

import skin.support.R;
import skin.support.content.res.SkinCompatResources;
import skin.support.content.res.SkinCompatVectorResources;

/**
 * Created by ximsfei on 2017/1/12.
 */
public class SkinCompatImageHelper extends SkinCompatHelper {
    private static final String TAG = SkinCompatImageHelper.class.getSimpleName();
    private final ImageView mView;
    private int mSrcResId = INVALID_ID;
    private int mSrcCompatResId = INVALID_ID;
    private int mTintResId = INVALID_ID;

    public SkinCompatImageHelper(ImageView imageView) {
        mView = imageView;
    }

    public void loadFromAttributes(AttributeSet attrs, int defStyleAttr) {
        TypedArray a = null;
        try {
            a = mView.getContext().obtainStyledAttributes(attrs, R.styleable.SkinCompatImageView, defStyleAttr, 0);
            mSrcResId = a.getResourceId(R.styleable.SkinCompatImageView_android_src, INVALID_ID);
            mSrcCompatResId = a.getResourceId(R.styleable.SkinCompatImageView_srcCompat, INVALID_ID);
            if (a.hasValue(R.styleable.SkinCompatImageView_android_tint)) {
                mTintResId = a.getResourceId(R.styleable.SkinCompatImageView_android_tint, 0);
            } else if (a.hasValue(R.styleable.SkinCompatImageView_tint)) {
                mTintResId = a.getResourceId(R.styleable.SkinCompatImageView_tint, 0);
            }
        } finally {
            if (a != null) {
                a.recycle();
            }
        }
        applySkin();
    }

    public void setImageResource(int resId) {
        mSrcResId = resId;
        applySkin();
    }

    private void applyTint() {
        mTintResId = checkResourceId(mTintResId);
        if (mTintResId == INVALID_ID) {
            return;
        }
        //Drawable tintDrawable = DrawableCompat.wrap(mView.getDrawable()).mutate();
        //DrawableCompat.setTintList(tintDrawable, SkinCompatResources.getInstance().getColorStateList(mTintResId));
        //mView.setImageDrawable(tintDrawable);
        ColorStateList colorStateList = SkinCompatResources.getInstance().getColorStateList(mTintResId);
        if (colorStateList != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                mView.setBackgroundTintList(colorStateList);
            }
        }
    }

    @Override
    public void applySkin() {
        mSrcCompatResId = checkResourceId(mSrcCompatResId);
        if (mSrcCompatResId != INVALID_ID) {
            Drawable drawable = SkinCompatVectorResources.getDrawableCompat(mView.getContext(), mSrcCompatResId);
            if (drawable != null) {
                mView.setImageDrawable(drawable);
            }
        } else {
            mSrcResId = checkResourceId(mSrcResId);
            if (mSrcResId == INVALID_ID) {
                return;
            }
            Drawable drawable = SkinCompatVectorResources.getDrawableCompat(mView.getContext(), mSrcResId);
            if (drawable != null) {
                mView.setImageDrawable(drawable);
            }
        }

        applyTint();
    }
}
