package com.sx.trans.supervision.constants;

import android.util.Log;

import com.sx.trans.supervision.utils.JSONUtils;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;

/**
 * 接口返回实体
 * by mr_wang
 */
public class Result implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer state;
	private Object data;
	private String msg;
	private Integer totalNum;
	private String PictureURL;//上传图片用到

	private int code;

	public void setCode(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public Result() {
	}

//	public Result(Integer state, String data, String msg) {
//		super();
//		this.state = state;
//		this.data = data;
//		this.msg = msg;
//	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Integer getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

	public <T> T getData(TypeToken<T> token) {
		return JSONUtils.fromJson(JSONUtils.toJson(this.data),token);
	}

	public <T> T getData(Class<T> clz) {
		Log.d("Result","getData.tostring():"+JSONUtils.toJson(this.data));
		return JSONUtils.fromJson(JSONUtils.toJson(this.data),clz);
	}

	public String getPictureURL() {
		return PictureURL;
	}

	public void setPictureURL(String pictureURL) {
		PictureURL = pictureURL;
	}


}
