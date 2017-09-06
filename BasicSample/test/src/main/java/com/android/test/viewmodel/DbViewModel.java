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
import java.util.Random;

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


    DatabaseCreator databaseCreator;
    private  LiveData<List<ProductEntity>> mObservableProducts = null;

//    private MutableLiveData<List<String>> mLiveData;

    private void init() {
        databaseCreator = DatabaseCreator.getInstance(this.getApplication());

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



    public void insertOne() {
        ProductEntity entity=new ProductEntity();
        Random r=new Random();
        int i=r.nextInt(1000);
        entity.setId(i);
        entity.setName("this is a test!");
        entity.setPrice(100);
        try{
            databaseCreator.getDatabase().productDao().insertProduct(entity);
        }catch (android.database.sqlite.SQLiteConstraintException e) {
            e.printStackTrace();
        }
    }


    public LiveData<List<ProductEntity>> getData() {
        return mObservableProducts;
    }


}
