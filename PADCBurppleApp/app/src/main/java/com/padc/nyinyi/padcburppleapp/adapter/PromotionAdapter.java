package com.padc.nyinyi.padcburppleapp.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.padc.nyinyi.padcburppleapp.R;
import com.padc.nyinyi.padcburppleapp.data.vos.PromotionVO;
import com.padc.nyinyi.padcburppleapp.delegates.BurppleItemDelegate;
import com.padc.nyinyi.padcburppleapp.viewholders.PromotionViewHolder;

/**
 * Created by Nyi Nyi on 1/17/2018.
 */
public class PromotionAdapter extends BaseRecyclerAdapter<PromotionViewHolder,PromotionVO> {

    private BurppleItemDelegate mDelegate;
    public PromotionAdapter(Context context,BurppleItemDelegate delegate) {
        super(context);
        mDelegate = delegate;
    }

    @Override
    public PromotionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View promotionScreenView = mLayoutInflator.inflate(R.layout.view_item_promotions, parent, false);
        return new PromotionViewHolder(promotionScreenView,mDelegate);
    }


}
