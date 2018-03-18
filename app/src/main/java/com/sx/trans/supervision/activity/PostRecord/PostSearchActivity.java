package com.sx.trans.supervision.activity.PostRecord;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sx.trans.supervision.Presenter.PostSearchPresenter;
import com.sx.trans.R;

import com.sx.trans.supervision.app.BaseActivity;
import com.sx.trans.app.LzApp;
import com.sx.trans.supervision.app.IConstants;
import com.sx.trans.supervision.constants.PostCheckTroubleInfo;
import com.sx.trans.supervision.constants.Result;
import com.sx.trans.supervision.utils.AlertDialogUtils;
import com.sx.trans.supervision.utils.JSONUtils;
import com.sx.trans.supervision.views.PublicView;
import com.sx.trans.supervision.widget.alertdialog.Effectstype;
import com.sx.trans.supervision.widget.alertdialog.NiftyDialogBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by mr_wang on 2017/9/4.
 * <p>
 * 查岗问题
 */

public class PostSearchActivity extends BaseActivity implements PublicView {

   private RelativeLayout rlSearchPosto;
   private RelativeLayout rlSearchTrouble;
   private TextView tvZhuangtai;
   private Button btIssued;
   private TextView tvPostName;
   private TextView tvPostTrouble;
   private Button btMenu;

    private void initControls(){
        rlSearchPosto = findViewById(R.id.rl_search_posto);
        rlSearchTrouble = findViewById(R.id.rl_search_trouble);
        tvZhuangtai = findViewById(R.id.tv_zhuangtai);
        btIssued = findViewById(R.id.bt_issued);
        tvPostName = findViewById(R.id.tv_post_name);
        tvPostTrouble = findViewById(R.id.tv_post_trouble);
        btMenu = findViewById(R.id.bt_menu);
    }
    private Dialog IsSuedDialog;
    public  PostSearchPresenter searchPostPresenter;
    private int requesttype = 0;
    private int tradeId = 0;
    private String name = "";
    private String answer = "";
    private String question = "";
    private ArrayList<PostCheckTroubleInfo.postcheckId> postcheckIdlist;
    private ArrayList<PostCheckTroubleInfo.postcheck> postchecklist;
    ArrayList<String> stringlist = new ArrayList<String>();
    private static final int postcheckId_RequestCode = 679;
    private static final int postcheck_RequestCode = 680;
    private NiftyDialogBuilder niftyDialogBuilderShort;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_searchpost);
        initControls();
        init();
        searchPostPresenter.getQuestion();
    }

    private void init() {
        setTitle(true, getString(R.string.search_post), true, getResources().getDrawable(R.drawable.inspect_ico_record));
        searchPostPresenter = new PostSearchPresenter(this, mContext);
        postcheckIdlist = new ArrayList<PostCheckTroubleInfo.postcheckId>();
        postchecklist = new ArrayList<PostCheckTroubleInfo.postcheck>();
    }

    @OnClick({R.id.rl_search_posto, R.id.rl_search_trouble, R.id.bt_issued,R.id.bt_menu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_search_posto:
                if (postcheckIdlist != null && postcheckIdlist.size() > 0) {
                    stringlist.clear();
                    for (PostCheckTroubleInfo.postcheckId postcheckId : postcheckIdlist) {
                        stringlist.add(postcheckId.getName());
                    }
                    Intent intent = new Intent(mContext, PostTroubleListActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(IConstants.mSpre_Constants.POSTCHECK, stringlist);
                    intent.putExtras(bundle);
                    intent.putExtra("title", "查岗行业");
                    intent.putExtra("trouble", name);
                    startActivityForResult(intent, postcheckId_RequestCode);
                }
                break;
            case R.id.rl_search_trouble:
                if (postchecklist != null && postchecklist.size() > 0) {
                    stringlist.clear();
                    for (PostCheckTroubleInfo.postcheck postcheck : postchecklist) {
                        stringlist.add(postcheck.getQuestion());
                    }
                    Intent intent = new Intent(mContext, PostTroubleListActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(IConstants.mSpre_Constants.POSTCHECK, stringlist);
                    intent.putExtras(bundle);
                    intent.putExtra("title", "查岗问题");
                    intent.putExtra("trouble", question);
                    startActivityForResult(intent, postcheck_RequestCode);
                }
                break;
            case R.id.bt_issued:
                requesttype = 1;
                niftyDialogBuilderShort =
                        AlertDialogUtils.showDialog(mContext, Effectstype.SlideBottom, "确认提示", "下发查岗指令?", mContext.getString(R.string.cancel), mContext.getString(R.string.confirm),
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        niftyDialogBuilderShort.dismiss();
                                    }
                                }, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        searchPostPresenter.sendCmd(
                                                tradeId, name, question, answer,
                                                LzApp.mSpfProxy.getString(IConstants.mSpre_Constants.USER_NAME, ""));
                                    }
                                });
                break;
            case R.id.bt_menu:
                startActivity(new Intent(mContext,PostRecordHistoryActivity.class));
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void Success(Result result, int code) {
        if (code == 0) {
            if (requesttype == 0) {//查询当前问题
                List list = (List) result.getData();
                postcheckIdlist = getData(list.get(0), new TypeToken<ArrayList<PostCheckTroubleInfo.postcheckId>>() {
                });
                postchecklist = getData(list.get(1), new TypeToken<ArrayList<PostCheckTroubleInfo.postcheck>>() {
                });
                tradeId = postcheckIdlist.get(0).getId();
                name = postcheckIdlist.get(0).getName();
                question = postchecklist.get(0).getQuestion();
                answer = postchecklist.get(0).getAnswer();
                tvPostName.setText(name);
                tvPostTrouble.setText(question);
                tvZhuangtai.setText(answer);
            } else {
                showSendDiaLogLoading(true, true,result.getData().toString());
                niftyDialogBuilderShort.dismiss();
            }
        } else {
            if (requesttype != 0) {
                showSendDiaLogLoading(true, false,result.getData().toString());
                niftyDialogBuilderShort.dismiss();
            }
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (postcheckId_RequestCode == requestCode && null != data && resultCode == Activity.RESULT_OK) {
            name = data.getStringExtra("trouble");
            tvPostName.setText(name);
            for (PostCheckTroubleInfo.postcheckId postcheckId : postcheckIdlist) {
                if (postcheckId.getName().equals(name)) {
                    tradeId = postcheckId.getId();
                }
            }


        } else if (postcheck_RequestCode == requestCode && null != data && resultCode == Activity.RESULT_OK) {
            question = data.getStringExtra("trouble");
            for (PostCheckTroubleInfo.postcheck postcheck : postchecklist) {
                if (postcheck.getQuestion().equals(question)) {
                    answer = postcheck.getAnswer();
                }
            }
            tvZhuangtai.setText(answer);
            tvPostTrouble.setText(question);
        }
    }

    @Override
    public void showError(String error) {
        Toast.makeText(mContext, error, Toast.LENGTH_SHORT).show();
        hideLoading();
    }

    @Override
    public void showLoading() {
        showDiaLogLoading(false);
    }

    @Override
    public void hideLoading() {
        HideDiaLogLoading();
    }

    //显示下发指令界面
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void showSendDiaLogLoading(boolean cancelable, boolean bl_send,String result) {
        IsSuedDialog = new Dialog(mContext, R.style.search_dialog);
        View view = LayoutInflater.from(mContext).inflate(R.layout.search_dialog, null);
        ImageView iv_send_bg = (ImageView) view.findViewById(R.id.iv_send_bg);
        TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
        if (bl_send) {
            iv_send_bg.setBackground(getResources().getDrawable(R.drawable.send_bg_succeed));
        } else {
            iv_send_bg.setBackground(getResources().getDrawable(R.drawable.send_bg_fail));
        }
        if (result!=null) {
            tv_name.setText(result);
        }else{
            if (bl_send) {
                tv_name.setText("下发成功");
            }else{
                tv_name.setText("下发失败");
            }
        }
        IsSuedDialog.setContentView(view);
        IsSuedDialog.setCancelable(cancelable);//设置背景是否可以点击
        IsSuedDialog.show();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.reset(this);

    }

    public <T> T getData(Object date, TypeToken<T> token) {
        return JSONUtils.fromJson(JSONUtils.toJson(date), token);
    }

}
