package com.sx.trans.safetyLearning.data.bean;

import com.sx.trans.base.BaseBean;

import java.io.Serializable;
import java.util.List;

/**
 * 作者 : 刘朝,
 * on 2017/9/8,
 * GitHub : https://github.com/liuzhao1006
 */

public class DataFragmentBean extends BaseBean {


    public List<ReturnDateBean> returnDate;


    public static class ReturnDateBean implements Serializable {


        /**
         * CompanyName : 陕西测试单位
         * stuNum : 4
         * Finish : 4
         * NoFinish : 0
         * hoursRatio : 0.12
         * FinishRatio : 1.00
         * ranking : 1
         */

        public String CompanyName, BasicsHours;
        public String stuNum;
        public String Finish;
        public String NoFinish;
        public String hoursRatio;
        public String FinishRatio;
        public String ranking;
        public String CompanyId;


    }
}
