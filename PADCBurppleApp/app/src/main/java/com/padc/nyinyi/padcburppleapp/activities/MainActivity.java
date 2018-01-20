package com.padc.nyinyi.padcburppleapp.activities;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Handler;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationAdapter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.padc.nyinyi.padcburppleapp.PADCBurppleApp;
import com.padc.nyinyi.padcburppleapp.Persistence.BurppleDBContract;
import com.padc.nyinyi.padcburppleapp.R;
import com.padc.nyinyi.padcburppleapp.adapter.GuideAdapter;
import com.padc.nyinyi.padcburppleapp.adapter.HighlightImagePagerAdapter;
import com.padc.nyinyi.padcburppleapp.adapter.PromotionAdapter;
import com.padc.nyinyi.padcburppleapp.components.ViewPagerCustomDuration;
import com.padc.nyinyi.padcburppleapp.data.vos.FeaturedVO;
import com.padc.nyinyi.padcburppleapp.data.vos.GuideVO;
import com.padc.nyinyi.padcburppleapp.data.vos.PromotionVO;
import com.padc.nyinyi.padcburppleapp.mvp.presenters.BurppleItemPresenter;
import com.padc.nyinyi.padcburppleapp.mvp.views.BurppleListView;
import com.rd.PageIndicatorView;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Nyi Nyi on 1/17/2018.
 */
public class MainActivity extends BaseActivity
        implements LoaderManager.LoaderCallbacks<Cursor>,BurppleListView{

    private static final int PROMOTION_LIST_LOADER_ID = 100;

    private static final int GUIDE_LIST_LOADER_ID = 200;

    private static final int FEATURE_LIST_LOADER_ID = 300;

    @BindView(R.id.vp_highlight_images)
    ViewPagerCustomDuration vpHighlightImages;
    @BindView(R.id.pageIndicatorView)
    PageIndicatorView pageIndicatorView;
    @BindView(R.id.rv_guide)
    RecyclerView rvGuide;
    @BindView(R.id.rv_promotion)
    RecyclerView rvPromotion;
    @BindView(R.id.bottom_navigation)
    AHBottomNavigation bottomNavigation;
    @BindView(R.id.tool_bar)
    Toolbar toolbar;

    @Inject
    BurppleItemPresenter mPresenter;

//    private BurppleItemPresenter mPresenter;

    HighlightImagePagerAdapter mHighlightImagePagerAdapter;

    GuideAdapter mGudieAdapter;
    PromotionAdapter mPromotionAdapter;

    int currentPage = 0;
    Timer timer;
    final long DELAY_MS = 5; //delay in milliseconds before task is to be executed
    final long PERIOD_MS = 3000; // time in milliseconds between successive task executions.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this, this);

        PADCBurppleApp sfcNewsApp = (PADCBurppleApp) getApplicationContext();
        sfcNewsApp.getmAppComponent().inject(this);

//        mPresenter = new BurppleItemPresenter();
        mPresenter.onCreate(this);
        setSupportActionBar(toolbar);

        if (toolbar != null)
        {
            getSupportActionBar().setTitle("");
        }

        // ViewPager Image
        mHighlightImagePagerAdapter = new HighlightImagePagerAdapter(getApplicationContext());
        vpHighlightImages.setAdapter(mHighlightImagePagerAdapter);
        vpHighlightImages.setOffscreenPageLimit(mHighlightImagePagerAdapter.getCount());

        //for pageIndicator
        pageIndicatorView.setCount(mHighlightImagePagerAdapter.getCount()); // specify total count of indicators
        pageIndicatorView.setSelection(0);

        //RecyclerView for Burpple GuideVO
        rvGuide.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.HORIZONTAL, false));

        mGudieAdapter = new GuideAdapter(getApplicationContext(),mPresenter);
        rvGuide.setAdapter(mGudieAdapter);

        //RecyclerView for Promotions
        rvPromotion.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.HORIZONTAL, false));

        mPromotionAdapter = new PromotionAdapter(getApplicationContext(),mPresenter);
        rvPromotion.setAdapter(mPromotionAdapter);

        //set up for image view pager rotate automatically
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                currentPage = vpHighlightImages.getCurrentItem() + 1;
                if (currentPage == mHighlightImagePagerAdapter.getCount()) {
                    currentPage = 0;
                }
                vpHighlightImages.setScrollDurationFactor(5);
                vpHighlightImages.setCurrentItem(currentPage, true);
            }
        };

        timer = new Timer(); // This will create a new Thread
        timer.schedule(new TimerTask() { // task to be scheduled

            @Override
            public void run() {
                handler.post(Update);
            }
        }, DELAY_MS, PERIOD_MS);

        AHBottomNavigationAdapter navigationAdapter = new AHBottomNavigationAdapter(this, R.menu.bottom_navigation_menu);
        navigationAdapter.setupWithBottomNavigation(bottomNavigation);

        // Set background color
        bottomNavigation.setDefaultBackgroundColor(getResources().getColor(R.color.pure_white));

//        bottomNavigation.setBehaviorTranslationEnabled(false);

        bottomNavigation.setAccentColor(getResources().getColor(R.color.primary));
        bottomNavigation.setInactiveColor(getResources().getColor(R.color.divider));

        bottomNavigation.setForceTint(true);
        bottomNavigation.setTranslucentNavigationEnabled(true);

        bottomNavigation.setBehaviorTranslationEnabled(true);

        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
        bottomNavigation.setCurrentItem(0);

        bottomNavigation.setNotificationBackgroundColor(Color.parseColor("#F63D2B"));
        bottomNavigation.setNotification("1", 3);

        /**Promotion and shop form database*/
        getSupportLoaderManager().initLoader(PROMOTION_LIST_LOADER_ID,null,this);

        /**Guide Term form database*/
        getSupportLoaderManager().initLoader(GUIDE_LIST_LOADER_ID,null,this);

        getSupportLoaderManager().initLoader(FEATURE_LIST_LOADER_ID,null,this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.onStart();

    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.onPause();
    }


    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.onStop();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        switch (id)
        {
            case PROMOTION_LIST_LOADER_ID:
                return new CursorLoader(this,
                        BurppleDBContract.PromotionEntry.CONTENT_URI,
                        null,
                        null,
                        null,
                        null);
            case GUIDE_LIST_LOADER_ID:
                return new CursorLoader(this,
                        BurppleDBContract.GuideEntry.CONTENT_URI,
                        null,
                        null,
                        null,
                        null);
            case FEATURE_LIST_LOADER_ID:
                return new CursorLoader(this,
                        BurppleDBContract.FeaturedEntry.CONTENT_URI,
                        null,
                        null,
                        null,
                        null);
            default:
                return null;

        }

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        switch (loader.getId())
        {
            case PROMOTION_LIST_LOADER_ID:
                mPresenter.onPromotionDataLoaded(data,getApplicationContext());
                break;
            case GUIDE_LIST_LOADER_ID:
                mPresenter.onGuideDataLoaded(data,getApplicationContext());
                break;
            case FEATURE_LIST_LOADER_ID:
                mPresenter.onFeatureDataLoaded(data,getApplicationContext());
                break;

        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    public void displayPromotionList(List<PromotionVO> promotionVOList) {

        mPromotionAdapter.setNewData(promotionVOList);
//        swipeRefreshLayout.setRefreshing(false);

    }

    @Override
    public void displayGuideList(List<GuideVO> guideVOList) {
        mGudieAdapter.setNewData(guideVOList);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void navigateToPromotionDetails(PromotionVO promotionVO) {

        showPromotionDialog(promotionVO);

    }

    private void showPromotionDialog(PromotionVO promotionVO) {
        LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
        View view = inflater.inflate(R.layout.alert_box_for_promotion_detail, null);
        ImageView ivPromotion = view.findViewById(R.id.iv_promotion);
        TextView tvPromotionTitle = view.findViewById(R.id.tv_promotion_title);
        TextView tvPromotionUntile = view.findViewById(R.id.tv_promotion_untile);
        TextView tvPromotionShopName = view.findViewById(R.id.tv_promotion_shop_name);
        TextView tvPromotionArea = view.findViewById(R.id.tv_promotion_area);
        TextView tvPromotionTerms = view.findViewById(R.id.tv_promotion_terms);

        Glide.with(getApplicationContext())
                .load(promotionVO.getmBurpplePromotionImage())
                .placeholder(R.drawable.place_holder_promotion)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(ivPromotion);

        tvPromotionTitle.setText(promotionVO.getmBurpplePromotionTitle());
        tvPromotionUntile.setText(promotionVO.getmBurpplePromotionUntil());
        tvPromotionShopName.setText(promotionVO.getmBurpplePromotionShop().getmBurppleShopName());
        tvPromotionArea.setText(promotionVO.getmBurpplePromotionShop().getmBurppleShopArea());

        String strPromotionTerms = "";
        for (int i = 0 ; i < promotionVO.getmBurpplePromotionTerms().size() ; i++)
        {
            strPromotionTerms += (i+1)+". "+promotionVO.getmBurpplePromotionTerms().get(i) + " \n";
        }

        tvPromotionTerms.setText(strPromotionTerms);


        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setView(view);
        final AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public void navigateToGuideDetails(GuideVO guideVO) {
        Intent intent = GuideDetailActivity.newIntent(getApplicationContext(),guideVO.getmBurppleGuideId());
        startActivity(intent);

    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }

    @Override
    public void displayFeatureList(List<FeaturedVO> featuredVOList) {
        mHighlightImagePagerAdapter.setImages(featuredVOList);
    }
}
