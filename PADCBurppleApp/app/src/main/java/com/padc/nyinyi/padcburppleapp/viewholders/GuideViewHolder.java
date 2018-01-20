package com.padc.nyinyi.padcburppleapp.viewholders;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.padc.nyinyi.padcburppleapp.PADCBurppleApp;
import com.padc.nyinyi.padcburppleapp.R;
import com.padc.nyinyi.padcburppleapp.activities.GuideDetailActivity;
import com.padc.nyinyi.padcburppleapp.data.vos.GuideVO;
import com.padc.nyinyi.padcburppleapp.delegates.BurppleItemDelegate;
import com.padc.nyinyi.padcburppleapp.networks.responses.GuideResponse;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Nyi Nyi on 1/17/2018.
 */

public class GuideViewHolder extends BaseViewHolder<GuideVO> {

    @BindView(R.id.iv_guide)
    ImageView ivGuide;

    private GuideVO mGuideVO;
    private BurppleItemDelegate mDelegate;

    public GuideViewHolder(View itemView, BurppleItemDelegate delegate) {
        super(itemView);
        ButterKnife.bind(this,itemView);
        mDelegate = delegate;
        itemView.setOnClickListener(this);
    }

    @Override
    public void setData(GuideVO data) {
        mGuideVO = data;
    }

    @Override
    public void bind(Context context) {

        if (mGuideVO != null)
        {
            Log.d(PADCBurppleApp.LOG_TAG,mGuideVO.getmBurppleGuideImage()+" Burpple Guide image.");
            Glide.with(context)
                    .load(mGuideVO.getmBurppleGuideImage())
                    .placeholder(R.drawable.place_holder_promotion)
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(ivGuide);

        }
    }

    @Override
    public void onClick(View view) {
        mDelegate.onTapGuidesItem(mGuideVO);
    }
}
