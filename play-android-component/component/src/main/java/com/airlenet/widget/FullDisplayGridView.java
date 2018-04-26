package com.airlenet.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by lig on 16/8/1.
 */
public class FullDisplayGridView extends GridView {
    public FullDisplayGridView(Context context) {
        super(context);
    }

    public FullDisplayGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FullDisplayGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(21)
    public FullDisplayGridView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
        //super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
