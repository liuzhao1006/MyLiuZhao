package com.sx.trans.login.bean;

import com.sx.trans.base.BaseBean;

/**
 * 作者 : 刘朝,
 * on 2017/9/11,
 * GitHub : https://github.com/liuzhao1006
 * 企业端 登录数据
 */

public class LoginCompanyBean extends BaseBean {
    @Override
    public String toString() {
        return "LoginCompanyBean{" +
                "birthday=" + birthday +
                ", education=" + education +
                ", auth='" + auth + '\'' +
                ", user_name='" + user_name + '\'' +
                ", sys_user_name='" + sys_user_name + '\'' +
                ", id_card='" + id_card + '\'' +
                ", is_cancel=" + is_cancel +
                ", is_leader=" + is_leader +
                ", user_state=" + user_state +
                ", entry_date=" + entry_date +
                ", is_train=" + is_train +
                ", password='" + password + '\'' +
                ", valid_date=" + valid_date +
                ", user_type=" + user_type +
                ", areaName='" + areaName + '\'' +
                ", skill='" + skill + '\'' +
                ", leave_date=" + leave_date +
                ", train_date=" + train_date +
                ", tel='" + tel + '\'' +
                ", id=" + id +
                ", job_number='" + job_number + '\'' +
                ", unit_id=" + unit_id +
                ", email='" + email + '\'' +
                ", duty_type='" + duty_type + '\'' +
                ", address='" + address + '\'' +
                ", create_time=" + create_time +
                ", is_open=" + is_open +
                ", is_leave=" + is_leave +
                ", sex='" + sex + '\'' +
                ", contract='" + contract + '\'' +
                ", telephone='" + telephone + '\'' +
                ", userName='" + userName + '\'' +
                ", user_img='" + user_img + '\'' +
                ", is_delete=" + is_delete +
                ", imgUrl='" + imgUrl + '\'' +
                ", areaCode=" + areaCode +
                ", areaId=" + areaId +
                ", post_id=" + post_id +
                ", name='" + name + '\'' +
                ", roleName='" + roleName + '\'' +
                ", is_reappoint=" + is_reappoint +
                ", transport_year='" + transport_year + '\'' +
                ", dept_id='" + dept_id + '\'' +
                '}';
    }

    /**
     * birthday : 1505923200000
     * education : 4
     * auth : 115o573i3u01507727398211
     * user_name : shang
     * sys_user_name : 乐乐
     * id_card : 612422199509095678
     * is_cancel : 0
     * is_leader : 1
     * user_state : 0
     * entry_date : 1505923200000
     * is_train : 1
     * password : E10ADC3949BA59ABBE56E057F20F883E
     * valid_date : 1505923200000
     * user_type : 2
     * areaName : 西安华辰交通设施有限责任公司
     * skill : 无
     * leave_date : 1505923200000
     * train_date : 1505923200000
     * tel : 17691007890
     * id : 11
     * job_number : 009
     * unit_id : 5
     * email : 18700807747@163.com
     * duty_type : 无
     * address : 榆林定边
     * create_time : 1506164154000
     * is_open : 0
     * is_leave : 0
     * sex : 0
     * contract : 无
     * telephone : 17691007890
     * userName : 乐乐
     * user_img : http://news.mydrivers.com/Img/20110518/04481549.png
     * is_delete : false
     * imgUrl :
     * areaCode : 5
     * areaId : 5
     * post_id : 11
     * name : shang
     * roleName : 1111
     * is_reappoint : 0
     * transport_year : 2
     * dept_id : 10
     */

    private long birthday;
    private int education;
    private String auth;
    private String user_name;
    private String sys_user_name;
    private String id_card;
    private int is_cancel;
    private int is_leader;
    private int user_state;
    private long entry_date;
    private int is_train;
    private String password;
    private long valid_date;
    private int user_type;
    private String areaName;
    private String skill;
    private long leave_date;
    private long train_date;
    private String tel;
    private int id;
    private String job_number;
    private int unit_id;
    private String email;
    private String duty_type;
    private String address;
    private long create_time;
    private int is_open;
    private int is_leave;
    private String sex;
    private String contract;
    private String telephone;
    private String userName;
    private String user_img;
    private boolean is_delete;
    private String imgUrl;
    private int areaCode;
    private int areaId;
    private int post_id;
    private String name;
    private String roleName;
    private int is_reappoint;
    private String transport_year;
    private String dept_id;

    public long getBirthday() {
        return birthday;
    }

    public void setBirthday(long birthday) {
        this.birthday = birthday;
    }

    public int getEducation() {
        return education;
    }

    public void setEducation(int education) {
        this.education = education;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getSys_user_name() {
        return sys_user_name;
    }

    public void setSys_user_name(String sys_user_name) {
        this.sys_user_name = sys_user_name;
    }

    public String getId_card() {
        return id_card;
    }

    public void setId_card(String id_card) {
        this.id_card = id_card;
    }

    public int getIs_cancel() {
        return is_cancel;
    }

    public void setIs_cancel(int is_cancel) {
        this.is_cancel = is_cancel;
    }

    public int getIs_leader() {
        return is_leader;
    }

    public void setIs_leader(int is_leader) {
        this.is_leader = is_leader;
    }

    public int getUser_state() {
        return user_state;
    }

    public void setUser_state(int user_state) {
        this.user_state = user_state;
    }

    public long getEntry_date() {
        return entry_date;
    }

    public void setEntry_date(long entry_date) {
        this.entry_date = entry_date;
    }

    public int getIs_train() {
        return is_train;
    }

    public void setIs_train(int is_train) {
        this.is_train = is_train;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getValid_date() {
        return valid_date;
    }

    public void setValid_date(long valid_date) {
        this.valid_date = valid_date;
    }

    public int getUser_type() {
        return user_type;
    }

    public void setUser_type(int user_type) {
        this.user_type = user_type;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public long getLeave_date() {
        return leave_date;
    }

    public void setLeave_date(long leave_date) {
        this.leave_date = leave_date;
    }

    public long getTrain_date() {
        return train_date;
    }

    public void setTrain_date(long train_date) {
        this.train_date = train_date;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJob_number() {
        return job_number;
    }

    public void setJob_number(String job_number) {
        this.job_number = job_number;
    }

    public int getUnit_id() {
        return unit_id;
    }

    public void setUnit_id(int unit_id) {
        this.unit_id = unit_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDuty_type() {
        return duty_type;
    }

    public void setDuty_type(String duty_type) {
        this.duty_type = duty_type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(long create_time) {
        this.create_time = create_time;
    }

    public int getIs_open() {
        return is_open;
    }

    public void setIs_open(int is_open) {
        this.is_open = is_open;
    }

    public int getIs_leave() {
        return is_leave;
    }

    public void setIs_leave(int is_leave) {
        this.is_leave = is_leave;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUser_img() {
        return user_img;
    }

    public void setUser_img(String user_img) {
        this.user_img = user_img;
    }

    public boolean isIs_delete() {
        return is_delete;
    }

    public void setIs_delete(boolean is_delete) {
        this.is_delete = is_delete;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(int areaCode) {
        this.areaCode = areaCode;
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public int getIs_reappoint() {
        return is_reappoint;
    }

    public void setIs_reappoint(int is_reappoint) {
        this.is_reappoint = is_reappoint;
    }

    public String getTransport_year() {
        return transport_year;
    }

    public void setTransport_year(String transport_year) {
        this.transport_year = transport_year;
    }

    public String getDept_id() {
        return dept_id;
    }

    public void setDept_id(String dept_id) {
        this.dept_id = dept_id;
    }
}
