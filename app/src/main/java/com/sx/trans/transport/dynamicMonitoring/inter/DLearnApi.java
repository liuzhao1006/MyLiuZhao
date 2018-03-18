package com.sx.trans.transport.dynamicMonitoring.inter;

import com.sx.trans.transport.dynamicMonitoring.bean.LearnStudyBean;
import com.sx.trans.transport.dynamicMonitoring.bean.VideoListBean;

import java.util.List;

/**
 * 作者 : 刘朝,
 * on 2017/9/12,
 * GitHub : https://github.com/liuzhao1006
 */

public interface DLearnApi {
    void getListData(List<LearnStudyBean> learnStudyBeans);

    void getVideoData(List<VideoListBean> videoListBeans);
}
