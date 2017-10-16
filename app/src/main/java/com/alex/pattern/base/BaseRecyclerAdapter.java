package com.alex.pattern.base;

import android.support.v7.widget.RecyclerView;

import com.alex.pattern.interfaces.IClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex
 */

public abstract class BaseRecyclerAdapter<VH extends RecyclerView.ViewHolder, M> extends RecyclerView.Adapter<VH> {

    protected List<M> mList = new ArrayList<>();
    protected IClickListener mClickListener;

    @Override
    public int getItemCount() {
        return (mList == null) ? 0 : mList.size();
    }

    protected BaseRecyclerAdapter() {
    }

    public void replaceItems(List<M> list) {
        mList = list;
        notifyDataSetChanged();
    }

    public void addItems(List<M> list) {
        mList.addAll(list);
        notifyDataSetChanged();
    }

    public void addItem(M item) {
        mList.add(item);
        notifyDataSetChanged();
    }

    public void removeItem(int position) {
        mList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mList.size());
    }

    public void updateItem(int position, M item) {
        mList.set(position, item);
        notifyItemChanged(position);
    }

    public void setClickListener(IClickListener listener) {
        mClickListener = listener;
    }

}
