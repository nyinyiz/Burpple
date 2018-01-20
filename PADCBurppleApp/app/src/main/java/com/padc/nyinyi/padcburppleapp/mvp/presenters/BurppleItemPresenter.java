package com.padc.nyinyi.padcburppleapp.mvp.presenters;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.padc.nyinyi.padcburppleapp.PADCBurppleApp;
import com.padc.nyinyi.padcburppleapp.data.models.PromotionModel;
import com.padc.nyinyi.padcburppleapp.data.vos.FeaturedVO;
import com.padc.nyinyi.padcburppleapp.data.vos.GuideVO;
import com.padc.nyinyi.padcburppleapp.data.vos.PromotionVO;
import com.padc.nyinyi.padcburppleapp.delegates.BurppleItemDelegate;
import com.padc.nyinyi.padcburppleapp.mvp.views.BurppleListView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


/**
 * Created by user on 1/6/18.
 */

public class BurppleItemPresenter extends BasePresenter<BurppleListView> implements BurppleItemDelegate {

    @Inject
    PromotionModel mPromotionModel;

    public BurppleItemPresenter() {

    }

    @Override
    public void onCreate(BurppleListView view) {
        super.onCreate(view);

        Log.d(PADCBurppleApp.LOG_TAG,view.getContext()+"");
//        mPromotionModel = new PromotionModel(view.getContext());
        PADCBurppleApp burppleApp = (PADCBurppleApp) view.getContext();
        burppleApp.getmAppComponent().inject(this);

    }

    @Override
    public void onStart() {

        List<PromotionVO> promotionVOList = mPromotionModel.getmPromotionVOS();

        if (!promotionVOList.isEmpty())
        {
           mView.displayPromotionList(promotionVOList);

        }else {
            mView.showLoading();
        }
    }


    public void onPromotionStartLoading(Context context)
    {
        mPromotionModel.startloadingMMNews(context);
    }

    public void onPromotionDataLoaded(Cursor data, Context context)
    {
        if (data != null && data.moveToFirst())
        {
            List<PromotionVO> promotionVOList = new ArrayList<>();
            do {
                PromotionVO promotionVO = PromotionVO.parseFromCursor(context,data);
                promotionVOList.add(promotionVO);
            }while (data.moveToNext());

            mView.displayPromotionList(promotionVOList);

        }
    }

    public void onGuideDataLoaded(Cursor data,Context context)
    {
        if (data != null && data.moveToFirst())
        {
            List<GuideVO> guideVOList = new ArrayList<>();
            do {
                GuideVO guideVO = GuideVO.parseFromCursor(context,data);
                guideVOList.add(guideVO);

            }while (data.moveToNext());

            mView.displayGuideList(guideVOList);

        }
    }

    @Override
    public void onStop() {

    }

    @Override
    public void onTapPromotionItem(PromotionVO promotionVO) {
        mView.navigateToPromotionDetails(promotionVO);

    }

    @Override
    public void onTapGuidesItem(GuideVO guideVO) {
        mView.navigateToGuideDetails(guideVO);
    }

    public void onFeatureDataLoaded(Cursor data, Context applicationContext) {
        if (data != null && data.moveToFirst())
        {
            List<FeaturedVO> FeatureVoList = new ArrayList<>();
            do {
                FeaturedVO guideVO = FeaturedVO.parseFromCursor(applicationContext,data);
                FeatureVoList.add(guideVO);

            }while (data.moveToNext());

            mView.displayFeatureList(FeatureVoList);

        }
    }
}
