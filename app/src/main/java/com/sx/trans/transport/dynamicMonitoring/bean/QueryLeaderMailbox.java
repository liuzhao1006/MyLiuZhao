package com.sx.trans.transport.dynamicMonitoring.bean;

import com.sx.trans.base.BaseBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xxxxxx on 2017/11/20.
 */

public class QueryLeaderMailbox extends BaseBean {

    /**
     * code : 0
     * data : [{"content":"公司垃圾桶摆放位置不太合适，客户刚进来看到垃圾，影响不好。","createDate":1511175106000,"groupId":5,"handleIdea":"","handleState":false,"id":1,"telephone":"13434424367","title":"公司垃圾桶摆放位置不太合适，","userId":61}]
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

    public static class DataBean implements Serializable {
        /**
         * content : 公司垃圾桶摆放位置不太合适，客户刚进来看到垃圾，影响不好。
         * createDate : 1511175106000
         * groupId : 5
         * handleIdea :
         * handleState : false
         * id : 1
         * telephone : 13434424367
         * title : 公司垃圾桶摆放位置不太合适，
         * userId : 61
         */

        private String content;
        private long createDate;
        private int groupId;
        private String handleIdea;
        private boolean handleState;
        private int id;
        private String telephone;
        private String title;
        private int userId;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public long getCreateDate() {
            return createDate;
        }

        public void setCreateDate(long createDate) {
            this.createDate = createDate;
        }

        public int getGroupId() {
            return groupId;
        }

        public void setGroupId(int groupId) {
            this.groupId = groupId;
        }

        public String getHandleIdea() {
            return handleIdea;
        }

        public void setHandleIdea(String handleIdea) {
            this.handleIdea = handleIdea;
        }

        public boolean isHandleState() {
            return handleState;
        }

        public void setHandleState(boolean handleState) {
            this.handleState = handleState;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}

