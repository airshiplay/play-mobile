package com.airlenet.widget.item;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.airlenet.component.R;


public class Item2TextView extends LinearLayout {

    TextView mLabel;
    TextView mContent;

    public Item2TextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    @TargetApi(11)
    public Item2TextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    @TargetApi(21)
    public Item2TextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs,
                R.styleable.item_double_text_view);
        String label = array
                .getString(R.styleable.item_double_text_view_label_text);
        int labelColor = array.getColor(R.styleable.item_double_text_view_label_text_color, 0);
        String content = array
                .getString(R.styleable.item_double_text_view_content_text);
        int contentColor = array.getColor(R.styleable.item_double_text_view_content_text_color, 0);
        String contentHintText = array
                .getString(R.styleable.item_double_text_view_content_hint_text);
        // int lable_paddingLeft = array.getDimensionPixelSize(
        // R.styleable.item_view_lable_paddingLeft, 0);
        array.recycle();

        //setOrientation(HORIZONTAL);
        mContent = new TextView(context);
        mLabel = new TextView(context);

        if (label != null) {
            mLabel.setText(label);
            if (labelColor != 0)
                mLabel.setTextColor(labelColor);
            // mLabel.setPadding(lable_paddingLeft, 0, 0, 0);
        }
        mLabel.setGravity(Gravity.CENTER_VERTICAL);

        mContent.setText(content);
        if (contentColor != 0)
            mContent.setTextColor(contentColor);
        mContent.setHint(contentHintText);
        LayoutParams labelparams = new LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        labelparams.weight = 1;
        labelparams.gravity = Gravity.CENTER;
        addView(mLabel, labelparams);

        LayoutParams directparams = new LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);

        directparams.gravity = Gravity.CENTER;
        addView(mContent, directparams);
    }

    /**
     * 设置内容文本
     *
     * @param text
     */
    public void setText(String text) {
        if (mContent != null)
            mContent.setText(text);
    }

    /**
     * 设置标签文本
     *
     * @param text
     */
    public void setLabel(String text) {
        if (null != mLabel) {
            mLabel.setText(text);
        }
    }


    /**
     * 获取内容文本
     *
     * @return
     */
    public String getText() {
        if (mContent != null) {
            return mContent.getText().toString();
        }
        return null;
    }
}
