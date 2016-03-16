package com.boyu100.boyoforandroidpad.bean;

import java.util.List;

/**
 * Created by Jacky on 2016/3/16.
 *
 */
public class MachineArrayBean {
    private List<MachineBean> machineArray;

    public List<MachineBean> getMachineArray() {
        return machineArray;
    }

    public void setMachineArray(List<MachineBean> machineArray) {
        this.machineArray = machineArray;
    }

    @Override
    public String toString() {
        return "MachineArrayBean{" +
                "machineArray=" + machineArray +
                '}';
    }
}
