package com.weiyi.mvpdemo.m.bean;

/**
 * Created by Dev on 2016/11/26 0026.
 */

public class CheckversionBean {
    int is_required;
    int succeed;
    String error_desc;
    String url;
    String description;

    @Override
    public String toString() {
        return "CheckversionBean{" +
                "is_required=" + is_required +
                ", succeed=" + succeed +
                ", error_desc='" + error_desc + '\'' +
                ", url='" + url + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getError_desc() {
        return error_desc;
    }

    public void setError_desc(String error_desc) {
        this.error_desc = error_desc;
    }

    public int getIs_required() {
        return is_required;
    }

    public void setIs_required(int is_required) {
        this.is_required = is_required;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSucceed() {
        return succeed;
    }

    public void setSucceed(int succeed) {
        this.succeed = succeed;
    }


}
