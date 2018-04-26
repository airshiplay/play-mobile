package com.airlenet.widget.listview;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.airlenet.component.R;

/**
 * Created by lig on 16/7/17.
 */
public class DoubleListView extends LinearLayout {
    private ListView leftListView;
    private ListView rightListView;
    private int selectedBackgroundColor = Color.WHITE;
    private int selectedTextColor = Color.parseColor("#3f9ae8");
    private int itemBackgroundColor = Color.parseColor("#f0f0f0");
    private int itemTextColor = Color.BLACK;

    public DoubleListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    @TargetApi(11)
    public DoubleListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @TargetApi(21)
    public DoubleListView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

        TypedArray array = context.obtainStyledAttributes(attrs,
                R.styleable.DoubleListView);
        int leftColor = array.getColor(R.styleable.DoubleListView_left_divider, Color.parseColor("#d9d9d9"));
        int rightColor = array.getColor(R.styleable.DoubleListView_right_divider, Color.parseColor("#d9d9d9"));
        int leftBackground = array.getColor(R.styleable.DoubleListView_left_background, Color.argb(0, 0, 0, 0));
        int rightBackground = array.getColor(R.styleable.DoubleListView_right_background, Color.argb(0, 0, 0, 0));
        array.recycle();

        leftListView = new ListView(context);
        rightListView = new ListView(context);
        leftListView.setId(R.id.left_listview);
        rightListView.setId(R.id.right_listview);
        leftListView.setBackgroundColor(leftBackground);
        rightListView.setBackgroundColor(rightBackground);

        leftListView.setDivider(new ColorDrawable(leftColor));
        leftListView.setDividerHeight(1);
        rightListView.setDivider(new ColorDrawable(rightColor));
        rightListView.setDividerHeight(1);
        setOrientation(LinearLayout.HORIZONTAL);
        LayoutParams lightParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        lightParams.weight = 1;
        addView(leftListView, lightParams);

        LayoutParams rightParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        rightParams.weight = 1;
        addView(rightListView, rightParams);
    }

    public ListView getLeftListView() {
        return leftListView;
    }

    public ListView getRightListView() {
        return rightListView;
    }

    public int getItemBackgroundColor() {
        return itemBackgroundColor;
    }

    public int getSelectedTextColor() {
        return selectedTextColor;
    }

    public int getItemTextColor() {
        return itemTextColor;
    }

    public int getSelectedBackgroundColor() {
        return selectedBackgroundColor;
    }
}
