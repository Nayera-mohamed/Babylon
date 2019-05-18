package com.nayera.babylon.ui.posts.postslisting;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.nayera.babylon.BabylonApplication;
import com.nayera.babylon.R;
import com.nayera.babylon.data.models.ApiResponse;
import com.nayera.babylon.data.models.Status;
import com.nayera.babylon.ui.common.BaseFragment;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PostsListingFragment extends BaseFragment {

    public static String TAG = "PostsListingFragment";

    ProgressBar progressBar;
    RecyclerView rvPostsListing;

    PostsListingViewModel mPostsListingViewModel;

    public static PostsListingFragment newInstance() {
        return new PostsListingFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_posts_listing, container, false);
        initViews(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void initViewModel() {
        PostsListingViewModelFactory factory =
                new PostsListingViewModelFactory((BabylonApplication) getActivity().getApplication());
        mPostsListingViewModel = ViewModelProviders.of(this, factory).get(PostsListingViewModel.class);

    }

    @Override
    public void bindViewModel() {
        mPostsListingViewModel.getLoadingStatus().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::showLoadingStatus);

        mPostsListingViewModel.getErrors().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::showError);

        mPostsListingViewModel.getPostsData().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ApiResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ApiResponse apiResponse) {
                        Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void showLoadingStatus(Boolean loadingStatus) {
//        if (loadingStatus) {
//            showProgress();
//        } else {
//            hideProgress();
//        }
    }

    @Override
    public void initViews(View view) {
        progressBar = (ProgressBar) view.findViewById(R.id.progressbar);
        rvPostsListing = (RecyclerView) view.findViewById(R.id.rvPostsListing);
    }

    @Override
    public void setListeners() {

    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError(Status status) {

    }
}
