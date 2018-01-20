
package com.padc.nyinyi.padcburppleapp.networks.responses;

import java.util.List;
import com.google.gson.annotations.SerializedName;
import com.padc.nyinyi.padcburppleapp.data.vos.PromotionVO;
import com.padc.nyinyi.padcburppleapp.networks.BurppleResponse;

public class PromotionResponse extends BurppleResponse{

    @SerializedName("apiVersion")
    private String mApiVersion;
    @SerializedName("code")
    private Long mCode;
    @SerializedName("message")
    private String mMessage;
    @SerializedName("page")
    private String mPage;
    @SerializedName("promotions")
    private List<PromotionVO> mPromotionVOS;

    public String getmApiVersion() {
        return mApiVersion;
    }

    public Long getmCode() {
        return mCode;
    }

    public String getmMessage() {
        return mMessage;
    }

    public String getmPage() {
        return mPage;
    }

    public List<PromotionVO> getmPromotionVOS() {
        return mPromotionVOS;
    }
}
