package com.airletnet.basiccomponent;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.SwitchCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by airlenet on 2018/3/30.
 */

public class PlayItemView extends LinearLayout {
    private AppCompatTextView mTitle;
    private AppCompatTextView mValue;
    private AppCompatImageView mTitleIcon;
    private AppCompatImageView mOpenIcon;
    private AppCompatEditText mEditText;
    private SwitchCompat mSwitchCompat;
    Paint mPaint = new Paint();
    int defaultSidelineColor = Color.parseColor("#bbbbbb");
    int position = 0;
    public static final int POSITION_SINGLE = 0;
    public static final int POSITION_TOP = 1;
    public static final int POSITION_CENTER = 2;
    public static final int POSITION_BOTTOM = 3;
    int valueType = 0;
    public static final int VALUE_TYPE_TEXTVIEW = 0;
    public static final int VALUE_TYPE_EDITTEXT = 1;
    public static final int VALUE_TYPE_SWITCH_COMPAT = 2;

    public PlayItemView(Context context) {
        super(context);
        initView(context);
        mOpenIcon.setImageResource(R.drawable.arrow_right);
        mPaint.setColor(defaultSidelineColor);
    }

    public PlayItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        final TypedArray a = context.obtainStyledAttributes(
                attrs, R.styleable.PlayItemView);
        valueType = a.getInt(R.styleable.PlayItemView_item_value_type, VALUE_TYPE_TEXTVIEW);
        initView(context);
        Drawable titleIconDrawable = a.getDrawable(R.styleable.PlayItemView_item_title_icon);
        if (titleIconDrawable == null) {
            mTitleIcon.setVisibility(View.GONE);
        } else {
            mTitleIcon.setImageDrawable(titleIconDrawable);
        }
        mTitle.setText(a.getString(R.styleable.PlayItemView_item_title));
        mTitle.setSingleLine();

        if (valueType == VALUE_TYPE_TEXTVIEW) {
            mValue.setText(a.getString(R.styleable.PlayItemView_item_value));
            mValue.setTextColor(Color.parseColor("#8a8a8a"));
            mValue.setGravity(Gravity.RIGHT);
            mValue.setSingleLine();
            mValue.setEllipsize(TextUtils.TruncateAt.MIDDLE);
        } else if (valueType == VALUE_TYPE_EDITTEXT) {
            mEditText.setText(a.getString(R.styleable.PlayItemView_item_value));
        } else if (valueType == VALUE_TYPE_SWITCH_COMPAT) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                mSwitchCompat.setChecked(a.getBoolean(R.styleable.PlayItemView_item_value, false));
            }
        }


        mTitle.setTextColor(Color.parseColor("#333333"));

        mOpenIcon.setImageResource(a.getResourceId(R.styleable.PlayItemView_item_open_icon, R.drawable.arrow_right));
        mOpenIcon.setVisibility(a.getBoolean(R.styleable.PlayItemView_item_open_show, false) ? View.VISIBLE : View.GONE);
        mPaint.setColor(a.getColor(R.styleable.PlayItemView_item_divider_color, defaultSidelineColor));
        position = a.getInt(R.styleable.PlayItemView_item_position, POSITION_SINGLE);
        a.recycle();
    }

    public void initView(Context context) {
        setOrientation(LinearLayout.HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);
        mTitle = new AppCompatTextView(context);

        mTitleIcon = new AppCompatImageView(context);
        mOpenIcon = new AppCompatImageView(context);
        LayoutParams titleIconLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        int margin = Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, context.getResources().getDisplayMetrics()));
        int top = margin / 2;
        titleIconLayoutParams.leftMargin = margin;
        titleIconLayoutParams.topMargin = top;
        titleIconLayoutParams.bottomMargin = top;
        addView(mTitleIcon, titleIconLayoutParams);


        LayoutParams titleLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1);
        titleLayoutParams.leftMargin = margin;
        titleLayoutParams.topMargin = top;
        titleLayoutParams.bottomMargin = top;
        addView(mTitle, titleLayoutParams);


        switch (valueType) {
            case VALUE_TYPE_TEXTVIEW: {
                mValue = new AppCompatTextView(context);
                LayoutParams valueLayoutParams = new LayoutParams(0, LayoutParams.WRAP_CONTENT, 1);
                valueLayoutParams.rightMargin = margin;
                valueLayoutParams.leftMargin = margin;
                valueLayoutParams.topMargin = top;
                valueLayoutParams.bottomMargin = top;
                addView(mValue, valueLayoutParams);
                break;
            }
            case VALUE_TYPE_EDITTEXT: {
                mEditText = new AppCompatEditText(context);
                LayoutParams valueLayoutParams = new LayoutParams(0, LayoutParams.WRAP_CONTENT, 1);
                valueLayoutParams.rightMargin = margin;
                valueLayoutParams.leftMargin = margin;
//                valueLayoutParams.topMargin =top;
//                valueLayoutParams.bottomMargin =top;
                addView(mEditText, valueLayoutParams);
                break;
            }
            case VALUE_TYPE_SWITCH_COMPAT: {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                    mSwitchCompat = new SwitchCompat(context);
                    LayoutParams valueLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                    valueLayoutParams.rightMargin = margin;
                    valueLayoutParams.leftMargin = margin;
                    valueLayoutParams.topMargin = top;
                    valueLayoutParams.bottomMargin = top;
                    addView(mSwitchCompat, valueLayoutParams);
                }
                break;
            }

        }

        LayoutParams openIconLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        openIconLayoutParams.rightMargin = margin;
        addView(mOpenIcon, openIconLayoutParams);

        setBackgroundResource(R.drawable.item_backgroup_selector);
    }

    public void setTitle(Character text) {
        mTitle.setText(text);
    }

    public void setTitleIcon(int res) {
        mTitleIcon.setImageResource(res);
        mTitleIcon.setVisibility(View.VISIBLE);
    }

    public void setValue(Character text) {
        if (valueType == VALUE_TYPE_TEXTVIEW) {
            mValue.setText(text);
        } else if (valueType == VALUE_TYPE_EDITTEXT) {
            mEditText.setText(text);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public void setValue(boolean checked) {
        if (valueType == VALUE_TYPE_SWITCH_COMPAT) {
            mSwitchCompat.setChecked(checked);
        }
    }
    public void setOnCheckedChangeListener(@Nullable CompoundButton.OnCheckedChangeListener listener) {
        if (valueType == VALUE_TYPE_SWITCH_COMPAT) {
            mSwitchCompat.setOnCheckedChangeListener(listener);
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    public void setOnEditorActionListener(TextView.OnEditorActionListener l) {
        mEditText.setOnEditorActionListener(l);
    }
    /**
     * hide open icon
     */
    public void hideOpenIcon() {
        mOpenIcon.setVisibility(View.GONE);
    }

    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        mOpenIcon.setVisibility(View.VISIBLE);
        super.setOnClickListener(l);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setStrokeWidth(1);
        switch (position) {
            case POSITION_SINGLE:
                canvas.drawLine(0, 0, getWidth(), 0, mPaint);
                canvas.drawLine(0, getHeight(), getWidth(), getHeight(), mPaint);
                break;
            case POSITION_TOP:
                canvas.drawLine(0, 0, getWidth(), 0, mPaint);
                canvas.drawLine(0 + mTitle.getLeft(), getHeight(), getWidth(), getHeight(), mPaint);
                break;
            case POSITION_CENTER:
                canvas.drawLine(0 + mTitle.getLeft(), 0, getWidth(), 0, mPaint);
                canvas.drawLine(0 + mTitle.getLeft(), getHeight(), getWidth(), getHeight(), mPaint);
                break;
            case POSITION_BOTTOM:
                canvas.drawLine(0 + mTitle.getLeft(), 0, getWidth(), 0, mPaint);
                canvas.drawLine(0, getHeight(), getWidth(), getHeight(), mPaint);
                break;
        }
    }
}
