package com.sx.trans.supervision.activity.company;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.sx.trans.supervision.Presenter.CompanyListPresenter;
import com.sx.trans.R;

import com.sx.trans.supervision.adapter.CompanyListAdapter;
import com.sx.trans.supervision.app.BaseActivity;
import com.sx.trans.supervision.constants.CompanyInfo;
import com.sx.trans.supervision.constants.Result;
import com.sx.trans.supervision.views.PublicView;
import com.sx.trans.supervision.widget.PullToRefreshView;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 业户公司列表
 */

public class CompanyListActivity extends BaseActivity implements PublicView, PullToRefreshView.OnHeaderRefreshListener, PullToRefreshView.OnFooterRefreshListener {


   private ListView lvCompany;
   private PullToRefreshView pullRefreshView;
   private LinearLayout ivNoData;
    private Context mContext;

    private void initControls(){
        lvCompany = findViewById(R.id.lv_company);
        pullRefreshView = findViewById(R.id.pull_refresh_view);
        ivNoData = findViewById(R.id.iv_no_data);
    }

    private CompanyListPresenter companyListPresenter;
    private ArrayList<CompanyInfo> companyInfoList = new ArrayList<CompanyInfo>();
    private CompanyListAdapter companyListAdapter;
    int tid;//行业id
    int areaCode;//区域code

    private int page = 1;
    private int pageSize = 10;
    private Boolean isLoadMore = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_list);
        initControls();
        mContext = this;
        setTitle(true, mContext.getString(R.string.owner_title), true, mContext.getResources().getDrawable(R.drawable.search));
        pullRefreshView.setOnHeaderRefreshListener(this);
        pullRefreshView.setOnFooterRefreshListener(this);

        tid = getIntent().getIntExtra("id", -1);

        companyListPresenter = new CompanyListPresenter(this, mContext);
        companyListPresenter.getCompanyList(tid, "", pageSize, 1);

        companyListAdapter = new CompanyListAdapter(mContext, companyInfoList);
        lvCompany.setAdapter(companyListAdapter);
    }

    @Override
    public void showLoading() {
        showDiaLogLoading(false);
    }

    @Override
    public void hideLoading() {
        HideDiaLogLoading();
    }

    @Override
    public void Success(Result result, int code) {
        if (code == 0) {
            ArrayList<CompanyInfo> subList = result.getData(new TypeToken<ArrayList<CompanyInfo>>() {
            });
            if (subList.size() < 1) {
                if (isLoadMore) {
                    Toast.makeText(mContext, R.string.no_more_maintenance, Toast.LENGTH_LONG).show();
                    pullRefreshView.setEnablePullLoadMoreDataStatus(false);
                } else {
                    pullRefreshView.setVisibility(View.GONE);
                    ivNoData.setVisibility(View.VISIBLE);
                }
            } else {
                pullRefreshView.setVisibility(View.VISIBLE);
                ivNoData.setVisibility(View.GONE);
                if (!isLoadMore) {
                    if (companyInfoList != null)
                        companyInfoList.clear();
                }
                companyInfoList.addAll(subList);
                companyListAdapter.notifyDataSetChanged();
            }
        } else {
            Toast.makeText(mContext, result.getMsg(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void showError(String error) {
        Toast.makeText(mContext, error, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onHeaderRefresh(PullToRefreshView view) {
        pullRefreshView.postDelayed(new Runnable() {
            @Override
            public void run() {
                isLoadMore = false;
                getCompanyList();
                pullRefreshView.onHeaderRefreshComplete();
                pullRefreshView.setEnablePullLoadMoreDataStatus(true);
            }
        }, 1000);
    }

    @Override
    public void onFooterRefresh(PullToRefreshView view) {
        pullRefreshView.postDelayed(new Runnable() {
            @Override
            public void run() {
                isLoadMore = true;
                getCompanyList();
                pullRefreshView.onFooterRefreshComplete();
            }
        }, 1000);
    }

    private void getCompanyList() {
        if (!isLoadMore) {
            page = 1;
            companyListPresenter.getCompanyList(tid, "", page * pageSize, page);
        } else {
            page = ++page;
            companyListPresenter.getCompanyList(tid, "", pageSize, page);
        }
    }

    @OnClick(R.id.bt_menu)
    void searchCompany() {
        Intent intent = new Intent(mContext, SearchCompanyActivity.class);
        intent.putExtra("id", tid);
        mContext.startActivity(intent);
    }
}
