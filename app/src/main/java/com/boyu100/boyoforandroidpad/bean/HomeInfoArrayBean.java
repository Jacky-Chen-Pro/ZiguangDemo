package com.boyu100.boyoforandroidpad.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Jacky on 2016/3/16.
 */
public class HomeInfoArrayBean implements Serializable{
    private String homePicPath;
    private String homePicInfo;
    private List<PicInfoArrayBean> picInfoArray;

    public String getHomePicPath() {
        return homePicPath;
    }

    public void setHomePicPath(String homePicPath) {
        this.homePicPath = homePicPath;
    }

    public String getHomePicInfo() {
        return homePicInfo;
    }

    public void setHomePicInfo(String homePicInfo) {
        this.homePicInfo = homePicInfo;
    }

    public List<PicInfoArrayBean> getHomeInfoArray() {
        return picInfoArray;
    }

    public void setHomeInfoArray(List<PicInfoArrayBean> picInfoArray) {
        this.picInfoArray = picInfoArray;
    }
}
