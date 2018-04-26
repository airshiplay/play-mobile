package com.airlenet.widget.item;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.airlenet.component.R;

public class ImageInputView extends LinearLayout {
    private ImageView labelView;
    private EditText inputView;
    private ImageView imageView;


    public ImageInputView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    @TargetApi(11)
    public ImageInputView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    @TargetApi(21)
    public ImageInputView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }


    private void init(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs,
                R.styleable.input_view);

        CharSequence inputHintText = array
                .getText(R.styleable.input_view_input_hint);
        CharSequence lableText = array
                .getText(R.styleable.input_view_label_text);
        Drawable background = array
                .getDrawable(R.styleable.input_view_input_background);
        Drawable label_src = array
                .getDrawable(R.styleable.input_view_label_src);
        Drawable src = array.getDrawable(R.styleable.input_view_input_src);
        int textColor = array.getColor(R.styleable.input_view_input_textColor,
                Color.BLACK);
        int textSize = array.getDimensionPixelSize(
                R.styleable.input_view_input_textSize, 0);
        int hintTextColor = array.getColor(
                R.styleable.input_view_input_hintTextColor, Color.GRAY);
        int input_inputType = array.getInt(
                R.styleable.input_view_input_inputType, EditorInfo.TYPE_NULL);
        int inut_imeOptions = array.getInt(
                R.styleable.input_view_input_imeOptions, EditorInfo.IME_NULL);
        array.recycle();
        setOrientation(HORIZONTAL);

        labelView = new ImageView(context);
        labelView.setImageDrawable(label_src);

        inputView = new EditText(context);
        inputView.setSingleLine();
        inputView.setHint(inputHintText);
        inputView.setTextColor(textColor);
        inputView.setHintTextColor(hintTextColor);
        if (textSize != 0)
            inputView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        if (input_inputType != EditorInfo.TYPE_NULL)
            inputView.setInputType(input_inputType);
        if (inut_imeOptions != EditorInfo.IME_NULL) {
            inputView.setImeOptions(inut_imeOptions);
        }
        // if(background!=null)
        inputView.setBackgroundDrawable(background);

        imageView = new ImageView(context);
        if (src != null)
            imageView.setImageDrawable(src);
        LayoutParams labelparams = new LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        labelparams.gravity = Gravity.CENTER_VERTICAL;
        addView(labelView, labelparams);
        LayoutParams editparams = new LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT, 1);
        editparams.gravity = Gravity.CENTER_VERTICAL;
        addView(inputView, editparams);
        if (src != null)
            addView(imageView, labelparams);
    }

    public void setImageOnClickListener(OnClickListener l) {
        imageView.setOnClickListener(l);
    }

    public Editable getText() {
        return inputView.getText();
    }

    public EditText getEditText() {
        return inputView;
    }

    public void setText(CharSequence text) {
        inputView.setText(text);
    }
}
