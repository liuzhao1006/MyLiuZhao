package com.sx.trans.company.api;

import com.sx.trans.company.bean.CommpanyAccidentBean;
import com.sx.trans.company.bean.CommpanyAppEmpVehicleDefindBean;
import com.sx.trans.company.bean.CommpanyEmergencyBean;
import com.sx.trans.company.bean.CommpanyMeetManageInfoBean;
import com.sx.trans.company.bean.CommpanyPoliciesAndObjectivesBean;
import com.sx.trans.company.bean.CommpanySafeInvestmentBean;
import com.sx.trans.company.bean.CommpanySecurityAdministrationBean;
import com.sx.trans.company.bean.CommpanySpecialactivitiesBean;
import com.sx.trans.company.bean.CommpanyVehicleBean;

import java.util.List;

/**
 * Created by Administrator on 2017/9/13.
 */

public interface CommpanyMeetManageInfoApi {
    void getListData(List<CommpanyMeetManageInfoBean> localLawBeans);//会议
    void getPoliciesAndObjectivesListData(List<CommpanyPoliciesAndObjectivesBean> PoliciesAndObjectivesBeans);//安全目标
    void getSecurityAdministrationData(List<CommpanySecurityAdministrationBean> SecurityAdministrationBeans);//制度规范
    void getSafeInvestmenData(List<CommpanySafeInvestmentBean> safeInvestmentBeans);//安全投入
    void getEmergencyData(List<CommpanyEmergencyBean> emergencyBeans);//安全演练
    void getCommpanyAccidentData(List<CommpanyAccidentBean> AccidentBeans);//事故
    void getAppEmpVehicleDefindData(List<CommpanyAppEmpVehicleDefindBean> AppEmpVehicleDefindBeans);//年审

    void getSpecialactivitiesData(List<CommpanySpecialactivitiesBean> SpecialactivitiesBeans);//年审


}