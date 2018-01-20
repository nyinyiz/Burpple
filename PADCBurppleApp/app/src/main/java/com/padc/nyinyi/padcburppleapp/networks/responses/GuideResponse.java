
package com.padc.nyinyi.padcburppleapp.networks.responses;

import java.util.List;
import com.google.gson.annotations.SerializedName;
import com.padc.nyinyi.padcburppleapp.data.vos.GuideVO;
import com.padc.nyinyi.padcburppleapp.networks.BurppleResponse;

public class GuideResponse extends BurppleResponse{

    @SerializedName("apiVersion")
    private String mApiVersion;
    @SerializedName("code")
    private Long mCode;
    @SerializedName("featured")
    private List<GuideVO> mGuideVO;
    @SerializedName("message")
    private String mMessage;
    @SerializedName("page")
    private String mPage;

    public String getmApiVersion() {
        return mApiVersion;
    }

    public Long getmCode() {
        return mCode;
    }

    public List<GuideVO> getmGuideVO() {
        return mGuideVO;
    }

    public String getmMessage() {
        return mMessage;
    }

    public String getmPage() {
        return mPage;
    }
}
