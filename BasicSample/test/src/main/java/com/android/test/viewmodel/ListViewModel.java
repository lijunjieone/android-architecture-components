package com.android.test.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.Transformations;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lijunjie on 24/08/2017.
 */

public class ListViewModel extends AndroidViewModel {
    public ListViewModel(Application application) {
        super(application);
        init();
    }



    private MutableLiveData<List<String>> mLiveData;

    private void init() {
        mLiveData = new MutableLiveData<List<String>>();


        List<String> data=new ArrayList<>();
        for(int i=0;i<30;i++) {
            data.add("position:"+i);
        }

        mLiveData.postValue(data);
    }

    public LiveData<List<String>> getData() {
        return mLiveData;
    }


}
