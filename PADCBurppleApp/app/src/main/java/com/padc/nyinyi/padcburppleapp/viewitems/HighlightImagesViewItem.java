package com.padc.nyinyi.padcburppleapp.viewitems;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.padc.nyinyi.padcburppleapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Kaung Htet Lin on 1/9/2018.
 */

public class HighlightImagesViewItem extends FrameLayout {

    @BindView(R.id.iv_highlight)
    ImageView ivHighlight;

    public HighlightImagesViewItem(@NonNull Context context) {
        super(context);
    }

    public HighlightImagesViewItem(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public HighlightImagesViewItem(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this, this);
    }

    public void setData(String imageUrl) {
        Glide.with(ivHighlight.getContext())
                .load(imageUrl)
                .into(ivHighlight);
    }
}
