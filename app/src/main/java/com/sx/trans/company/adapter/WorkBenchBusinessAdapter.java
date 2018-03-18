package com.sx.trans.company.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.sx.trans.R;

import java.util.List;

/**
 * Created by liuchao on 2017/6/21.
 */

public class WorkBenchBusinessAdapter extends RecyclerView.Adapter<WorkBenchBusinessViewHolder> {


    private List<String> mList;
    private Context context;
    private WindowManager manager;


    public WorkBenchBusinessAdapter(Context context, List<String> mList) {
        this.mList = mList;
        this.context = context;
        manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    }

    @Override
    public WorkBenchBusinessViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_work_bench_business, viewGroup, false);
        WorkBenchBusinessViewHolder holder = new WorkBenchBusinessViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final WorkBenchBusinessViewHolder workBenchBusinessViewHolder, int i) {

        String s = mList.get(i);

        switch (i) {
            case 0:
                workBenchBusinessViewHolder.ivShop.setImageResource(R.drawable.service_realtime_location);
                break;
            case 1:
                workBenchBusinessViewHolder.ivShop.setImageResource(R.drawable.service_realtime_video);
                break;
            case 2:
                workBenchBusinessViewHolder.ivShop.setImageResource(R.drawable.service_track_playback);
                break;
//            case 3:
//                workBenchBusinessViewHolder.ivShop.setImageResource(R.drawable.service_regional_analysis);
//                break;
            case 3:
//                workBenchBusinessViewHolder.ivShop.setImageResource(R.drawable.service_industry_analysis);
                break;
            case 4:
//                workBenchBusinessViewHolder.ivShop.setImageResource(R.drawable.service_alert_analysis);
                break;
//            case 6:
//                workBenchBusinessViewHolder.ivShop.setImageResource(R.drawable.service_enterprise_analysis);
//                break;
            case 5:
//                workBenchBusinessViewHolder.ivShop.setImageResource(R.drawable.service_paltform_analysis);
                break;
//            case 8:
//                workBenchBusinessViewHolder.ivShop.setImageResource(R.drawable.ico_inspect_the_sentries);
//                break;
        }


        workBenchBusinessViewHolder.tvWorkBenchBusiness.setText(s);

        if (clickListener != null) {
            workBenchBusinessViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onItemClick(workBenchBusinessViewHolder.itemView, workBenchBusinessViewHolder.getLayoutPosition());
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private WorkBenchBusinessAdapter.OnItemClickListener clickListener;

    public void setOnItemClickListener(WorkBenchBusinessAdapter.OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

}
