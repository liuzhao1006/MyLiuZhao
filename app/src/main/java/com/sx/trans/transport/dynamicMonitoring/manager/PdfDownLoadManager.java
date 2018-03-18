package com.sx.trans.transport.dynamicMonitoring.manager;

import android.content.Context;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.sx.trans.R;
import com.sx.trans.app.AppConfig;
import com.yanzhenjie.nohttp.Headers;
import com.yanzhenjie.nohttp.Logger;
import com.yanzhenjie.nohttp.download.DownloadListener;
import com.yanzhenjie.nohttp.error.NetworkError;
import com.yanzhenjie.nohttp.error.ServerError;
import com.yanzhenjie.nohttp.error.StorageReadWriteError;
import com.yanzhenjie.nohttp.error.StorageSpaceNotEnoughError;
import com.yanzhenjie.nohttp.error.TimeoutError;
import com.yanzhenjie.nohttp.error.URLError;
import com.yanzhenjie.nohttp.error.UnKnownHostError;

import java.text.DecimalFormat;
import java.util.Locale;

/**
 * 作者: 刘朝
 * 日期: 2017/10/10
 * 描述: 文件下载管理类
 */

public class PdfDownLoadManager implements DownloadListener {

    private Context context;
    /**
     * 下载过程显示,下载速度,下载进度
     */
    private TextView mTvResult;
    /**
     * 进度条展示
     */
    private ProgressBar mProgressBar;

    /*文件下载*/
    private final static String PROGRESS_KEY = "download_single_progress";

    public PdfDownLoadManager(Context context, TextView mTvResult, ProgressBar mProgressBar) {
        this.context = context;
        this.mProgressBar = mProgressBar;
        this.mTvResult = mTvResult;
    }


    @Override
    public void onDownloadError(int what, Exception exception) {
        Logger.e(exception);
        String message = context.getString(R.string.download_error);
        String messageContent;
        if (exception instanceof ServerError) {
            messageContent = context.getString(R.string.download_error_server);
        } else if (exception instanceof NetworkError) {
            messageContent = context.getString(R.string.download_error_network);
        } else if (exception instanceof StorageReadWriteError) {
            messageContent = context.getString(R.string.download_error_storage);
        } else if (exception instanceof StorageSpaceNotEnoughError) {
            messageContent = context.getString(R.string.download_error_space);
        } else if (exception instanceof TimeoutError) {
            messageContent = context.getString(R.string.download_error_timeout);
        } else if (exception instanceof UnKnownHostError) {
            messageContent = context.getString(R.string.download_error_un_know_host);
        } else if (exception instanceof URLError) {
            messageContent = context.getString(R.string.download_error_url);
        } else {
            messageContent = context.getString(R.string.download_error_un);
        }
        message = String.format(Locale.getDefault(), message, messageContent);
        mTvResult.setText(message);
    }

    @Override
    public void onStart(int what, boolean isResume, long rangeSize, Headers responseHeaders, long allCount) {

        int progress = AppConfig.getInstance().getInt(PROGRESS_KEY, 0);
        if (allCount != 0) {
            progress = (int) (rangeSize * 100 / allCount);
            mProgressBar.setProgress(progress);
        }
        updateProgress(progress, 0);
    }

    @Override
    public void onProgress(int what, int progress, long fileCount, long speed) {
        updateProgress(progress, speed);
        mProgressBar.setProgress(progress);
        AppConfig.getInstance().putInt(PROGRESS_KEY, progress);

    }

    @Override
    public void onFinish(int what, String filePath) {
        mTvResult.setText(R.string.download_status_finish);
        if (finish != null) {
            finish.onDownFinish(what, filePath);
        }
    }

    @Override
    public void onCancel(int what) {
        mTvResult.setText(R.string.download_status_be_pause);
    }

    private void updateProgress(int progress, long speed) {
        double newSpeed = speed / 1024D;
        DecimalFormat decimalFormat = new DecimalFormat("###0.00");
        String sProgress = context.getString(R.string.download_progress);
        sProgress = String.format(Locale.getDefault(), sProgress, progress, decimalFormat.format(newSpeed));
        mTvResult.setText(sProgress);
    }

    /**
     * 下载完成接口
     */
    public interface Finish {
        /**
         * 下载完成
         *
         * @param what     接口标志
         * @param filePath 下载路径
         */
        void onDownFinish(int what, String filePath);
    }

    private Finish finish;

    public void setOnFinish(Finish finish) {
        this.finish = finish;
    }
}
