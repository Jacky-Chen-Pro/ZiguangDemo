package com.boyu100.boyoforandroidpad.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.boyu100.boyoforandroidpad.R;
import com.boyu100.boyoforandroidpad.adapter.PicsInfoAdapter;
import com.boyu100.boyoforandroidpad.bean.HomeInfoArrayBean;
import com.boyu100.boyoforandroidpad.utils.ToastUtils;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;


/**
 * Created by Jacky on 2016/3/12.
 */
@EActivity(R.layout.activity_picsinfo)
public class PicsInfoActivity extends FragmentActivity{

    @ViewById
    View wholeview;

    private int timeLength = 1000*60;//1一分钟的时间
    //检测用户是否长时间没有操作
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            ToastUtils.showShorToast("无人操作，返回主页");
            PicsInfoActivity.this.finish();
        }
    };

    @AfterViews
    void initOperation() {
        handler.sendEmptyMessageDelayed(0, timeLength);
    }

    @ViewById
    ViewPager viewpager;

    @ViewById
    ImageView left,right;

    @ViewById
    ImageView close;

    PicsInfoAdapter mAdapter;

    private HomeInfoArrayBean mBean;

    @Override
    protected void onPause() {
        super.onPause();
        if(handler != null && handler.hasMessages(0))
            handler.removeMessages(0);
    }

    public static final void goPicsInfoActivity(Context ctx, HomeInfoArrayBean bean) {
        Intent intent = new Intent();
        intent.setClass(ctx, PicsInfoActivity_.class);
        intent.putExtra("homeInfo", bean);
        ctx.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBean = (HomeInfoArrayBean) getIntent().getSerializableExtra("homeInfo");
    }

    @AfterViews
    void initPicsInfo() {
        mAdapter = new PicsInfoAdapter(getSupportFragmentManager(), mBean.getHomeInfoArray());
        viewpager.setAdapter(mAdapter);

        viewpager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()){
                    case MotionEvent.ACTION_UP:
                        handler.removeMessages(0);
                        handler.sendEmptyMessageDelayed(0, timeLength);
                        Log.i("pics", "手指抬起");
                        break;
                }
                return false;
            }
        });
    }

    @Click(R.id.left)
    void doLeft() {
        if(viewpager.getCurrentItem() != 0) {
            viewpager.setCurrentItem(viewpager.getCurrentItem() - 1, true);
        }else {
            ToastUtils.showShorToast("已经是第一页了");
        }
    }

    @Click(R.id.right)
    void doRight() {
        if(viewpager.getCurrentItem() < mAdapter.getCount()-1) {
            viewpager.setCurrentItem(viewpager.getCurrentItem()+1,true);
        }else {
            ToastUtils.showShorToast("已经是最后一页了");

        }
    }

    @Click(R.id.close)
    void doClose() {
        this.finish();
    }
}
