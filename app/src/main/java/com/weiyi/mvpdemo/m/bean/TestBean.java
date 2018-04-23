package com.weiyi.mvpdemo.m.bean;

/**
 * Created by Administrator on 2018/4/21 0021.
 */

public class TestBean {

    /**
     * status : 1
     * msg : {"email":"7755555555@qq.com","phone":"15555555555","store_address":"15555555555","loginimg":""}
     */

    private int status;
    private MsgBean msg;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public MsgBean getMsg() {
        return msg;
    }

    public void setMsg(MsgBean msg) {
        this.msg = msg;
    }

    public static class MsgBean {
        /**
         * email : 7755555555@qq.com
         * phone : 15555555555
         * store_address : 15555555555
         * loginimg :
         */

        private String email;
        private String phone;
        private String store_address;
        private String loginimg;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getStore_address() {
            return store_address;
        }

        public void setStore_address(String store_address) {
            this.store_address = store_address;
        }

        public String getLoginimg() {
            return loginimg;
        }

        public void setLoginimg(String loginimg) {
            this.loginimg = loginimg;
        }
    }
}
