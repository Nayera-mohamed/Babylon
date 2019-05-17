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

    APIInterface mApiInterface;

    @Inject
    public RemoteDataSource(APIInterface apiInterface) {
        mApiInterface = apiInterface;
    }

    @Override
    public Observable<List<User>> getUsers() {
        return mApiInterface.getUsers();
    }

    @Override
    public Observable<List<Post>> getPosts() {
        return mApiInterface.getPosts();
    }

    @Override
    public Observable<List<Comment>> getComments() {
        return mApiInterface.getComments();
    }
}
