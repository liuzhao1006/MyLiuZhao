package com.sx.trans.transport.dynamicMonitoring.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.jauker.widget.BadgeView;
import com.sx.trans.R;
import com.sx.trans.base.ItemApi;
import com.sx.trans.transport.dynamicMonitoring.bean.LearnStudyBean;
import com.sx.trans.widget.font.LzTextView;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuAdapter;

import java.util.List;

/**
 * 作者 : 刘朝,
 * on 2017/9/12,
 * GitHub : https://github.com/liuzhao1006
 */

public class LearnStudyAdapter extends SwipeMenuAdapter<LearnStudyAdapter.AccidentHolder> {

    private Context context;
    private List<LearnStudyBean> beanList;
    private ItemApi api;
    private final WindowManager manager;

    public LearnStudyAdapter(Context context, List<LearnStudyBean> beanList, ItemApi api) {
        this.beanList = beanList;
        this.context = context;
        this.api = api;
        manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    }

    @Override
    public View onCreateContentView(ViewGroup parent, int viewType) {
        return LayoutInflater.from(context).inflate(R.layout.item_learn_study, parent, false);
    }

    @Override
    public AccidentHolder onCompatCreateViewHolder(View realContentView, int viewType) {
        return new AccidentHolder(realContentView);
    }

    @Override
    public void onBindViewHolder(final AccidentHolder holder, final int position) {
        if (position >= 3) {
            return;
        }
        LearnStudyBean accidentBean = beanList.get(position);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) holder.study_ll.getLayoutParams();
        params.height = manager.getDefaultDisplay().getHeight() / 12;
        holder.study_adapter_tap.setText(accidentBean.getStudyPlanName());
        holder.study_adapter_tap.setTextColor(context.getResources().getColor(R.color.chengjinglan));
        if (accidentBean.getIsFinish().equals("0")) {
            holder.study_adapter_.setText("未学");
        } else {
            holder.study_adapter_.setText("已学");
        }
        holder.study_adapter_.setTextColor(context.getResources().getColor(R.color.textcolor));
        BadgeView badgeView = new BadgeView(context);
        badgeView.setTargetView(holder.study_adapter_);
        badgeView.setBackground(context.getResources().getDrawable(R.drawable.badegview_background));
        badgeView.setBadgeGravity(Gravity.CENTER | Gravity.RIGHT | Gravity.TOP);
        badgeView.setText("必");
        badgeView.setTextSize(14);
        badgeView.setBadgeMargin(0, 5, 5, 0);
        badgeView.setTextColor(context.getResources().getColor(R.color.chengjinglan));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                api.onItemClick(holder.itemView, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return beanList.size();
    }

    public class AccidentHolder extends RecyclerView.ViewHolder {
        public LzTextView study_adapter_tap;
        public LzTextView study_adapter_;
        public LinearLayout study_ll;

        public AccidentHolder(View itemView) {
            super(itemView);
            study_adapter_tap = (LzTextView) itemView.findViewById(R.id.study_adapter_tap);
            study_adapter_ = (LzTextView) itemView.findViewById(R.id.study_adapter_);
            study_ll = itemView.findViewById(R.id.study_ll);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private LearnStudyAdapter.OnItemClickListener clickListener;

    public void setOnItemClickListener(LearnStudyAdapter.OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }
}