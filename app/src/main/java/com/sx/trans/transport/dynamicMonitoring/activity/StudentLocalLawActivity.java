package com.sx.trans.transport.dynamicMonitoring.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.sx.lzlibrary.base.BaseAdapter;
import com.sx.trans.R;
import com.sx.trans.app.AppConfig;
import com.sx.trans.base.BaseNetApi;
import com.sx.trans.base.ItemApi;
import com.sx.trans.transport.dynamicMonitoring.adapter.LawAdapter;
import com.sx.trans.transport.dynamicMonitoring.adapter.LocalLawAdapter;
import com.sx.trans.transport.dynamicMonitoring.bean.LocalLawBean;
import com.sx.trans.transport.dynamicMonitoring.bean.PdfBean;
import com.sx.trans.transport.dynamicMonitoring.bean.ReadPdfBean;
import com.sx.trans.transport.dynamicMonitoring.inter.LawApi;
import com.sx.trans.transport.dynamicMonitoring.manager.StudentManager;
import com.sx.trans.widget.down.CallServer;
import com.sx.trans.widget.view.ListViewDecoration;
import com.yanzhenjie.nohttp.Headers;
import com.yanzhenjie.nohttp.Logger;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.download.DownloadListener;
import com.yanzhenjie.nohttp.download.DownloadRequest;
import com.yanzhenjie.nohttp.error.NetworkError;
import com.yanzhenjie.nohttp.error.ServerError;
import com.yanzhenjie.nohttp.error.StorageReadWriteError;
import com.yanzhenjie.nohttp.error.StorageSpaceNotEnoughError;
import com.yanzhenjie.nohttp.error.TimeoutError;
import com.yanzhenjie.nohttp.error.URLError;
import com.yanzhenjie.nohttp.error.UnKnownHostError;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.sx.trans.widget.utils.Utils.toURLEncoded;

/**
 * 地方法律法规政策
 * 规章制度
 */
public class StudentLocalLawActivity extends BaseActivity implements BaseNetApi, ItemApi, LawApi {

    private SwipeRefreshLayout srv;
    private SwipeMenuRecyclerView rlv;
    private LocalLawAdapter adapter;
    private static final int LOCALLAW = 0x0006;//法律法规
    private static final int LOCALLAW_LOCAL = 0x0007;//地方法律法规
    private static final int LOCALLAW_LAW = 0x0008;//制度规范
    private List<LocalLawBean> mList = new ArrayList<>();
    private List<ReadPdfBean> ruleList = new ArrayList<>();
    private int positions = -1;//初始化点击位置

    //请求访问外部存储
    private static final int READ_EXTERNAL_STORAGE_REQUEST_CODE = 103;
    //请求写入外部存储
    private static final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 104;
    /*文件下载*/
    private final static String PROGRESS_KEY = "download_single_progress";

    /**
     * 下载状态.
     */
    TextView mTvResult;
    /**
     * 下载进度条.
     */
    ProgressBar mProgressBar;
    /**
     * 下载请求.
     */
    private DownloadRequest mDownloadRequest;
    private List<PdfBean> mList1;
    private LinearLayout ll_down;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_local_law;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_local_law);
        leftGoBack(this);
        ll_down = findViewById(R.id.ll_down);
        srv = findViewById(R.id.ref_rlv);
        rlv = findViewById(R.id.rlv);
        mProgressBar = findViewById(R.id.pb_progress);
        mTvResult = findViewById(R.id.tv_result);
        TextView l = findViewById(R.id.layout_top_modleinfo);
        l.setText("地方法律法规");
        setRecycleViewData();
        if (getIntent() != null) {
            String studentLocalLawActivity = getIntent().getStringExtra("StudentLocalLawActivity");
            if (!TextUtils.isEmpty(studentLocalLawActivity)) {
                if ("SystemSpecification".equals(studentLocalLawActivity)) {
                    l.setText("制度规范");
                    if (ruleList.size() > 0) {
                        ruleList.clear();
                    }
                    StudentManager manager = new StudentManager(this, this);

                } else if ("DMFragment".equals(studentLocalLawActivity)) {
                    l.setText("地方法律法规");
                    StudentManager manager = new StudentManager(this, this);
                    if (mList.size() > 0) {

                    }
                    manager.getLaw();
                    manager.setOnLawData(new StudentManager.LawData() {
                        @Override
                        public void onLawData(int what, List<LocalLawBean> lawList) {
                            if (lawList.size() <= 0) {
                                return;
                            }
                            if (srv.isRefreshing()) {
                                srv.setRefreshing(false);
                            }
                            mList.clear();
                            mList.addAll(lawList);
                            LogUtils.i(mList.size());
                            LawAdapter mAdapter = new LawAdapter(R.layout.item_sign_line, mList);
                            rlv.setAdapter(mAdapter);
                            mAdapter.setItemClickListener(new BaseAdapter.onItemClickListener() {
                                @Override
                                public void onItemClick(int position, View v) {
//                                    download2(mList.get(position));
                                }
                            });
                        }
                    });
                } else if ("StudentSpecialStudyActivity".equals(studentLocalLawActivity)) {
                }
            }
        }
    }

    /**
     * 外部存储权限申请返回
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == WRITE_EXTERNAL_STORAGE_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission Granted
//                gotoCamera();
//                fromCamera();
//                download(mList1.get(positions));
            }
        } else if (requestCode == READ_EXTERNAL_STORAGE_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission Granted
//                gotoPhoto();
//                fromAlbumRadio();
            }
        }
    }


    private void setRecycleViewData() {
        rlv.setLayoutManager(new LinearLayoutManager(this));// 布局管理器。
        rlv.setHasFixedSize(true);// 如果Item够简单，高度是确定的，打开FixSize将提高性能。
        rlv.setItemAnimator(new DefaultItemAnimator());// 设置Item默认动画，加也行，不加也行。
        rlv.addItemDecoration(new ListViewDecoration());// 添加分割线。
    }


    /**
     * listView展示
     *
                * @param mList
                */
        private void setCommonContract(List<PdfBean> mList) {
            adapter = new LocalLawAdapter(this, mList, this);
        rlv.setAdapter(adapter);
    }


    @Override
    public void netStart() {
        srv.setRefreshing(true);
    }

    @Override
    public void netStop() {
        srv.setRefreshing(false);
    }

    @Override
    public void netSuccessed(int what, String data) {
        switch (what) {
            case LOCALLAW://法律法规
                if (mList1 != null) {
                    mList1 = null;
                }
                mList1 = new ArrayList<>();
                mList1.add(new PdfBean("2017年中华人民共和国道路交通安全法实施条例", "","07分14秒"));
                mList1.add(new PdfBean("道路旅客运输及客运站管理规定", "","05分10秒"));
                mList1.add(new PdfBean("中华人民共和国道路交通安全法", "","06分10秒"));
                mList1.add(new PdfBean("中华人民共和国道路交通运输条例", "","04分50秒"));
                mList1.add(new PdfBean("中华人民共和国治安管理处罚法", "","08分30秒"));
                setCommonContract(mList1);

                break;
            case LOCALLAW_LAW://制度规范

                if (mList1 != null) {
                    mList1 = null;
                }
                mList1 = new ArrayList<>();
                mList1.add(new PdfBean("服务用语规范制度", "","05分10秒"));
                mList1.add(new PdfBean("交接班制度", "","06分10秒"));
                mList1.add(new PdfBean("每日工作流程制度", "","05分10秒"));
                mList1.add(new PdfBean("危险品运输公司安全生产标准化", "","04分50秒"));
                mList1.add(new PdfBean("危险品运输企业安全生产责任制度", "","07分14秒"));
                mList1.add(new PdfBean("现场管理制度", "","06分10秒"));
                mList1.add(new PdfBean("值班制度", "","08分30秒"));
                setCommonContract(mList1);

                break;
            case LOCALLAW_LOCAL://地方法律法规
                if (mList1 != null) {
                    mList1 = null;
                }
                mList1 = new ArrayList<>();
                mList1.add(new PdfBean("615交通事故案例分析", "","06分10秒"));
                mList1.add(new PdfBean("保护乘客人身财产安全案例", "","08分30秒"));
                mList1.add(new PdfBean("道路运输(培训资料6月27日)", "","02分10秒"));
                mList1.add(new PdfBean("典型事故案例分析", "","04分50秒"));
                mList1.add(new PdfBean("学习新《安全生产法》（马瑛，市交通企业）.pdf", "","06分10秒"));
                mList1.add(new PdfBean("一般事故案例分析", "","07分14秒"));
                setCommonContract(mList1);
                break;
        }


    }

    @Override
    public void netFailed(int what, String message) {

    }


    @Override
    public void onItemClick(View view, int position) {
        if (positions != -1) {
            positions = -1;
        }
        positions = position;
        download(mList1.get(position));
        LogUtils.d(positions);
    }

    @Override
    public void getListData(List<LocalLawBean> localLawBeans) {
//        LogUtils.i(localLawBeans.get(0).toString());
//
//        if (localLawBeans == null || localLawBeans.size() <= 0) {
//            return;
//        }
//        if (mList.size() > 0) {
//            mList.clear();
//        }
//        if (srv.isRefreshing()) {
//            srv.setRefreshing(false);
//        }
//        LogUtils.i(localLawBeans.get(0));
//        mList = localLawBeans;
//        setCommonContract(mList);
    }

    /*文件下载*/

    /**
     * 开始下载。
     */
    private void download(PdfBean bean) {
        ll_down.setVisibility(View.VISIBLE);
        // 开始下载了，但是任务没有完成，代表正在下载，那么暂停下载。
        if (mDownloadRequest != null && mDownloadRequest.isStarted() && !mDownloadRequest.isFinished()) {
            // 暂停下载。
            mDownloadRequest.cancel();
        } else if (mDownloadRequest == null || mDownloadRequest.isFinished()) {// 没有开始或者下载完成了，就重新下载。
            String url = "http://202.99.199.99:8080/public/" + toURLEncoded(bean.name + ".pdf");
            LogUtils.w(bean.name);
            mDownloadRequest = new DownloadRequest(url, RequestMethod.GET,
                    AppConfig.getInstance().APP_PATH_ROOT,
                    true, true);
            CallServer.getInstance().download(0, mDownloadRequest, downloadListener);
        }
    }

    /**
     * 开始下载。法规政策
     */
    private void download2(LocalLawBean bean) {
        ll_down.setVisibility(View.VISIBLE);
        String url;
        // 开始下载了，但是任务没有完成，代表正在下载，那么暂停下载。
        if (mDownloadRequest != null && mDownloadRequest.isStarted() && !mDownloadRequest.isFinished()) {
            // 暂停下载。
            mDownloadRequest.cancel();
        } else if (mDownloadRequest == null || mDownloadRequest.isFinished()) {// 没有开始或者下载完成了，就重新下载。
            if (TextUtils.isEmpty(bean.getFile_title()) || TextUtils.isEmpty(bean.getFile_url())) {
                Toast.makeText(this, "文件不存在,请联系管路员处理...", Toast.LENGTH_SHORT).show();
                ll_down.setVisibility(View.INVISIBLE);
                return;
            }
            if (bean.getFile_title().contains(".pdf") || bean.getFile_title().contains(".PDF")) {
                url = bean.getFile_url() + toURLEncoded(bean.getFile_title());
            } else {
                url = bean.getFile_url() + toURLEncoded(bean.getFile_title() + ".pdf");
            }

//            String url = "http://202.99.199.99:8080/public/" + toURLEncoded(bean.name + ".pdf");
            mDownloadRequest = new DownloadRequest(url, RequestMethod.GET,
                    AppConfig.getInstance().APP_PATH_ROOT,
                    true, true);
            CallServer.getInstance().download(0, mDownloadRequest, downloadListener);
        }
    }

    /**
     * 开始下载。规章制度
     */
    private void download3(ReadPdfBean bean) {
        ll_down.setVisibility(View.VISIBLE);
        String url;
        // 开始下载了，但是任务没有完成，代表正在下载，那么暂停下载。
        if (mDownloadRequest != null && mDownloadRequest.isStarted() && !mDownloadRequest.isFinished()) {
            // 暂停下载。
            mDownloadRequest.cancel();
        } else if (mDownloadRequest == null || mDownloadRequest.isFinished()) {// 没有开始或者下载完成了，就重新下载。
//            if (TextUtils.isEmpty(bean.getFileTitle()) || TextUtils.isEmpty(bean.getFileTitle())) {
//                Toast.makeText(this, "文件不存在,请联系管路员处理...", Toast.LENGTH_SHORT).show();
//                ll_down.setVisibility(View.INVISIBLE);
//                return;
//            }
//            if (bean.getFileTitle().contains(".pdf") || bean.getFileTitle().contains(".PDF")) {
//                url = bean.getFileTitle() + toURLEncoded(bean.getFileTitle());
//            } else {
//                url = bean.getFileTitle() + toURLEncoded(bean.getFileTitle() + ".pdf");
//            }

//            String url = "http://202.99.199.99:8080/public/" + toURLEncoded(bean.name + ".pdf");
//            mDownloadRequest = new DownloadRequest(url, RequestMethod.GET,
//                    AppConfig.getInstance().APP_PATH_ROOT,
//                    true, true);
            CallServer.getInstance().download(0, mDownloadRequest, downloadListener);
        }
    }

    /**
     * 下载监听
     */
    private DownloadListener downloadListener = new DownloadListener() {

        @Override
        public void onStart(int what, boolean isResume, long beforeLength, Headers headers, long allCount) {
            int progress = AppConfig.getInstance().getInt(PROGRESS_KEY, 0);
            if (allCount != 0) {
                progress = (int) (beforeLength * 100 / allCount);
                mProgressBar.setProgress(progress);
            }
            updateProgress(progress, 0);

//            mBtnStart.setText(R.string.download_status_pause);
//            mBtnStart.setEnabled(true);
        }

        @Override
        public void onDownloadError(int what, Exception exception) {
            ll_down.setVisibility(View.INVISIBLE);
            Logger.e(exception);
//            mBtnStart.setText(R.string.download_status_again_download);
//            mBtnStart.setEnabled(true);

            String message = getString(R.string.download_error);
            String messageContent;
            if (exception instanceof ServerError) {
                messageContent = getString(R.string.download_error_server);
            } else if (exception instanceof NetworkError) {
                messageContent = getString(R.string.download_error_network);
            } else if (exception instanceof StorageReadWriteError) {
                messageContent = getString(R.string.download_error_storage);
            } else if (exception instanceof StorageSpaceNotEnoughError) {
                messageContent = getString(R.string.download_error_space);
            } else if (exception instanceof TimeoutError) {
                messageContent = getString(R.string.download_error_timeout);
            } else if (exception instanceof UnKnownHostError) {
                messageContent = getString(R.string.download_error_un_know_host);
            } else if (exception instanceof URLError) {
                messageContent = getString(R.string.download_error_url);
            } else {
                messageContent = getString(R.string.download_error_un);
            }
            message = String.format(Locale.getDefault(), message, messageContent);
            mTvResult.setText(message);
        }

        @Override
        public void onProgress(int what, int progress, long fileCount, long speed) {
            updateProgress(progress, speed);
            mProgressBar.setProgress(progress);
            AppConfig.getInstance().putInt(PROGRESS_KEY, progress);
        }

        @Override
        public void onFinish(int what, String filePath) {

            Logger.d("Download finish, file path: " + filePath);
//            Snackbar.show(StudentLocalLawActivity.this, getText(R.string.download_status_finish));// 提示下载完成
            mTvResult.setText(R.string.download_status_finish);

//            mBtnStart.setText(R.string.download_status_re_download);
//            mBtnStart.setEnabled(true);

            if (positions == -1) {
                Toast.makeText(StudentLocalLawActivity.this, "无法获取到数据", Toast.LENGTH_SHORT).show();
                return;
            }
            LogUtils.d(positions);
            //下载完成跳转到展示PDF页面,带路径参数
            Intent intent = new Intent(StudentLocalLawActivity.this, StudentPdfActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("pdfContent", mList1.get(positions));
            intent.putExtra("StudentPdfActivity", bundle);
            startActivity(intent);
            ll_down.setVisibility(View.INVISIBLE);
        }

        @Override
        public void onCancel(int what) {
            mTvResult.setText(R.string.download_status_be_pause);
//            mBtnStart.setText(R.string.download_status_resume);
//            mBtnStart.setEnabled(true);
        }

        private void updateProgress(int progress, long speed) {
            double newSpeed = speed / 1024D;
            DecimalFormat decimalFormat = new DecimalFormat("###0.00");
            String sProgress = getString(R.string.download_progress);
            sProgress = String.format(Locale.getDefault(), sProgress, progress, decimalFormat.format(newSpeed));
            mTvResult.setText(sProgress);
        }
    };


    @Override
    protected void onDestroy() {
        // 暂停下载
        if (mDownloadRequest != null) {
            mDownloadRequest.cancel();
            ll_down.setVisibility(View.INVISIBLE);
        }
        super.onDestroy();
    }
}
