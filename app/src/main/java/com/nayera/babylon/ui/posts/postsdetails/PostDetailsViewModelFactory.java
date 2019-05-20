package com.nayera.babylon.ui.posts.postsdetails;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.nayera.babylon.BabylonApplication;
import com.nayera.babylon.data.models.Post;
import com.nayera.babylon.ui.posts.postslisting.PostsListingViewModel;

public class PostDetailsViewModelFactory implements ViewModelProvider.Factory {

    BabylonApplication mApplication;
    Post mPost;

    public PostDetailsViewModelFactory(BabylonApplication application, Post post) {
        mApplication = application;
        mPost = post;
    }


    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(PostDetailsViewModel.class)) {
            return (T) new PostDetailsViewModel(mApplication, mPost);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}