package com.example.java_mock_rest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.java_mock_rest.adapters.ProductsAdapter;
import com.example.java_mock_rest.models.ProductsDataModel;
import com.example.java_mock_rest.models.ProductsModel;
import com.example.java_mock_rest.net.NetClient;
import com.example.java_mock_rest.net.NetInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onResume() {
        super.onResume();
        //
        getObservableViewProducts().subscribe(getObserverViewProducts());


    }

    public Observable<ProductsModel> getObservableViewProducts(){
        return NetClient.getRetrofit().create(NetInterface.class)
                .getProducts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public DisposableObserver<ProductsModel> getObserverViewProducts(){
        return new DisposableObserver<ProductsModel>() {

            @Override
            public void onNext(@NonNull ProductsModel productResponse) {
                List<ProductsDataModel> products = productResponse.getData();
                recyclerView = findViewById(R.id.recyclerView);
                recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
                adapter = new ProductsAdapter(products, getBaseContext());
                recyclerView.setAdapter(adapter);
                for (ProductsDataModel product : products) {
                    Log.d("checkk", product.getName());
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d("checkk", "error");
            }

            @Override
            public void onComplete() {
                Log.d("checkk", "complete");
            }
        };
    }

}
