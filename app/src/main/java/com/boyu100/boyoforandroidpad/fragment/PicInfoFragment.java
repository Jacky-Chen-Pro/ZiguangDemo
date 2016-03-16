package com.boyu100.boyoforandroidpad.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bm.library.PhotoView;
import com.boyu100.boyoforandroidpad.R;
import com.boyu100.boyoforandroidpad.adapter.PicsInfoAdapter;
import com.boyu100.boyoforandroidpad.base.AppApplication;
import com.boyu100.boyoforandroidpad.bean.PicInfoArrayBean;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.io.File;

/**
 * Created by Jacky on 2016/3/12.
 */
@EFragment(R.layout.fragment_picinfo)
public class PicInfoFragment extends Fragment {
    @ViewById
    PhotoView img;

    private int imgId;
    private PicInfoArrayBean mBean;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBean = (PicInfoArrayBean) getArguments().getSerializable("picBean");
    }

    public static PicInfoFragment_ getPicInfoFragment(PicInfoArrayBean picBean) {
        PicInfoFragment_ fragment_ = new PicInfoFragment_();
        Bundle bundle = new Bundle();
        bundle.putSerializable("picBean", picBean);
        fragment_.setArguments(bundle);
        return fragment_;
    }

    @AfterViews
    void initImg(){
//        img.setImageResource(imgId);
        Picasso.with(getActivity()).load(new File(AppApplication.getExternalSdPath() + mBean.getPicPath())).into(img);
        img.enable();
    }

}
