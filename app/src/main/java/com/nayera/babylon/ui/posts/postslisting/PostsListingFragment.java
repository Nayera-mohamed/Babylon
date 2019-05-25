package com.nayera.babylon.ui.posts.postslisting;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nayera.babylon.BabylonApplication;
import com.nayera.babylon.R;
import com.nayera.babylon.data.models.ApiResponse;
import com.nayera.babylon.data.models.Post;
import com.nayera.babylon.data.models.Status;
import com.nayera.babylon.helpers.Utils;
import com.nayera.babylon.ui.common.BaseFragment;
import com.nayera.babylon.ui.posts.postsdetails.PostDetailsActivity;
import com.nayera.babylon.helpers.uihelpers.VerticalSpaceItemDecoration;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class PostsListingFragment extends BaseFragment implements PostsListAdapter.OnItemClickListener {

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
        initRecyclerView();
    }

    private void initRecyclerView() {
        VerticalSpaceItemDecoration spaceItemDecoration = new VerticalSpaceItemDecoration((int) getResources().getDimension(R.dimen.margin_normal));
        rvPostsListing.addItemDecoration(spaceItemDecoration);
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
                .subscribe(this::getPostDataResponse);
    }

    private void getPostDataResponse(ApiResponse apiResponse) {
        if (apiResponse.getStatus().equals(Status.SUCCESS)) {

            PostsListAdapter adapter = new PostsListAdapter((List<Post>) apiResponse.getData(), this);
            rvPostsListing.setAdapter(adapter);
            rvPostsListing.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
    }


    private void showLoadingStatus(Boolean loadingStatus) {
        if (loadingStatus) {
            showProgress();
        } else {
            hideProgress();
        }
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
        if (status.equals(Status.NO_NETWORK)) {
            Utils.showToastMsg(getActivity(), getResources().getString(R.string.no_network_error));
        } else {
            Utils.showToastMsg(getActivity(), getResources().getString(R.string.generic_error));
        }
    }

    @Override
    public void onItemClick(Post post) {
        PostDetailsActivity.startActivity(getActivity(), post);

    }
}
