package com.airlenet.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.airlenet.component.R;

/**
 * Created by lig on 16/7/27.
 */
public class ExcelLayout extends ViewGroup {
    private int columns = 2;
    private int rows = 3;
    private int[] lineHeights;
    private int cellColor;
    int padding;
    private int textGravity = Gravity.CENTER;

    public ExcelLayout(Context context) {
        super(context);
        init(context, null);
    }

    public ExcelLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ExcelLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @TargetApi(21)
    public ExcelLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics());
        TypedArray array = context.obtainStyledAttributes(attrs,
                R.styleable.excel_layout);
        rows = array.getInt(R.styleable.excel_layout_rowCount, 1);
        columns = array.getInt(R.styleable.excel_layout_columnCount, 1);
        int backgroudColor = array.getColor(R.styleable.excel_layout_space_color, Color.parseColor("#e3e3e3"));
        CharSequence[] names = array.getTextArray(R.styleable.excel_layout_columnNames);
        cellColor = array.getColor(R.styleable.excel_layout_cell_color, Color.WHITE);
        array.recycle();
        setBackgroundColor(backgroudColor);
        setPadding(0, 0, 1, 1);
        lineHeights = new int[rows];
        for (int i = 0; i < rows * columns; i++) {
            TextView textView = new TextView(context);
            textView.setBackgroundColor(cellColor);
            textView.setGravity(textGravity);
            textView.setPadding(padding, padding, padding, padding);
            if (names != null && i < names.length) {
                textView.setText(names[i]);
            }
            addView(textView);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);

        // wrap_content
        int width = 0;
        int height = 0;
        int cCount = getChildCount();
        rows = (cCount) / columns + 1;//总行数
        lineHeights = new int[rows];
        int maxLineHeight = 0;
        for (int i = 0; i < cCount; i++) {
            int lineWidth = 0;
            int lineHeight = 0;
            View child = getChildAt(i);
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) child
                    .getLayoutParams();
            lp.width = sizeWidth / columns;
            lp.leftMargin = 1;
            lp.topMargin = 1;
            measureChild(child, widthMeasureSpec, heightMeasureSpec);


            int childWidth = child.getMeasuredWidth() + lp.leftMargin
                    + lp.rightMargin;
            int childHeight = child.getMeasuredHeight() + lp.topMargin
                    + lp.bottomMargin;
            if (maxLineHeight < childHeight)
                maxLineHeight = childHeight;
            if (i % columns == columns - 1 || i == cCount - 1) {//行最后一个,或最后一个
                lineHeights[i / columns] = maxLineHeight;
                maxLineHeight = 0;
                height = height + lineHeights[i / columns];
            }
        }
        System.out.println(height);
        setMeasuredDimension(
                //
                modeWidth == MeasureSpec.EXACTLY ? sizeWidth : width + getPaddingLeft() + getPaddingRight(),
                modeHeight == MeasureSpec.EXACTLY ? sizeHeight : height + getPaddingTop() + getPaddingBottom()//
        );

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int cCount = getChildCount();
        int left = getPaddingLeft();
        int top = getPaddingTop();
        int height = 0;
        int width = 0;
        for (int i = 0; i < cCount; i++) {
            View child = getChildAt(i);
            child.setBackgroundColor(cellColor);
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) child
                    .getLayoutParams();

            int lc, tc, rc, bc;
            if (i % columns == 0) {///行第一个
                height = lineHeights[i / columns] - 1;
            }
            int realHeight = child.getMeasuredHeight();
            width = child.getMeasuredWidth();
            child.getWidth();
            lc = left + lp.leftMargin;
            tc = top + lp.topMargin;
            rc = lc + width;
            bc = tc + height;
            if (lp.gravity == Gravity.CENTER) {
                child.setPadding(child.getPaddingLeft(), child.getPaddingTop() + (height - realHeight) / 2, child.getPaddingRight(), child.getPaddingBottom() + (height - realHeight) / 2);
            } else if (lp.gravity == Gravity.CENTER_VERTICAL) {
                child.setPadding(child.getPaddingLeft(), child.getPaddingTop() + (height - realHeight) / 2, child.getPaddingRight(), child.getPaddingBottom() + (height - realHeight) / 2);
            }
            child.layout(lc, tc, rc, bc);

            left += child.getMeasuredWidth() + lp.leftMargin
                    + lp.rightMargin;
            if (i % columns == 0) {///行第一个

            } else if (i % columns == columns - 1 || i == cCount - 1) {//行最后一个,或最后一个
                top += lineHeights[i / columns];
                left = getPaddingLeft();
            } else {

            }

        }
    }

    /**
     * @param rowIndex 从0开始
     * @param values
     */

    public void setLineValue(int rowIndex, String... values) {
        for (int i = 0; i < columns; i++) {
            if (i < values.length)
                ((TextView) getChildAt(rowIndex * columns + i)).setText(values[i]);
        }
    }

    /**
     * 添加行数
     *
     * @param rowCount
     */
    public void setViewLine(int rowCount) {

        int childCount = getChildCount();//根据需要增加或移除多余的view
        if (rowCount * columns > childCount) {//默认不够,增加
            for (int j = 0; j < rowCount * columns - childCount; j++) {
                TextView nameText = new TextView(getContext());
                nameText.setGravity(textGravity);
                nameText.setPadding(padding, padding, padding, padding);
                nameText.setBackgroundColor(cellColor);
                addView(nameText);
            }

        } else {//多余
            int extraCount = childCount - rowCount * columns;
            for (int i = 0; i < extraCount; i++) {
                removeViewAt(getChildCount() - 1);
            }
        }


    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(getContext(), attrs);
        layoutParams.gravity = Gravity.CENTER;
        return layoutParams;
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER;
        return layoutParams;
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(p);
        layoutParams.gravity = Gravity.CENTER;
        return layoutParams;
    }
}
