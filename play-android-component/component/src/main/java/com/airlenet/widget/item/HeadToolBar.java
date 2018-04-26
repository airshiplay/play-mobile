package com.airlenet.widget.item;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.widget.TintTypedArray;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.TextView;

/**
 * Created by lig on 16/7/16.
 */
public class HeadToolBar extends Toolbar {
    TextView mTextView;
    int mTitleTextAppearance;
    int mTitleTextColor;

    public HeadToolBar(Context context) {
        super(context);
    }

    public HeadToolBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public HeadToolBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, @Nullable AttributeSet attrs) {
        final TypedArray a = context.obtainStyledAttributes(attrs,  android.support.v7.appcompat.R.styleable.Toolbar,android.support.v7.appcompat.R.attr.toolbarStyle,0);
//        final TintTypedArray a = TintTypedArray.obtainStyledAttributes(getContext(), attrs,
//                android.support.v7.appcompat.R.styleable.Toolbar, android.support.v7.appcompat.R.attr.toolbarStyle, 0);
        mTextView = new TextView(context);
        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER;
        addView(mTextView, params);
        final CharSequence title = a.getText(android.support.v7.appcompat.R.styleable.Toolbar_title);
        mTextView.setText(title);
        mTitleTextAppearance = a.getResourceId(android.support.v7.appcompat.R.styleable.Toolbar_titleTextAppearance, 0);
        mTitleTextColor = a.getColor(android.support.v7.appcompat.R.styleable.Toolbar_titleTextColor, 0xffffffff);
        mTextView.setTextAppearance(context, mTitleTextAppearance);
        mTextView.setTextColor(mTitleTextColor);
        a.recycle();
    }

    @Override
    public void setTitle(@StringRes int resId) {
        super.setTitle("");
        if (mTextView != null)
            mTextView.setText(resId);
        if (mTitleTextAppearance != 0) {
            mTextView.setTextAppearance(getContext(), mTitleTextAppearance);
        }
        if (mTitleTextColor != 0) {
            mTextView.setTextColor(mTitleTextColor);
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        super.setTitle("");
        if (mTextView != null)
            mTextView.setText(title);
        if (mTitleTextAppearance != 0) {
            mTextView.setTextAppearance(getContext(), mTitleTextAppearance);
        }
        if (mTitleTextColor != 0) {
            mTextView.setTextColor(mTitleTextColor);
        }
    }
}
