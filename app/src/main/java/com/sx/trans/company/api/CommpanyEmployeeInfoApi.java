package com.sx.trans.company.api;

import com.sx.trans.company.bean.CommpanyEmployeeInfoBean;
import com.sx.trans.company.bean.CommpanyVehicleBean;

import java.util.List;

/**
 * Created by Administrator on 2017/9/13.
 */

public interface CommpanyEmployeeInfoApi {
    void getListData(List<CommpanyEmployeeInfoBean> localLawBeans);
}