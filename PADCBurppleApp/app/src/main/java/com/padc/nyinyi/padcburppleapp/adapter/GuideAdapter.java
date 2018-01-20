package com.padc.nyinyi.padcburppleapp.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.padc.nyinyi.padcburppleapp.R;
import com.padc.nyinyi.padcburppleapp.data.vos.GuideVO;
import com.padc.nyinyi.padcburppleapp.delegates.BurppleItemDelegate;
import com.padc.nyinyi.padcburppleapp.viewholders.GuideViewHolder;


/**
 * Created by Nyi Nyi on 1/17/2018.
 */
public class GuideAdapter extends BaseRecyclerAdapter<GuideViewHolder,GuideVO> {

    private BurppleItemDelegate mDelegate;

    public GuideAdapter(Context context,BurppleItemDelegate burppleItemDelegate) {
        super(context);
        mDelegate = burppleItemDelegate;
    }

    @Override
    public GuideViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View guideScreenView = mLayoutInflator.inflate(R.layout.view_item_guides, parent, false);
        return new GuideViewHolder(guideScreenView,mDelegate);
    }


}
