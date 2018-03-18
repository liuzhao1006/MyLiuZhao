package com.sx.trans.transport.dynamicMonitoring.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.apkfuns.logutils.LogUtils;
import com.bumptech.glide.Glide;
import com.sx.trans.R;
import com.sx.trans.base.ItemApi;
import com.sx.trans.transport.dynamicMonitoring.bean.VideoListBean;
import com.sx.trans.widget.font.LzTextView;
import com.sx.trans.widget.utils.GetDataUtils;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuAdapter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

/**
 * 作者 : 刘朝,
 * on 2017/9/12,
 * GitHub : https://github.com/liuzhao1006
 */

public class VideoListAdapter extends SwipeMenuAdapter<VideoListAdapter.AccidentHolder> {

    private Context context;
    private List<VideoListBean> beanList;
    private ItemApi api;
    private final WindowManager manager;

    public VideoListAdapter(Context context, List<VideoListBean> beanList, ItemApi api) {
        this.beanList = beanList;
        this.context = context;
        this.api = api;
        manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    }

    @Override
    public View onCreateContentView(ViewGroup parent, int viewType) {
        return LayoutInflater.from(context).inflate(R.layout.item_video_list, parent, false);
    }

    @Override
    public AccidentHolder onCompatCreateViewHolder(View realContentView, int viewType) {
        return new AccidentHolder(realContentView);
    }

    @Override
    public void onBindViewHolder(final AccidentHolder holder, final int position) {

        VideoListBean accidentBean = beanList.get(position);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) holder.ll2.getLayoutParams();
        params.height = manager.getDefaultDisplay().getHeight() / 3 - 65;
        holder.ll2.setLayoutParams(params);
        holder.baifen_tv.setText(accidentBean.getTitle());
        float currrTime = Float.valueOf(accidentBean.getCurrTime());
        if (currrTime == 0) {
            holder.isFinsh.setVisibility(View.INVISIBLE);
            holder.type_iv.setImageResource(R.drawable.nofinish_img);
            holder.isFinsh.setText("");
        } else {
            if (Float.valueOf(accidentBean.getPlayTime()) <= currrTime) {
                holder.type_iv.setImageResource(R.drawable.isfinish_img);
                holder.isFinsh.setVisibility(View.INVISIBLE);
                holder.isFinsh.setText("");
            } else {
                holder.isFinsh.setVisibility(View.VISIBLE);
                holder.type_iv.setImageResource(R.drawable.isandnofinish_img);
                float time = Float.valueOf(accidentBean.getCurrTime());
                holder.isFinsh.setText((GetDataUtils.secToTime((int) time)) + "/" + GetDataUtils.setTiemType(accidentBean.getPlayTime()));

            }

        }
        String url = "http://www.ycycjy.cn:16542" + accidentBean.getImagePath();
        LogUtils.d(url);
        Glide.with(context)
                .load(url)
                .error(R.drawable.shibai)
                .into(holder.img);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                api.onItemClick(holder.itemView, position);
            }
        });
    }


    private int setTiemZhuanMiao(String str) {

        int hourValue = Integer.parseInt(str.split("时")[0]);
        int minValue = Integer.parseInt(str.split("时")[1].split("分")[0]);
        int secValue = Integer.parseInt(str.split("时")[1].split("分")[1].split("秒")[0]);
        return hourValue * 3600 + minValue * 60 + secValue;
    }

    @Override
    public int getItemCount() {
        return beanList.size();
    }

    public class AccidentHolder extends RecyclerView.ViewHolder {
        public LzTextView baifen_tv;
        public LzTextView isFinsh;
        public ImageView img;
        public ImageView type_iv;
        public LinearLayout ll2;

        public AccidentHolder(View itemView) {
            super(itemView);

            baifen_tv = (LzTextView) itemView.findViewById(R.id.history_adapter_konwedge);
            img = (ImageView) itemView.findViewById(R.id.history_adapter_iv);
            isFinsh = (LzTextView) itemView.findViewById(R.id.isFinsh_tv);
            type_iv = (ImageView) itemView.findViewById(R.id.type_iv);
            ll2 = itemView.findViewById(R.id.ll_2);

        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private VideoListAdapter.OnItemClickListener clickListener;

    public void setOnItemClickListener(VideoListAdapter.OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    /**
     * 获取网络配置
     *
     * @param keyName
     * @param context
     * @return
     */
    public String getProperties(Context context, String keyName) {
        Properties props = new Properties();
        try {
            props.load(context.getAssets().open("config.properties"));
//            props.load(context.openFileInput("config.properties"));
            return props.getProperty(keyName);
        } catch (FileNotFoundException e) {
            Log.e("BaseManager::", "config.properties Not Found Exception", e);
            return "配置文件不存在";
        } catch (IOException e) {
            Log.e("BaseManager::", "config.properties IO Exception", e);
            return "读取失败";
        }

    }

}