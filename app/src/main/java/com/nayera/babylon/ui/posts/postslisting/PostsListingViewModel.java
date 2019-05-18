package com.nayera.babylon.ui.posts.postslisting;

import android.app.Application;

import com.nayera.babylon.BabylonApplication;
import com.nayera.babylon.data.models.ApiResponse;
import com.nayera.babylon.data.models.Comment;
import com.nayera.babylon.data.models.Post;
import com.nayera.babylon.data.models.Status;
import com.nayera.babylon.data.models.User;
import com.nayera.babylon.ui.common.BaseViewModel;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function3;

public class PostsListingViewModel extends BaseViewModel {
    @Inject
    public PostsListingRepository postsListingRepository;

    BabylonApplication mApplication;

    List<Post> postsListing;

    public PostsListingViewModel(BabylonApplication application) {
        mApplication = application;
        mApplication.getApplicationComponent().inject(this);
    }

    public Observable<ApiResponse> getPostsData() {
        return Observable.zip(postsListingRepository.getPosts(), postsListingRepository.getUsers(), postsListingRepository.getComments(),
                new Function3<Object, Object, Object, Object>() {
                    @Override
                    public Object apply(Object o, Object o2, Object o3) throws Exception {

                        ApiResponse posts = (ApiResponse<Post>) o;
                        ApiResponse users = (ApiResponse<User>) o2;
                        ApiResponse comments = (ApiResponse<Comment>) o3;

                        if (posts.getStatus() != null && posts.getStatus().equals(Status.SUCCESS)) {
                            return new ApiResponse<>(postsListing, Status.SUCCESS);
                        } else {
                            return null;
                        }
                    }
                })
                .doOnSubscribe(disposable -> showProgressBar())
                .doOnError(throwable -> hideProgressBar())
                .doOnComplete(() -> hideProgressBar())
                .onErrorReturnItem(new ApiResponse<>(Status.ERROR));
    }

}
