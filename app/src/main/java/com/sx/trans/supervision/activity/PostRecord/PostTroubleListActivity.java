package com.sx.trans.supervision.activity.PostRecord;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.sx.trans.R;

import com.sx.trans.supervision.adapter.TroubleAdapter;
import com.sx.trans.supervision.app.BaseActivity;
import com.sx.trans.supervision.app.IConstants;
import com.sx.trans.supervision.utils.JSONUtils;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by mr_wang on 2017/9/5.
 * 查岗问题选择
 */

public class PostTroubleListActivity extends BaseActivity {

private    ListView lvTroublelist;
    private  ArrayList<String> stringArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posttrouble_list);
        lvTroublelist =findViewById(R.id.lv_troublelist);
        init();
    }

    private void init(){
        stringArrayList=new ArrayList<String>();
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = this.getIntent().getExtras();
            if (bundle != null) {
                setTitle(true, intent.getStringExtra("title"), false, null);
                stringArrayList = getData(bundle.getSerializable(IConstants.mSpre_Constants.POSTCHECK),new TypeToken<ArrayList<String>>() {
                });
                String name=intent.getStringExtra("trouble");
                if (stringArrayList!=null&&stringArrayList.size()>0) {
                    TroubleAdapter troubleAdapter = new TroubleAdapter(mContext, stringArrayList,name);
                    lvTroublelist.setAdapter(troubleAdapter);
                }
            }
        }

        lvTroublelist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mContext, PostSearchActivity.class);
                intent.putExtra("trouble", stringArrayList.get(position).toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }


    public <T> T getData(Object date,TypeToken<T> token) {
        return JSONUtils.fromJson(JSONUtils.toJson(date),token);
    }
}
