package com.nayera.babylon.ui.posts.postsdetails;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.nayera.babylon.BabylonApplication;
import com.nayera.babylon.R;
import com.nayera.babylon.data.models.ApiResponse;
import com.nayera.babylon.data.models.Post;
import com.nayera.babylon.data.models.Status;
import com.nayera.babylon.helpers.Utils;
import com.nayera.babylon.ui.common.BaseFragment;
import com.nayera.babylon.helpers.uihelpers.VerticalSpaceItemDecoration;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class PostDetailsFragment extends BaseFragment {

    public static String TAG = "PostDetailsFragment";
    public static String POST_KEY = "PostKey";

    TextView txtPostTitle;
    TextView txtPostBody;
    TextView txtAuthorName;
    TextView txtCommentsCount;
    ProgressBar progressBar;


    PostDetailsViewModel mPostDetailsViewModel;

    public static PostDetailsFragment newInstance(Post post) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(POST_KEY, post);
        PostDetailsFragment postDetailsFragment = new PostDetailsFragment();
        postDetailsFragment.setArguments(bundle);
        return postDetailsFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_post_details, container, false);
        initViews(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void injectDependencies() {

    }

    @Override
    public void initViewModel() {

        Post selectedPost = null;

        if (getArguments() != null)
            selectedPost = (Post) getArguments().get(POST_KEY);

        PostDetailsViewModelFactory factory =
                new PostDetailsViewModelFactory((BabylonApplication) getActivity().getApplication(), selectedPost);
        mPostDetailsViewModel = ViewModelProviders.of(this, factory).get(PostDetailsViewModel.class);

    }

    @Override
    public void bindViewModel() {
        mPostDetailsViewModel.getLoadingStatus().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::showLoadingStatus);

        mPostDetailsViewModel.getErrors().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::showError);

        mPostDetailsViewModel.getPost().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::getPostDataResponse);
    }


    private void getPostDataResponse(ApiResponse apiResponse) {
        if (apiResponse.getStatus().equals(Status.SUCCESS)) {
            Post currentPost = (Post) apiResponse.getData();
            updateUI(currentPost);
        } else
            showError(apiResponse.getStatus());
    }

    private void updateUI(Post currentPost) {
        txtPostTitle.setText(currentPost.getTitle());
        txtPostBody.setText(currentPost.getBody());
        if (currentPost.getUser() != null)
            txtAuthorName.setText(currentPost.getUser().getName());
        if (currentPost.getCommentList() != null) {
            int commentsCount = currentPost.getCommentList().size();
            txtCommentsCount.setText(String.valueOf(commentsCount).concat(getString(R.string.comments)));
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
        txtPostTitle = (TextView) view.findViewById(R.id.txtPostTitle);
        txtPostBody = (TextView) view.findViewById(R.id.txtPostBody);
        txtAuthorName = (TextView) view.findViewById(R.id.txtAuthorName);
        txtCommentsCount = (TextView) view.findViewById(R.id.txtCommentsCount);
        progressBar = (ProgressBar) view.findViewById(R.id.progressbar);
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
        Utils.showToastMsg(getActivity(), getResources().getString(R.string.generic_error));
    }


}
