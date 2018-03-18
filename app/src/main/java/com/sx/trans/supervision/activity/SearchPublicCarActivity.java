package com.sx.trans.supervision.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.sx.trans.R;

import com.sx.trans.supervision.adapter.VehicleSearchAdapter;
import com.sx.trans.supervision.app.BaseActivity;
import com.sx.trans.app.LzApp;
import com.sx.trans.supervision.app.IConstants;
import com.sx.trans.supervision.app.UrlConstants;
import com.sx.trans.supervision.constants.Result;
import com.sx.trans.supervision.constants.VehicleInfo;
import com.sx.trans.supervision.utils.JSONUtils;
import com.google.gson.reflect.TypeToken;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Request;

/**
 * Created by mr_wang on 2017/8/22.
 * 顶部搜索车辆公共界面,
 */

public class SearchPublicCarActivity extends BaseActivity implements View.OnClickListener {

    private ImageView btSearchback;
    private RelativeLayout rl_searchback;
    private EditText etSearchCar;
    private ImageView btDeleted;
    private RelativeLayout rldeleted;
//    private RelativeLayout rlSearch;
    private TextView rlSearch;
    private RelativeLayout rl_searchlist;
    private ListView lvSearch;
    private LinearLayout ll_backgroud;
    private LinearLayout no_date;
    private ImageView bt_searchback;
    private RelativeLayout rl_searchback1;
    private ImageView bt_deleted;
    private RelativeLayout rl_deleted;
    private RelativeLayout rl_search;

    private void initControls() {
//        btSearchback = findViewById(R.id.bt_searchback);
//        rl_searchback = findViewById(R.id.rl_searchback);
        etSearchCar = findViewById(R.id.et_searchCar);
        btDeleted = findViewById(R.id.bt_deleted);
        rldeleted = findViewById(R.id.rl_deleted);
        rlSearch = findViewById(R.id.rl_search);
        rl_searchlist = findViewById(R.id.rl_searchlist);
        lvSearch = findViewById(R.id.lv_search);
        ll_backgroud = findViewById(R.id.ll_backgroud);
        no_date = findViewById(R.id.no_date);
//        btSearchback.setOnClickListener(this);
//        rl_searchback.setOnClickListener(this);
        btDeleted.setOnClickListener(this);
        rldeleted.setOnClickListener(this);
        rlSearch.setOnClickListener(this);

    }

    private ArrayList<VehicleInfo> historyVehiclelist;//本地历史车辆记录
    private ArrayList<VehicleInfo> VehicleList;//车辆记录
    private ArrayList<VehicleInfo> SearchVehiclelist;//网络车辆列表
    private String search = "";//搜索结果
    private String color = "";//搜索结果
    private Result result;
    private int IntetnType = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publicsearch_car);
        initControls();
        IntetnType = getIntent().getIntExtra("IntenType", -1);
        if(IntetnType==1){
            setTitle(true, getString(R.string.my_service_4), false, null);
        }else{
            setTitle(true, getString(R.string.my_service_2), false, null);
        }
        init();
    }

    //初始化
    private void init() {
//        ll_backgroud.getBackground().setAlpha(50);

        lvSearch.setEmptyView(no_date);
        etSearchCar.addTextChangedListener(watcher);
        timeHandler.postDelayed(runnable, 300);// 打开定时器，延迟弹出键盘

        historyVehiclelist = new ArrayList<VehicleInfo>();
        SearchVehiclelist = new ArrayList<VehicleInfo>();
        VehicleList = new ArrayList<VehicleInfo>();

        //搜索列表iteam监听
        lvSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                IntentVehicle(position);
            }
        });

        //查询本地搜索记录
        try {
            if (DataSupport.findAll(VehicleInfo.class) == null || DataSupport.findAll(VehicleInfo.class).size() == 0)
                return;
        } catch (Exception e) {
            return;
        }

        historyVehiclelist.addAll(DataSupport.findAll(VehicleInfo.class));
        Collections.reverse(historyVehiclelist);
        if (IntetnType == 1) {//如果为实时视频,做一次过滤
            for (VehicleInfo vehicleInfo : historyVehiclelist) {
                if (vehicleInfo.getParamValue() != null && vehicleInfo.getParamValue().length() > 0) {
                    VehicleList.add(vehicleInfo);
                }
            }
        } else {
            VehicleList.addAll(historyVehiclelist);
        }
        if (VehicleList.size() > 0) {
            setSeachAdpter(VehicleList);
        }
    }

    //跳转车辆定位
    private void IntentVehicle(int position) {
        Intent intent = null;
        switch (IntetnType) {
            case 0://车辆定位
                break;
            case 1://实时视频
                int VideoType = -1;
                if (SearchVehiclelist.size() == 0) {
                    VideoType = VehicleList.get(position).getVideoType();
                } else {
                    VideoType = SearchVehiclelist.get(position).getVideoType();
                }
                if (VideoType == 0 || VideoType == 1 || VideoType == 2) {//顺达
                    intent = new Intent(mContext, VideoSDListActivity.class);
                } else {
                    intent = new Intent(mContext, VideoActivity.class);
                }
                break;
            case 2://轨迹回放
                intent = new Intent(mContext, TrackActivity.class);
                break;

        }
        Bundle bundle = new Bundle();
        if (SearchVehiclelist.size() == 0) {
            bundle.putSerializable(IConstants.mSpre_Constants.SEARCHINFO, VehicleList.get(position));
        } else {
            bundle.putSerializable(IConstants.mSpre_Constants.SEARCHINFO, SearchVehiclelist.get(position));
            SaveSearchVehicle(position);
        }
        intent.putExtras(bundle);
        startActivity(intent);
//        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    //存储本地历史搜索记录
    private void SaveSearchVehicle(int position) {
        VehicleInfo vehicleInfo = SearchVehiclelist.get(position);
        vehicleInfo.setSearch(0);
        boolean hasVehicle = false;//判断是否存在
        int id = 0;
        if (historyVehiclelist.size() > 0) {
            for (VehicleInfo vehicleInfo1 : historyVehiclelist) {
                if (vehicleInfo1.getRegistrationNo().equals(vehicleInfo.getRegistrationNo())) {
                    hasVehicle = true;
                    id = vehicleInfo1.getId();
                }
            }
        }
        if (hasVehicle) {
            DataSupport.delete(VehicleInfo.class, id);
        } else {
            if (historyVehiclelist.size() > 0 && historyVehiclelist.size() >= 20) {
                DataSupport.delete(VehicleInfo.class, 20);
            }
        }
        vehicleInfo.save();
    }

    //获得网络车辆列表
    private void getSeachVehicle() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("regisNo", search);
        params.put("color", color);
        params.put("areaCode", LzApp.mSpfProxy.getString(IConstants.mSpre_Constants.USER_AREACODE, ""));
       LogUtils.i(UrlConstants.SEARCHVEHICLE + LzApp.mSpfProxy.getInt(IConstants.mSpre_Constants.UID, -1) + "")//
       ;
        OkHttpUtils
                .get()//
                .params(params)
                .addHeader("auth", LzApp.mSpfProxy.getString(IConstants.mSpre_Constants.USER_AUTH, ""))
                .url(UrlConstants.SEARCHVEHICLE + LzApp.mSpfProxy.getInt(IConstants.mSpre_Constants.UID, -1) + "")//
                .build()//
                .execute(new MyStringCallback());
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
//            case R.id.bt_searchback://返回
//                finish();
////                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
//                break;
//            case R.id.rl_searchback:
//                finish();
////                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
//                break;
            case R.id.bt_deleted://删除
                etSearchCar.setText("");
                break;
            case R.id.rl_deleted:
                etSearchCar.setText("");
                break;
            case R.id.rl_search://搜索
                search = etSearchCar.getText().toString();
                if (search != null && search.length() > 0) {
                    getSeachVehicle();
                }
                break;
        }
    }

    public class MyStringCallback extends StringCallback {
        @Override
        public void onBefore(Request request, int id) {
            Log.e(TAG, "loading");
            showDiaLogLoading(false);
        }

        @Override
        public void onAfter(int id) {
            Log.e(TAG, "Sample-okHttp");
            HideDiaLogLoading();
        }

        @Override
        public void onError(Call call, Exception e, int id) {
            e.printStackTrace();
            HideDiaLogLoading();
            if (e.toString().contains("SocketTimeout")) {//连接超时提示
                Toast.makeText(mContext, getString(R.string.socket_time_out), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(mContext, getString(R.string.no_network), Toast.LENGTH_SHORT).show();

            }
        }

        @Override
        public void onResponse(String response, int id) {
            try {
                result = JSONUtils.fromJson(response, Result.class);
                if (result.getCode() == 0) {
                    SearchVehiclelist = result.getData(new TypeToken<ArrayList<VehicleInfo>>() {
                    });
                    setSeachAdpter(SearchVehiclelist);
                } else {

                }
            } catch (Exception e) {
                Log.d(TAG, "异常信息:" + e.getMessage());
            }
            HideDiaLogLoading();
        }

        @Override
        public void inProgress(float progress, long total, int id) {
            Log.e(TAG, "inProgress:" + progress);
        }
    }

    //设置listview列表
    private void setSeachAdpter(ArrayList<VehicleInfo> vehicleInfoArrayList) {
        if (vehicleInfoArrayList == null) {
            no_date.setVisibility(View.VISIBLE);
            return;
        }
        lvSearch.setAdapter(new VehicleSearchAdapter(mContext, vehicleInfoArrayList, search));
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
                SearchVehiclelist.clear();
                if (historyVehiclelist != null && historyVehiclelist.size() > 0)
                    setSeachAdpter(historyVehiclelist);
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
