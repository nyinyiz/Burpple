package com.padc.nyinyi.padcburppleapp.data.models;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import com.padc.nyinyi.padcburppleapp.PADCBurppleApp;
import com.padc.nyinyi.padcburppleapp.Persistence.BurppleDBContract;
import com.padc.nyinyi.padcburppleapp.dagger.AppComponent;
import com.padc.nyinyi.padcburppleapp.dagger.AppModule;
import com.padc.nyinyi.padcburppleapp.dagger.DaggerAppComponent;
import com.padc.nyinyi.padcburppleapp.dagger.NetworkModule;
import com.padc.nyinyi.padcburppleapp.data.vos.BurpplePromotionShop;
import com.padc.nyinyi.padcburppleapp.data.vos.PromotionVO;
import com.padc.nyinyi.padcburppleapp.events.RestApiEvents;
import com.padc.nyinyi.padcburppleapp.networks.BurppleDataAgent;
import com.padc.nyinyi.padcburppleapp.networks.BurppleDataAgentImpl;
import com.padc.nyinyi.padcburppleapp.utils.AppConstants;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Nyi Nyi on 1/17/2018.
 */

public class PromotionModel {

//    private static PromotionModel objInstance;

    private List<PromotionVO> mPromotionVOS;

    private int mmPageIndex = 1;

    @Inject
    BurppleDataAgent mDataAgent;


    public PromotionModel(Context context) {
        Log.d(PADCBurppleApp.LOG_TAG,context+"");
        EventBus.getDefault().register(this);
        mPromotionVOS = new ArrayList<>();

        PADCBurppleApp burppleApp = (PADCBurppleApp) context.getApplicationContext();
        burppleApp.getmAppComponent().inject(this);

    }
/*

    public static PromotionModel getInstance() {
        if (objInstance == null) {
            objInstance = new PromotionModel();
        }
        return objInstance;
    }
*/

    public void startloadingMMNews(Context context) {
        mDataAgent.loadPromotions(context, AppConstants.ACCESS_TOKEN,
                mmPageIndex);
    }

    public List<PromotionVO> getmPromotionVOS() {
        return mPromotionVOS;
    }


    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onPromotionDataLoaded(RestApiEvents.PromotionDataLoadedEvent event) {
        mPromotionVOS.addAll(event.getLoadPromotion());

        //TODO LOGIC TO SAVE THE DATA IN PERSISTENCE LAYER
        ContentValues[] promotionCVs = new ContentValues[event.getLoadPromotion().size()];
        List<ContentValues> burpplePromotionShopList = new ArrayList<>();
        List<ContentValues> burpplePromotionTermsList = new ArrayList<>();


        for (int index = 0; index < promotionCVs.length; index++) {

            promotionCVs[index] = event.getLoadPromotion().get(index).parseToContentValues();

            BurpplePromotionShop burpplePromotionShop = event.getLoadPromotion().get(index).getmBurpplePromotionShop();
            burpplePromotionShopList.add(burpplePromotionShop.parseToContentValues());


            for (String burpplePromotionTerms : event.getLoadPromotion().get(index).getmBurpplePromotionTerms()) {
                ContentValues imageInNewsCV = new ContentValues();
                imageInNewsCV.put(BurppleDBContract.BurpplePromotionTermsEntry.COLUMN_BURPPLE_PROMOTION_ID, event.getLoadPromotion().get(index).getmBurpplePromotionId());
                imageInNewsCV.put(BurppleDBContract.BurpplePromotionTermsEntry.COLUMN_BURPPLE_TERMS_NAME, burpplePromotionTerms);

                burpplePromotionTermsList.add(imageInNewsCV);
            }

        }


        int insertPromotionTerms = event.getContext().getContentResolver().bulkInsert(BurppleDBContract.BurpplePromotionTermsEntry.CONTENT_URI,
                burpplePromotionTermsList.toArray(new ContentValues[0]));
        Log.d(PADCBurppleApp.LOG_TAG, "insertPromotionTerms : " + insertPromotionTerms);

        int insertedPromotionShop = event.getContext().getContentResolver().bulkInsert(BurppleDBContract.BurpplePromotionShopEntry.CONTENT_URI,
                burpplePromotionShopList.toArray(new ContentValues[0]));
        Log.d(PADCBurppleApp.LOG_TAG, "insertedPromotionShop : " + insertedPromotionShop);

        int insertPromotion = event.getContext().getContentResolver().bulkInsert(BurppleDBContract.PromotionEntry.CONTENT_URI, promotionCVs);
        Log.d(PADCBurppleApp.LOG_TAG, "Inserted Row : " + insertPromotion);


    }

}
