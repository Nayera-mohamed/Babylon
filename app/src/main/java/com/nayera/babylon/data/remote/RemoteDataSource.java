package com.nayera.babylon.data.remote;

import com.nayera.babylon.api.APIInterface;
import com.nayera.babylon.data.models.ApiResponse;
import com.nayera.babylon.data.models.Comment;
import com.nayera.babylon.data.models.Post;
import com.nayera.babylon.data.models.User;

import java.util.List;

import io.reactivex.Observable;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class RemoteDataSource implements IRemoteDataSource {

    @Inject
    APIInterface apiInterface;

    @Override
    public Observable<List<User>> getUsers() {
        return apiInterface.getUsers();
    }

    @Override
    public Observable<List<Post>> getPosts() {
        return apiInterface.getPosts();
    }

    @Override
    public Observable<List<Comment>> getComments() {
        return apiInterface.getComments();
    }
}
