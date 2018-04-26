package com.airlenet.widget.item;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.airlenet.component.R;

/**
 * Created by lig on 16/7/17.
 */
public class GenderItemView extends LinearLayout {
    private TextView labelView;
    private RadioButton male;
    private RadioButton female;


    public GenderItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    @TargetApi(11)
    public GenderItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @TargetApi(21)
    public GenderItemView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs,
                R.styleable.input_view);
        CharSequence lableText = array
                .getText(R.styleable.input_view_label_text);


        array.recycle();
        setOrientation(LinearLayout.HORIZONTAL);
        labelView = new TextView(context);
        labelView.setText(lableText);
        setGravity(Gravity.CENTER_VERTICAL);
        LayoutParams labelparams = new LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        labelparams.gravity = Gravity.CENTER_VERTICAL;
        labelparams.weight=1;
        addView(labelView, labelparams);

        RadioGroup radioGroup = new RadioGroup(context);
        male = new RadioButton(context);
        male.setText("男");
        male.setPadding(30,0,30,0);
        female = new RadioButton(context);
        female.setText("女");
        female.setPadding(30,0,20,0);
        radioGroup.addView(male);
        radioGroup.addView(female);

        radioGroup.setOrientation(RadioGroup.HORIZONTAL);
        LayoutParams radiogroupParams = new LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        radiogroupParams.gravity = Gravity.RIGHT;
        radioGroup.setGravity(Gravity.RIGHT);
        addView(radioGroup, radiogroupParams);

    }

}
