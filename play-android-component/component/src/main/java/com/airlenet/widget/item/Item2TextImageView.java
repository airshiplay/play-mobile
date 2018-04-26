package com.airlenet.widget.item;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.airlenet.component.R;

/**
 * tup
 */
public class Item2TextImageView extends LinearLayout {

    TextView mLabel;
    TextView mContent;
    ImageView mImageView;

    public Item2TextImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    @TargetApi(11)
    public Item2TextImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    @TargetApi(21)
    public Item2TextImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
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
        String contentHintText = array
                .getString(R.styleable.item_double_text_view_content_hint_text);
        Drawable image = array.getDrawable(R.styleable.item_double_text_view_image_src);
        boolean contentTextWeight = array.getBoolean(R.styleable.item_double_text_view_content_text_weight, false);
        int space = array.getDimensionPixelSize(R.styleable.item_double_text_view_space, 8);
        // int lable_paddingLeft = array.getDimensionPixelSize(
        // R.styleable.item_view_lable_paddingLeft, 0);
        array.recycle();

        //setOrientation(HORIZONTAL);

        mLabel = new TextView(context);

        if (label != null) {
            mLabel.setText(label);
            if (labelColor != 0)
                mLabel.setTextColor(labelColor);
            // mLabel.setPadding(lable_paddingLeft, 0, 0, 0);
        }
        mLabel.setGravity(Gravity.CENTER_VERTICAL);
        LayoutParams labelparams = new LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        if (!contentTextWeight)
            labelparams.weight = 1;
        labelparams.gravity = Gravity.CENTER;
        addView(mLabel, labelparams);


        mContent = new TextView(context);
        mContent.setText(content);
        mContent.setHint(contentHintText);
        LayoutParams directparams = new LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        if (contentTextWeight)
            directparams.weight = 1;
        directparams.gravity = Gravity.CENTER;
        addView(mContent, directparams);


        if (image != null) {
            mImageView = new ImageView(context);
            mImageView.setImageDrawable(image);
            LayoutParams imageParams = new LayoutParams(
                    LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT);
            imageParams.gravity = Gravity.CENTER;
            imageParams.leftMargin = space;
            addView(mImageView, imageParams);
        }
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
