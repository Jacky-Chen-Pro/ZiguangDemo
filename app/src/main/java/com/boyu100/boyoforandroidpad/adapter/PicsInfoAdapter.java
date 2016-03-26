package com.boyu100.boyoforandroidpad.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.boyu100.boyoforandroidpad.bean.PicInfoArrayBean;
import com.boyu100.boyoforandroidpad.fragment.PicInfoFragment;

import java.util.List;


/**
 * Created by Jacky on 2016/3/12.
 */
public class PicsInfoAdapter extends FragmentPagerAdapter {
    private List<PicInfoArrayBean> mPicInfoArrayBeans;

    public PicsInfoAdapter(FragmentManager fm,List<PicInfoArrayBean>  pics) {
        super(fm);
        this.mPicInfoArrayBeans = pics;
    }

    @Override
    public Fragment getItem(int position) {
        return PicInfoFragment.getPicInfoFragment(mPicInfoArrayBeans.get(position));
    }

    @Override
    public int getCount() {
        return mPicInfoArrayBeans.size();
    }

}
