package com.airletnet.mobile.pulltorefresh;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by lig on 2018/4/10.
 */

public class EmptyView extends LinearLayout {
    private ImageView mIcon;
    private TextView mErrorTitle;
    private TextView mErrorMsg;
    private TextView mErrorOperate;

    public EmptyView(Context context) {
        super(context);
        setOrientation(LinearLayout.VERTICAL);
        init(context);
    }

    public EmptyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setOrientation(LinearLayout.VERTICAL);
        init(context);
    }

    public void init(Context context) {
        setGravity(Gravity.CENTER);
        mIcon = new ImageView(context);
        addView(mIcon);

        mErrorTitle = new TextView(context);
        mErrorTitle.setGravity(Gravity.CENTER);
        addView(mErrorTitle);

        mErrorMsg = new TextView(context);
        mErrorMsg.setGravity(Gravity.CENTER);
        addView(mErrorMsg);

        mErrorOperate = new TextView(context);
        mErrorOperate.setText(R.string.loading);
        mErrorOperate.setGravity(Gravity.CENTER);
        addView(mErrorOperate);
    }

    public void setInfo(int resId,int titleId,int msgId,int operateId){
        mIcon.setImageResource(resId);
        mErrorTitle.setText(titleId);
        mErrorMsg.setText(msgId);
        mErrorOperate.setText(operateId);
    }
    public void setInfo(int resId,String titleId,String msgId,String operateId){
        mIcon.setImageResource(resId);
        mErrorTitle.setText(titleId);
        mErrorMsg.setText(msgId);
        mErrorOperate.setText(operateId);
    }
    public void setInfo(int titleId,int msgId,int operateId){
        mIcon.setImageDrawable(null);
        mErrorTitle.setText(titleId);
        mErrorMsg.setText(msgId);
        mErrorOperate.setText(operateId);
    }
    public void setInfo(int titleId,int msgId){
        mIcon.setImageDrawable(null);
        mErrorTitle.setText(titleId);
        mErrorMsg.setText(msgId);
    }
    public void setInfo(String titleId,String msgId,String operateId){
        mIcon.setImageDrawable(null);
        mErrorTitle.setText(titleId);
        mErrorMsg.setText(msgId);
        mErrorOperate.setText(operateId);
    }

    public void setOperate(View.OnClickListener listener){
        mErrorOperate.setOnClickListener(listener);
    }
}
