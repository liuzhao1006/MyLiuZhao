package com.sx.trans.transport.home.bean;

/**
 * <p>
 * 从业表
 */
public class AppEmployeeAccident {
    //事故报告
    private Integer id;

    /**
     * 事故名称
     */
    private String accidentname;
    /**
     * 事故分类
     */
    private Integer accidenttype;

    /**
     * 事故时间
     */
    private String accidentdate;

    /**
     * 事故地点
     */
    private String accidentaddress;
    /**
     * 天气情况
     */
    private String weathercondition;
    /**
     * 事发路段公路技术等级
     */
    private Integer highway_technical_grade;
    /**
     * 事发路段线性状况
     */
    private Integer section_linear_condition;
    /**
     * 事发路段路面状况
     */
    private Integer section_pavement_condition;
    /**
     * 事故直接原因
     */
    private Integer accidentreason;
    /**
     * 驾驶员id
     */
    private Integer driverid;
    /**
     * 事故责任
     */
    private Integer accidentliability;
    /**
     * 车辆id
     */
    private Integer carid;
    /**
     * 车牌号
     */
    private String carno;
    /**
     * 三者车牌
     */
    private String thirdcarno;
    /**
     * 报案人
     */
    private String informant;

    /**
     * 报案人电话
     */
    private String informanttel;
    /**
     * 事故概况
     */
    private String accidentsurvey;
    /**
     * 事故初步原因及责任分析
     */
    private String accidentreasonanalysis;
    /**
     * 伤亡与损失情况
     */
    private String casualtiesandlosses;

    //事故调查
    /**
     * 是否处理
     */
    private int ishandle;

    /**
     * 是否结案
     */
    private int isclosed;


    /**
     * 事故原因
     */
    private String accidentreson;


    /**
     * 处理日期
     */
    private String handledate;


    /**
     * 结案方式
     */
    private int closedtype;


    /**
     * 所属机构
     */
    private int groupid;
    /**
     * 参与方
     */
    private String participate;
    /**
     * 调查处理情况
     */
    private String investigationandhandling;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccidentname() {
        return accidentname;
    }

    public void setAccidentname(String accidentname) {
        this.accidentname = accidentname;
    }

    public Integer getAccidenttype() {
        return accidenttype;
    }

    public void setAccidenttype(Integer accidenttype) {
        this.accidenttype = accidenttype;
    }

    public String getAccidentdate() {
        return accidentdate;
    }

    public void setAccidentdate(String accidentdate) {
        this.accidentdate = accidentdate;
    }

    public String getAccidentaddress() {
        return accidentaddress;
    }

    public void setAccidentaddress(String accidentaddress) {
        this.accidentaddress = accidentaddress;
    }

    public String getWeathercondition() {
        return weathercondition;
    }

    public void setWeathercondition(String weathercondition) {
        this.weathercondition = weathercondition;
    }

    public Integer getHighway_technical_grade() {
        return highway_technical_grade;
    }

    public void setHighway_technical_grade(Integer highway_technical_grade) {
        this.highway_technical_grade = highway_technical_grade;
    }

    public Integer getSection_linear_condition() {
        return section_linear_condition;
    }

    public void setSection_linear_condition(Integer section_linear_condition) {
        this.section_linear_condition = section_linear_condition;
    }

    public Integer getSection_pavement_condition() {
        return section_pavement_condition;
    }

    public void setSection_pavement_condition(Integer section_pavement_condition) {
        this.section_pavement_condition = section_pavement_condition;
    }

    public Integer getAccidentreason() {
        return accidentreason;
    }

    public void setAccidentreason(Integer accidentreason) {
        this.accidentreason = accidentreason;
    }

    public Integer getDriverid() {
        return driverid;
    }

    public void setDriverid(Integer driverid) {
        this.driverid = driverid;
    }

    public Integer getAccidentliability() {
        return accidentliability;
    }

    public void setAccidentliability(Integer accidentliability) {
        this.accidentliability = accidentliability;
    }

    public Integer getCarid() {
        return carid;
    }

    public void setCarid(Integer carid) {
        this.carid = carid;
    }

    public String getCarno() {
        return carno;
    }

    public void setCarno(String carno) {
        this.carno = carno;
    }

    public String getThirdcarno() {
        return thirdcarno;
    }

    public void setThirdcarno(String thirdcarno) {
        this.thirdcarno = thirdcarno;
    }

    public String getInformant() {
        return informant;
    }

    public void setInformant(String informant) {
        this.informant = informant;
    }

    public String getInformanttel() {
        return informanttel;
    }

    public void setInformanttel(String informanttel) {
        this.informanttel = informanttel;
    }

    public String getAccidentsurvey() {
        return accidentsurvey;
    }

    public void setAccidentsurvey(String accidentsurvey) {
        this.accidentsurvey = accidentsurvey;
    }

    public String getAccidentreasonanalysis() {
        return accidentreasonanalysis;
    }

    public void setAccidentreasonanalysis(String accidentreasonanalysis) {
        this.accidentreasonanalysis = accidentreasonanalysis;
    }

    public String getCasualtiesandlosses() {
        return casualtiesandlosses;
    }

    public void setCasualtiesandlosses(String casualtiesandlosses) {
        this.casualtiesandlosses = casualtiesandlosses;
    }

    public int getIshandle() {
        return ishandle;
    }

    public void setIshandle(int ishandle) {
        this.ishandle = ishandle;
    }

    public int getIsclosed() {
        return isclosed;
    }

    public void setIsclosed(int isclosed) {
        this.isclosed = isclosed;
    }

    public String getAccidentreson() {
        return accidentreson;
    }

    public void setAccidentreson(String accidentreson) {
        this.accidentreson = accidentreson;
    }

    public String getHandledate() {
        return handledate;
    }

    public void setHandledate(String handledate) {
        this.handledate = handledate;
    }

    public int getClosedtype() {
        return closedtype;
    }

    public void setClosedtype(int closedtype) {
        this.closedtype = closedtype;
    }

    public int getGroupid() {
        return groupid;
    }

    public void setGroupid(int groupid) {
        this.groupid = groupid;
    }

    public String getParticipate() {
        return participate;
    }

    public void setParticipate(String participate) {
        this.participate = participate;
    }

    public String getInvestigationandhandling() {
        return investigationandhandling;
    }

    public void setInvestigationandhandling(String investigationandhandling) {
        this.investigationandhandling = investigationandhandling;
    }

    @Override
    public String toString() {
        return "AppEmployeeAccident{" +
                "id=" + id +
                ", accidentname='" + accidentname + '\'' +
                ", accidenttype=" + accidenttype +
                ", accidentdate='" + accidentdate + '\'' +
                ", accidentaddress='" + accidentaddress + '\'' +
                ", weathercondition='" + weathercondition + '\'' +
                ", highway_technical_grade=" + highway_technical_grade +
                ", section_linear_condition=" + section_linear_condition +
                ", section_pavement_condition=" + section_pavement_condition +
                ", accidentreason=" + accidentreason +
                ", driverid=" + driverid +
                ", accidentliability=" + accidentliability +
                ", carid=" + carid +
                ", carno='" + carno + '\'' +
                ", thirdcarno='" + thirdcarno + '\'' +
                ", informant='" + informant + '\'' +
                ", informanttel='" + informanttel + '\'' +
                ", accidentsurvey='" + accidentsurvey + '\'' +
                ", accidentreasonanalysis='" + accidentreasonanalysis + '\'' +
                ", casualtiesandlosses='" + casualtiesandlosses + '\'' +
                ", ishandle=" + ishandle +
                ", isclosed=" + isclosed +
                ", accidentreson='" + accidentreson + '\'' +
                ", handledate='" + handledate + '\'' +
                ", closedtype=" + closedtype +
                ", groupid=" + groupid +
                ", participate='" + participate + '\'' +
                ", investigationandhandling='" + investigationandhandling + '\'' +
                '}';
    }
}