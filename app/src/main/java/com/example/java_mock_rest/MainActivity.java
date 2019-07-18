package com.example.java_mock_rest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.java_mock_rest.models.ProductsDataModel;
import com.example.java_mock_rest.models.ProductsModel;
import com.example.java_mock_rest.models.TaskModel;
import com.example.java_mock_rest.models.TasksModel;
import com.example.java_mock_rest.net.NetClient;
import com.example.java_mock_rest.net.NetInterface;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    @Override
    protected void onResume() {
        super.onResume();
//        getObservableViewProducts().subscribeWith(getObserverViewProducts());
        getObservableViewTasks().subscribeWith(getObserverViewTasks());
    }

    public Observable<TasksModel> getObservableViewTasks(){
        String accessToken = "13f80728d20a7729798df86dcc55f898";
        return NetClient.getRetrofit().create(NetInterface.class)
                .getTasks(accessToken)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public DisposableObserver<TasksModel> getObserverViewTasks(){
        return new DisposableObserver<TasksModel>() {

            @Override
            public void onNext(@NonNull TasksModel tasksResponse) {
                List<TaskModel> tasks = tasksResponse.getData();
                Log.d("goga", tasks.get(1).getTitle());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d("goga", "gopa");
            }

            @Override
            public void onComplete() {
                Log.d("goga", "gg");
            }
        };
    }


//    public Observable<ProductsModel> getObservableViewProducts(){
//        return NetClient.getRetrofit().create(NetInterface.class)
//                .getProducts()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//    }
//
//    public DisposableObserver<ProductsModel> getObserverViewProducts(){
//        return new DisposableObserver<ProductsModel>() {
//
//            @Override
//            public void onNext(@NonNull ProductsModel productResponse) {
//                List<ProductsDataModel> products = productResponse.getData();
//                Log.d("goga", products.get(1).getName());
//            }
//
//            @Override
//            public void onError(@NonNull Throwable e) {
//                Log.d("goga", "gopa");
//            }
//
//            @Override
//            public void onComplete() {
//                Log.d("goga", "gg");
//            }
//        };
//    }
}
