package com.boyu100.boyoforandroidpad.base;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;

import com.boyu100.boyoforandroidpad.bean.HomeInfoArrayBean;
import com.boyu100.boyoforandroidpad.bean.MachineArrayBean;
import com.boyu100.boyoforandroidpad.utils.StringUtils;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by Jacky on 2016/3/12.
 */
public class AppApplication extends Application {
    private static Context _context;
    private static SharedPreferences mSharedPreference;
    @Override
    public void onCreate() {
        super.onCreate();
        this._context = this;
        mSharedPreference = getSharedPreferences(Constants.SP_NAME, MODE_PRIVATE);
    }

    public static Context context() {
        return _context;
    }

    /**
     * 存储sp值
     * @param key
     * @param value
     */
    public static void setSPValue(String key, String value) {
        SharedPreferences.Editor editor = mSharedPreference.edit();
        editor.putString(key, value);
        editor.commit();
    }

    /**
     * 获取sp值
     * @param key
     * @return
     */
    public static String getSPValue(String key) {
        return mSharedPreference.getString(key, StringUtils.EMPTY_STRING);
    }

    public static final List<HomeInfoArrayBean> getHomeInfoArray(int machineId) {
        String gsonStr = getSPValue(Constants.SP_VALUE_DATA);
        Gson gson = new Gson();
        MachineArrayBean bean = gson.fromJson(gsonStr, MachineArrayBean.class);

        for(int i=0; i<bean.getMachineArray().size(); i++) {
            if(machineId == bean.getMachineArray().get(i).getMachinePosition()) {
                return bean.getMachineArray().get(i).getHomeInfoArray();
            }
        }
        return null;
    }

    public static final String getExternalSdPath() {
        return Environment.getExternalStorageDirectory().getPath();
    }
}
