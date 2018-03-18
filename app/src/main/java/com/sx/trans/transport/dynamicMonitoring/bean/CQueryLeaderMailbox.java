package com.sx.trans.transport.dynamicMonitoring.bean;

import com.sx.trans.base.BaseBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xxxxxx on 2017/11/21.
 */

public class CQueryLeaderMailbox extends BaseBean  {

    /**
     * code : 0
     * data : [{"content":"sdflks;dfj","createDate":1511231562000,"groupId":1,"handleIdea":"hahahah","handleState":true,"id":3,"telephone":"18392184165","title":"jaja ","userId":61},{"content":"","createDate":1511231312000,"groupId":1,"handleIdea":"","handleState":false,"id":2,"telephone":"","title":"","userId":61}]
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
         * content : sdflks;dfj
         * createDate : 1511231562000
         * groupId : 1
         * handleIdea : hahahah
         * handleState : true
         * id : 3
         * telephone : 18392184165
         * title : jaja
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
