package com.sx.trans.transport.home.enums;

/**
 * 作者 : 刘朝,
 * on 2017/9/21,
 * GitHub : https://github.com/liuzhao1006
 */

public enum EmploymentTypeEnum {

    /**
     * 车牌号 vehicleNo、车牌颜色 plateColor、车辆类别 brand
     * <p>
     * employmentType		从业类型
     * 备注：1001  客运驾驶员
     * 2001	货运驾驶员
     * 3001	危运驾驶员
     * 9001	出租驾驶员
     * 13001	公交驾驶员
     * 14001 轨道驾驶员
     *
     * @param holder   视图管理者
     * @param data     数据源
     * @param position
     */

    EMPLOYMENT_ONE(1001, "客运驾驶员"),
    EMPLOYMENT_THRESS(3001, "危运驾驶员"),
    EMPLOYMENT_NICE(9001, "出租驾驶员"),
    EMPLOYMENT_THIRTEEN(13001, "公交驾驶员"),
    EMPLOYMENT_FOURTEEN(14001, "轨道驾驶员"),
    EMPLOYMENT_TWENTY(2001, "货运驾驶员"),;
    private Integer code;
    private String msg;

    EmploymentTypeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
