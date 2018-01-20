package com.padc.nyinyi.padcburppleapp.delegates;

import com.padc.nyinyi.padcburppleapp.data.vos.GuideVO;
import com.padc.nyinyi.padcburppleapp.data.vos.PromotionVO;

/**
 * Created by User on 11/11/2017.
 */

public interface BurppleItemDelegate {
    void onTapPromotionItem(PromotionVO promotionVO);
    void onTapGuidesItem(GuideVO guideVO);
}
