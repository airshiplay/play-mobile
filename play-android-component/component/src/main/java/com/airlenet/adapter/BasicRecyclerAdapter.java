package com.airlenet.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by lig on 16/7/20.
 */
public abstract class BasicRecyclerAdapter<VHolder extends BasicRecyclerAdapter.ViewHolder> extends RecyclerView.Adapter<VHolder> {
    protected ArrayList<Object> datas = new ArrayList<Object>();

    public BasicRecyclerAdapter(RecyclerView recyclerView) {
        recyclerView.setAdapter(this);
    }

    public static abstract class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }


    @Override
    public int getItemCount() {
        return datas.size();
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

    public abstract void request(Context context);

    public abstract void request(Context context, boolean more);
}
