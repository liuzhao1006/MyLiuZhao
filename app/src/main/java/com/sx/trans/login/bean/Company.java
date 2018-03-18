package com.sx.trans.login.bean;

import com.sx.trans.base.BaseBean;

import java.util.List;

/**
 * Created by liuzhao on 2017/11/6.
 */

public class Company extends BaseBean {

    @Override
    public String toString() {
        return "Company{" +
                "data2=" + data2 +
                ", data1=" + data1 +
                '}';
    }

    private List<Data2Bean> data2;
    private List<Data1Bean> data1;

    public List<Data2Bean> getData2() {
        return data2;
    }

    public void setData2(List<Data2Bean> data2) {
        this.data2 = data2;
    }

    public List<Data1Bean> getData1() {
        return data1;
    }

    public void setData1(List<Data1Bean> data1) {
        this.data1 = data1;
    }

    public static class Data2Bean extends BaseBean {
        @Override
        public String toString() {
            return "Data2Bean{" +
                    "Account='" + Account + '\'' +
                    ", Company='" + Company + '\'' +
                    ", DeleteMark='" + DeleteMark + '\'' +
                    ", Createtime='" + Createtime + '\'' +
                    ", CreateUserID='" + CreateUserID + '\'' +
                    ", ID='" + ID + '\'' +
                    ", EmployeeID='" + EmployeeID + '\'' +
                    ", CreateUserName='" + CreateUserName + '\'' +
                    ", UserType='" + UserType + '\'' +
                    ", Name='" + Name + '\'' +
                    ", Password='" + Password + '\'' +
                    '}';
        }

        /**
         * Account : 18392012888
         * Company : 0
         * DeleteMark : False
         * Createtime : 2017/10/28 16:46:00
         * CreateUserID : 9312
         * ID : 9286
         * EmployeeID : 9312
         * CreateUserName : 用车同步
         * UserType : 166
         * Name : 协会监管
         * Password : E10ADC3949BA59ABBE56E057F20F883E
         */

        private String Account;
        private String Company;
        private String DeleteMark;
        private String Createtime;
        private String CreateUserID;
        private String ID;
        private String EmployeeID;
        private String CreateUserName;
        private String UserType;
        private String Name;
        private String Password;

        public String getAccount() {
            return Account;
        }

        public void setAccount(String Account) {
            this.Account = Account;
        }

        public String getCompany() {
            return Company;
        }

        public void setCompany(String Company) {
            this.Company = Company;
        }

        public String getDeleteMark() {
            return DeleteMark;
        }

        public void setDeleteMark(String DeleteMark) {
            this.DeleteMark = DeleteMark;
        }

        public String getCreatetime() {
            return Createtime;
        }

        public void setCreatetime(String Createtime) {
            this.Createtime = Createtime;
        }

        public String getCreateUserID() {
            return CreateUserID;
        }

        public void setCreateUserID(String CreateUserID) {
            this.CreateUserID = CreateUserID;
        }

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getEmployeeID() {
            return EmployeeID;
        }

        public void setEmployeeID(String EmployeeID) {
            this.EmployeeID = EmployeeID;
        }

        public String getCreateUserName() {
            return CreateUserName;
        }

        public void setCreateUserName(String CreateUserName) {
            this.CreateUserName = CreateUserName;
        }

        public String getUserType() {
            return UserType;
        }

        public void setUserType(String UserType) {
            this.UserType = UserType;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public String getPassword() {
            return Password;
        }

        public void setPassword(String Password) {
            this.Password = Password;
        }
    }

    public static class Data1Bean extends BaseBean {
        @Override
        public String toString() {
            return "Data1Bean{" +
                    "imgUrl='" + imgUrl + '\'' +
                    ", positionName='" + positionName + '\'' +
                    ", dpName='" + dpName + '\'' +
                    ", areaName='" + areaName + '\'' +
                    ", auth='" + auth + '\'' +
                    ", idCard='" + idCard + '\'' +
                    ", groupId=" + groupId +
                    ", name='" + name + '\'' +
                    ", roleName='" + roleName + '\'' +
                    ", tel='" + tel + '\'' +
                    ", id=" + id +
                    ", userName='" + userName + '\'' +
                    '}';
        }

        /**
         * imgUrl :
         * positionName : 运管员
         * dpName : 瓮安教管部
         * areaName : 瓮安县瓮水出租汽车有限公司
         * auth : 888g3a255k21509182522175
         * idCard : 610323198501039832
         * groupId : 84
         * name : 18392012888
         * roleName : 考核管理员
         * tel : 18392012888
         * id : 88
         * userName : 张华
         */

        private String imgUrl;
        private String positionName;
        private String dpName;
        private String areaName;
        private String auth;
        private String idCard;
        private int groupId;
        private String name;
        private String roleName;
        private String tel;
        private int id;
        private String userName;

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getPositionName() {
            return positionName;
        }

        public void setPositionName(String positionName) {
            this.positionName = positionName;
        }

        public String getDpName() {
            return dpName;
        }

        public void setDpName(String dpName) {
            this.dpName = dpName;
        }

        public String getAreaName() {
            return areaName;
        }

        public void setAreaName(String areaName) {
            this.areaName = areaName;
        }

        public String getAuth() {
            return auth;
        }

        public void setAuth(String auth) {
            this.auth = auth;
        }

        public String getIdCard() {
            return idCard;
        }

        public void setIdCard(String idCard) {
            this.idCard = idCard;
        }

        public int getGroupId() {
            return groupId;
        }

        public void setGroupId(int groupId) {
            this.groupId = groupId;
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

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
    }
}
