package com.nayera.babylon.ui.posts.postslisting;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class PostsListingViewModelFactory implements ViewModelProvider.Factory {

    public PostsListingViewModelFactory() {
    }


    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(PostsListingViewModel.class)) {
            return (T) new PostsListingViewModel();
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}