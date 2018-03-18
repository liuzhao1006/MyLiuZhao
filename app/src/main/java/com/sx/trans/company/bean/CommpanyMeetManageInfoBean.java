package com.sx.trans.company.bean;

import com.sx.trans.base.BaseBean;

/**
 * Created by Administrator on 2017/9/13.
 */

public class CommpanyMeetManageInfoBean extends BaseBean {


    /**
     * content : ewqew
     * createDate : 1503478079000
     * createUser : admin
     * fileInfo : ewqeqw
     * groupId : 1
     * id : 9a2102c8554c499fb1577e7ce488e531
     * isDelete : false
     * title : ewqew
     */

    private String content;
    private long createDate;
    private String createUser;
    private String fileInfo;
    private String groupId;
    private String id;
    private boolean isDelete;
    private String title;

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public void setFileInfo(String fileInfo) {
        this.fileInfo = fileInfo;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setIsDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public long getCreateDate() {
        return createDate;
    }

    public String getCreateUser() {
        return createUser;
    }

    public String getFileInfo() {
        return fileInfo;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getId() {
        return id;
    }

    public boolean getIsDelete() {
        return isDelete;
    }

    public String getTitle() {
        return title;
    }
}
