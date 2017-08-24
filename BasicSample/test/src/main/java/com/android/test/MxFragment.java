package com.android.test;

import android.arch.lifecycle.LifecycleFragment;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.test.databinding.ListFragment2Binding;
//import com.android.test.databinding.ListFragmentBinding;

//import com.android.test.databinding.ListFragmentBinding;

/**
 * Created by lijunjie on 23/08/2017.
 */

public class MxFragment extends LifecycleFragment {

    public static final String TAG=MxFragment.class.getSimpleName();
    ListFragment2Binding mBinding;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        mBinding = DataBindingUtil.inflate(inflater, R.layout.list_fragment2, container, false);
        mBinding.setIsLoading(false);
        mBinding.getRoot().setBackgroundColor(Color.RED);
        return mBinding.getRoot();
//        FrameLayout f=new FrameLayout(getContext());
//        f.setBackgroundColor(Color.BLUE);
//        return f;
    }
}
