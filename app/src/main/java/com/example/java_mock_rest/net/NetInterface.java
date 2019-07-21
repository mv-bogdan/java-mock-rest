package com.example.java_mock_rest.net;

import com.example.java_mock_rest.models.Credentials;
import com.example.java_mock_rest.models.Employee;
import com.example.java_mock_rest.models.EmployeeModel;
import com.example.java_mock_rest.models.EmployeeUpdateModel;
import com.example.java_mock_rest.models.LoginModel;
import com.example.java_mock_rest.models.ProductsDataModel;
import com.example.java_mock_rest.models.ProductsModel;
import com.example.java_mock_rest.models.ProfileModel;
import com.example.java_mock_rest.models.TasksModel;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface NetInterface {

    @POST("register")
    Observable<ProfileModel> register(@Body Credentials credentials);

    @POST("login")
    Observable<LoginModel> login(@Body Credentials credentials);

    @GET("unknown")
    Observable<ProductsModel> getProducts();

    @GET("unknown/{id}")
    Observable<ProductsDataModel> viewProduct(@Path("id") String productId);

    @POST("users")
    Observable<EmployeeModel> createEmployee(@Body Employee employee);

    @PUT("users/{id}")
    Observable<EmployeeUpdateModel> updateEmployee(@Path("id") String taskId, @Body Employee employee);

    @DELETE("users/{id}")
    Observable<EmployeeModel> deleteEmployee(@Path("id") String employeeId);

    @GET("users?page={id}")
    Observable<EmployeeModel> showEmployees(@Path("id") String pageId);

    @GET("v1/task")
    Observable<TasksModel> getTasks(@Header("Authorization") String accessToken);

}


