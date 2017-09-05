package com.android.test.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.android.test.db.entity.ProductEntity;
import com.android.test.model.Product;

import java.util.List;

/**
 * Created by lijunjie on 05/09/2017.
 */

@Dao
public interface ProductDao {

    @Query("select * from products")
    LiveData<List<ProductEntity>> getAllProducts();

    @Insert()
    void insertAllProducts(List<ProductEntity> products);

    @Insert
    void insertProduct(ProductEntity product);
}
