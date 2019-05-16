package com.nayera.babylon.data.remote;

import com.nayera.babylon.data.models.ApiResponse;
import com.nayera.babylon.data.models.Comment;
import com.nayera.babylon.data.models.Post;
import com.nayera.babylon.data.models.User;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

public interface IRemoteDataSource {

    Observable<List<User>> getUsers();

    Observable<List<Post>> getPosts();

    Observable<List<Comment>> getComments();

}
