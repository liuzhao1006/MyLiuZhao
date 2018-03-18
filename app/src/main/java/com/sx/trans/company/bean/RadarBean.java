package com.sx.trans.company.bean;

import com.sx.trans.base.BaseBean;

import java.util.List;

/**
 * Created by xxxxxx on 2017/11/22.
 */

public class RadarBean extends BaseBean {


    @Override
    public String toString() {
        return "RadarBean{" +
                "seriesData=" + seriesData +
                ", companyName='" + companyName + '\'' +
                ", nameData=" + nameData +
                ", indicatorData=" + indicatorData +
                '}';
    }

    /**
     * seriesData : {"实际分数":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],"标准分数":[100,35,50,85,35,25,70,40,160,90,100,85,50,70,45,40,35]}
     * companyName : 长顺县兴隆汽运有限责任公司
     * nameData : ["实际分数","标准分数"]
     * indicatorData : [{"max":100,"name":"联网联控"},{"max":35,"name":"绩效考评与持续改进"},{"max":50,"name":"事故报告调查处理"},{"max":85,"name":"应急救援"},{"max":35,"name":"安全文化"},{"max":25,"name":"职业健康"},{"max":70,"name":"隐患排查与治理"},{"max":40,"name":"危险源辩识与风险控制"},{"max":160,"name":"作业管理"},{"max":90,"name":"队伍建设"},{"max":100,"name":"科技创新与信息化"},{"max":85,"name":"装备设施"},{"max":50,"name":"安全投入"},{"max":70,"name":"法规和安全管理制度"},{"max":45,"name":"安全责任体系"},{"max":40,"name":"管理机构和人员"},{"max":35,"name":"安全目标"}]
     */

    private SeriesDataBean seriesData;
    private String companyName;
    private List<String> nameData;
    private List<IndicatorDataBean> indicatorData;

    public SeriesDataBean getSeriesData() {
        return seriesData;
    }

    public void setSeriesData(SeriesDataBean seriesData) {
        this.seriesData = seriesData;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public List<String> getNameData() {
        return nameData;
    }

    public void setNameData(List<String> nameData) {
        this.nameData = nameData;
    }

    public List<IndicatorDataBean> getIndicatorData() {
        return indicatorData;
    }

    public void setIndicatorData(List<IndicatorDataBean> indicatorData) {
        this.indicatorData = indicatorData;
    }

    public static class SeriesDataBean  extends BaseBean{
            private List<Integer> 实际分数;
            private List<Integer> 标准分数;

            @Override
            public String toString() {
                return "SeriesDataBean{" +
                        "实际分数=" + 实际分数 +
                        ", 标准分数=" + 标准分数 +
                        '}';
            }

            public List<Integer> get实际分数() {
                return 实际分数;
            }

            public void set实际分数(List<Integer> 实际分数) {
                this.实际分数 = 实际分数;
            }

            public List<Integer> get标准分数() {
                return 标准分数;
            }

            public void set标准分数(List<Integer> 标准分数) {
                this.标准分数 = 标准分数;
            }
    }

    public static class IndicatorDataBean extends BaseBean {
        @Override
        public String toString() {
            return "IndicatorDataBean{" +
                    "max=" + max +
                    ", name='" + name + '\'' +
                    '}';
        }

        /**
         * max : 100
         * name : 联网联控
         */

        private int max;
        private String name;

        public int getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
