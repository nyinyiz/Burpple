package com.padc.nyinyi.padcburppleapp.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padc.nyinyi.padcburppleapp.R;
import com.padc.nyinyi.padcburppleapp.data.vos.FeaturedVO;
import com.padc.nyinyi.padcburppleapp.viewitems.HighlightImagesViewItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nyi Nyi on 1/17/2018.
 */
public class HighlightImagePagerAdapter extends PagerAdapter{

    private LayoutInflater mLayoutInflater;
    private List<String> mImages;
    private List<FeaturedVO> mFeatureVOList;

    public HighlightImagePagerAdapter(Context context) {
        super();
        mLayoutInflater = LayoutInflater.from(context);
        mImages = new ArrayList<>();
        mFeatureVOList = new ArrayList<>();

    }

    @Override
    public int getCount() {
//        return mImages.size();
        return mFeatureVOList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == (View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        HighlightImagesViewItem itemView = (HighlightImagesViewItem) mLayoutInflater.inflate(R.layout.view_item_highlight_image, container, false);

        itemView.setData(mFeatureVOList.get(position).getmBurppleFeaturedImage());
        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    public void setImages(List<FeaturedVO> featuredVOlist) {
        mFeatureVOList = featuredVOlist;
        notifyDataSetChanged();
    }
}
