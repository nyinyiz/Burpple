package com.padc.nyinyi.padcburppleapp.networks;

import com.padc.nyinyi.padcburppleapp.networks.responses.FeaturedResponse;
import com.padc.nyinyi.padcburppleapp.networks.responses.GuideResponse;
import com.padc.nyinyi.padcburppleapp.networks.responses.PromotionResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by user on 1/18/18.
 */

public interface BurppleAPI {

    @FormUrlEncoded
    @POST("v1/getFeatured.php")
    Call<FeaturedResponse> loadFeatured(
            @Field("access_token") String accessToken,
            @Field("page") int pageIndex
    );

    @FormUrlEncoded
    @POST("v1/getPromotions.php")
    Call<PromotionResponse> loadPromotions(
            @Field("access_token") String accessToken,
            @Field("page") int pageIndex
    );

    @FormUrlEncoded
    @POST("v1/getGuides.php")
    Call<GuideResponse> loadGuides(
            @Field("access_token") String accessToken,
            @Field("page") int pageIndex
    );

}
