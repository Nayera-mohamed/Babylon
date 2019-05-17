package com.nayera.babylon.ui.posts.postslisting;


import android.content.Context;

import com.nayera.babylon.data.remote.RemoteDataSource;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;



@Module
public class PostsListingModule {

    @Provides
    PostsListingRepository getPostsListsRepository(Context context, RemoteDataSource remoteDataSource) {
        return new PostsListingRepository(context, remoteDataSource);
    }

}
