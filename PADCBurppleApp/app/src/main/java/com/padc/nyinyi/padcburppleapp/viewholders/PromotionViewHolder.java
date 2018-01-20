package com.padc.nyinyi.padcburppleapp.viewholders;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.padc.nyinyi.padcburppleapp.PADCBurppleApp;
import com.padc.nyinyi.padcburppleapp.R;
import com.padc.nyinyi.padcburppleapp.components.ISO8601DateParser;
import com.padc.nyinyi.padcburppleapp.data.vos.PromotionVO;
import com.padc.nyinyi.padcburppleapp.delegates.BurppleItemDelegate;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Nyi Nyi on 1/17/2018.
 */
public class PromotionViewHolder extends BaseViewHolder<PromotionVO>{

    @BindView(R.id.iv_promotion)
    ImageView ivPromotion;
    @BindView(R.id.tv_promotion_title)
    TextView tvPromotionTitle;
    @BindView(R.id.tv_promotion_untile)
    TextView tvPromotionUntile;
    @BindView(R.id.tv_promotion_shop_name)
    TextView tvPromotionShopName;
    @BindView(R.id.tv_promotion_area)
    TextView tvPromotionArea;

    private PromotionVO mPromotionVO;
    private BurppleItemDelegate mDelegate;

    public PromotionViewHolder(View itemView,BurppleItemDelegate delegate) {
        super(itemView);
        ButterKnife.bind(this,itemView);
        mDelegate = delegate;
        itemView.setOnClickListener(this);
    }

    @Override
    public void setData(PromotionVO data) {
        mPromotionVO = data;

    }

    @Override
    public void bind(Context context) {

        if (mPromotionVO != null)
        {
            Glide.with(context)
                    .load(mPromotionVO.getmBurpplePromotionImage())
                    .placeholder(R.drawable.place_holder_promotion)
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(ivPromotion);


            if (mPromotionVO.getmBurpplePromotionShop() == null)
            {
                Log.d(PADCBurppleApp.LOG_TAG,"Null");
            }else {
               tvPromotionShopName.setText(mPromotionVO.getmBurpplePromotionShop().getmBurppleShopName());
               tvPromotionArea.setText(mPromotionVO.getmBurpplePromotionShop().getmBurppleShopArea());
            }

            tvPromotionTitle.setText(mPromotionVO.getmBurpplePromotionTitle());
            tvPromotionUntile.setText(ISO8601DateParser.getFormattedDateAndTime(context,mPromotionVO.getmBurpplePromotionUntil()));
        }




    }

    @Override
    public void onClick(View view) {
        mDelegate.onTapPromotionItem(mPromotionVO);
    }
}
