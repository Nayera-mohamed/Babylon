package com.nayera.babylon.ui.posts.postslisting;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.nayera.babylon.ui.common.BaseFragment;

public class PostsListingFragment extends BaseFragment {

    public static String TAG = "PostsListingFragment";

    PostsListingViewModel mPostsListingViewModel;

    public static PostsListingFragment newInstance() {
        return new PostsListingFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void initViewModel() {
        PostsListingViewModelFactory factory =
                new PostsListingViewModelFactory();
        mPostsListingViewModel = ViewModelProviders.of(this, factory).get(PostsListingViewModel.class);

    }

    @Override
    public void bindViewModel() {

    }

    @Override
    public void unbindViewModel() {

    }

    @Override
    public void initViews() {

    }

    @Override
    public void setListeners() {

    }
}
