package com.sx.trans.supervision.activity.company;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.sx.trans.supervision.Presenter.SearchCompanyListPresenter;
import com.sx.trans.R;

import com.sx.trans.supervision.activity.company.CompanyDetailActivity;
import com.sx.trans.supervision.adapter.CompanySearchAdapter;
import com.sx.trans.supervision.app.BaseActivity;
import com.sx.trans.supervision.app.IConstants;
import com.sx.trans.supervision.constants.CompanyInfo;
import com.sx.trans.supervision.constants.Result;
import com.sx.trans.supervision.constants.VehicleInfo;
import com.sx.trans.supervision.views.PublicView;
import com.google.gson.reflect.TypeToken;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 搜索公司界面
 */

public class SearchCompanyActivity extends BaseActivity implements PublicView {

   private ImageView btSearchback;
   private RelativeLayout rl_searchback;
   private EditText etSearchCar;
   private ImageView btDeleted;
   private RelativeLayout rldeleted;
   private RelativeLayout rlSearch;
   private RelativeLayout rl_searchlist;
   private ListView lvSearch;
   private LinearLayout ll_backgroud;
   private LinearLayout no_date;
    private void initControls(){
        btSearchback = findViewById(R.id.bt_searchback);
        rl_searchback = findViewById(R.id.rl_searchback);
        etSearchCar = findViewById(R.id.et_searchCar);
        btDeleted = findViewById(R.id.bt_deleted);
        rldeleted = findViewById(R.id.rl_deleted);
        rlSearch = findViewById(R.id.rl_search);
        rl_searchlist = findViewById(R.id.rl_searchlist);
        lvSearch = findViewById(R.id.lv_search);
        ll_backgroud = findViewById(R.id.ll_backgroud);
        no_date = findViewById(R.id.no_date);



    }
    private ArrayList<CompanyInfo> historyCompanyList;//本地历史业户记录
    private ArrayList<CompanyInfo> searchCompanyList;//网络业户列表
    private String search = "";//搜索结果
    private Result result;

    private SearchCompanyListPresenter companyListPresenter;
    int tid;//行业id

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publicsearch_company);
        initControls();

        if (0 < getIntent().getIntExtra("id", -1)) {
            tid = getIntent().getIntExtra("id", -1);
        } else {
            tid = 0;
        }
        init();
    }

    //初始化
    private void init() {

        etSearchCar.addTextChangedListener(watcher);
        timeHandler.postDelayed(runnable, 300);// 打开定时器，延迟弹出键盘

        lvSearch.setEmptyView(no_date);
        historyCompanyList = new ArrayList<CompanyInfo>();
        searchCompanyList = new ArrayList<CompanyInfo>();

        //搜索列表iteam监听
        lvSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                IntentCompany(position);
            }
        });

        //查询本地搜索记录
        try {
            if (DataSupport.findAll(VehicleInfo.class) == null || DataSupport.findAll(VehicleInfo.class).size() == 0)
                return;
        } catch (Exception e) {
            return;
        }
        historyCompanyList.addAll(DataSupport.findAll(CompanyInfo.class));
        if (historyCompanyList != null && historyCompanyList.size() > 0) {
            Collections.reverse(historyCompanyList);
            setSeachAdpter(historyCompanyList);
        }
    }

    //跳转
    private void IntentCompany(int position) {
        Intent intent = new Intent(mContext, CompanyDetailActivity.class);
        Bundle bundle = new Bundle();
        if (searchCompanyList.size() == 0) {
            bundle.putSerializable(IConstants.mSpre_Constants.COMPANY_INFO, historyCompanyList.get(position));
        } else {
            bundle.putSerializable(IConstants.mSpre_Constants.COMPANY_INFO, searchCompanyList.get(position));
            searchCompanyList.get(position).setUid(searchCompanyList.get(position).getId());
            SaveSearchCompany(searchCompanyList.get(position));
        }
        intent.putExtras(bundle);
        mContext.startActivity(intent);
    }

    //存储本地历史搜索记录
    private void SaveSearchCompany(CompanyInfo companyInfo) {
        companyInfo.setSearch(0);
        boolean hasVehicle = false;//判断是否存在
        int id=0;
        if (historyCompanyList.size() > 0) {
            for (CompanyInfo companyInfo1 : historyCompanyList) {
                if (companyInfo1.getName().equals(companyInfo.getName())) {
                    hasVehicle = true;
                    id=companyInfo1.getId();
                }
            }
        }
        if (hasVehicle) {
            DataSupport.delete(CompanyInfo.class, id);
        }else{
            if (historyCompanyList.size() > 0 && historyCompanyList.size() >= 20) {
                DataSupport.delete(CompanyInfo.class,20);
            }
        }
        companyInfo.save();
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
            searchCompanyList = result.getData(new TypeToken<ArrayList<CompanyInfo>>() {
            });
            setSeachAdpter(searchCompanyList);
        } else {
            Toast.makeText(mContext, result.getMsg(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showError(String error) {
        Toast.makeText(mContext, error, Toast.LENGTH_SHORT).show();
    }

    @OnClick({R.id.bt_searchback, R.id.rl_searchback, R.id.bt_deleted, R.id.rl_deleted, R.id.rl_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_searchback://返回
                finish();
//                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                break;
            case R.id.rl_searchback:
                finish();
//                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                break;
            case R.id.bt_deleted://删除
                etSearchCar.setText("");
                break;
            case R.id.rl_deleted:
                etSearchCar.setText("");
                break;
            case R.id.rl_search://搜索
                search = etSearchCar.getText().toString();
                if (search != null && search.length() > 0) {
                    companyListPresenter = new SearchCompanyListPresenter(this, mContext);
                    companyListPresenter.getCompanyList(search);
                }else{
                    Toast.makeText(mContext, "请输入业户名称", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    //设置listview列表
    private void setSeachAdpter(ArrayList<CompanyInfo> searchCompanyList) {
        if (searchCompanyList == null) {
            no_date.setVisibility(View.VISIBLE);
            return;
        }
        lvSearch.setAdapter(new CompanySearchAdapter(mContext, searchCompanyList, search));
        lvSearch.setVisibility(View.VISIBLE);
        rl_searchlist.setVisibility(View.VISIBLE);
    }

    //搜索Editext监听
    private TextWatcher watcher = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // TODO Auto-generated method stub
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
            // TODO Auto-generated method stub
        }

        @Override
        public void afterTextChanged(Editable s) {
            search = etSearchCar.getText().toString();
            if (s.toString() == null || s.length() == 0) {
                btDeleted.setVisibility(View.GONE);
                searchCompanyList.clear();
                if (historyCompanyList != null && historyCompanyList.size() > 0)
                    setSeachAdpter(historyCompanyList);
            } else {
                btDeleted.setVisibility(View.VISIBLE);
            }
        }
    };

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            InputMethodManager inputManager = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.showSoftInput(etSearchCar, 0);
        }
    };

    Handler timeHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    break;
            }
        }
    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timeHandler.removeCallbacks(runnable);
        timeHandler = null;
    }
}
