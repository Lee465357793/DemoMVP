package com.weiyi.mvpdemo.m.bean;

import java.io.Serializable;

/**
 * Created by Lee on 2017/5/23.
 */

public class AppVersionInfo implements Serializable {

    /**
     * code : 200
     * message : success
     * data : {"id":"12","version_id":"1","version":"1.0","time":"1495429791","description":"【1】这个版本很牛逼；\r\n【2】新版本来了，主人，快来宠幸我把！","url":"http://www.paicl.net/download/weiyi2.8.apk"}
     */

    private int code;
    private String message;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public class DataBean {
        /**
         * id : 12
         * version_id : 1
         * version : 1.0
         * time : 1495429791
         * description : 【1】这个版本很牛逼；
         【2】新版本来了，主人，快来宠幸我把！
         * url : http://www.paicl.net/download/weiyi2.8.apk
         */

        private String id;
        private String version_id;
        private String version;
        private String time;
        private String description;
        private String url;
        private String must_up = "1";

        public void setMust_up(String must_up) {
            this.must_up = must_up;
        }

        public String getMust_up() {
            return must_up;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getVersion_id() {
            return version_id;
        }

        public void setVersion_id(String version_id) {
            this.version_id = version_id;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
