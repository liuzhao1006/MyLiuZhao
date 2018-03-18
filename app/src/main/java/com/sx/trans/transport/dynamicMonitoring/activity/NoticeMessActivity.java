package com.sx.trans.transport.dynamicMonitoring.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.amap.api.maps.model.Text;
import com.apkfuns.logutils.LogUtils;
import com.sx.lzlibrary.widget.dialog.AlertDialog;
import com.sx.trans.R;
import com.sx.trans.base.BaseNetApi;
import com.sx.trans.base.BaseTransActivity;
import com.sx.trans.company.adapter.CommonRulesPdfAdapter;
import com.sx.trans.transport.dynamicMonitoring.bean.CQueryLeaderMailbox;
import com.sx.trans.transport.dynamicMonitoring.bean.QueryNoticeMessage;
import com.sx.trans.widget.view.ListViewDecoration;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import org.xutils.common.util.LogUtil;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by xxxxxx on 2017/11/21.
 */

public class NoticeMessActivity extends BaseTransActivity implements BaseNetApi {


    private  TextView   tv_handlefeedback_title;
    private  TextView  tv_handlefeedback_date ;
    private List<String> mList = new ArrayList<>();
    private  TextView  tv_tousuren ;
    private  TextView tv_contactfind  ;
    private TextView ltv_down;
    private TextView ltv_down_count;
    private  ImageView im;
    private QueryNoticeMessage.DataBean bean;
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.noticemess;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        TextView l = findViewById(R.id.layout_top_modleinfo);

        l.setText("通知消息详情");
        tv_handlefeedback_title=  findViewById(R.id.tv_handlefeedback_title);
        tv_handlefeedback_date= findViewById(R.id.tv_handlefeedback_date);
        tv_tousuren=  findViewById(R.id.tv_tousuren);
        tv_contactfind= findViewById(R.id.tv_contactfind);
        //附件：显示附件名称，可点击下载；
        ltv_down = findViewById(R.id.ltv_down);
        //附件：显示附件名称，可点击下载；数量
        ltv_down_count = findViewById(R.id.ltv_down_count);
        im=findViewById(R.id.im);//展示图片
        Intent intent = getIntent();
        if (intent != null) {

            Bundle data = intent.getBundleExtra("SafeRulesDetialActivity");
            if (data != null) {
                bean = (QueryNoticeMessage.DataBean) data.getSerializable("SafeRulesDetialActivity.class");
                LogUtils.i(bean);

                initData(bean);
            }
        }
        leftGoBack(this);

    }

//加载数据
    private void initData(final QueryNoticeMessage.DataBean bean) {
        tv_handlefeedback_title.setText(bean.getTitle());
        tv_handlefeedback_date.setText(bean.getDate()+"");
        tv_tousuren.setText(bean.getAbstracts());
        tv_contactfind.setText(Html
                .fromHtml(bean.getContent()));
        if (!TextUtils.isEmpty(bean.getFileInfo())&&bean.getFileInfo().contains("pdf")) {
            ltv_down.setVisibility(View.VISIBLE);
            ltv_down_count.setVisibility(View.VISIBLE);
            String[] item = bean.getFileInfo().split(",");
            ltv_down_count.setText(item.length + "条");
            if (mList.size() > 0) {
                mList.clear();
            }
            for (int i = 0; i < item.length; i++) {
                mList.add(item[i]);
            }
            ltv_down.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog(mList);
                }
            });


        }else if(bean.getFileInfo().contains("jpg")||bean.getFileInfo().contains("png")){
            im.setVisibility(View.VISIBLE);
            String  ur="http://192.168.103.122:8888"+bean.getFileInfo().split("::")[1];
            downLoadImagin(ur);

        }
    }

    private void downLoadImagin(String url) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);

        //请求加入队列
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //此处处理请求失败的业务逻辑
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //我写的这个例子是请求一个图片
                //response的body是图片的byte字节
                byte[] bytes = response.body().bytes();
                //response.body().close();

                //把byte字节组装成图片
                final Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

                //回调是运行在非ui主线程，
                //数据请求成功后，在主线程中更新
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //网络图片请求成功，更新到主线程的ImageView
                        im.setImageBitmap(bmp);
                    }
                });
            }
        });
    }

    /**
     * 图表弹窗展示
     *
     * @param mList
     */
    private SwipeRefreshLayout srv;
    private SwipeMenuRecyclerView rlv;
    private void dialog(List<String> mList) {
        View v = LayoutInflater.from(this).inflate(R.layout.dialog_rule_pdf_item, null);
        srv = v.findViewById(R.id.ref_rlv);
        rlv = v.findViewById(R.id.rlv);
        rlv.setLayoutManager(new LinearLayoutManager(this));// 布局管理器。
        rlv.setHasFixedSize(true);// 如果Item够简单，高度是确定的，打开FixSize将提高性能。
        rlv.setItemAnimator(new DefaultItemAnimator());// 设置Item默认动画，加也行，不加也行。
        rlv.addItemDecoration(new ListViewDecoration());// 添加分割线。
        CommonRulesPdfAdapter adapter = new CommonRulesPdfAdapter(R.layout.item_rule_pdf_dialog, mList, NoticeMessActivity.this);
        rlv.setAdapter(adapter);
        srv.setRefreshing(false);
        srv.setOnRefreshListener(mOnRefreshListener);


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setContentView(v)
                .formBottom(true)
                .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT, 600)
                .addDefaultAnimation()
                .show();
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
    /**
     * 刷新监听。
     */
    private SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            refreshData();

        }
    };


    /**
     * 防止刷新不隐藏
     */
    private void refreshData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (srv.isRefreshing()) {
                    srv.setRefreshing(false);
                }
            }
        }, 3000);
    }


}
