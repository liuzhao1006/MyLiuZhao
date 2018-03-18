package com.sx.trans.supervision.activity.PostRecord;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.sx.trans.supervision.Presenter.PostSearchPresenter;
import com.sx.trans.R;

import com.sx.trans.supervision.adapter.SearchRecordAdapter;
import com.sx.trans.supervision.app.BaseActivity;
import com.sx.trans.supervision.constants.PostCheckInfo;
import com.sx.trans.supervision.constants.Result;
import com.sx.trans.supervision.views.PublicView;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by mr_wang on 2017/9/4.
 * 查岗记录
 */

public class PostRecordHistoryActivity extends BaseActivity implements PublicView{

   private ListView lvRecordlist;
   private LinearLayout ivNodate;

    private void initControls(){
        lvRecordlist = findViewById(R.id.lv_recordlist);
        ivNodate = findViewById(R.id.iv_nodate);

    }

//    @InjectView(R.id.pull_refresh_view)
//    PullToRefreshView pullRefreshView;
    private ArrayList<PostCheckInfo> postCheckInfos;
    private PostSearchPresenter searchPostPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_searchrecord);
        initControls();
        init();
        searchPostPresenter.checkStatistic();
    }

    private void init(){
        setTitle(true, getString(R.string.record_post), false, null);
        postCheckInfos=new ArrayList<PostCheckInfo>();
        lvRecordlist.setEmptyView(ivNodate);
        searchPostPresenter = new PostSearchPresenter(this, mContext);
//        pullRefreshView.setOnHeaderRefreshListener(this);
    }

//    @Override
//    public void onHeaderRefresh(PullToRefreshView view) {
//        searchPostPresenter.checkStatistic();
//    }

    @Override
    public void showLoading() {
        showDiaLogLoading(true);
    }

    @Override
    public void hideLoading() {
        HideDiaLogLoading();
    }

    @Override
    public void Success(Result result, int code) {
        if (code==0) {
            postCheckInfos= result.getData(new TypeToken<ArrayList<PostCheckInfo>>() {
            });
            if (postCheckInfos == null || postCheckInfos.size() == 0) {
                ivNodate.setVisibility(View.VISIBLE);
//                pullRefreshView.setVisibility(View.GONE);
                return;
            }
            SearchRecordAdapter searchRecordAdapter=new SearchRecordAdapter(mContext,postCheckInfos);
            lvRecordlist.setAdapter(searchRecordAdapter);
        }
    }

    @Override
    public void showError(String error) {
        hideLoading();
        Toast.makeText(mContext,error,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.reset(this);

    }

}
