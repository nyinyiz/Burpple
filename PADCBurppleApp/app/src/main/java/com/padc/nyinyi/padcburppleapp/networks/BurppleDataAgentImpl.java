package com.padc.nyinyi.padcburppleapp.networks;

import android.content.Context;

import com.google.gson.Gson;
import com.padc.nyinyi.padcburppleapp.events.RestApiEvents;
import com.padc.nyinyi.padcburppleapp.networks.responses.FeaturedResponse;
import com.padc.nyinyi.padcburppleapp.networks.responses.GuideResponse;
import com.padc.nyinyi.padcburppleapp.networks.responses.PromotionResponse;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by user on 1/18/18.
 */

public class BurppleDataAgentImpl implements BurppleDataAgent {

//    private static BurppleDataAgentImpl objInstance;
    private BurppleAPI theAPI;

    public BurppleDataAgentImpl() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://padcmyanmar.com/padc-3/burpple-food-places/apis/")
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .client(okHttpClient)
                .build();

        theAPI = retrofit.create(BurppleAPI.class);

    }

   /* public static BurppleDataAgentImpl getInstance() {
        if (objInstance == null){
            objInstance = new BurppleDataAgentImpl();
        }
        return objInstance;
    }*/

    @Override
    public void loadFeatured(final Context context, String accessToken, int pageNo) {
        Call<FeaturedResponse> loadMMNewsCall = theAPI.loadFeatured(accessToken, pageNo);
        loadMMNewsCall.enqueue(new BurppleCallBack<FeaturedResponse>() {
            @Override
            public void onResponse(Call<FeaturedResponse> call, Response<FeaturedResponse> response) {
                super.onResponse(call, response);
                FeaturedResponse getNewsResponse = response.body();

                if (getNewsResponse != null && getNewsResponse.getmFeatured().size() > 0) {
                    RestApiEvents.FeaturedDataLoadedEvent featuredDataLoadedEvent = new
                            RestApiEvents.FeaturedDataLoadedEvent(
                            getNewsResponse.getmPage(), getNewsResponse.getmFeatured(),context);

                    EventBus.getDefault().post(featuredDataLoadedEvent);
                }
            }

        });

    }

    @Override
    public void loadPromotions(final Context context, String accessToken, int pageNo) {
        Call<PromotionResponse> loadMMNewsCall = theAPI.loadPromotions(accessToken, pageNo);
        loadMMNewsCall.enqueue(new BurppleCallBack<PromotionResponse>() {
            @Override
            public void onResponse(Call<PromotionResponse> call, Response<PromotionResponse> response) {
                super.onResponse(call, response);
                PromotionResponse getPromotionResponse = response.body();

                if (getPromotionResponse != null && getPromotionResponse.getmPromotionVOS().size() > 0) {
                    RestApiEvents.PromotionDataLoadedEvent promotionDataLoadedEvent = new
                            RestApiEvents.PromotionDataLoadedEvent(
                            getPromotionResponse.getmPage(), getPromotionResponse.getmPromotionVOS(),context);

                    EventBus.getDefault().post(promotionDataLoadedEvent);
                }
            }

        });

    }

    @Override
    public void loadGuides(final Context context, String accessToken, int pageNo) {

        Call<GuideResponse> loadMMNewsCall = theAPI.loadGuides(accessToken, pageNo);
        loadMMNewsCall.enqueue(new BurppleCallBack<GuideResponse>() {
            @Override
            public void onResponse(Call<GuideResponse> call, Response<GuideResponse> response) {
                super.onResponse(call, response);
                GuideResponse getGuideResponse = response.body();

                if (getGuideResponse != null && getGuideResponse.getmGuideVO().size() > 0) {
                    RestApiEvents.GuidedDataLoadedEvent guidedDataLoadedEvent = new
                            RestApiEvents.GuidedDataLoadedEvent(
                            getGuideResponse.getmPage(), getGuideResponse.getmGuideVO(),context);

                    EventBus.getDefault().post(guidedDataLoadedEvent);
                }
            }

        });

    }
}
