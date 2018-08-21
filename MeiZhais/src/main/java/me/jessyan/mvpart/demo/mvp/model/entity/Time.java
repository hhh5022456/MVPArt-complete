package me.jessyan.mvpart.demo.mvp.model.entity;

import java.util.List;

public class Time {

    /**
     * code : 10000
     * data : [{"avatar_url":"http://www.jingfuzi.com/uploadfile/member/baby/4e/26/84/180x180.jpg","bb_id":"84","birth_weight":"5.2cmkg","birthday":"2037-11-27","sex":"女","truename":"黄连上清片"}]
     * info : success
     */

    private int code;
    private String info;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * avatar_url : http://www.jingfuzi.com/uploadfile/member/baby/4e/26/84/180x180.jpg
         * bb_id : 84
         * birth_weight : 5.2cmkg
         * birthday : 2037-11-27
         * sex : 女
         * truename : 黄连上清片
         */

        private String avatar_url;
        private String bb_id;
        private String birth_weight;
        private String birthday;
        private String sex;
        private String truename;

        public String getAvatar_url() {
            return avatar_url;
        }

        public void setAvatar_url(String avatar_url) {
            this.avatar_url = avatar_url;
        }

        public String getBb_id() {
            return bb_id;
        }

        public void setBb_id(String bb_id) {
            this.bb_id = bb_id;
        }

        public String getBirth_weight() {
            return birth_weight;
        }

        public void setBirth_weight(String birth_weight) {
            this.birth_weight = birth_weight;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getTruename() {
            return truename;
        }

        public void setTruename(String truename) {
            this.truename = truename;
        }
    }
}
