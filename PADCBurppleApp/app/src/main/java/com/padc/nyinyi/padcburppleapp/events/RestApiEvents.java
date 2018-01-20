package com.padc.nyinyi.padcburppleapp.events;

import android.content.Context;
import com.padc.nyinyi.padcburppleapp.data.vos.FeaturedVO;
import com.padc.nyinyi.padcburppleapp.data.vos.GuideVO;
import com.padc.nyinyi.padcburppleapp.data.vos.PromotionVO;

import java.util.List;

/**
 * Created by User on 12/2/2017.
 */

public class RestApiEvents {

    public static class EmptyResponseEvent
    {

    }

    public static class ErrorInvokingAPIEvent {
        private String errorMsg;

        public ErrorInvokingAPIEvent(String errorMsg) {
            this.errorMsg = errorMsg;
        }

        public String getErrorMsg() {
            return errorMsg;
        }
    }

    public static class FeaturedDataLoadedEvent{
        private String loadedpageIndex;
        private List<FeaturedVO> loadFeatured;

        private Context context;

        public FeaturedDataLoadedEvent(String loadedpageIndex, List<FeaturedVO> loadFeatured, Context context) {
            this.loadedpageIndex = loadedpageIndex;
            this.loadFeatured = loadFeatured;
            this.context = context;

        }

        public String  getLoadedpageIndex() {
            return loadedpageIndex;
        }

        public List<FeaturedVO> getLoadFeatured() {
            return loadFeatured;
        }

        public Context getContext() {
            return context;
        }
    }

    public static class PromotionDataLoadedEvent{
        private String loadedpageIndex;
        private List<PromotionVO> loadPromotion;

        private Context context;

        public PromotionDataLoadedEvent(String loadedpageIndex, List<PromotionVO> loadPromotion, Context context) {
            this.loadedpageIndex = loadedpageIndex;
            this.loadPromotion = loadPromotion;
            this.context = context;

        }

        public String  getLoadedpageIndex() {
            return loadedpageIndex;
        }

        public List<PromotionVO> getLoadPromotion() {
            return loadPromotion;
        }

        public Context getContext() {
            return context;
        }
    }


    public static class GuidedDataLoadedEvent{
        private String loadedpageIndex;
        private List<GuideVO> loadGuided;

        private Context context;

        public GuidedDataLoadedEvent(String loadedpageIndex, List<GuideVO> loadGuided, Context context) {
            this.loadedpageIndex = loadedpageIndex;
            this.loadGuided = loadGuided;
            this.context = context;

        }

        public String  getLoadedpageIndex() {
            return loadedpageIndex;
        }

        public List<GuideVO> getLoadGuided() {
            return loadGuided;
        }

        public Context getContext() {
            return context;
        }
    }




}
