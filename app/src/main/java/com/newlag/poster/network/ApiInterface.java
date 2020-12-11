package com.newlag.poster.network;

import com.newlag.poster.model.Image;
import com.newlag.poster.model.Post;
import com.newlag.poster.model.User;
import java.util.ArrayList;
import java.util.HashMap;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    @GET("load_images")
    Single<ArrayList<Image>> loadImages();

    @POST("load_profile")
    Single<User> loadUser(@Body HashMap<String,String> hashMap);

    @POST("load_profile_username")
    Single<User> loadProfile(@Body HashMap<String, String> hashMap);

    @POST("load_profile_posts")
    Single<ArrayList<Post>> loadProfilePosts(@Body HashMap<String, String> hashMap);

    @POST("search")
    Single<ArrayList<User>> search(@Body HashMap<String,String> hashMap);

    @POST("register")
    Single<String> regUser(@Body User user);

    @POST("login")
    Single<String> login(@Body User user);

    @POST("post")
    Single<Integer> post(@Body HashMap<String, String> hashMap);

    @POST("auth")
    Single<Integer> auth(@Body HashMap<String,String> hashMap);

    @POST("update_profile")
    Single<String> updateProfile(@Body HashMap<String,String> hashMap);

    @GET("load_posts")
    Single<ArrayList<Post>> loadPosts();
}
