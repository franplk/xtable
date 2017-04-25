package com.emar.exception;


public enum ErrorCode {

    NULL_OBJ("XVIEW001","对象为空"),
    ERROR_ADD_USER("XVIEW002","添加用户失败"),
	UNKNOWN_ERROR("XVIEW003","系统繁忙，请稍后再试....");

    private String value;
    private String desc;

    ErrorCode(String value, String desc) {
    	this.value = value;
    	this.desc = desc;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
    	StringBuffer info = new StringBuffer();
    	info.append("[").append(this.value).append("]").append(this.desc);
        return info.toString();
    }
}
