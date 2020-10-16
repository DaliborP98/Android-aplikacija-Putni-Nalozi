package com.example.putni_nalozi.models;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PutniNaloziAPI {


    @POST("/login")
    Call<User> login(@Body User user);

    @POST("/user/add")
    Call<Void> add(@Body User user);

    @GET("/user/{userId}/putniNalozi")
    Call<List<PutniNalog>> getPutneNalogeByUser(@Path("userId") int id);

    @GET("/putniNalozi")
    Call<List<PutniNalog>> getPutneNaloge();

    @GET("/posaljiLozinku/{email}")
    Call<Void> getLozinka(@Path("email") String email);

    @POST("/user/{userId}/putniNalozi")
    Call<Void> noviPutniNalog(@Path("userId") int id, @Body PutniNalog putniNalog);

    @GET("/putniNalozi/broj")
    Call<Integer> getBrojPutnogNaloga();

    @GET("/putniNalozi/nedefinirano")
    Call<List<PutniNalog>> getNedefiniranePutneNaloge();

    @PUT("/user/{userId}/putniNalozi/{id}")
    Call<Void> updatePutniNalog(@Path("userId") int userId, @Path("id") int nalogId, @Body PutniNalog putniNalog);

    @DELETE("/putniNalozi/{id}")
    Call<Void> izbrisiPutniNalog( @Path("id") int id);

}
