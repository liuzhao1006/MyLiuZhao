package com.sx.trans.app;

/**
 * 作者 : 刘朝,
 * on 2017/9/8,
 * GitHub : https://github.com/liuzhao1006
 */

public class LoginConfig {
    public static final  String LOGINTYPE = "Logintype";//此次登陆的类型判断是从业还是企业端
    public static final String DEVICE_TOKEN = "deviceToken";//友盟单播发送标记
    public static final String USERTYPE = "LoginUserType";
    public static final String COMPANY = "Company";
    public static final String NAME = "Name";
    public static final String USER_NAME = "LoginuserName";
    public static final String PASS_WORD = "LoginpassWord";
    public static final String USER_COMPANY_NAME = "LoginuserCompanyName";
    public static final String PASS_COMPANY_WORD = "LoginpassCompanyWord";


    /**
     * 从业端 用车从业端返回的接口
     * "carId": "2",
     * "departmentId": 0,
     * "driverType": 0,
     * "driverYear": 0,
     * "groupId": "1",
     * "id": "61",
     * "phone": "18392012233",
     * "photo": "",
     * "registrationNo": "贵JT0107",
     * "sex": 0,
     * "unitId": 0,
     * "userName": "张信哲"
     */
    public static final String EMPLOYEE_CARDID = "carId";
    public static final String EMPLOYEE_DEPARTMENTID = "departmentId";
    public static final String EMPLOYEE_DRIVERTYPE = "driverType";
    public static final String EMPLOYEE_DRIVERYEAR = "driverYear";
    public static final String EMPLOYEE_GROUPID = "groupId";
    public static final String EMPLOYEE_ID = "id";
    public static final String EMPLOYEE_PHONE = "phone";
    public static final String EMPLOYEE_PHOTO = "photo";
    public static final String EMPLOYEE_REGISTARTIONNO = "registrationNo";
    public static final String EMPLOYEE_SEX = "sex";
    public static final String EMPLOYEE_UNITID = "unitId";
    public static final String EMPLOYEE_USERNAME = "userName";

    /**
     * 从业端 安全学习登录接口返回的数据
     * "IsUpload": "0",
     * "Status": "0",
     * "Company": "0",
     * "ValidityDate": "0",
     * "Address": "西安",
     * "IsPay": "1",
     * "ProfessionId": "0",
     * "ArchiveNo": "",
     * "Sex": "男",
     * "DrivingType": "0",
     * "Birthday": "2017/10/26 0:00:00",
     * "CertificateNo": "M81123",
     * "ProfessionName": "",
     * "CompanyName": "",
     * "StuName": "张信哲",
     * "Phone": "18392012233",
     * "IdCard": "610323199502019875",
     * "Id": "4965",
     * "PhotoImage": "",
     * "Certificate": "M81123"
     */
    public static final String EMPLOYEE_ISUPLOAD_LEARN = "IsUpload";
    public static final String EMPLOYEE_STATUS_LEARN = "Status";
    public static final String EMPLOYEE_COMPANY_LEARN = "Company";
    public static final String EMPLOYEE_VALIDITYDATE_LEARN = "ValidityDate";
    public static final String EMPLOYEE_ADDRESS_LEARN = "Address";
    public static final String EMPLOYEE_ISPAY_LEARN = "IsPay";
    public static final String EMPLOYEE_PROFESSIONID_LEARN = "ProfessionId";
    public static final String EMPLOYEE_ARCHIVENO_LEARN = "ArchiveNo";
    public static final String EMPLOYEE_SEX_LEARN = "Sex";
    public static final String EMPLOYEE_DRIVINGTYPE_LEARN = "DrivingType";
    public static final String EMPLOYEE_BRITHDAY_LEARN = "Birthday";
    public static final String EMPLOYEE_CRETIFICATENO_LEARN = "CertificateNo";
    public static final String EMPLOYEE_PROFESSIONNAME_LEARN = "ProfessionName";
    public static final String EMPLOYEE_COMPANYNAME_LEARN = "CompanyName";
    public static final String EMPLOYEE_STUNAME_LEARN = "StuName";
    public static final String EMPLOYEE_PHONE_LEARN = "Phone";
    public static final String EMPLOYEE_ID_LEARN = "Id";
    public static final String EMPLOYEE_PHOTOIMAGE_LEARN = "PhotoImage";
    public static final String EMPLOYEE_CRETIFICATE_LEARN = "Certificate";


    /**
     * 企业端 登录接口返回的数据
     * "imgUrl": "",
     * "positionName": "运管员",
     * "dpName": "瓮安教管部",
     * "areaName": "瓮安县瓮水出租汽车有限公司",
     * "auth": "888g3a255k21509182522175",
     * "idCard": "610323198501039832",
     * "groupId": 84,
     * "name": "18392012888",
     * "roleName": "考核管理员",
     * "tel": "18392012888",
     * "id": 88,
     * "userName": "张华"
     */
    public static final String COMPANY_IMGURL = "imgUrl";
    public static final String COMPANY_POSITIONNAME = "positionName";
    public static final String COMPANY_DPNAME = "dpName";
    public static final String COMPANY_AREANAME = "areaName";
    public static final String COMPANY_AUTH = "auth";
    public static final String COMPANY_IDCARD = "idCard";
    public static final String COMPANY_GROUPID = "groupId";
    public static final String COMPANY_NAME = "name";
    public static final String COMPANY_ROLENAME = "roleName";
    public static final String COMPANY_TEL = "tel";
    public static final String COMPANY_ID = "id";
    public static final String COMPANY_USERNAME = "userName";


    /**
     * 企业端 安全学习接口数据
     * "Account": "18392012888",
     * "Company": "0",
     * "DeleteMark": "False",
     * "Createtime": "2017/10/28 16:46:00",
     * "CreateUserID": "9312",
     * "ID": "9286",
     * "EmployeeID": "9312",
     * "CreateUserName": "用车同步",
     * "UserType": "166",
     * "Name": "协会监管",
     * "Password": "E10ADC3949BA59ABBE56E057F20F883E"
     */
    public static final String COMPANY_ACCOUNT_LEARN = "Account";
    public static final String COMPANY_COMPANY_LEARN = "Company";
    public static final String COMPANY_DELETETIME_LEARN = "DeleteMark";
    public static final String COMPANY_CREATETIME_LEARN = "Createtime";
    public static final String COMPANY_CREATEUSERID_LEARN = "CreateUserID";
    public static final String COMPANY_ID_LEARN = "ID";
    public static final String COMPANY_EMPLOYEEID_LEARN = "EmployeeID";
    public static final String COMPANY_CREATEUSERNAME_LEARN = "CreateUserName";
    public static final String COMPANY_NAME_LEARN = "Name";
    public static final String COMPANY_PASSWORD_LEARN = "Password";



}
