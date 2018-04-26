package com.airlenet.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.widget.BaseAdapter;

/**
 * ListView ,GridView,不建议使用
 */
@Deprecated
public abstract class BasicAdapter extends BaseAdapter {
    protected ArrayList<Object> datas;

    public BasicAdapter() {
        datas = new ArrayList<Object>();
    }

    public void addData(Object object) {
        datas.add(object);
        notifyDataSetChanged();
    }

    public void setData(int position, Object object) {
        datas.set(position, object);
        notifyDataSetChanged();
    }


    public void setAllData(ArrayList listDatas) {
        datas.clear();
        datas.addAll(listDatas);
        notifyDataSetChanged();
    }
    public void addAllData(ArrayList listDatas) {
        datas.addAll(listDatas);
        notifyDataSetChanged();
    }
    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    public abstract void request(Context context);

    public abstract void request(Context context, boolean more);

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
