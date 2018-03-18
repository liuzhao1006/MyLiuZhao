package com.sx.trans.supervision.activity.PostRecord;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.sx.trans.supervision.Presenter.PostSearchPresenter;
import com.sx.trans.R;

import com.sx.trans.supervision.adapter.SearchMorRecordAdapter;
import com.sx.trans.supervision.app.BaseActivity;
import com.sx.trans.supervision.app.IConstants;
import com.sx.trans.supervision.constants.PostCheckInfo;
import com.sx.trans.supervision.constants.PostCheckMoreInfo;
import com.sx.trans.supervision.constants.Result;
import com.sx.trans.supervision.views.PublicView;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by mr_wang on 2017/9/4.
 * <p>
 * 查岗详细页
 */

public class PostRecordListActivity extends BaseActivity implements PublicView{


   private ListView lvRecordmorlist;
   private  RelativeLayout ivNodate;

    private void initControls(){
        lvRecordmorlist = findViewById(R.id.lv_recordmorlist);
        ivNodate = findViewById(R.id.iv_nodate);

    }

    private ArrayList<PostCheckMoreInfo> postCheckInfos;
    private PostSearchPresenter postSearchPresenter;
    private int page=1;
    private String question="";
    private String time="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_mor_post);
        initControls();
        inti();
    }


    private void inti() {
        setTitle(true, getString(R.string.search_postmore), true, getResources().getDrawable(R.drawable.search));
        lvRecordmorlist.setEmptyView(ivNodate);
        postCheckInfos=new ArrayList<PostCheckMoreInfo>();
        postSearchPresenter=new PostSearchPresenter(this,this);
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = this.getIntent().getExtras();
            if (bundle != null) {
                PostCheckInfo postCheckInfo = (PostCheckInfo) bundle.getSerializable(IConstants.mSpre_Constants.SEARCHRECORD);
                if (postCheckInfo != null) {
                    question=postCheckInfo.getQuestion();
                    time=postCheckInfo.getTime();
                }
            }
        }
        postSearchPresenter.checkDetail(question,time,10,page);


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
        if (code==0){
            postCheckInfos= result.getData(new TypeToken<ArrayList<PostCheckMoreInfo>>() {
            });
            SearchMorRecordAdapter searchMorRecordAdapter=new SearchMorRecordAdapter(mContext,postCheckInfos);
            lvRecordmorlist.setAdapter(searchMorRecordAdapter);

            if (postCheckInfos==null||postCheckInfos.size()==0)
                ivNodate.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showError(String error) {
        Toast.makeText(mContext,error,Toast.LENGTH_SHORT).show();
    }
}
