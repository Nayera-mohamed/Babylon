package com.nayera.babylon.api;

import com.nayera.babylon.data.models.Comment;
import com.nayera.babylon.data.models.Post;
import com.nayera.babylon.data.models.User;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;

public interface APIInterface {

    @GET("posts")
    Observable<List<Post>> getPosts();

    @GET("users")
    Observable<List<User>> getUsers();

    @GET("comments")
    Observable<List<Comment>> getComments();

}
