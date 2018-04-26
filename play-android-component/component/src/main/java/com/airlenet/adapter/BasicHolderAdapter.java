package com.airlenet.adapter;

import android.view.View;
import android.view.ViewGroup;

/**
 * ListView ,GridView,建议使用此Adapter
 *
 * @param <VHolder>
 */
public abstract class BasicHolderAdapter<VHolder extends BasicHolderAdapter.ViewHolder>
        extends BasicAdapter {

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = onCreateViewHolder(parent, getItemViewType(position));
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        onBindViewHolder((VHolder) holder, position);
        return holder.itemView;
    }

    public void bindViewHolder(VHolder holder, int position) {

    }

    /**
     * 绑定数据
     *
     * @param holder
     * @param position
     */
    public abstract void onBindViewHolder(VHolder holder, int position);

    /**
     * 创建ViewHolder,即每个item定义.
     *
     * @param parent
     * @param viewType
     * @return
     */
    public abstract VHolder onCreateViewHolder(ViewGroup parent, int viewType);

    /**
     * 继承此类实现ViewHolder
     */
    public static abstract class ViewHolder {
        public final View itemView;

        public ViewHolder(View itemView) {
            this.itemView = itemView;
            itemView.setTag(this);
        }
    }
}
