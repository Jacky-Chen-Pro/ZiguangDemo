package com.boyu100.boyoforandroidpad.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Jacky on 2016/3/16.
 */
public class MachineBean implements Serializable{


    private int machinePosition;
    private List<HomeInfoArrayBean> homeInfoArray;

    public int getMachinePosition() {
        return machinePosition;
    }

    public void setMachinePosition(int machinePosition) {
        this.machinePosition = machinePosition;
    }

    public List<HomeInfoArrayBean> getHomeInfoArray() {
        return homeInfoArray;
    }

    public void setHomeInfoArray(List<HomeInfoArrayBean> homeInfoArray) {
        this.homeInfoArray = homeInfoArray;
    }
}
