package com.sx.trans.transport.dynamicMonitoring.activity;

import android.Manifest;
import android.content.Intent;
import android.os.PowerManager;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.sx.trans.R;
import com.sx.trans.base.BaseNetApi;
import com.sx.trans.base.BaseTransActivity;
import com.sx.trans.transport.dynamicMonitoring.bean.VideoListBean;
import com.sx.trans.transport.dynamicMonitoring.ijkwedget.AndroidMediaController;
import com.sx.trans.transport.dynamicMonitoring.ijkwedget.IjkVideoView;
import com.sx.trans.transport.dynamicMonitoring.ijkwedget.PlayerManager;
import com.sx.trans.widget.font.LzTextView;

public class VideoShowActivity extends BaseTransActivity implements BaseNetApi, PlayerManager.PlayerStateListener {


    private PlayerManager player;
    private TextView countTime, currentTime;
    // private SeekBar seekbar;
//    private String videoPath = "http://47.92.29.57:14804/UploadFile/Video/CZ/jixu_chuzu_9_02.flv";
    private String videoPath;
    private AndroidMediaController mMediaController;
    private IjkVideoView mVideoView;
    private SeekBar seekbar;
    private IjkVideoView videoView;
    FrameLayout fl_preview;
    private long upper = -1;
    // 通过用户指定的播放进度更新时间等信息
    private int pp = 1;

    private IjkVideoView video_view;
    private TextView title;
    private LinearLayout ll;
    // //拍照权限
    private static final String[] PICTURE_PERMISSIONS = {Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};
    // 拍照权限请求码
    private static final int REQUEST_PICTURE_PERMISSION = 1;
    PowerManager powerManager = null;
    PowerManager.WakeLock wakeLock = null;
    private VideoListBean accidentBean;
    private LzTextView time_start;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_video_show;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        Intent intent = getIntent();
        if (intent != null) {
            Bundle data = intent.getBundleExtra("VideoShowActivity");
            accidentBean = (VideoListBean) data.getSerializable("VideoShowActivity.class");
            LogUtils.i(accidentBean);
        }
        if (accidentBean.getVideoURL().equals(" ")) {
            showToast("暂无视频资源");
        } else {
            videoPath = "http://www.ycycjy.cn:16542" + accidentBean.getVideoURL();
            LogUtils.i(videoPath);
        }
        player = new PlayerManager(this);
        video_view = (IjkVideoView) findViewById(R.id.video_view);
        player.play(videoPath);
        LzTextView title = findViewById(R.id.titile_tv);
        time_start = findViewById(R.id.time_start);
        LzTextView time_end = findViewById(R.id.time_end);
        title.setText(accidentBean.getTitle());
        time_end.setText(accidentBean.getPlayTime());


    }


    @Override
    public void netStart() {

    }

    @Override
    public void netStop() {

    }

    @Override
    public void netSuccessed(int what, String data) {

    }

    @Override
    public void netFailed(int what, String message) {

    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onError() {

    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onPlay() {

    }

    @Override
    public void onPrepared() {

    }
}
