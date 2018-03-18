package com.sx.trans.company.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.sx.lzlibrary.base.BaseAdapter;
import com.sx.trans.R;
import com.sx.trans.app.AppConfig;
import com.sx.trans.company.bean.SafeInputBean;
import com.sx.trans.transport.dynamicMonitoring.activity.StudentPdfActivity;
import com.sx.trans.transport.dynamicMonitoring.bean.PdfBean;
import com.sx.trans.transport.dynamicMonitoring.manager.PdfDownLoadManager;
import com.sx.trans.widget.down.CallServer;
import com.sx.trans.widget.utils.Utils;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.download.DownloadRequest;

import java.util.List;

/**
 * 作者: 刘朝
 * 日期: 2017/10/10
 * 描述: 制度规范下载文件的列表适配器, 适合所有相同内容
 */

public class SpecilStudyDownPdfAdapter extends BaseAdapter {
    private Context context;
    /**
     * 下载请求.
     */
    private DownloadRequest mDownloadRequest;
    private PdfDownLoadManager manager;

    //假数据
    private int i;
    private SafeInputBean data1;

    public SpecilStudyDownPdfAdapter(int layoutResId, List data, Context context) {
        super(layoutResId, data);
        this.context = context;

    }

    @Override
    protected void bindTheData(final RecyclerView.ViewHolder holder, Object data, int position) {

        final String[] title = data.toString().split(",");
        TextView tv_name = holder.itemView.findViewById(R.id.tv_name);
        tv_name.setText(title[0]);
        final ProgressBar pb_progress = holder.itemView.findViewById(R.id.pb_progress);
        final TextView tv_result = holder.itemView.findViewById(R.id.tv_result);
        if (title[0].contains(".pdf") || title[0].contains(".xlsx")) {
           //"中华人民共和国道路交通安全法.pdf::/ueditor/jsp/upload/file/20171116/1510824792760056295.pdf"
            tv_result.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    download(title, holder, title[1], pb_progress, tv_result);
                }
            });
        } else if (title[0].contains(".png") || title[0].contains(".jpg")) {
            //图片
            LogUtils.i("我是图片");
        }
    }

    private void downLoadManager(final String[] title, final RecyclerView.ViewHolder holder, final ProgressBar pb_progress, TextView tv_result) {
        manager = new PdfDownLoadManager(context, tv_result, pb_progress);

        // 下载完成
        manager.setOnFinish(new PdfDownLoadManager.Finish() {
            @Override
            public void onDownFinish(int what, String filePath) {
                final String path=filePath;
//                i = 1;
                pb_progress.setVisibility(View.GONE);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        LogUtils.i("下载完成onDownFinish");
                        //下载完成跳转到展示PDF页面,带路径参数
                        Intent intent = new Intent(context, StudentPdfActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("pdfContent", new PdfBean(title[0],path,"" ));
                        intent.putExtra("StudentPdfActivity", bundle);
                        context.startActivity(intent);

                    }
                });
            }
        });
    }

    /**
     * 开始下载。
     */
    private void download(String[] title, RecyclerView.ViewHolder holder, String bean, ProgressBar pb_progress, TextView tv_result) {
        // 开始下载了，但是任务没有完成，代表正在下载，那么暂停下载。
        LogUtils.i(bean);
        downLoadManager(title, holder, pb_progress, tv_result);
//        if (i == 1) {
//            LogUtils.i("已经下载过了");
//            return;
//        }
        if (mDownloadRequest != null && mDownloadRequest.isStarted() && !mDownloadRequest.isFinished()) {
            // 暂停下载。
            mDownloadRequest.cancel();
        } else if (mDownloadRequest == null || mDownloadRequest.isFinished()) {// 没有开始或者下载完成了，就重新下载。
            String url = Utils.getProperties(context, "IP") + title[1];
            LogUtils.i(url);
            mDownloadRequest = new DownloadRequest(url, RequestMethod.GET,
                    AppConfig.getInstance().APP_PATH_ROOT,
                    true, true);
            CallServer.getInstance().download(0, mDownloadRequest, manager);
        }
    }
}
