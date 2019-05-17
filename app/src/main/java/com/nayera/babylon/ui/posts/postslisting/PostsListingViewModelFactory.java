package com.nayera.babylon.ui.posts.postslisting;

import android.app.Application;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.nayera.babylon.BabylonApplication;

public class PostsListingViewModelFactory implements ViewModelProvider.Factory {

    BabylonApplication mApplication;

    public PostsListingViewModelFactory(BabylonApplication application) {
        mApplication = application;
    }


    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(PostsListingViewModel.class)) {
            return (T) new PostsListingViewModel(mApplication);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}