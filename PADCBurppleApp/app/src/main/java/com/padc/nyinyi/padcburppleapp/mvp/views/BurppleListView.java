package com.padc.nyinyi.padcburppleapp.mvp.views;

import android.content.Context;

import com.padc.nyinyi.padcburppleapp.data.vos.FeaturedVO;
import com.padc.nyinyi.padcburppleapp.data.vos.GuideVO;
import com.padc.nyinyi.padcburppleapp.data.vos.PromotionVO;

import java.util.List;

/**
 * Created by user on 1/6/18.
 */

public interface BurppleListView {

    void displayPromotionList(List<PromotionVO> promotionVOList);

    void displayGuideList(List<GuideVO> guideVOList);

    void showLoading();

    void navigateToPromotionDetails(PromotionVO promotionVO);

    void navigateToGuideDetails(GuideVO guideVO);

    Context getContext();

    void displayFeatureList(List<FeaturedVO> guideVOList);
}
