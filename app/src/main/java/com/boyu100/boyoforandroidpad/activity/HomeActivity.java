package com.boyu100.boyoforandroidpad.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.boyu100.boyoforandroidpad.R;
import com.boyu100.boyoforandroidpad.base.AppApplication;
import com.boyu100.boyoforandroidpad.base.Constants;
import com.boyu100.boyoforandroidpad.bean.HomeInfoArrayBean;
import com.boyu100.boyoforandroidpad.bean.MachineBean;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.io.File;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Jacky on 2016/3/12.
 */
@EActivity(R.layout.activity_home)
public class HomeActivity extends FragmentActivity {
    @ViewById
    ImageView background;
    private List<HomeInfoArrayBean> mHomeInfoArrayBean;

    public static void goHomeActivity(Context ctx) {
        Intent intent = new Intent();
        intent.setClass(ctx, HomeActivity_.class);
        ctx.startActivity(intent);
    }

    @AfterViews
    void doHandleData() {
        mHomeInfoArrayBean = AppApplication.getHomeInfoArray(Integer.valueOf(AppApplication.getSPValue(Constants.SP_VALUE_MACHINE)));
        Picasso.with(HomeActivity.this).load(new File(Environment.getExternalStorageDirectory() + mHomeInfoArrayBean.get(0).getHomePicPath())).into(background);
    }

    @ViewById
    LinearLayout first,second;
    @ViewById
    ImageView firstImg,secondImg;


    @Click(R.id.first)
    void goFirstInfoActivity() {
        PicsInfoActivity_.goPicsInfoActivity(this, mHomeInfoArrayBean.get(0));
    }

    @Click(R.id.second)
    void goSecondInfoActivity() {
        PicsInfoActivity_.goPicsInfoActivity(this,mHomeInfoArrayBean.get(1));
    }
}
