package com.sx.trans.transport.dynamicMonitoring.manager;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.apkfuns.logutils.LogUtils;
import com.sx.trans.base.BaseManager;
import com.sx.trans.base.BaseNetApi;
import com.sx.trans.network.api.NetJsonApi;
import com.sx.trans.network.request.ResponseData;
import com.sx.trans.transport.dynamicMonitoring.bean.LearnStudyBean;
import com.sx.trans.transport.dynamicMonitoring.bean.VideoListBean;
import com.sx.trans.transport.dynamicMonitoring.inter.DLearnApi;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;

/**
 * 作者 : 刘朝,
 * on 2017/9/12,
 * GitHub : https://github.com/liuzhao1006
 */

public class DManager extends BaseManager implements NetJsonApi {

    private Context context;
    private BaseNetApi api;
    private DLearnApi dlApi;
    private int type;


    private static final int D_LEARN = 0x0010;//安全学习
    private static final int VIDEO_LIST = 0x0011;//视频列表
    private ResponseData data;


    public DManager(int type, Context context, DLearnApi dlApi, BaseNetApi api) {
        this.context = context;
        this.api = api;
        this.type = type;
        this.dlApi = dlApi;
        data = new ResponseData(context, this);

    }

    /**
     * 安全学习
     * 传值注释：
     * StudyYear                  学习年份：2017
     * CompanyId                所属公司：27
     * <p>
     * 返回值注释：
     * Id					学习计划编号
     * StudyMonth			学习月份
     * StudyPlanName 		学习计划名称
     * http://47.92.124.79:8094
     * /GetDataService.asmx/GetStudyPlan?CompanyId=4146&ProfessionId=2042&StuId=19&StudyYear=2017
     * http://47.92.124.79:8094/GetDataService.asmx/GetStudyPlan?CompanyId=4146&ProfessionId=2042&StuId=19&StudyYear=2017
     */
    public void getData(String CompanyId, String ProfessionId, String StuId, int StudyYear) {
        String url = getProperties(context, "studentLearning");
        StringBuffer sb = new StringBuffer();
        sb.append("CompanyId=")
                .append(CompanyId)
                .append("&ProfessionId=")
                .append(ProfessionId)
                .append("&StuId=")
                .append(StuId)
                .append("&StudyYear=")
                .append(StudyYear + "");
        Request<String> request = doGet(context, url, sb.toString());
        request(D_LEARN, request, data);
    }

    /**
     * 当月视频列表
     * <p>
     * http://47.92.124.79:8094/GetDataService.asmx/GetStudyMonthToCourseware?
     * <p>
     * StudyMonth=201709&CompanyId=4146&ProfessionId=2042&userId=19
     */
    public void getVideoListData(String studyMonth, String companyId, String industryId, String cardId) {
        //获取URL //加载配置的IP端口//加载参数//加载参数文件
        String url = getProperties(context, "studentVideoList");
        StringBuffer sb = new StringBuffer();
        sb.append(url).append("StudyMonth=")
                .append(studyMonth)
                .append("&CompanyId=")
                .append(companyId)
                .append("&ProfessionId=")
                .append(industryId)
                .append("&userId=")
                .append(cardId);
        Request<String> request = NoHttp.createStringRequest(sb.toString(), RequestMethod.GET);
        LogUtils.i(url);
        request(VIDEO_LIST, request, data);
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
        api.netSuccessed(what, "获取信息成功");
        detialData(data);
    }

    @Override
    public void netFailed(int what, String message) {
    }

    /**
     * 访问成功,获取数据跳转到详情页面
     *
     * @param data
     */
    private void detialData(String data) {
        if (type == D_LEARN) {
            LogUtils.i(data);
            dlApi.getListData(JSON.parseArray(data, LearnStudyBean.class));
        } else if (type == VIDEO_LIST) {
            LogUtils.i(data);
            dlApi.getVideoData(JSON.parseArray(data, VideoListBean.class));
        }
    }
}
