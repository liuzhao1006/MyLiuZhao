package com.sx.trans.transport.manager;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.apkfuns.logutils.LogUtils;
import com.sx.trans.app.LoginConfig;
import com.sx.trans.base.BaseManager;
import com.sx.trans.base.BaseNetApi;
import com.sx.trans.company.bean.SafeAnnualBean;
import com.sx.trans.company.bean.SafeInsuranceBean;
import com.sx.trans.network.api.NetJsonApi;
import com.sx.trans.network.cache.PrefUtils;
import com.sx.trans.network.request.ResponseData;
import com.sx.trans.transport.dynamicMonitoring.bean.CQueryLeaderMailbox;
import com.sx.trans.transport.dynamicMonitoring.bean.QueryNoticeMessage;
import com.sx.trans.transport.dynamicMonitoring.bean.SendLeaderMailbox;
import com.sx.trans.transport.dynamicMonitoring.bean.SpecialLearnBean;
import com.sx.trans.transport.home.bean.AccidentBeans;
import com.sx.trans.transport.home.bean.AlamBean;
import com.sx.trans.transport.home.bean.AnnualPlanBean;
import com.sx.trans.transport.home.bean.MaintenanceBean;
import com.sx.trans.transport.home.bean.RemberBean;
import com.sx.trans.transport.home.bean.SafeLearnBean;
import com.sx.trans.transport.home.bean.YearPlanBean;
import com.sx.trans.transport.home.bean.YearPlanDetailBean;
import com.sx.trans.transport.me.bean.MeVehicleInfoBean;
import com.yanzhenjie.nohttp.rest.Request;

import java.util.List;


/**
 * 作者 : 刘朝,
 * on 2017/9/20,
 * GitHub : https://github.com/liuzhao1006
 */

public class EmployeesManager extends BaseManager implements NetJsonApi {

    private Context context;
    private BaseNetApi api;
    private StringBuffer sb;

    private static final int ACCIDENT = 0x0001;//事故
    private static final int YEARCARFUL = 0x0002;//年审
    private static final int MAINTENANCE = 0x0003;//维护
    private static final int REMINDING = 0x0004;//提醒
    private static final int ALAM = 0x1005;//报警
    private static final int ANNUALPLAN = 0x1006;//年度计划
    private static final int ANNUALPLANDETAIL = 0x10906;//年度计划详情
    private static final int LEARNINGOVER = 0x0005;//学习完成率
    private static final int MANAGER_INSURANCE = 0x2131;//保险
    private static final int STUDENT_CAR_ID = 0x2132;//保险
    private static final int SPECAILSTUDY = 0x2133;//专项学习
    private static final int QUERYLEADERMAILBOX = 0x2134;//查询领导信箱
    private static final int SENDLEADERMAILBOXS = 0x21351;//发送领导信箱

    private static final int QUERYNOTICE = 0x2137;//查询通知消息

    private ResponseData responseData;
    private int employeeId;//从业端用户id,数据库主键,
    private int vehicleId;
    private int groupId;//企业id
    private String company;
    private String ProfessionId;

    public EmployeesManager(Context context, BaseNetApi api) {
        this.context = context;
        this.api = api;
        sb = new StringBuffer();
        responseData = new ResponseData(context, this);
        employeeId = Integer.parseInt(PrefUtils.getString(context, LoginConfig.EMPLOYEE_ID, null));
        vehicleId = Integer.parseInt(PrefUtils.getString(context, LoginConfig.EMPLOYEE_CARDID, null));
        groupId = Integer.parseInt(PrefUtils.getString(context, LoginConfig.EMPLOYEE_GROUPID, null));
        company = PrefUtils.getString(context, LoginConfig.EMPLOYEE_COMPANY_LEARN, null);
        ProfessionId = PrefUtils.getString(context, LoginConfig.EMPLOYEE_PROFESSIONID_LEARN, null);
    }


    /**
     * 从业端 获取事故数据请求
     * <p>
     * http://192.168.101.75:8080/app/appEmpAccident?=
     *
     * @param pageNum  分页->第几页
     * @param pageSize 分页->每页固定20条数据
     */
    public void getAccident(int pageNum, int pageSize,String accidentName) {
        Request<String> request = doPost(context, "mployeesAccident");
        request.add("employeeId", employeeId)
                .add("accidentName",accidentName)
                .add("PageNum", pageNum)
                .add("PageSize", pageSize);
        request(ACCIDENT, request, responseData);
    }

    /**
     * 从业端 专项学习
     */
    public void getSpecialLearn(int PageNum, int PageSize) {
        Request<String> request = doGet(context, "employeeSpeciaStudy", "groupId=" + PrefUtils.getString(context, LoginConfig.EMPLOYEE_ID, null) + "&PageNum=" + PageNum + "&PageSize=" + PageSize);
        request(SPECAILSTUDY, request, responseData);
    }

    /**
     * 从业端 查询领导信箱
     */
    public void getLeaderMailbox(int PageNum, int PageSize) {
        Request<String> request = doGet(context, "queryLeaderMailbox", "employeeId=" + employeeId + "&PageNum=" + PageNum + "&PageSize=" + PageSize);
        request(QUERYLEADERMAILBOX, request, responseData);
    }

    /**
     * 从业端 发送领导信箱
     *
     * @param t1 标题
     * @param t2 联系电话
     * @param t3 内容
     */
    public void getSendLeadMailbox(String t1, String t2, String t3) {
        Request<String> request = doPost(context, "sendLeaderMailbox");
        request.add("userId", employeeId)
                .add("groupId", groupId)
                .add("title", t1)
                .add("telephone", t2)
                .add("content", t3);
        request(SENDLEADERMAILBOXS, request, responseData);
    }


    /**
     * 从业端 查询通知消息
     */
    public void getQueryNotice(int PageNum, int PageSize) {
        Request<String> request = doGet(context, "queryNotice", "groupId=" + groupId + "&PageNum=" + PageNum + "&PageSize=" + PageSize);


        request(QUERYNOTICE, request, responseData);
    }

    /**
     * 从业端 报警
     */
    public void getAlam() {
        Request<String> request = doPost(context, "mployeesAlam");
        request.add("employeeId", employeeId);
        request(ALAM, request, responseData);
    }

//    /**
//     * 从业端 年度计划
//     */
//    public void getAnnualPlan(int pageNum, int pageSize) {
//        Request<String> request = doPost(context, "mployeesAnnualPlan");
//        request.add("groupId", groupId)
//                .add("PageNum", pageNum).add("PageSize", pageSize);
//        request(ANNUALPLAN, request, responseData);
//    }

    /**
     * 从业端 年度计划
     */
    public void getAnnualPlan(String strYear) {
        sb.delete(0, sb.length());
        sb.append("company=")
                .append(company)
                .append("&strYear=")
                .append(strYear);

        LogUtils.i(sb.toString());
        Request<String> request = doGet(context, "mployeesAnnualPlan", sb.toString());

        request(ANNUALPLAN, request, responseData);
    }

    /**
     * 从业端 年度计划详情
     */
    public void getYearPlanDetail(String strYear, String professionId) {
        sb.delete(0, sb.length());
        sb.append("company=")
                .append(company)
                .append("&strYear=")
                .append(strYear)
                .append("&ProfessionId=")
                .append(professionId);
        Request<String> request = doGet(context, "mployeesAnnualPlanDetail", sb.toString());

        request(ANNUALPLANDETAIL, request, responseData);
    }

    /**
     * 从业端 获取年审数据请求
     */
    public void getYearTrial(int yearCheckType, String vehicleNo) {
        Request<String> request = doPost(context, "companyAnnual");
        request.add("employeeId", employeeId)
                .add("yearCheckType", yearCheckType)
                .add("vehicleNo", vehicleNo);
        request(YEARCARFUL, request, responseData);
    }

    /**
     * 从业端 获取维护数据请求
     */
    public void getMaintenance(int pageNum, int pageSize,String vehicleNo) {
        Request<String> request = doPost(context, "mployeesMaintain");
        request.add("employeeId", employeeId).add("vehicleNo",vehicleNo).add("PageNum", pageNum)
                .add("PageSize", pageSize);
        request(MAINTENANCE, request, responseData);
    }

    /**
     * 从业端 获取提醒数据请求
     */
    public void getRember(int pageNum, int pageSize, int interfaceType) {
        Request<String> request = doPost(context, "mployeesRemind");
        request.add("employeeId", employeeId)
                .add("PageNum", pageNum)
                .add("PageSize", pageSize).add("interfaceType", interfaceType);
        request(REMINDING, request, responseData);
    }

    /**
     * 从业端  学习完成率
     * 王猛  11:19:10
     * CompanyId = 5160;
     * ProfessionId = 2045;
     * StuId = 88;
     * <p>
     * "CompanyId=4146&ProfessionId=2042&StuId=19&StudyYear=2017";
     */
    public void getLearningOver() {
        //获取URL //加载配置的IP端口//加载参数//加载参数文件
        String CompanyId = PrefUtils.getString(context, LoginConfig.EMPLOYEE_COMPANY_LEARN, null);
        String ProfessionId = PrefUtils.getString(context, LoginConfig.EMPLOYEE_PROFESSIONID_LEARN, null);
        String StuId = PrefUtils.getString(context, LoginConfig.EMPLOYEE_STATUS_LEARN, null);
        Request<String> request = doPost(context, "employeeLearningOver");
        request.add("CompanyId", CompanyId)
                .add("ProfessionId", ProfessionId)
                .add("StuId", StuId);
        request(LEARNINGOVER, request, responseData);
    }

    /**
     * 从业端 我的 保险
     * groupid     企业人员ID
     * PageNum    页码
     * PageSize     个数
     */
    public void getEmployeeIdInsuranceList() {
        Request<String> request = doPost(context, "companyInsurance");
        request.add("employeeId", employeeId);
        request(MANAGER_INSURANCE, request, responseData);
    }

    /**
     * 从业端 我的  车辆信息
     * vehicleId = 13
     */
    public void getEmployeeIdVehicleList() {
        Request<String> request = doPost(context, "mployeesVehicle");
        request.add("vehicleId", vehicleId);
        request(STUDENT_CAR_ID, request, responseData);
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

        switch (what) {
            case ACCIDENT:
                accidentData.onAccidentData(what, JSON.parseArray(data, AccidentBeans.class));
                break;
            case YEARCARFUL:
                yearTrialData.onYearTrialData(what, JSON.parseArray(data, SafeAnnualBean.class));
                break;
            case MAINTENANCE:
                maintenanceData.onMaintenanceData(what, JSON.parseArray(data, MaintenanceBean.class));
                break;
            case REMINDING:
                remberData.onRemberData(what, JSON.parseArray(data, RemberBean.class));
                break;
            case ALAM:
                alamData.onAlamData(what, JSON.parseArray(data, AlamBean.class));
                break;
            case ANNUALPLAN:
                annualPlanData.onAnnualPlanData(what, JSON.parseArray(data, YearPlanBean.class));
                break;
            case LEARNINGOVER:
                learnData.onLearnData(what, JSON.parseArray(data, SafeLearnBean.class));
                break;
            case MANAGER_INSURANCE:
                insuranceData.onInsuranceData(what, JSON.parseArray(data, SafeInsuranceBean.class));
                break;
            case STUDENT_CAR_ID:
                carData.onCarData(what, JSON.parseArray(data, MeVehicleInfoBean.class));
                break;
            case SPECAILSTUDY:
                studydata.onStudyData(what, JSON.parseArray(data, SpecialLearnBean.class));
                break;
            case QUERYLEADERMAILBOX:
                quertyLeadMdata.onQueryData(what, JSON.parseArray(data, CQueryLeaderMailbox.DataBean.class));
                break;
            case SENDLEADERMAILBOXS:

                sendLeadMdata.onSendData(what, data);
                break;

            case QUERYNOTICE:
                queryNoticedata.onqueyNotice(what, JSON.parseArray(data, QueryNoticeMessage.DataBean.class));
                break;
            case ANNUALPLANDETAIL:
                yearPlanData.onYearPlanData(what, JSON.parseArray(data, YearPlanDetailBean.class));
                break;
        }
    }

    @Override
    public void netFailed(int what, String message) {
        api.netFailed(what, message);
    }

    /*==============================接口回调=================================*/

    /*===事故接口数据回调===*/
    public interface AccidentData {
        void onAccidentData(int what, List<AccidentBeans> accidentList);
    }

    private AccidentData accidentData;

    public void setOnAccidentData(AccidentData accidentData) {
        this.accidentData = accidentData;
    }
    /*===事故接口数据回调===*/


    /*===专项学习接口数据回调===*/
    public interface StudyData {
        void onStudyData(int what, List<SpecialLearnBean> vehicleLedgetList);
    }

    private StudyData studydata;

    public void setStudyData(StudyData studydata) {
        this.studydata = studydata;
    }
    /*===专项学习接口数据回调===*/

    /*===查询领导信箱接口数据回调===*/
    public interface QuertyLeadMdata {
        void onQueryData(int what, List<CQueryLeaderMailbox.DataBean> vehicleLedgetList);
    }

    private QuertyLeadMdata quertyLeadMdata;

    public void setQueryLeaderMData(QuertyLeadMdata quertyLeadMdata) {
        this.quertyLeadMdata = quertyLeadMdata;
    }
    /*===查询领导信箱接口数据回调===*/


    /*===发送领导信箱接口数据回调===*/
    public interface SendLeadMdata {
        void onSendData(int what, String vehicleLedgetList);
    }

    private SendLeadMdata sendLeadMdata;

    public void setSendMData(SendLeadMdata sendLeadMdata) {
        this.sendLeadMdata = sendLeadMdata;
    }
    /*===发送领导信箱接口数据回调===*/


    /*===查询通知消息接口数据回调===*/
    public interface QueryNoticedata {
        void onqueyNotice(int what, List<QueryNoticeMessage.DataBean> vehicleLedgetList);
    }

    private QueryNoticedata queryNoticedata;

    public void setqueyNotice(QueryNoticedata queryNoticedata) {
        this.queryNoticedata = queryNoticedata;
    }
    /*===查询通知消息接口数据回调===*/


    /*===年审接口数据回调===*/
    public interface YearTrialData {
        void onYearTrialData(int what, List<SafeAnnualBean> yearTrialList);
    }

    private YearTrialData yearTrialData;

    public void setOnYearTrialData(YearTrialData yearTrialData) {
        this.yearTrialData = yearTrialData;
    }
    /*===年审接口数据回调===*/

    /*===维护接口数据回调===*/
    public interface MaintenanceData {
        void onMaintenanceData(int what, List<MaintenanceBean> maintenanceList);
    }

    private MaintenanceData maintenanceData;

    public void setOnMaintenanceData(MaintenanceData maintenanceData) {
        this.maintenanceData = maintenanceData;
    }
    /*===维护接口数据回调===*/

    /*===提醒接口数据回调===*/
    public interface RemberData {
        void onRemberData(int what, List<RemberBean> stopBeanList);
    }

    private RemberData remberData;

    public void setOnRemberData(RemberData remberData) {
        this.remberData = remberData;
    }
    /*===提醒接口数据回调===*/

    /*===报警接口数据回调===*/
    public interface AlamData {
        void onAlamData(int what, List<AlamBean> alamList);
    }

    private AlamData alamData;

    public void setOnAlamData(AlamData alamData) {
        this.alamData = alamData;
    }
    /*===报警接口数据回调===*/

    /*===年度计划接口数据回调===*/
    public interface AnnualPlanData {
        void onAnnualPlanData(int what, List<YearPlanBean> annualPlanList);
    }

    private AnnualPlanData annualPlanData;

    public void setOnAnnualPlanData(AnnualPlanData annualPlanData) {
        this.annualPlanData = annualPlanData;
    }


    /*===年度计划接口数据回调===*/
      /*===年度计划详情接口数据回调===*/
    public interface YearPlanData {
        void onYearPlanData(int what, List<YearPlanDetailBean> annualPlanList);
    }

    private YearPlanData yearPlanData;

    public void setOnYearPlanData(YearPlanData yearPlanData) {
        this.yearPlanData = yearPlanData;
    }


    /*===年度计划详情接口数据回调===*/
    /*===学习完成率接口数据回调===*/
    public interface LearnData {
        void onLearnData(int what, List<SafeLearnBean> learnList);
    }

    private LearnData learnData;

    public void setOnLearnData(LearnData learnData) {
        this.learnData = learnData;
    }

    /*===学习完成率接口数据回调===*/
    /*===从业端保险接口数据回调===*/
    public interface InsuranceData {
        void onInsuranceData(int what, List<SafeInsuranceBean> learnList);
    }

    private InsuranceData insuranceData;

    public void setOnInsuranceData(InsuranceData insuranceData) {
        this.insuranceData = insuranceData;
    }

    /*===从业端保险接口数据回调===*/
    /*===从业端车辆信息接口数据回调===*/
    public interface CarData {
        void onCarData(int what, List<MeVehicleInfoBean> carList);
    }

    private CarData carData;

    public void setOnCarData(CarData carData) {
        this.carData = carData;
    }
    /*===从业端车辆信息接口数据回调===*/

}
