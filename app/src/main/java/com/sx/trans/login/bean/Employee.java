package com.sx.trans.login.bean;

import com.sx.trans.base.BaseBean;

import java.util.List;

/**
 * Created by liuzhao on 2017/11/1.
 * 学员端登录实体类
 */

public class Employee extends BaseBean {

    @Override
    public String toString() {
        return "Employee{" +
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

    /**
     * 用车系统
     */
    public static class Data2Bean extends BaseBean {
        /**
         * IsUpload : 0
         * Status : 0
         * Company : 0
         * ValidityDate : 0
         * Address : 西安
         * IsPay : 1
         * ProfessionId : 0
         * ArchiveNo :
         * Sex : 男
         * DrivingType : 0
         * Birthday : 2017/10/26 0:00:00
         * CertificateNo : M81123
         * ProfessionName :
         * CompanyName :
         * StuName : 张信哲
         * Phone : 18392012233
         * IdCard : 610323199502019875
         * Id : 4965
         * PhotoImage :
         * Certificate : M81123
         */

        private String IsUpload;
        private String Status;
        private String Company;
        private String ValidityDate;
        private String Address;
        private String IsPay;
        private String ProfessionId;
        private String ArchiveNo;
        private String Sex;
        private String DrivingType;
        private String Birthday;
        private String CertificateNo;
        private String ProfessionName;
        private String CompanyName;
        private String StuName;
        private String Phone;
        private String IdCard;
        private String Id;
        private String PhotoImage;
        private String Certificate;

        @Override
        public String toString() {
            return "Data2Bean{" +
                    "IsUpload='" + IsUpload + '\'' +
                    ", Status='" + Status + '\'' +
                    ", Company='" + Company + '\'' +
                    ", ValidityDate='" + ValidityDate + '\'' +
                    ", Address='" + Address + '\'' +
                    ", IsPay='" + IsPay + '\'' +
                    ", ProfessionId='" + ProfessionId + '\'' +
                    ", ArchiveNo='" + ArchiveNo + '\'' +
                    ", Sex='" + Sex + '\'' +
                    ", DrivingType='" + DrivingType + '\'' +
                    ", Birthday='" + Birthday + '\'' +
                    ", CertificateNo='" + CertificateNo + '\'' +
                    ", ProfessionName='" + ProfessionName + '\'' +
                    ", CompanyName='" + CompanyName + '\'' +
                    ", StuName='" + StuName + '\'' +
                    ", Phone='" + Phone + '\'' +
                    ", IdCard='" + IdCard + '\'' +
                    ", Id='" + Id + '\'' +
                    ", PhotoImage='" + PhotoImage + '\'' +
                    ", Certificate='" + Certificate + '\'' +
                    '}';
        }

        public String getIsUpload() {
            return IsUpload;
        }

        public void setIsUpload(String IsUpload) {
            this.IsUpload = IsUpload;
        }

        public String getStatus() {
            return Status;
        }

        public void setStatus(String Status) {
            this.Status = Status;
        }

        public String getCompany() {
            return Company;
        }

        public void setCompany(String Company) {
            this.Company = Company;
        }

        public String getValidityDate() {
            return ValidityDate;
        }

        public void setValidityDate(String ValidityDate) {
            this.ValidityDate = ValidityDate;
        }

        public String getAddress() {
            return Address;
        }

        public void setAddress(String Address) {
            this.Address = Address;
        }

        public String getIsPay() {
            return IsPay;
        }

        public void setIsPay(String IsPay) {
            this.IsPay = IsPay;
        }

        public String getProfessionId() {
            return ProfessionId;
        }

        public void setProfessionId(String ProfessionId) {
            this.ProfessionId = ProfessionId;
        }

        public String getArchiveNo() {
            return ArchiveNo;
        }

        public void setArchiveNo(String ArchiveNo) {
            this.ArchiveNo = ArchiveNo;
        }

        public String getSex() {
            return Sex;
        }

        public void setSex(String Sex) {
            this.Sex = Sex;
        }

        public String getDrivingType() {
            return DrivingType;
        }

        public void setDrivingType(String DrivingType) {
            this.DrivingType = DrivingType;
        }

        public String getBirthday() {
            return Birthday;
        }

        public void setBirthday(String Birthday) {
            this.Birthday = Birthday;
        }

        public String getCertificateNo() {
            return CertificateNo;
        }

        public void setCertificateNo(String CertificateNo) {
            this.CertificateNo = CertificateNo;
        }

        public String getProfessionName() {
            return ProfessionName;
        }

        public void setProfessionName(String ProfessionName) {
            this.ProfessionName = ProfessionName;
        }

        public String getCompanyName() {
            return CompanyName;
        }

        public void setCompanyName(String CompanyName) {
            this.CompanyName = CompanyName;
        }

        public String getStuName() {
            return StuName;
        }

        public void setStuName(String StuName) {
            this.StuName = StuName;
        }

        public String getPhone() {
            return Phone;
        }

        public void setPhone(String Phone) {
            this.Phone = Phone;
        }

        public String getIdCard() {
            return IdCard;
        }

        public void setIdCard(String IdCard) {
            this.IdCard = IdCard;
        }

        public String getId() {
            return Id;
        }

        public void setId(String Id) {
            this.Id = Id;
        }

        public String getPhotoImage() {
            return PhotoImage;
        }

        public void setPhotoImage(String PhotoImage) {
            this.PhotoImage = PhotoImage;
        }

        public String getCertificate() {
            return Certificate;
        }

        public void setCertificate(String Certificate) {
            this.Certificate = Certificate;
        }
    }

    /**
     * 安全学习
     */
    public static class Data1Bean extends BaseBean {
        /**
         * carId : 2
         * departmentId : 0
         * driverType : 0
         * driverYear : 0
         * groupId : 1
         * id : 61
         * phone : 18392012233
         * photo :
         * registrationNo : 贵JT0107
         * sex : 0
         * unitId : 0
         * userName : 张信哲
         */

        private String carId;
        private int departmentId;
        private int driverType;
        private int driverYear;
        private String groupId;
        private String id;
        private String phone;
        private String photo;
        private String registrationNo;
        private int sex;
        private int unitId;
        private String userName;

        @Override
        public String toString() {
            return "Data1Bean{" +
                    "carId='" + carId + '\'' +
                    ", departmentId=" + departmentId +
                    ", driverType=" + driverType +
                    ", driverYear=" + driverYear +
                    ", groupId='" + groupId + '\'' +
                    ", id='" + id + '\'' +
                    ", phone='" + phone + '\'' +
                    ", photo='" + photo + '\'' +
                    ", registrationNo='" + registrationNo + '\'' +
                    ", sex=" + sex +
                    ", unitId=" + unitId +
                    ", userName='" + userName + '\'' +
                    '}';
        }

        public String getCarId() {
            return carId;
        }

        public void setCarId(String carId) {
            this.carId = carId;
        }

        public int getDepartmentId() {
            return departmentId;
        }

        public void setDepartmentId(int departmentId) {
            this.departmentId = departmentId;
        }

        public int getDriverType() {
            return driverType;
        }

        public void setDriverType(int driverType) {
            this.driverType = driverType;
        }

        public int getDriverYear() {
            return driverYear;
        }

        public void setDriverYear(int driverYear) {
            this.driverYear = driverYear;
        }

        public String getGroupId() {
            return groupId;
        }

        public void setGroupId(String groupId) {
            this.groupId = groupId;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getRegistrationNo() {
            return registrationNo;
        }

        public void setRegistrationNo(String registrationNo) {
            this.registrationNo = registrationNo;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public int getUnitId() {
            return unitId;
        }

        public void setUnitId(int unitId) {
            this.unitId = unitId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
    }
}
