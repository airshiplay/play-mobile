package com.airletnet.basiccomponent;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by airlenet on 2018/3/30.
 */

public class PlayItemView extends LinearLayout{
    private AppCompatTextView mTitle;
    private AppCompatTextView mValue;
    private AppCompatImageView mTitleIcon;
    private AppCompatImageView mOpenIcon;
    public PlayItemView(Context context) {
        super(context);
        initView(context);
        mOpenIcon.setImageResource( R.drawable.arrow_right);
    }

    public PlayItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
        final TypedArray a = context.obtainStyledAttributes(
                attrs, R.styleable.PlayItemView);
        mTitleIcon.setImageDrawable(a.getDrawable(R.styleable.PlayItemView_item_title_icon));
        mTitle.setText(a.getString(R.styleable.PlayItemView_item_title));
        mValue.setText(a.getString(R.styleable.PlayItemView_item_value));
        mOpenIcon.setImageResource(a.getResourceId(R.styleable.PlayItemView_item_open_icon,R.drawable.arrow_right));
        mOpenIcon.setVisibility(a.getBoolean(R.styleable.PlayItemView_item_open_show,true)? View.VISIBLE: View.GONE);
        a.recycle();
    }
    public void initView(Context context){
        setOrientation(LinearLayout.HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);
        mTitle = new AppCompatTextView(context);
        mValue  = new AppCompatTextView(context);
        mTitleIcon = new AppCompatImageView(context);
        mOpenIcon  = new AppCompatImageView(context);
        LayoutParams titleIconLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        int margin = Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, 16, context.getResources().getDisplayMetrics()));
        titleIconLayoutParams.leftMargin = margin;
        titleIconLayoutParams.topMargin =margin;
        titleIconLayoutParams.bottomMargin =margin;
        addView(mTitleIcon,titleIconLayoutParams);


        LayoutParams titleLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1);
        titleLayoutParams.leftMargin = margin;
        titleLayoutParams.topMargin =margin;
        titleLayoutParams.bottomMargin =margin;
        addView(mTitle, titleLayoutParams);

        LayoutParams valueLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        valueLayoutParams.rightMargin= margin;
        valueLayoutParams.leftMargin = margin;
        valueLayoutParams.topMargin =margin;
        valueLayoutParams.bottomMargin =margin;
        addView(mValue,valueLayoutParams);


        LayoutParams openIconLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        openIconLayoutParams.rightMargin = margin;
        addView(mOpenIcon,openIconLayoutParams);
        mTitle.setTextColor(Color.parseColor("#333333"));
        mValue.setTextColor(Color.parseColor("#8a8a8a"));
        mValue.setGravity(Gravity.RIGHT);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            setBackground(newSelector(getContext(),255,999,-1,-1));
        }else {
            setBackgroundDrawable(newSelector(getContext(),255,999,-1,-1));
        }
        setClickable(true);
    }
//    private ColorStateList createColorStateList(int normal, int pressed, int focused, int unable) {
//        int[] colors = new int[] { pressed, focused, normal, focused, unable, normal };
//        int[][] states = new int[6][];
//        states[0] = new int[] { android.R.attr.state_pressed, android.R.attr.state_enabled };
//        states[1] = new int[] { android.R.attr.state_enabled, android.R.attr.state_focused };
//        states[2] = new int[] { android.R.attr.state_enabled };
//        states[3] = new int[] { android.R.attr.state_focused };
//        states[4] = new int[] { android.R.attr.state_window_focused };
//        states[5] = new int[] {};
//        ColorStateList colorList = new ColorStateList(states, colors);
//        return colorList;
//    }
    public static StateListDrawable newSelector(Context context, int idNormal, int idPressed, int idFocused,
                                                int idUnable) {
        StateListDrawable bg = new StateListDrawable();
        Drawable normal = idNormal == -1 ? null :new ColorDrawable(idNormal);
        Drawable pressed = idPressed == -1 ? null : new ColorDrawable(idPressed);
        Drawable focused = idFocused == -1 ? null : new ColorDrawable(idFocused);
        Drawable unable = idUnable == -1 ? null : new ColorDrawable(idUnable);
        // View.PRESSED_ENABLED_STATE_SET
        bg.addState(new int[] { android.R.attr.state_pressed, android.R.attr.state_enabled }, pressed);
        // View.ENABLED_FOCUSED_STATE_SET
        bg.addState(new int[] { android.R.attr.state_enabled, android.R.attr.state_focused }, focused);
        // View.ENABLED_STATE_SET
        bg.addState(new int[] { android.R.attr.state_enabled }, normal);
        // View.FOCUSED_STATE_SET
        bg.addState(new int[] { android.R.attr.state_focused }, focused);
        // View.WINDOW_FOCUSED_STATE_SET
        bg.addState(new int[] { android.R.attr.state_window_focused }, unable);
        // View.EMPTY_STATE_SET
        bg.addState(new int[] {}, normal);
        return bg;
    }
    public void setTitle(Character text){
        mTitle.setText(text);
    }

    public void setTitleIcon(int res){
        mTitleIcon.setImageResource(res);
    }
    public void setValue(Character text){
        mValue.setText(text);
    }
    public void setOpen(boolean open){
        mOpenIcon.setVisibility(View.GONE);
    }
}
