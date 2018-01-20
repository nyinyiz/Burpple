package com.padc.nyinyi.padcburppleapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import com.padc.nyinyi.padcburppleapp.viewholders.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nyi Nyi on 1/17/2018.
 */
public abstract class BaseRecyclerAdapter<T extends BaseViewHolder, W>
        extends RecyclerView.Adapter<T> {


    protected List<W> mData;
    protected LayoutInflater mLayoutInflator;
    Context context;

    public BaseRecyclerAdapter(Context context) {
        mData = new ArrayList<>();
        this.context = context;
        mLayoutInflator = LayoutInflater.from(context);
    }

    @Override
    public void onBindViewHolder(T holder, int position) {
        holder.setData(mData.get(position));
        holder.bind(context);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setNewData(List<W> newData) {
        mData = newData;
        notifyDataSetChanged();
    }

    public void appendNewData(List<W> newData) {
        mData.addAll(newData);
        notifyDataSetChanged();
    }

    public W getItemAt(int position) {
        if (position < mData.size() - 1)
            return mData.get(position);

        return null;
    }

    public List<W> getItems() {
        if (mData == null)
            return new ArrayList<>();

        return mData;
    }

    public void removeData(W data) {
        mData.remove(data);
        notifyDataSetChanged();
    }

    public void addNewData(W data) {
        mData.add(data);
        notifyDataSetChanged();
    }

    public void clearData() {
        mData = new ArrayList<>();
        notifyDataSetChanged();
    }
}
