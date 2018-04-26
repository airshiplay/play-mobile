package com.airlenet.widget.item;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.airlenet.component.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class ItemView extends LinearLayout {

    ImageView mIcon;
    TextView mLabel;
    ImageView mDirect;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public ItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initData(context, attrs);
    }
    @TargetApi(21)
    public ItemView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initData(context, attrs);
    }

    public ItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initData(context, attrs);
    }


    private void initData(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.item_view);
        Drawable icon = array.getDrawable(R.styleable.item_view_icon_src);
        String label = array.getString(R.styleable.item_view_label_text);
        int labelColor = array.getColor(R.styleable.item_view_label_text_color,0);
        Drawable direct = array.getDrawable(R.styleable.item_view_direct_src);
        int lable_paddingLeft = array.getDimensionPixelSize(R.styleable.item_view_label_paddingLeft, 0);
        int directSize = array.getDimensionPixelSize(R.styleable.item_view_direct_size, 0);
        int labelIconPadding = array.getDimensionPixelOffset(R.styleable.item_view_label_icon_padding, 0);
        int space = array.getDimensionPixelSize(R.styleable.item_view_space, 8);
        boolean circle = array.getBoolean(R.styleable.item_view_circle, false);
        array.recycle();

        //setOrientation(HORIZONTAL);
        mIcon = new ImageView(context);
        mLabel = new TextView(context);
        if (circle) {
            mDirect = new CircleImageView(context);
        } else {
            mDirect = new ImageView(context);
        }

        if (icon != null) {
            mIcon.setImageDrawable(icon);
        }
        if (label != null) {
            mLabel.setText(label);
            if(labelColor!=0){
                mLabel.setTextColor(labelColor);
            }
            mLabel.setPadding(lable_paddingLeft, 0, 0, 0);
        }
        mLabel.setGravity(Gravity.CENTER_VERTICAL);
        if (direct != null) {
            mDirect.setImageDrawable(direct);
        }
        if (mIcon != null) {
            LayoutParams iconparams = new LayoutParams(LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT);
            iconparams.gravity = Gravity.CENTER;


            addView(mIcon, iconparams);
        }
        LayoutParams labelparams = new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        labelparams.weight = 1;
        labelparams.gravity = Gravity.CENTER;
        if (getOrientation() == LinearLayout.HORIZONTAL) {
            labelparams.leftMargin = labelIconPadding;
        } else {
            labelparams.topMargin = labelIconPadding;
        }
        addView(mLabel, labelparams);

        if (mDirect != null) {
            LayoutParams directparams = null;
            if (directSize == 0) {
                directparams = new LayoutParams(LayoutParams.WRAP_CONTENT,
                        LayoutParams.WRAP_CONTENT);
            } else {
                directparams = new LayoutParams(directSize, directSize);
            }
            directparams.leftMargin = space;
            directparams.gravity = Gravity.CENTER;
            addView(mDirect, directparams);
        }
    }

    public void setText(String text) {
        if (mLabel != null)
            mLabel.setText(text);
    }

    public String getText() {
        if (mLabel != null)
            return mLabel.getText().toString();
        return null;
    }

    // public ItemView(Context context, AttributeSet attrs) {
    // this(context, attrs, 0);
    // initData(context, attrs, 0);
    // }

    public ImageView getDirectImageView() {
        return mDirect;
    }

}
