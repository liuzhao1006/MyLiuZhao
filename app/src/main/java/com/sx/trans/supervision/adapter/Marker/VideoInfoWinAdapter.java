package com.sx.trans.supervision.adapter.Marker;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.model.Marker;
import com.sx.trans.R;

import com.sx.trans.app.LzApp;

/**
 * Created by hfd on 2017/7/13.
 * 视频回放 marker 气泡
 */

public class VideoInfoWinAdapter implements AMap.InfoWindowAdapter {
    private String title;
    private String state;
    private Context mContext = LzApp.mInstance.getBaseContext();
    private TextView tv_state;
    private TextView tv_title;

    @Override
    public View getInfoWindow(Marker marker) {
        initData(marker);
        View view = initView();
        return view;
    }

    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }

    private void initData(Marker marker) {
        title = marker.getTitle();
        state = marker.getSnippet();
    }

    @NonNull
    private View initView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.activity_video_popup, null);
        tv_title = (TextView) view.findViewById(R.id.tv_title);
        tv_title.setText("车速: "+state);
        tv_state = (TextView) view.findViewById(R.id.tv_state);
        tv_state.setText(title);
        return view;
    }

}
