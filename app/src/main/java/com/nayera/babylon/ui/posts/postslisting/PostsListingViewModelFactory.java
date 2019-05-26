package com.nayera.babylon.ui.posts.postslisting;

import android.app.Application;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.nayera.babylon.BabylonApplication;

public class PostsListingViewModelFactory implements ViewModelProvider.Factory {
    PostsListingRepository mPostsListingRepository;

    public PostsListingViewModelFactory(PostsListingRepository postsListingRepository) {
        mPostsListingRepository = postsListingRepository;
    }


    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(PostsListingViewModel.class)) {
            return (T) new PostsListingViewModel(mPostsListingRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}