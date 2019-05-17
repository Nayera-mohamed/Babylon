package com.nayera.babylon.ui.posts.postslisting;

import android.app.Application;

import com.nayera.babylon.BabylonApplication;
import com.nayera.babylon.data.models.ApiResponse;
import com.nayera.babylon.ui.common.BaseViewModel;

import javax.inject.Inject;

import io.reactivex.Observable;

public class PostsListingViewModel extends BaseViewModel {
    @Inject
    public PostsListingRepository postsListingRepository;

    BabylonApplication mApplication;

    public PostsListingViewModel(BabylonApplication application) {
        mApplication = application;
        mApplication.getApplicationComponent().inject(this);
    }

    public Observable<ApiResponse> getPosts() {
        return postsListingRepository.getPosts();
    }

}
