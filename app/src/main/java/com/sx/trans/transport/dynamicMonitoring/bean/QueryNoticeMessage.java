package com.sx.trans.transport.dynamicMonitoring.bean;

import com.sx.trans.base.BaseBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xxxxxx on 2017/11/21.
 */

public class QueryNoticeMessage extends BaseBean {

    /**
     * code : 0
     * data : [{"abstracts":"切实组织所有员工积极配合，防止踩踏事件发生。","content":" <p>2017年11月20日，在我公司和平大厦进行防火演习。<\/p>","date":"2017-11-20 00:00:00","fileInfo":"57c796ce7be1e.jpg::/ueditor/jsp/upload/file/20171120/1511158908433033817.jpg","id":15,"ispublish":"是","pulishday":3,"title":"安全防火演习","type":"图片新闻"},{"abstracts":"云平台发布成功","content":"","date":"2017-10-21 00:00:00","fileInfo":"","id":14,"ispublish":"是","pulishday":2,"title":"云平台发布","type":"图片新闻"},{"abstracts":"提高团队意识","content":" <p>aaa<\/p>","date":"2017-10-10 00:00:00","fileInfo":"","id":13,"ispublish":"否","pulishday":2,"title":"亿程公司开展团队建设","type":"工作简报"}]
     * message : 有数据
     */

    private int code;
    private String message;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * abstracts : 切实组织所有员工积极配合，防止踩踏事件发生。
         * content :  <p>2017年11月20日，在我公司和平大厦进行防火演习。</p>
         * date : 2017-11-20 00:00:00
         * fileInfo : 57c796ce7be1e.jpg::/ueditor/jsp/upload/file/20171120/1511158908433033817.jpg
         * id : 15
         * ispublish : 是
         * pulishday : 3
         * title : 安全防火演习
         * type : 图片新闻
         */

        private String abstracts;
        private String content;
        private String date;
        private String fileInfo;
        private int id;
        private String ispublish;
        private int pulishday;
        private String title;
        private String type;

        public String getAbstracts() {
            return abstracts;
        }

        public void setAbstracts(String abstracts) {
            this.abstracts = abstracts;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getFileInfo() {
            return fileInfo;
        }

        public void setFileInfo(String fileInfo) {
            this.fileInfo = fileInfo;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getIspublish() {
            return ispublish;
        }

        public void setIspublish(String ispublish) {
            this.ispublish = ispublish;
        }

        public int getPulishday() {
            return pulishday;
        }

        public void setPulishday(int pulishday) {
            this.pulishday = pulishday;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
