package com.sx.trans.supervision.activity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.PolylineOptions;
import com.amap.api.maps.utils.SpatialRelationUtil;
import com.amap.api.maps.utils.overlay.SmoothMoveMarker;
import com.sx.trans.supervision.Presenter.TrackVideoPresenter;
import com.sx.trans.R;

import com.sx.trans.supervision.adapter.DateAdapter;
import com.sx.trans.supervision.app.BaseActivity;
import com.sx.trans.supervision.app.IConstants;
import com.sx.trans.supervision.constants.Result;
import com.sx.trans.supervision.constants.TrackInfo;
import com.sx.trans.supervision.constants.TrackVideo;
import com.sx.trans.supervision.constants.VehicleInfo;
import com.sx.trans.supervision.utils.AMapUtil;
import com.sx.trans.supervision.utils.CommonUtils;
import com.sx.trans.supervision.utils.DateUtils;
import com.sx.trans.supervision.views.PublicView;
import com.sx.trans.supervision.widget.DateHorizontalListView;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by mr_wang on 2017/8/23.
 * 轨迹回放
 */

public class TrackActivity extends BaseActivity implements PublicView ,View.OnClickListener{
  private  RelativeLayout btDateLeft;
  private  DateHorizontalListView horizontallist;
  private  RelativeLayout btDateRight;
  private  TextView tvFirstTime;
  private  TextView tvLastStopTime;
  private  RelativeLayout rlTrackBlack;
  private  MapView MapView;
  private  TextView tvGpsspeed;
  private  TextView tvGpstime;
  private  Button btPlay;
  private  Button btStop;
  private  Button btPause;
  private  LineChart mlineChart1;


    private void initControls(){
        btDateLeft = findViewById(R.id.bt_date_left);
        horizontallist = findViewById(R.id.horizontallist);
        btDateRight = findViewById(R.id.bt_date_right);
        tvFirstTime = findViewById(R.id.tv_first_time);
        tvLastStopTime = findViewById(R.id.tv_last_stop_time);
        rlTrackBlack = findViewById(R.id.rl_Track_Black);
        MapView = findViewById(R.id.MapView);
        tvGpsspeed = findViewById(R.id.tv_gpsspeed);
        tvGpstime = findViewById(R.id.tv_gpstime);
        btPlay = findViewById(R.id.bt_play);
        btStop = findViewById(R.id.bt_stop);
        btPause = findViewById(R.id.bt_pause);
        mlineChart1 = findViewById(R.id.mlineChart1);

    }

    private int seletedpostion = 0;
    private String selectedTime;//所选时间
    private DateAdapter dateAdapter;
    private String day = DateUtils.getTimeChange(new Date(), CommonUtils.DATE_FORMAT_DATE);
    private ArrayList<Date> datelist = DateUtils.getLastDays(day);
    private AMap aMap;
    private UiSettings uiSettings;
    private ArrayList<LatLng> points= new ArrayList<LatLng>();
    private SmoothMoveMarker smoothMarker;
    private ArrayList<TrackVideo> trackList;
    private int position = 0;
    private float Speed=0;//速度
    private boolean BeginPlay=false;//初始化
    private boolean bl_stop=false;
    private double alltime=0;//所有总时间
    private List<Double> timelist=new ArrayList<Double>();//分段时间集合
    private DecimalFormat fnum  =   new DecimalFormat("##0.00");
    private boolean FirstMove=true;
    private TrackVideoPresenter trackVideoPresenter;
    private int vehicleColor=0;
    private String registrationNo="1";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track);
        initControls();
        init(savedInstanceState);
    }

    private void init(Bundle savedInstanceState){
        rlTrackBlack.getBackground().setAlpha(150);
        s();
        setDate();
        setMapView(savedInstanceState);
        setLineChart();
        setLineChartDate();
        trackVideoPresenter=new TrackVideoPresenter(this,mContext);
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = this.getIntent().getExtras();
            if (bundle != null) {
                VehicleInfo vehicleInfo = (VehicleInfo) bundle.getSerializable(IConstants.mSpre_Constants.SEARCHINFO);
                if (vehicleInfo != null) {
                    vehicleColor = vehicleInfo.getColor();
                    registrationNo = vehicleInfo.getRegistrationNo();
                    setTitle(true,registrationNo,false,null);
                    trackVideoPresenter.getDayTrack(vehicleColor,registrationNo, returnTime(selectedTime, 0), returnTime(selectedTime, 1));
                }
            }
        }

    }

    //设置日期控件
    private void setDate() {
        selectedTime = DateUtils.format_3(datelist.get(datelist.size() - 1));
        dateAdapter = new DateAdapter(this, datelist);
        horizontallist.setAdapter(dateAdapter);
        seletedpostion = DateUtils.getLastDays(day).size() - 1;

        seletedpostion = seletedpostion - 3;
        horizontallist.setSelection(seletedpostion);

        horizontallist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (!selectedTime.equals(DateUtils.format_3(datelist.get(position)))) {
                    dateAdapter.mPosition = position;
                    dateAdapter.notifyDataSetChanged();
                    selectedTime = DateUtils.format_3(datelist.get(position));
                    trackVideoPresenter.getDayTrack(vehicleColor,registrationNo, returnTime(selectedTime, 0), returnTime(selectedTime, 1));
                }
            }
        });
    }

    //地图初始化
    private void setMapView(Bundle savedInstanceState) {
        MapView.onCreate(savedInstanceState);// 此方法必须重写
        if (aMap == null) {
            aMap = MapView.getMap();
            uiSettings = aMap.getUiSettings();
            uiSettings.setTiltGesturesEnabled(false);// 禁用倾斜手势。
            uiSettings.setRotateGesturesEnabled(false);// 禁用旋转手势。
            uiSettings.setZoomControlsEnabled(false);//去除默认放大缩小按钮
            uiSettings.setLogoBottomMargin(-50);//隐藏高德地图图标
//            uiSettings.setAllGesturesEnabled(false);//禁止所有手势(其中滑动手势被外面的下拉列表上下滑动覆盖)
        }
    }
private void s(){
    RelativeLayout bt_date_left = findViewById(R.id.bt_date_left);
    RelativeLayout bt_date_right = findViewById(R.id.bt_date_right);
    Button bt_play = findViewById(R.id.bt_play);
    Button bt_stop = findViewById(R.id.bt_stop);
    Button bt_pause = findViewById(R.id.bt_pause);
    bt_date_left.setOnClickListener(this);
    bt_date_right.setOnClickListener(this);
    bt_play.setOnClickListener(this);
    bt_stop.setOnClickListener(this);
    bt_pause.setOnClickListener(this);
}

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_play:
                Log.d("asd","position:"+position+",size:"+points.size());
                if (position==points.size()) {//播放结束
                    timeHandler.removeCallbacks(runnable);
                    BeginPlay = false;
                    setPlayView();
                    playTrackMove();
                }else if (position==0||position==1){//起始播放
                    BeginPlay = true;
                    if (points!=null&&points.size()>0) {
                        playTrackMove();
                    }
                }else if (bl_stop){//开始暂停播放
                    smoothMarker.startSmoothMove();
                    timeHandler.postDelayed(runnable, 0);
                }
                break;
            case R.id.bt_stop:
                bl_stop=true;
                timeHandler.removeCallbacks(runnable);
                smoothMarker.stopMove();
                break;
            case R.id.bt_pause:
                BeginPlay=false;
                smoothMarker.stopMove();
                setPlayView();
                break;
            case R.id.bt_date_left:
                if (seletedpostion != 0) {
                    seletedpostion = seletedpostion - 4;
                    if (seletedpostion < 0) {
                        seletedpostion = 0;
                    }
                    dateAdapter = new DateAdapter(this, datelist);
                    horizontallist.setAdapter(dateAdapter);
                    horizontallist.setSelection(seletedpostion);
                } else {
                    seletedpostion = 0;
                }
                break;
            case R.id.bt_date_right:
                if (seletedpostion != DateUtils.getLastDays(day).size() - 1) {
                    seletedpostion = seletedpostion + 4;
                    if (seletedpostion > DateUtils.getLastDays(day).size() - 1) {
                        seletedpostion = DateUtils.getLastDays(day).size() - 4;
                    }
                    dateAdapter = new DateAdapter(this, datelist);
                    horizontallist.setAdapter(dateAdapter);
                    horizontallist.setSelection(seletedpostion);
                } else {
                    seletedpostion = DateUtils.getLastDays(day).size() - 4;
                }
                break;
        }
    }


    Handler timeHandler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    SetTrackDate();
                    // 更新markers
                    break;
            }
        }
    };

    Runnable runnable=new Runnable() {
        @Override
        public void run() {
            if (position<timelist.size()) {
                timeHandler.postDelayed(runnable, (int) (timelist.get(position) * 1000));
            }
            timeHandler.sendEmptyMessage(0);
        }
    };

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
        if (code==0) {
            TrackInfo trackInfo=result.getData(TrackInfo.class);
            String startTime=trackInfo.getFist();String endTime=trackInfo.getLast();
            if (startTime!=null&&!startTime.equals("-")&&startTime.length()>8) {
                startTime=startTime.substring(startTime.length()-8,startTime.length());
            }else{
                startTime="00:00:00";
            }
            if (endTime!=null&&!endTime.equals("-")&&endTime.length()>8) {
                endTime=endTime.substring(endTime.length()-8,endTime.length());
            }else{
                endTime="00:00:00";
            }
            tvFirstTime.setText(startTime);
            tvLastStopTime.setText(endTime);

            trackList=trackInfo.getLocation();
            if (trackList != null && trackList.size() > 0) {
                setPlayView();
            }else{
                aMap.clear();
            }
            setLineChartDate();
        }else{
            Log.d(TAG,"result.getCode():"+result.getCode()+",result.getMsg():"+result.getMsg());
            Toast.makeText(mContext,getString(R.string.trajectory_nothing),Toast.LENGTH_SHORT).show();
            trackList.clear();
            aMap.clear();
            setLineChartDate();
        }
    }
    @Override
    public void showError(String error) {
        Toast.makeText(mContext,error,Toast.LENGTH_SHORT).show();
    }

    //初始化
    private void setPlayView(){
        aMap.clear();
        position = 0;bl_stop=false;
        timelist.clear();alltime=0;points.clear();
        smoothMarker = new SmoothMoveMarker(aMap);
        // 设置滑动的图标
        timeHandler.removeCallbacks(runnable);
        draw(trackList);

        //设置第一个移动点
        if (BeginPlay==false&&points!=null&&points.size()>0) {
            tvGpsspeed.setText("0.0");
//            String GpsTime = trackList.get(position).getGpsTime();
//            tvGpstime.setText(GpsTime.substring(GpsTime.length() - 8, GpsTime.length()));
            ArrayList<LatLng> playlatlng = new ArrayList<>();
            playlatlng.add(points.get(position));
            playlatlng.add(points.get(position));
            Speed = 1;
            smoothMarker.setTotalDuration(0);
            DrawTrack(playlatlng);
        }
    }

    //开始移动
    private void playTrackMove(){
        //计算总时间和设置分段时间
        for (int i=0;i<points.size();i++){
            if (i+1<points.size()) {
                setSpeedTime(points.get(i),points.get(i + 1),trackList.get(i).getGpsSpeed());
            }
        }
        int intAlltime=(int)alltime;
        smoothMarker.setTotalDuration(intAlltime);
        DrawTrack(points);
        timeHandler.postDelayed(runnable, 0);
    }

    //计算两点所需时间 Speedtime
    private void setSpeedTime(LatLng latLng1,LatLng latLng2,float Speed){
        float distance = AMapUtils.calculateLineDistance(latLng1,latLng2);
        double dbdistance=Double.parseDouble(fnum.format(distance));
//        double spped2=(Speed*1000/3600);
        double time=dbdistance/Speed/10;
        if (dbdistance/Speed>10){
            time=time/10;
        }
        if (Double.isNaN(time) || Double.isInfinite(time)) {
            time=0;
        }
        timelist.add(time);
        alltime=alltime+time;
    }

    //设置移动时的数据
    private void SetTrackDate() {
        if (trackList==null||trackList.size()<=0)
            return;
        if (position<trackList.size()) {
            Speed = trackList.get(position).getGpsSpeed();
            tvGpsspeed.setText(Speed + "");
            String GpsTime = trackList.get(position).getGpsTime();
            tvGpstime.setText(GpsTime.substring(GpsTime.length() - 8, GpsTime.length()));
            position++;
        }else{
            position=0;
        }
    }

    //移动车辆动画
    private void DrawTrack(List<LatLng> points) {
        if (points.size() > 1) {
            LatLng drivePoint = points.get(0);
            Pair<Integer, LatLng> pair = SpatialRelationUtil.calShortestDistancePoint(points, drivePoint);
            points.set(pair.first, drivePoint);
            final List<LatLng> subList = points.subList(pair.first, points.size());

            // 设置滑动的轨迹左边点
            smoothMarker.setDescriptor(BitmapDescriptorFactory.fromResource(R.drawable.track_location));
            smoothMarker.setPoints(subList);
            // 开始滑动
            smoothMarker.startSmoothMove();
        }
    }

    //绘制起始终点
    private void draw(List<TrackVideo> tracks) {
        aMap.clear();
        if(tracks==null)
            return;
        for (int i = 0; i < tracks.size(); i++) {
                LatLng latLng = AMapUtil.getGpsToLatlng(new LatLng(tracks.get(i).getLatitude(), tracks.get(i).getLongitude()));
                if (tracks.get(i).getLatitude()>1&&tracks.get(i).getLatitude()>1) {
                    points.add(latLng);
                }
                if (i == 0) {
                    MarkerOptions startOption = new MarkerOptions();
                    startOption.position(latLng);
                    startOption.anchor(0.5f, 0.5f);
                    startOption.title("起点");
                    startOption.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                            .decodeResource(getResources(), R.drawable.track_start)));
                    aMap.addMarker(startOption);
                }
                if (i == tracks.size() - 1) {
                    MarkerOptions endOption = new MarkerOptions();
                    endOption.position(latLng);
                    endOption.anchor(0.5f, 0.9f);
                    endOption.title("终点");
                    endOption.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                            .decodeResource(getResources(), R.drawable.track_end)));
                    aMap.addMarker(endOption);
                }

        }
        Log.d("asd", "points.size():" + points.size());
        drawLineMap();
    }

    /**
     * 在地图上画线
     */
    private void drawLineMap() {
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        if (points.size() > 1) {
            PolylineOptions polt = new PolylineOptions();
            for (int i = 0; i < points.size(); i++) {
                polt.add(points.get(i));
                builder.include(points.get(i));
            }
            //纹理
            polt.setCustomTexture(BitmapDescriptorFactory.fromResource(R.drawable.track_path));
            polt.width(15).geodesic(true);
            aMap.addPolyline(polt);

            //所有点在视野范围内
            if (FirstMove) {
                aMap.moveCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 18));
                aMap.moveCamera(CameraUpdateFactory.zoomOut());
                FirstMove=false;
            }else {
                aMap.animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 10));
            }
        }
    }

    //初始化折线图
    private void setLineChart(){
        mlineChart1.setDrawGridBackground(false);
        mlineChart1.getDescription().setEnabled(false);
        //显示边界
        mlineChart1.setNoDataText("");
        mlineChart1.setTouchEnabled(false); // 设置是否可以触摸
        mlineChart1.setScaleXEnabled(false);//关闭x轴上的缩放
        mlineChart1.setScaleYEnabled(false);//关闭y轴上的缩放
        mlineChart1.setDoubleTapToZoomEnabled(true);

        //折线图例 标签 设置
        Legend legend = mlineChart1.getLegend();
        legend.setForm(Legend.LegendForm.LINE);
        legend.setTextSize(11f);
        //显示位置
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);


        YAxis leftAxis = mlineChart1.getAxisLeft();
//        leftAxis.setAxisLineDashedLine();
        leftAxis.enableGridDashedLine(8f, 8f, 0f); //设置横向表格为虚线
        YAxis rightAxis = mlineChart1.getAxisRight();
        XAxis xAxis = mlineChart1.getXAxis();
        //XY轴的设置
        //X轴设置显示位置在底部
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisLineColor(mContext.getResources().getColor(R.color.gray_e));
        xAxis.setAxisLineWidth(1f);
        xAxis.setAxisMinimum(0f);
        xAxis.setGranularity(1f);
        xAxis.setDrawGridLines(false); //是否显示X坐标轴上的刻度竖线，默认是true
        xAxis.setDrawLabels(false);


        leftAxis.setAxisMaximum(150);
        //保证Y轴从0开始，不然会上移一点
        leftAxis.setAxisMinimum(0f);
        leftAxis.setTextColor(mContext.getResources().getColor(R.color.gray_9));
        leftAxis.setGridColor(mContext.getResources().getColor(R.color.gray_e)); //X轴上的刻度竖线的颜色

        leftAxis.setAxisLineColor(mContext.getResources().getColor(R.color.gray_e));
        leftAxis.setAxisLineWidth(1f);

        //不显示右Y轴
        rightAxis.setEnabled(false);



        //是否显示注释
        Legend l = mlineChart1.getLegend();
        // modify the legend ...
        l.setForm(Legend.LegendForm.LINE);
        l.setEnabled(false);

        mlineChart1.animateXY(2500,2500);
    }

    //设置折线图数据
    private void setLineChartDate(){
        ArrayList<Entry> values = new ArrayList<Entry>();
        float max=0;
        if (trackList==null||trackList.size()==0) {
            for (int i = 0; i < 1; i++) {
                values.add(new Entry(i, 0));
            }
        }else {
            for (int i = 0; i < trackList.size(); i++) {
                if (max<trackList.get(i).getGpsSpeed()){
                    max=trackList.get(i).getGpsSpeed();
                }
                if (trackList.get(i).getGpsSpeed() > 200) {
                    values.add(new Entry(i, 200));
                } else {
                    values.add(new Entry(i, trackList.get(i).getGpsSpeed()));
                }
            }
        }
        if (max<=50) {
            max=50;
        }else if (max<=75){
            max=75;
        } else if (max<=150) {
            max=150;
        } else if (max<=200) {
            max=200;
        }
        mlineChart1.getAxisLeft().setAxisMaximum(max);


        LineDataSet set1;
        if (mlineChart1.getData() != null &&
                mlineChart1.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet)mlineChart1.getData().getDataSetByIndex(0);
            set1.setValues(values);
            mlineChart1.getData().notifyDataChanged();
            mlineChart1.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(values, "");

            set1.setDrawIcons(false);
            set1.setLineWidth(1.75f); // 线宽
            set1.setColor(getResources().getColor(R.color.track_speed));
//                set1.setCircleSize(3f);// 显示的圆形大小
//                set1.setColor(Color.WHITE);// 显示颜色
//                set1.setCircleColor(Color.WHITE);// 圆形的颜色
            set1.setHighLightColor(getResources().getColor(R.color.track_speed)); // 高亮的线的颜色

            //填充色
//                if (Utils.getSDKInt() >= 18) {
//                    // fill drawable only supported on api level 18 and above
//                    Drawable drawable = ContextCompat.getDrawable(this, R.drawable.red);
//                    set1.setFillDrawable(drawable);
//                }
//                else {
//                    set1.setFillColor(Color.BLACK);
//                }

            ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
            dataSets.add(set1); // add the datasets
            // create a data object with the datasets
            LineData data = new LineData(dataSets);

            // set data
            mlineChart1.setData(data);
        }

        //设置不显示圆点和数值
        List<ILineDataSet> sets = mlineChart1.getData()
                .getDataSets();
        for (ILineDataSet iSet : sets) {
            LineDataSet set = (LineDataSet) iSet;
            set.setDrawCircles(false);
            set.setDrawValues(false);
        }
        mlineChart1.invalidate();
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        MapView.onSaveInstanceState(outState);
    }

    private String returnTime(String time,int i){
        if (i==0){
            return time.substring(0,10)+" 00:00:00";
        }else{
            return time.substring(0,10)+" 23:59:59";
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
       MapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        MapView.onPause();
    }

    @Override
    protected void onDestroy() {
        MapView.onDestroy();
        timeHandler.removeCallbacks(runnable);
        super.onDestroy();
    }


}
