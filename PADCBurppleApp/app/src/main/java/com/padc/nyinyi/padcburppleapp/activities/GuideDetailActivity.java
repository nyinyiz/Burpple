package com.padc.nyinyi.padcburppleapp.activities;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.TextView;

import com.padc.nyinyi.padcburppleapp.Persistence.BurppleDBContract;
import com.padc.nyinyi.padcburppleapp.R;
import com.padc.nyinyi.padcburppleapp.data.vos.GuideVO;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GuideDetailActivity extends BaseActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    public static final String GUIDE_ID = "GUIDE_ID";
    public static final int GUIDE_DETAIL_LOADER_ID = 100;

    @BindView(R.id.tv_burpple_guide_title)
    TextView tvBurppleGuideTitle;
    @BindView(R.id.tv_burpple_guide_detail)
    TextView tvBurppleGuideDetail;

    private String mGuideId;


    public static Intent newIntent(Context context, String guideId)
    {
        Intent intent = new Intent(context,GuideDetailActivity.class);
        intent.putExtra(GUIDE_ID,guideId);
        return intent;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_detail);
        ButterKnife.bind(this,this);

        mGuideId = getIntent().getStringExtra(GUIDE_ID);


        if (TextUtils.isEmpty(mGuideId))
        {
            throw new UnsupportedOperationException("newsId required for NewsDetailsActivity");
        }else {
            getSupportLoaderManager().initLoader(GUIDE_DETAIL_LOADER_ID,null,this);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this,
                BurppleDBContract.GuideEntry.CONTENT_URI,
                null,
                BurppleDBContract.GuideEntry.COLUMN_BURPPLE_GUIDE_ID + " = ? ",
                new String []{mGuideId},
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (data != null && data.moveToFirst())
        {
            GuideVO guideVO = GuideVO.parseFromCursor(getApplicationContext(),data);
            bindData(guideVO);

        }
    }

    private void bindData(GuideVO guideVO) {

        tvBurppleGuideTitle.setText(guideVO.getmBurppleGuideTitle());
        tvBurppleGuideDetail.setText(guideVO.getmBurppleGuideDesc());

        if (getSupportActionBar() != null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(guideVO.getmBurppleGuideTitle());
        }

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
