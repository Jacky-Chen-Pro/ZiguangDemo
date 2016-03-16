package com.boyu100.boyoforandroidpad.bean;

import java.io.Serializable;

/**
 * Created by Jacky on 2016/3/16.
 */
public class PicInfoArrayBean implements Serializable{
    private String picPath;
    private String picInfo;

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public String getPicInfo() {
        return picInfo;
    }

    public void setPicInfo(String picInfo) {
        this.picInfo = picInfo;
    }
}
