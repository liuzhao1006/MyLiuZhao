package com.sx.trans.company.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.sx.lzlibrary.base.BaseAdapter;
import com.sx.trans.R;
import com.sx.lzlibrary.widget.dialog.AlertDialog;
import com.sx.trans.company.safemanager.SafeMeetingDetialActivity;

import java.util.ArrayList;
import java.util.List;


public class CompeletePersonDetailAdapter extends BaseAdapter {
    Context context;
    public CompeletePersonDetailAdapter(int layoutResId, List data,Context context) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void bindTheData(RecyclerView.ViewHolder holder, Object data, int position) {


        TextView name = holder.itemView.findViewById(R.id.name);
        TextView jindu = holder.itemView.findViewById(R.id.jindu);
        TextView totaltime = holder.itemView.findViewById(R.id.totaltime);
        TextView havelearn = holder.itemView.findViewById(R.id.havelearn);
        ImageView pic_list = holder.itemView.findViewById(R.id.pic_list);
        final ArrayList<String> mlist=new ArrayList<>();
        mlist.add("http://192.168.103.119:8089/UpLoad/20180201/20180201150224821.png") ;
        mlist.add("http://192.168.103.119:8089/UpLoad/20180201/20180201150224821.png");
        pic_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //展示图片

                dialog(mlist);
            }
        });
        name.setText("2");
        jindu.setText("2%");
        totaltime.setText("2");
        havelearn.setText("2");

    }
    private void dialog(List images) {
        View v = LayoutInflater
                .from(context).inflate(R.layout.dialog_pic_list_item, null);

        GridView gridView=v.findViewById(R.id.gridView);
        PictureAdapter pictureAdapter = new PictureAdapter(images, (Activity) context);

        gridView.setAdapter(pictureAdapter);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setContentView(v)
                .formBottom(true)
                .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT, 400)
                .addDefaultAnimation()
                .show();
    }
}
