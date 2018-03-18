package com.sx.trans.company.manager;

import android.content.Context;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.apkfuns.logutils.LogUtils;
import com.sx.trans.app.LoginConfig;
import com.sx.trans.base.BaseManager;
import com.sx.trans.base.BaseNetApi;
import com.sx.trans.company.bean.CommpanyEmployeeInfoBean;
import com.sx.trans.company.bean.CommpanyEmployeeListBean;
import com.sx.trans.company.bean.CommpanyHidderDrangBean;
import com.sx.trans.company.bean.CommpanyRiskBean;
import com.sx.trans.company.bean.CommpanySafeExerciseBean;
import com.sx.trans.company.bean.CommpanyVehicItemBean;
import com.sx.trans.company.bean.CommpanyVehicleBean;
import com.sx.trans.company.bean.CompanyLegerBean;
import com.sx.trans.company.bean.RadarBean;
import com.sx.trans.company.bean.SafeAgreementBean;
import com.sx.trans.company.bean.SafeAnnualBean;
import com.sx.trans.company.bean.SafeInputBean;
import com.sx.trans.company.bean.SafeInsuranceBean;
import com.sx.trans.company.bean.SafeMeetingBean;
import com.sx.trans.company.bean.SafeRepotBean;
import com.sx.trans.company.bean.SafeRulesBean;
import com.sx.trans.company.bean.SafeRulesDetialBean;
import com.sx.trans.company.bean.SafeSpiecialCampaignBean;
import com.sx.trans.company.bean.SpecialTargerBean;
import com.sx.trans.network.api.NetJsonApi;
import com.sx.trans.network.cache.PrefUtils;
import com.sx.trans.network.request.ResponseData;
import com.sx.trans.safetyLearning.accounted.bean.AccountTotleBean;
import com.sx.trans.supervision.constants.Request.ALarmListBean;
import com.sx.trans.supervision.constants.Request.AllCompanyBean;
import com.sx.trans.supervision.constants.Request.CompanyAndMonthBean;
import com.sx.trans.supervision.constants.Request.CompanyListBean;
import com.sx.trans.supervision.constants.Request.HomeAlarBean;
import com.sx.trans.supervision.constants.Request.HomeDAteBean;
import com.sx.trans.transport.dynamicMonitoring.bean.HandleLeaderMailboxBean;
import com.sx.trans.transport.dynamicMonitoring.bean.QueryLeaderMailbox;
import com.sx.trans.transport.dynamicMonitoring.bean.QueryNoticeMessage;
import com.sx.trans.transport.dynamicMonitoring.bean.SpecialLearnBean;
import com.sx.trans.transport.home.bean.AccidentBeans;
import com.sx.trans.transport.home.bean.MaintenanceBean;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.rest.Request;

import java.util.List;

/**
 * 作者 : 刘朝,
 * on 2017/9/20,
 * GitHub : https://github.com/liuzhao1006
 * 企业端接口管理类
 */

public class CompanyManager extends BaseManager implements NetJsonApi {

    private Context context;
    private BaseNetApi api;
    private StringBuffer sb;
    private ResponseData responseData;

    private static final int COMPANY_LEDGET = 0x209;//企业台账数据
    private static final int VEHICLE_LEDGET = 0x210;//企业车辆台账数据
    private static final int VEHICLE_LEDGET_ITEM = 0x2101;//企业车辆台账数据
    private static final int PERSON_LEDGET = 0x211;//企业人员列表台账数据
    private static final int PERSON_LEDGET_ITEM = 0x2111;//企业人员台账数据
    /*======安全管理====*/
    private static final int SAFE_MANAGER_DRANG = 0x212;//隐患
    private static final int SAFE_MANAGER_INSURANCE = 0x213;//保险
    private static final int SAFE_MANAGER_MEETING = 0x214;//会议
    private static final int SAFE_MANAGER_ANNUAL = 0x215;//年审
    private static final int SAFE_MANAGER_MAINTAIN = 0x216;//维护
    private static final int SAFE_MANAGER_ACCIDENT = 0x217;//事故
    private static final int SAFE_MANAGER_RULES = 0x218;//规章制度
    private static final int SAFE_MANAGER_SAFE_INPUT = 0x219;//安全投入
    private static final int SAFE_RISK = 0x220;//安全风险指数
    private static final int SAFE_RISK_DISTRIBUTION = 0x221;//企业安全风险分布分析
    private static final int SAFE_EXERCISE = 0x222;//企业安全演习
    private static final int SAFE_AGREEMENT = 0x223;//企业安全责任书
    private static final int SAFE_SPECIALCAMPAIGN = 0x224;//专项活动
    private static final int COMPANYSTATISTICS = 0x020;//安全学习企业总人数接口
    private static final int SPECIALTARGER = 0x02100;//安全学习企业总人数接口
    private static final int SPECIALLEARN = 0x02888;//专项学习
    private static final int QUERYLEADERMAILBOX = 0x02889;//查询领导信箱
    private static final int QUERYNOTICE = 0x02890;//查询通知消息
    private static final int QUERYSAFEREPORT = 0x02891;//查询安全报告
    private static final int HANDLELEADERMAILBOX = 0x02892;//处理领导信箱
    private static final int SUPERVISIONALARM = 0x02893;//总监管报警
    private static final int QUERYCOMPANYALARMLIST = 0x02894;//查询各个公司的报警状况
    private static final int QUERYALLCOMPANY = 0x02895;//查询所有公司名称以及id
    private static final int QUERYDETAILBYCOMPANYMONTH = 0x02896;//查询每个公司每个月份的业户详情
    private static final int QUERYDEATAILALL = 0x02897;//查询所有车辆信息
    private static final int QUERYSUPVISEDATA = 0x02898;//获取监管首页Fragment的信息
    private int groupId;//企业编号
    private int EmployeeId;//从业人员编号,从业端地方法律法规和企业端规章制度同一个接口.

    public CompanyManager(Context context, BaseNetApi api) {
        this.context = context;
        this.api = api;
        sb = new StringBuffer();
        responseData = new ResponseData(context, this);
        groupId = Integer.parseInt(PrefUtils.getString(context, LoginConfig.COMPANY_GROUPID, null));
//        EmployeeId = Integer.parseInt(PrefUtils.getString(context, LoginConfig.COMPANY_EMPLOYEEID_LEARN, null));
        EmployeeId= Integer.parseInt(PrefUtils.getString(context, LoginConfig.EMPLOYEE_ID, null));
    }

    /**
     * 企业端 获取企业台账数据请求
     * <p><p>
     */
    public void getCompanyDetial() {
        Request<String> request = doPost(context, "companyLeger");
        request.add("groupId", groupId);
        request(COMPANY_LEDGET, request, responseData);
    }

    /**
     * 企业端 处理领导信箱
     *
     * @param id   信箱ID
     * @param idea 处理意见
     */
    public void getHandleLeadMailbox(int id, String idea) {
        Request<String> request = doPost(context, "handleLeaderMailbox");
        request.add("id", id);
        request.add("handleIdea", idea);

        request(HANDLELEADERMAILBOX, request, responseData);
    }

    /**
     * 企业端查询所有报警信息
     */
    public void getAlarmAll() {
        sb.delete(0, sb.length());
        sb.append(groupId);
        Request<String> request = doGet(context, "querySupervisionAlarm", sb.toString());

        request(SUPERVISIONALARM, request, responseData);
    }

    /**
     * 企业端根据公司查询所有报警信息
     */
    public void getAlarmlist() {
        sb.delete(0, sb.length());
        sb.append(groupId);
        Request<String> request = doGet(context, "QueryCompanyAlarmList", sb.toString());

        request(QUERYCOMPANYALARMLIST, request, responseData);
    }


    /**
     * 企业端查询所有车辆信息
     */
    public void getAllDetail() {
        sb.delete(0, sb.length());
        sb.append(groupId);
        Request<String> request = doGet(context, "QueryAllDetail", sb.toString());

        request(QUERYDEATAILALL, request, responseData);
    }

    /**
     * 企业端查询监管首页信息
     */
    public void getSuperviseData() {
        sb.delete(0, sb.length());
        sb.append(groupId);
        Request<String> request = doGet(context, "QuerySuperviseData", sb.toString());

        request(QUERYSUPVISEDATA, request, responseData);
    }

    /**
     * @param companyId 公司Id
     * @param yearMonth 月份
     */
    public void getDetailBycompany(String companyId, String yearMonth) {
        sb.delete(0, sb.length());
        sb.append(companyId)
                .append("?month=")
                .append(yearMonth);
        Request<String> request = doGet(context, "QueryDetailByCompanyMonth", sb.toString());

        request(QUERYDETAILBYCOMPANYMONTH, request, responseData);
    }

    /**
     * 企业端查询所有公司信息
     */
    public void getCompanylist() {
        sb.delete(0, sb.length());
        sb.append(groupId);
        Request<String> request = doGet(context, "QueryAllCompany", sb.toString());

        request(QUERYALLCOMPANY, request, responseData);
    }

    /**
     * 企业端 安全风险指数数据请求
     * <p>
     */
    public void getCompanyRisk(int riskType, String begintime, String endtime) {
        sb.delete(0, sb.length());
        sb.append("groupId=")
                .append(groupId)
                .append("&riskType=")
                .append(riskType)
                .append("&begintime=")
                .append(begintime)
                .append("&endtime=")
                .append(endtime);
        Request<String> request = doGet(context, "companyRisk", sb.toString());

        request(SAFE_RISK, request, responseData);
    }

    /**
     * 企业端 默认安全风险指数数据请求
     * <p>
     */
    public void getDefaltCompanyRisk(int riskType) {
        sb.delete(0, sb.length());
        sb.append("groupId=")
                .append(groupId)
                .append("&riskType=")
                .append(riskType);
        Request<String> request = doGet(context, "companyRisk", sb.toString());

        request(SAFE_RISK, request, responseData);
    }

    /**
     * 企业端 查询通知消息
     */
    public void getQueryNotice(int PageNum, int PageSize) {
        Request<String> request = doGet(context, "queryNotice", "groupId=" + groupId + "&PageNum=" + PageNum + "&PageSize=" + PageSize);


        request(QUERYNOTICE, request, responseData);
    }

    /**
     * @param PageNum
     * @param PageSize
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @param type      类型
     */
    public void getQuerySafeReport(int PageNum, int PageSize, String startDate, String endDate, int type) {
        Request<String> request = doGet(context, "querySecurityReport", "groupId=" + groupId + "&PageNum=" + PageNum + "&PageSize=" + PageSize + "&startDate=" + startDate + "&endDate=" + endDate + "&type=" + type);


        request(QUERYSAFEREPORT, request, responseData);
    }

    /**
     * 企业端 安全目标数据请求
     * <p>
     * PageNum    页码
     * PageSize   个数
     * pointName  目标名称
     */
    public void getSpecialTarger(int pageNum, int pageSize, String pointName) {
        Request<String> request = doPost(context, "companySpecialTarget");
        request.add("groupId", groupId)
                .add("PageNum", pageNum)
                .add("PageSize", pageSize)
                .add("pointName", pointName);
        request(SPECIALTARGER, request, responseData);

    }

    /**
     * 企业端 获取企业车辆台账列表数据请求
     * <p>
     * 查询全部数据
     * <p>
     * 查询企业全部：
     * groupid     企业人员ID
     * PageNum    页码
     * PageSize     个数
     * <p>
     * 搜索车牌号查询：
     * groupid     企业人员ID
     * PageNum    页码
     * PageSize     个数
     * vehicleNo		车牌号
     */
    public void getVehicleLedgetList(int pageNum, int pageSize, String vehicleNo) {
        Request<String> request = doPost(context, "companyVehicleLedge");
        request.add("groupId", groupId)
                .add("PageNum", pageNum)
                .add("PageSize", pageSize)
                .add("vehicleNo", vehicleNo);
        request(VEHICLE_LEDGET, request, responseData);
    }

    /**
     * 企业端 获取企业车辆台账数据请求
     * <p>
     * vehicleId		车牌号
     */
    public void getVehicleItemList(String vehicleId) {
        Request<String> request = doPost(context, "companyVehicleLedgeItem");
        request.add("vehicleId", vehicleId);
        request(VEHICLE_LEDGET_ITEM, request, responseData);
    }


    /**
     * 企业端 获取企业人员台账列表请求
     * <p>
     * groupid     企业人员ID
     * PageNum    页码
     * PageSize     个数
     * <p>
     * 搜索人员查询：
     * groupid     企业人员ID
     * PageNum    页码
     * PageSize     个数
     * userName		人员姓名
     */
    public void getPersonLedgetList(int pageNum, int pageSize, String userName) {

        Request<String> request = doPost(context, "companyPersonLedge");
        request.add("groupId", groupId)
                .add("PageNum", pageNum)
                .add("PageSize", pageSize)
                .add("userName", userName);
        request(PERSON_LEDGET, request, responseData);
    }

    /**
     * 企业端 获取企业人员台账请求
     * <p>
     * groupid     企业人员ID
     * PageNum    页码
     * PageSize     个数
     * <p>
     * 搜索人员查询：
     * groupid     企业人员ID
     * PageNum    页码
     * PageSize     个数
     * userName		人员姓名
     */
    public void getPersonLedgetItemList(String employeeId) {
        Request<String> request = doPost(context, "companyPersonLedgeItem");
        request.add("employeeId", employeeId);
        request(PERSON_LEDGET_ITEM, request, responseData);
    }


    /**
     * 隐患
     * groupid     企业人员ID
     * PageNum    页码
     * PageSize     个数
     */
    public void getSafeManagerDrangList(int pageNum, int pageSize, String planCompanyName) {
        Request<String> request = doPost(context, "companyHiddenDanger");
        request.add("groupId", groupId)
                .add("planCompanyName", planCompanyName)
                .add("PageNum", pageNum)
                .add("PageSize", pageSize);
        request(SAFE_MANAGER_DRANG, request, responseData);
    }

    /**
     * 保险
     * groupid     企业人员ID
     * PageNum    页码
     * PageSize     个数
     *
     * @param pageNum
     * @param pageSize
     */
    public void getSafeManagerInsuranceList(int pageNum, int pageSize, String vehicleNo) {
        Request<String> request = doPost(context, "companyInsurance");
        request.add("groupId", groupId)
                .add("vehicleNo", vehicleNo)
                .add("PageNum", pageNum)
                .add("PageSize", pageSize);
        request(SAFE_MANAGER_INSURANCE, request, responseData);
    }


    /**
     * 会议信息
     *
     * @param pageNum
     * @param pageSize
     * @param title
     */
    public void getSafeMeetingList(int pageNum, int pageSize, String title) {
        Request<String> request = doPost(context, "companyMeeting");
        request.add("groupId", groupId)
                .add("PageNum", pageNum)
                .add("PageSize", pageSize)
                .add("title", title);
        request(SAFE_MANAGER_MEETING, request, responseData);
    }

    /**
     * 年审
     *
     * @param pageNum       页数
     * @param pageSize      每页数量
     * @param yearCheckType 证件类型
     * @param vehicleNo     查询条件
     */
    public void getSafeAnnualList(int pageNum, int pageSize, int yearCheckType, String vehicleNo) {
        Request<String> request = doPost(context, "companyAnnual");
        request.add("groupId", groupId)
                .add("PageNum", pageNum)
                .add("PageSize", pageSize)
                .add("yearCheckType", yearCheckType)
                .add("vehicleNo", vehicleNo);
        request(SAFE_MANAGER_ANNUAL, request, responseData);
    }

    /**
     * 维护
     *
     * @param pageNum
     * @param pageSize
     */
    public void getSafeMaintainList(int pageNum, int pageSize, String vehicleNo) {
        Request<String> request = doPost(context, "companyMainta");
        request.add("groupId", groupId)
                .add("vehicleNo", vehicleNo)
                .add("PageNum", pageNum)
                .add("PageSize", pageSize);
        request(SAFE_MANAGER_MAINTAIN, request, responseData);
    }

    /**
     * 事故
     *
     * @param pageNum
     * @param pageSize
     */
    public void getSafeAccidentList(int pageNum, int pageSize, String accidentName) {
        Request<String> request = doPost(context, "companyAccident");
        request.add("groupId", groupId)
                .add("accidentName", accidentName)
                .add("PageNum", pageNum)
                .add("PageSize", pageSize);
        request(SAFE_MANAGER_ACCIDENT, request, responseData);
    }

    /**
     * 规章制度
     *
     * @param pageNum
     * @param pageSize
     */
    public void getSafeRulesList(int type, int pageNum, int pageSize, String institutionName) {
        Request<String> request = doPost(context, "companyRules");
        if (type == 1) {
            request.add("groupId", groupId);
        } else if (type == 2) {
            request.add("EmployeeId", EmployeeId);
        }
        request.add("institutionName", institutionName)
                .add("PageNum", pageNum)
                .add("PageSize", pageSize);
        request(SAFE_MANAGER_RULES, request, responseData);
    }

    /**
     * 安全投入
     *
     * @param pageNum
     * @param pageSize
     */
    public void getSafeInputList(int pageNum, int pageSize, String yearmonth) {
        Request<String> request = doPost(context, "companyInput");
        if (yearmonth.equals("") | yearmonth == null) {
            request.add("groupId", groupId)

                    .add("PageNum", pageNum)
                    .add("PageSize", pageSize);
        } else {

            if (yearmonth.contains("-")) {
                request.add("groupId", groupId)
                        .add("year", yearmonth.split("-")[0])
                        .add("month", yearmonth.split("-")[1])
                        .add("PageNum", pageNum)
                        .add("PageSize", pageSize);
            } else {
                request.add("groupId", groupId)
                        .add("year", yearmonth)
                        .add("month", "")
                        .add("PageNum", pageNum)
                        .add("PageSize", pageSize);
            }

        }

        request(SAFE_MANAGER_SAFE_INPUT, request, responseData);
    }

    /**
     * 安全风险分布分析
     * groupId=5&industryType=1&safeProdCompTestDate=2017-11-22
     */
    public void getRiskDistribution(int industryType, String safeProdCompTestDate) {
        sb.delete(0, sb.length());
        sb.append("groupId=")
                .append(groupId)
                .append("&industryType=")
                .append(industryType)
                .append("&safeProdCompTestDate=")
                .append(safeProdCompTestDate);
        Request<String> request = doGet(context, "companyRiskDistribution", sb.toString());
        request(SAFE_RISK_DISTRIBUTION, request, responseData);
    }

    /**
     * 安全学习 统计请求
     *
     * @return http://47.92.29.57:14806/GetDataService.asmx/GetCompanyProgress?CompanyId=1&strYearMonth=201709
     */
    public String getAccount(String date, String companyId) {
        sb.delete(0, sb.length());
        sb.append(getProperties(context, "companyStatistics"))
                .append("CompanyId=")
                .append(companyId)
                .append("&strYearMonth=")
                .append(date);
        LogUtils.i(sb.toString());
        if (TextUtils.isEmpty(companyId)) {
            return "可以请求数据";
        }
        Request<String> request = NoHttp.createStringRequest(sb.toString());
        request(COMPANYSTATISTICS, request, responseData);
        return "请求数据已发送";
    }

    /**
     * 安全演习
     */
    public void getExercise(int pageNum, int pageSize, String rehearsalName) {
        Request<String> request = doPost(context, "companyExercise");
        request.add("groupId", groupId)
                .add("rehearsalName", rehearsalName)
                .add("PageNum", pageNum)
                .add("PageSize", pageSize);
        request(SAFE_EXERCISE, request, responseData);
    }

    /**
     * 安全责任书
     */
    public void getAgreement() {
        Request<String> request = doPost(context, "companyAgreement");
        request.add("groupId", groupId);
        request(SAFE_AGREEMENT, request, responseData);
    }

    /**
     * 专项活动
     */
    public void getSpecialCampaign(int PageNum, int PageSize, String title) {
        Request<String> request = doPost(context, "companySpecialCampaign");
        request.add("groupId", groupId)
                .add("title", title)
                .add("PageNum", PageNum)
                .add("PageSize", PageSize);
        request(SAFE_SPECIALCAMPAIGN, request, responseData);
    }

    /**
     * 企业端 查询领导信箱
     */
    public void getLeaderMailbox(int PageNum, int PageSize) {
        Request<String> request = doGet(context, "queryLeaderMailbox", "groupId=" + groupId + "&PageNum=" + PageNum + "&PageSize=" + PageSize);
        request(QUERYLEADERMAILBOX, request, responseData);
    }


    /**
     * 企业端 专项血
     */

    public void getSpecialLearn(int PageNum, int PageSize) {
        Request<String> request = doGet(context, "employeeSpeciaStudy", "groupId=" + groupId + "&PageNum=" + PageNum + "&PageSize=" + PageSize);
        request(SPECIALLEARN, request, responseData);
    }

    @Override
    public void netStart() {
        api.netStart();
    }

    @Override
    public void netStop() {
        api.netStop();
    }

    @Override
    public void netSuccessed(int what, String data) {
        api.netSuccessed(what, data);//可以进行进一步拆分
        switch (what) {
            case COMPANY_LEDGET:
                safeCompanyLegerdata.onSafeCompanyLegerListData(what, JSON.parseArray(data, CompanyLegerBean.class));
                break;
            case VEHICLE_LEDGET_ITEM:
                safeCompanyVehicItemdata.onSafeCompanyVehicItemListData(what, JSON.parseArray(data, CommpanyVehicItemBean.class));
                break;
            case VEHICLE_LEDGET:
                vehicleLedgetdata.onVehicleLedgetListData(what, JSON.parseArray(data, CommpanyVehicleBean.class));
                break;
            case PERSON_LEDGET:
                personLedgetdata.onPersonLedgetListData(what, JSON.parseArray(data, CommpanyEmployeeListBean.class));
                break;
            case PERSON_LEDGET_ITEM:
                safeCompanyPersonItemdata.onSafeCompanyPersonItemListData(what, JSON.parseArray(data, CommpanyEmployeeInfoBean.class));
                break;
            case SAFE_MANAGER_DRANG:
                safeDrangdata.onSafeDrangListData(what, JSON.parseArray(data, CommpanyHidderDrangBean.class));
                break;
            case SAFE_MANAGER_INSURANCE:
                safeInsurancedata.onSafeInsuranceListData(what, JSON.parseArray(data, SafeInsuranceBean.class));
                break;
            case SAFE_MANAGER_MEETING:
                safeMeetingdata.onSafeMeetingListData(what, JSON.parseArray(data, SafeMeetingBean.class));
                break;
            case SAFE_MANAGER_ANNUAL:
                safeAnnualdata.onSafeAnnualListData(what, JSON.parseArray(data, SafeAnnualBean.class));
                break;
            case SAFE_MANAGER_MAINTAIN:
                safeMaintaindata.onSafeMaintainListData(what, JSON.parseArray(data, MaintenanceBean.class));
                break;
            case SAFE_MANAGER_ACCIDENT:
                safeAccidentdata.onSafeAccidentListData(what, JSON.parseArray(data, AccidentBeans.class));
                break;
            case SAFE_MANAGER_RULES:
                safeRulesdata.onSafeRulesListData(what, JSON.parseArray(data, SafeRulesDetialBean.class));
                break;
            case SAFE_MANAGER_SAFE_INPUT:
                safeInputdata.onSafeInputListData(what, JSON.parseArray(data, SafeInputBean.class));
                break;
            case SAFE_RISK:
                safeCompanyRiskdata.onSafeCompanyRiskData(what, JSON.parseArray(data, CommpanyRiskBean.class));
                break;
            case SAFE_RISK_DISTRIBUTION:
                safeCompanyRiskDistributiondata.onSafeCompanyRiskDistributionData(what, JSON.parseArray(data, RadarBean.class));
                break;
            case SAFE_EXERCISE:
                safeCompanyExercisedata.onSafeCompanyExerciseData(what, JSON.parseArray(data, CommpanySafeExerciseBean.class));
                break;
            case SAFE_AGREEMENT:
                safeAgreementdata.onSafeAgreementData(what, JSON.parseArray(data, SafeAgreementBean.class));
                break;
            case SAFE_SPECIALCAMPAIGN:
                safeSpecialCampaigndata.onSafeSpecialCampaignData(what, JSON.parseArray(data, SafeSpiecialCampaignBean.class));
                break;
            case COMPANYSTATISTICS://安全学习
                safeLearndata.onSafeLearnData(what, JSON.parseArray(data, AccountTotleBean.class));
                break;
            case SPECIALTARGER://安全目标
                specialTargerdata.onSpecialTargerData(what, JSON.parseArray(data, SpecialTargerBean.class));
                break;
            case SPECIALLEARN://专项学习
                learnData.onLearn(what, JSON.parseArray(data, SpecialLearnBean.class));
                break;
            case QUERYLEADERMAILBOX:
                quertyLeadMdata.onQueryData(what, JSON.parseArray(data, QueryLeaderMailbox.DataBean.class));
                break;
            case QUERYNOTICE:
                queryNoticedata.onqueyNotice(what, JSON.parseArray(data, QueryNoticeMessage.DataBean.class));
                break;
            case QUERYSAFEREPORT:
                querySafeRepotdata.onqueySafeReport(what, JSON.parseArray(data, SafeRepotBean.DataBean.class));
                break;
            case HANDLELEADERMAILBOX:

                handLeadMdata.onhandData(what, data);
                break;
            case SUPERVISIONALARM:

                QueryAllAlarm.onQueryAlarmData(what, JSON.parseArray(data, HomeAlarBean.class));
                break;
            case QUERYCOMPANYALARMLIST:

                queryAlarmList.onQueryAlarmDataList(what, JSON.parseArray(data, ALarmListBean.class));
                break;
            case QUERYALLCOMPANY:

                queryCompanyList.onQueryCompanyDataList(what, JSON.parseArray(data, CompanyListBean.class));
                break;
            case QUERYDETAILBYCOMPANYMONTH:

                queryDetaliByCompanyMonth.onQueryByCompanyMonth(what, JSON.parseArray(data, CompanyAndMonthBean.class));
                break;

            case QUERYDEATAILALL:

                queryDetaliAll.onQueryAll(what, JSON.parseArray(data, AllCompanyBean.class));
                break;
            case QUERYSUPVISEDATA:

                querySupervise.onQuerySupervise(what, JSON.parseArray(data, HomeDAteBean.class));
                break;
        }
    }

    @Override
    public void netFailed(int what, String message) {
        api.netFailed(what, message);
    }

    /*===========================================接口配置===================================================*/

    /*===企业车辆台账列表接口数据回调===*/
    public interface VehicleLedgetListData {
        void onVehicleLedgetListData(int what, List<CommpanyVehicleBean> vehicleLedgetList);
    }

    private VehicleLedgetListData vehicleLedgetdata;

    public void setOnVehicleLedgetListData(VehicleLedgetListData vehicleLedgetdata) {
        this.vehicleLedgetdata = vehicleLedgetdata;
    }
    /*===企业车辆台账列表接口数据回调===*/


    /*===处理领导信箱接口数据回调===*/
    public interface HandLeadMdata {
        void onhandData(int what, String vehicleLedgetList);
    }

    private HandLeadMdata handLeadMdata;

    public void sethandMData(HandLeadMdata handLeadMdata) {
        this.handLeadMdata = handLeadMdata;
    }
    /*===处理领导信箱接口数据回调===*/


    /*===查询监管报警总数数据接口数据回调===*/
    public interface QueryAllAlarm {
        void onQueryAlarmData(int what, List<HomeAlarBean> vehicleLedgetList);
    }

    private QueryAllAlarm QueryAllAlarm;

    public void setqueryallAlarm(QueryAllAlarm QueryAllAlarm) {
        this.QueryAllAlarm = QueryAllAlarm;
    }
    /*===查询监管报警总数数据接口数据回调===*/


    /*===查询各个公司的报警状况===*/
    public interface QueryAlarmList {
        void onQueryAlarmDataList(int what, List<ALarmListBean> vehicleLedgetList);
    }

    private QueryAlarmList queryAlarmList;

    public void setQueryAlarmList(QueryAlarmList queryAlarmList) {
        this.queryAlarmList = queryAlarmList;
    }
    /*===查询各个公司的报警状况===*/

    /*===查询所有公司的信息===*/
    public interface QueryCompanyList {
        void onQueryCompanyDataList(int what, List<CompanyListBean> vehicleLedgetList);
    }

    private QueryCompanyList queryCompanyList;

    public void setQueryCompanyList(QueryCompanyList queryCompanyList) {
        this.queryCompanyList = queryCompanyList;
    }
    /*===查询所有公司的信息===*/


    /*===通过公司ID和月份查询详细信息===*/
    public interface QueryDetaliByCompanyMonth {
        void onQueryByCompanyMonth(int what, List<CompanyAndMonthBean> vehicleLedgetList);
    }

    private QueryDetaliByCompanyMonth queryDetaliByCompanyMonth;

    public void setQueryByCompanyMonth(QueryDetaliByCompanyMonth queryDetaliByCompanyMonth) {
        this.queryDetaliByCompanyMonth = queryDetaliByCompanyMonth;
    }
    /*===通过公司ID和月份查询详细信息===*/


    /*===查询所有车辆信息===*/
    public interface QueryDetaliAll {
        void onQueryAll(int what, List<AllCompanyBean> vehicleLedgetList);
    }

    private QueryDetaliAll queryDetaliAll;

    public void setQueryDetaliall(QueryDetaliAll queryDetaliAll) {
        this.queryDetaliAll = queryDetaliAll;
    }
    /*===查询所有车辆信息===*/


    /*===查询监管首页信息===*/
    public interface QuerySupervise {
        void onQuerySupervise(int what, List<HomeDAteBean> vehicleLedgetList);
    }

    private QuerySupervise querySupervise;

    public void setSuperviseData(QuerySupervise querySupervise) {
        this.querySupervise = querySupervise;
    }
    /*===查询监管首页信息===*/

    /*===查询安全报告接口数据回调===*/
    public interface QuerySafeReportdata {
        void onqueySafeReport(int what, List<SafeRepotBean.DataBean> vehicleLedgetList);
    }

    private QuerySafeReportdata querySafeRepotdata;

    public void setqueySafeReport(QuerySafeReportdata querySafeRepotdata) {
        this.querySafeRepotdata = querySafeRepotdata;
    }
    /*===查询安全报告接口数据回调===*/


    /*===查询领导信箱接口数据回调===*/
    public interface QuertyLeadMdata {
        void onQueryData(int what, List<QueryLeaderMailbox.DataBean> vehicleLedgetList);
    }

    private QuertyLeadMdata quertyLeadMdata;

    public void setQueryLeaderMData(QuertyLeadMdata quertyLeadMdata) {
        this.quertyLeadMdata = quertyLeadMdata;
    }
    /*===查询领导信箱接口数据回调===*/

    /*===查询通知消息接口数据回调===*/
    public interface QueryNoticedata {
        void onqueyNotice(int what, List<QueryNoticeMessage.DataBean> vehicleLedgetList);
    }

    private QueryNoticedata queryNoticedata;

    public void setqueyNotice(QueryNoticedata queryNoticedata) {
        this.queryNoticedata = queryNoticedata;
    }
    /*===查询通知消息接口数据回调===*/


    /*===专项学习接口数据回调===*/
    public interface LearnData {
        void onLearn(int what, List<SpecialLearnBean> vehicleLedgetList);
    }

    private LearnData learnData;

    public void setOnLearnData(LearnData learnData) {
        this.learnData = learnData;
    }
    /*===专项学习接口数据回调===*/


    /*===企业人员台账列表接口数据回调===*/
    public interface PersonLedgetListData {
        void onPersonLedgetListData(int what, List<CommpanyEmployeeListBean> personLedgetList);
    }

    private PersonLedgetListData personLedgetdata;

    public void setOnPersonLedgetListData(PersonLedgetListData personLedgetdata) {
        this.personLedgetdata = personLedgetdata;
    }
    /*===企业人员台账列表接口数据回调===*/


    /*===安全管理 隐患数据接口回调===*/
    public interface SafeDrangListData {
        void onSafeDrangListData(int what, List<CommpanyHidderDrangBean> safeDrangList);
    }

    private SafeDrangListData safeDrangdata;

    public void setOnSafeDrangListData(SafeDrangListData safeDrangdata) {
        this.safeDrangdata = safeDrangdata;
    }
    /*===安全管理 隐患数据接口回调===*/

    /*===安全管理 保险数据接口回调===*/
    public interface SafeInsuranceListData {
        void onSafeInsuranceListData(int what, List<SafeInsuranceBean> safeInsuranceList);
    }

    private SafeInsuranceListData safeInsurancedata;

    public void setOnSafeInsuranceListData(SafeInsuranceListData safeInsurancedata) {
        this.safeInsurancedata = safeInsurancedata;
    }

    /*===安全管理 保险数据接口回调===*/
    /*===安全管理 会议数据接口回调===*/
    public interface SafeMeetingListData {
        void onSafeMeetingListData(int what, List<SafeMeetingBean> safeMeetingList);
    }

    private SafeMeetingListData safeMeetingdata;

    public void setOnSafeMeetingListData(SafeMeetingListData safeMeetingdata) {
        this.safeMeetingdata = safeMeetingdata;
    }
    /*===安全管理 会议数据接口回调===*/

    /*===安全管理 年审数据接口回调===*/
    public interface SafeAnnualListData {
        void onSafeAnnualListData(int what, List<SafeAnnualBean> safeAnnualList);
    }

    private SafeAnnualListData safeAnnualdata;

    public void setOnSafeAnnualListData(SafeAnnualListData safeAnnualdata) {
        this.safeAnnualdata = safeAnnualdata;
    }

    /*===安全管理 年审数据接口回调===*/
      /*===安全管理 维护数据接口回调===*/
    public interface SafeMaintainListData {
        void onSafeMaintainListData(int what, List<MaintenanceBean> safeMaintainList);
    }

    private SafeMaintainListData safeMaintaindata;

    public void setOnSafeMaintainListData(SafeMaintainListData safeMaintaindata) {
        this.safeMaintaindata = safeMaintaindata;
    }
    /*===安全管理 维护数据接口回调===*/


    /*===安全管理 事故数据接口回调===*/
    public interface SafeAccidentListData {
        void onSafeAccidentListData(int what, List<AccidentBeans> safeAccidentList);
    }

    private SafeAccidentListData safeAccidentdata;

    public void setOnSafeAccidentListData(SafeAccidentListData safeAccidentdata) {
        this.safeAccidentdata = safeAccidentdata;
    }
    /*===安全管理 事故数据接口回调===*/

    /*===安全管理 规章制度数据接口回调===*/
    public interface SafeRulesListData {
        void onSafeRulesListData(int what, List<SafeRulesDetialBean> safeRulesList);
    }

    private SafeRulesListData safeRulesdata;

    public void setOnSafeRulesListData(SafeRulesListData safeRulesdata) {
        this.safeRulesdata = safeRulesdata;
    }
    /*===安全管理 规章制度数据接口回调===*/

    /*===安全管理 规章制度数据接口回调===*/
    public interface SafeInputListData {
        void onSafeInputListData(int what, List<SafeInputBean> safeInputList);
    }

    private SafeInputListData safeInputdata;

    public void setOnSafeInputListData(SafeInputListData safeInputdata) {
        this.safeInputdata = safeInputdata;
    }
    /*===安全管理 规章制度数据接口回调===*/

    /*===企业端 首页 企业台账数据接口回调===*/
    public interface SafeCompanyLegerListData {
        void onSafeCompanyLegerListData(int what, List<CompanyLegerBean> safeCompanyLegerList);
    }

    private SafeCompanyLegerListData safeCompanyLegerdata;

    public void setOnSafeCompanyLegerListData(SafeCompanyLegerListData safeCompanyLegerdata) {
        this.safeCompanyLegerdata = safeCompanyLegerdata;
    }
    /*===安全管理 规章制度数据接口回调===*/

    /*===企业端 首页 企业台账数据接口回调===*/
    public interface SafeCompanyVehicItemListData {
        void onSafeCompanyVehicItemListData(int what, List<CommpanyVehicItemBean> safeCompanyVehicItemList);
    }

    private SafeCompanyVehicItemListData safeCompanyVehicItemdata;

    public void setOnSafeCompanyVehicItemListData(SafeCompanyVehicItemListData safeCompanyVehicItemdata) {
        this.safeCompanyVehicItemdata = safeCompanyVehicItemdata;
    }
    /*===安全管理 企业台账数据接口回调===*/

    /*===企业端 首页 企业人员台账数据接口回调===*/
    public interface SafeCompanyPersonItemListData {
        void onSafeCompanyPersonItemListData(int what, List<CommpanyEmployeeInfoBean> safeCompanyPersonItemList);
    }

    private SafeCompanyPersonItemListData safeCompanyPersonItemdata;

    public void setOnSafeCompanyPersonItemListData(SafeCompanyPersonItemListData safeCompanyPersonItemdata) {
        this.safeCompanyPersonItemdata = safeCompanyPersonItemdata;
    }
    /*===安全管理 企业人员台账数据接口回调===*/

    /*===企业端 首页 企业安全风险指数数据接口回调===*/
    public interface SafeCompanyRiskData {
        void onSafeCompanyRiskData(int what, List<CommpanyRiskBean> safeCompanyRiskList);
    }

    private SafeCompanyRiskData safeCompanyRiskdata;

    public void setOnSafeCompanyRiskData(SafeCompanyRiskData safeCompanyRiskdata) {
        this.safeCompanyRiskdata = safeCompanyRiskdata;
    }

    /*===安全管理 企业安全风险指数数据接口回调===*/
    /*===企业端 首页 企业安全风险指数数据接口回调===*/
    public interface SafeCompanyRiskDistributionData {
        void onSafeCompanyRiskDistributionData(int what, List<RadarBean> safeCompanyRiskDistributionList);
    }

    private SafeCompanyRiskDistributionData safeCompanyRiskDistributiondata;

    public void setOnSafeCompanyRiskDistributionData(SafeCompanyRiskDistributionData safeCompanyRiskDistributiondata) {
        this.safeCompanyRiskDistributiondata = safeCompanyRiskDistributiondata;
    }

    /*===安全管理 企业安全风险指数数据接口回调===*/
    /*===企业端 首页 企业安全演习数据接口回调===*/
    public interface SafeCompanyExerciseData {
        void onSafeCompanyExerciseData(int what, List<CommpanySafeExerciseBean> safeCompanyExerciseList);
    }

    private SafeCompanyExerciseData safeCompanyExercisedata;

    public void setOnSafeCompanyExerciseData(SafeCompanyExerciseData safeCompanyExercisedata) {
        this.safeCompanyExercisedata = safeCompanyExercisedata;
    }

    /*===安全管理 企业安全演习数据接口回调===*/
    /*===企业端 首页 企业安全责任书数据接口回调===*/
    public interface SafeAgreementData {
        void onSafeAgreementData(int what, List<SafeAgreementBean> safeAgreementList);
    }

    private SafeAgreementData safeAgreementdata;

    public void setOnSafeAgreementData(SafeAgreementData safeAgreementdata) {
        this.safeAgreementdata = safeAgreementdata;
    }

    /*===安全管理 企业安全责任书数据接口回调===*/
    /*===企业端 专项活动数据接口回调===*/
    public interface SafeSpecialCampaignData {
        void onSafeSpecialCampaignData(int what, List<SafeSpiecialCampaignBean> safeSpecialCampaignList);
    }

    private SafeSpecialCampaignData safeSpecialCampaigndata;

    public void setOnSafeSpecialCampaignData(SafeSpecialCampaignData safeSpecialCampaigndata) {
        this.safeSpecialCampaigndata = safeSpecialCampaigndata;
    }

    /*===安全管理 专项活动数据接口回调===*/
    /*===企业端 专项活动数据接口回调===*/
    public interface SafeLearnData {
        void onSafeLearnData(int what, List<AccountTotleBean> safeLearnList);
    }

    private SafeLearnData safeLearndata;

    public void setOnSafeLearnData(SafeLearnData safeLearndata) {
        this.safeLearndata = safeLearndata;
    }

    /*===安全管理 专项活动数据接口回调===*/
    /*===企业端 安全目标数据接口回调===*/
    public interface SpecialTargerData {
        void onSpecialTargerData(int what, List<SpecialTargerBean> specialTargerList);
    }

    private SpecialTargerData specialTargerdata;

    public void setOnSpecialTargerData(SpecialTargerData specialTargerdata) {
        this.specialTargerdata = specialTargerdata;
    }
    /*===安全管理 安全目标数据接口回调===*/
}
