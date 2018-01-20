package com.padc.nyinyi.padcburppleapp.viewholders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Nyi Nyi on 1/17/2018.
 */
public abstract class BaseViewHolder<W> extends RecyclerView.ViewHolder implements View.OnClickListener {

    private W mData;

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void setData(W data);


    public abstract void bind(Context context);
}