package com.sx.trans.base;

import com.sx.trans.transport.home.bean.AccidentBean;
import com.sx.trans.transport.home.bean.MaintenanceBean;
import com.sx.trans.transport.home.bean.RemberBean;
import com.sx.trans.transport.home.bean.StopBean;
import com.sx.trans.transport.home.bean.YearCarfulBean;

import java.util.List;

/**
 * 作者 : 刘朝,
 * on 2017/9/12,
 * GitHub : https://github.com/liuzhao1006
 */

public interface ArrayListApi {
    void getListData(List<AccidentBean> accidentBean);

    void getMData(List<MaintenanceBean> accidentBean);

    void getCListData(List<YearCarfulBean> accidentBean);

    void getRember(List<RemberBean> remberBeans);//提醒
}
