package com.internship.internshala;

//import javax.security.auth.callback.Callback;

import retrofit.Callback;
import retrofit.client.Response;
//import retrofit2.client.Response;
import retrofit.http.Field;
import retrofit.http.POST;
import retrofit.http.FormUrlEncoded;

public interface RegisterAPI {
    @FormUrlEncoded
    @POST("/lattice/insert.php")

    // API's endpoints
            
    public void insertUser(@Field("name") String name,
                           @Field("address") String address,
                           @Field("email") String email,
                           @Field("phone") String phone,
                           @Field("password") String password,
                           Callback<Response> callback);

    // In registration method @Field used to set the keys and String data type is representing its a string type value and callback is used to get the response from api and it will set it in our POJO class

}
