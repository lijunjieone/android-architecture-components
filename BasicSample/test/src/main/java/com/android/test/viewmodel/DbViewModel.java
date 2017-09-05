package com.android.test.viewmodel;

import android.app.Application;
import android.arch.core.util.Function;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;

import com.android.test.db.DatabaseCreator;
import com.android.test.db.entity.ProductEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lijunjie on 24/08/2017.
 */

public class DbViewModel extends AndroidViewModel {
    public DbViewModel(Application application) {
        super(application);
        init();
    }

    private static final MutableLiveData ABSENT = new MutableLiveData();
    {
        //noinspection unchecked
        ABSENT.setValue(null);
    }



    private  LiveData<List<ProductEntity>> mObservableProducts = null;

//    private MutableLiveData<List<String>> mLiveData;

    private void init() {
        final DatabaseCreator databaseCreator = DatabaseCreator.getInstance(this.getApplication());

        LiveData<Boolean> databaseCreated = databaseCreator.isDatabaseCreated();
        mObservableProducts = Transformations.switchMap(databaseCreated,
                new Function<Boolean, LiveData<List<ProductEntity>>>() {
                    @Override
                    public LiveData<List<ProductEntity>> apply(Boolean isDbCreated) {
                        if (!Boolean.TRUE.equals(isDbCreated)) { // Not needed here, but watch out for null
                            //noinspection unchecked
                            return ABSENT;
                        } else {
                            //noinspection ConstantConditions
                            return databaseCreator.getDatabase().productDao().getAllProducts();
                        }
                    }
                });

        databaseCreator.createDb(this.getApplication());
    }



    public LiveData<List<ProductEntity>> getData() {
        return mObservableProducts;
    }


}
