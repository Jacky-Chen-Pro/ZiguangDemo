package com.boyu100.boyoforandroidpad.activity;

import android.app.ProgressDialog;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.boyu100.boyoforandroidpad.R;
import com.boyu100.boyoforandroidpad.base.AppApplication;
import com.boyu100.boyoforandroidpad.base.Constants;
import com.boyu100.boyoforandroidpad.bean.MachineArrayBean;
import com.boyu100.boyoforandroidpad.utils.StringUtils;
import com.boyu100.boyoforandroidpad.utils.ToastUtils;
import com.google.gson.Gson;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Fullscreen;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * Created by Jacky on 2016/3/12.
 */

@Fullscreen
@EActivity(R.layout.activity_choose_machine)
public class ChooseMachineActivity extends FragmentActivity{

    private String mLocalPath = "/boyu/machines.txt";
    private String mJson = "";
    private MachineArrayBean mMachineInfo = new MachineArrayBean();
    private ProgressDialog mProgressDialog;

    @ViewById
    EditText position;
    @ViewById
    Button ok;

    int mMachineId = 0;

    @Click(R.id.ok)
    void goHomeActivity() {
        try {

            mMachineId = Integer.parseInt(position.getText().toString().trim());
            if(isInMachineList(mMachineId)) {
                ((AppApplication) AppApplication.context()).setSPValue(Constants.SP_VALUE_MACHINE, mMachineId + "");
                ((AppApplication) AppApplication.context()).setSPValue(Constants.SP_VALUE_DATA, mJson);
                HomeActivity.goHomeActivity(this);
            }else {
                ToastUtils.showShorToast("Json文件不存在，或者此id不存在");
            }

        }catch (Exception e) {
            e.printStackTrace();
            ToastUtils.showShorToast("您输入的数字有误，或者json格式有问题");
        }
    }
    @AfterViews
    void showProgressAndGetDate() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        showProgress();
        getAllDate();
        hideProgress();
    }

    @Background
    void getAllDate() {
        Gson gson = new Gson();

        try {
            mJson = readLocalJson();
            mMachineInfo = gson.fromJson(mJson, MachineArrayBean.class);

            for(int i=0; i< mMachineInfo.getMachineArray().size(); i++) {
                Log.i("machineBean[" + i + "]", mMachineInfo.getMachineArray().get(i).toString());
            }

            if(StringUtils.isNotEmpty(AppApplication.getSPValue(Constants.SP_VALUE_MACHINE))) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        position.setText(Integer.valueOf(AppApplication.getSPValue(Constants.SP_VALUE_MACHINE)) + "");
                        position.setSelection(position.getText().toString().length());
                    }
                });
                HomeActivity.goHomeActivity(this);
            }
        }catch (Exception e) {
            e.printStackTrace();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ToastUtils.showShorToast("您的json文件格式有问题");
                }
            });
        }
    }

    void hideProgress() {
        if(mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
    }

    void showProgress() {
        if(mProgressDialog == null) {
            mProgressDialog = ProgressDialog.show(ChooseMachineActivity.this,null,"loading...");
            mProgressDialog.show();
        }else {
            mProgressDialog.show();
        }
    }

    private String readLocalJson(){
        String jsonString="";
        String resultString="";
        try {
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(new FileInputStream(Environment.getExternalStorageDirectory()+ mLocalPath)));
            while ((jsonString=bufferedReader.readLine())!=null) {
                resultString+=jsonString;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("json:", resultString);
        return resultString;
    }

    private boolean isInMachineList(int machineId) {
        boolean isExist = false;
        if(mMachineInfo.getMachineArray().size() >0) {
            for(int i=0; i<mMachineInfo.getMachineArray().size(); i++) {
                if(machineId == mMachineInfo.getMachineArray().get(i).getMachinePosition()) {
                    isExist = true;
                    break;
                }
            }
        }else {
            isExist = false;
        }
        return isExist;
    }
}
