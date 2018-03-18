package com.sx.trans.transport.dynamicMonitoring.inter;

import com.sx.trans.transport.dynamicMonitoring.bean.LocalLawBean;

import java.util.List;

/**
 * 作者 : 刘朝,
 * on 2017/9/12,
 * GitHub : https://github.com/liuzhao1006
 */

public interface LawApi {
    void getListData(List<LocalLawBean> localLawBeans);
}
