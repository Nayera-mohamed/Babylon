package com.nayera.babylon.ui.posts.postsdetails;

import com.nayera.babylon.BabylonApplication;
import com.nayera.babylon.data.models.ApiResponse;
import com.nayera.babylon.data.models.Post;
import com.nayera.babylon.data.models.Status;
import com.nayera.babylon.ui.common.BaseViewModel;

import io.reactivex.Observable;

public class PostDetailsViewModel extends BaseViewModel {

    BabylonApplication mApplication;

    Post mPost;

    public PostDetailsViewModel(BabylonApplication application, Post post) {
        mApplication = application;
        mPost = post;
    }

    public Observable<ApiResponse<Post>> getPost() {
        ApiResponse<Post> apiResponse = new ApiResponse<Post>();
        if (mPost != null) {
            apiResponse.setData(mPost);
            apiResponse.setStatus(Status.SUCCESS);
        }else
            apiResponse.setStatus(Status.ERROR);

        return Observable.just(apiResponse)
                .doOnSubscribe(disposable -> showProgressBar())
                .doOnError(throwable -> hideProgressBar())
                .doOnComplete(() -> hideProgressBar())
                .onErrorReturnItem(new ApiResponse<>(Status.ERROR));
    }


}
