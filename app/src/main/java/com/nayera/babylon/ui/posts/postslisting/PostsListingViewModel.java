package com.nayera.babylon.ui.posts.postslisting;

import android.app.Application;

import com.nayera.babylon.BabylonApplication;
import com.nayera.babylon.data.models.ApiResponse;
import com.nayera.babylon.data.models.Comment;
import com.nayera.babylon.data.models.Post;
import com.nayera.babylon.data.models.Status;
import com.nayera.babylon.data.models.User;
import com.nayera.babylon.ui.common.BaseViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function3;

public class PostsListingViewModel extends BaseViewModel {
    @Inject
    public PostsListingRepository postsListingRepository;

    BabylonApplication mApplication;

    List<Post> postsListing;
    ApiResponse<Post> currentPostsList;

    public PostsListingViewModel(BabylonApplication application) {
        mApplication = application;
        if (mApplication.getApplicationComponent() != null)
            mApplication.getApplicationComponent().inject(this);
    }

    public Observable<ApiResponse> getPostsData() {
        if (currentPostsList == null) {
            return Observable.zip(postsListingRepository.getPosts(), postsListingRepository.getUsers(), postsListingRepository.getComments(),
                    new Function3<Object, Object, Object, Object>() {
                        @Override
                        public Object apply(Object o, Object o2, Object o3) throws Exception {

                            ApiResponse posts = (ApiResponse<Post>) o;
                            ApiResponse users = (ApiResponse<User>) o2;
                            ApiResponse comments = (ApiResponse<Comment>) o3;

                            if (posts.getStatus() != null && posts.getStatus().equals(Status.SUCCESS)) {
                                currentPostsList = getPostsList(posts, users, comments);
                                return currentPostsList;
                            } else {
                                showError(posts.getStatus());
                                return posts;
                            }
                        }
                    })
                    .doOnSubscribe(disposable -> showProgressBar())
                    .doOnError(throwable -> hideProgressBar())
                    .doOnComplete(() -> hideProgressBar())
                    .onErrorReturnItem(new ApiResponse<>(Status.ERROR));
        } else
            return Observable.just(currentPostsList);
    }

    /**
     * This method operates on all three api responses and modify all posts objects to contain their list of comments and the user who wrote the post
     *
     * @param posts
     * @param users
     * @param comments
     * @return
     */
    ApiResponse<Post> getPostsList(ApiResponse posts, ApiResponse users, ApiResponse comments) {
        postsListing = (List<Post>) posts.getData();

        HashMap commentsMap = getCommentsMap(comments);
        HashMap usersMap = getUsersMap(users);

        for (int i = 0; i < postsListing.size(); i++) {
            Post post = postsListing.get(i);
            if (commentsMap.containsKey(post.getId())) {
                post.setCommentList((List<Comment>) commentsMap.get(post.getId()));
            }
            if (usersMap.containsKey(post.getUserId())) {
                post.setUser((User) usersMap.get(post.getUserId()));
            }
            postsListing.set(i, post);
        }

        posts.setData(postsListing);
        return posts;
    }

    /**
     * Returns comments as a map to reduce search time
     *
     * @param comments
     * @return
     */
    HashMap getCommentsMap(ApiResponse comments) {
        HashMap commentsMap = new HashMap();
        if (comments.getStatus().equals(Status.SUCCESS)) {
            List<Comment> commentsList = (List<Comment>) comments.getData();
            for (int i = 0; i < commentsList.size(); i++) {
                Comment currentComment = commentsList.get(i);
                if (commentsMap.containsKey(currentComment.getPostId())) {
                    List<Comment> commentsListPerPost = (List<Comment>) commentsMap.get(currentComment.getPostId());
                    commentsListPerPost.add(currentComment);
                    commentsMap.put(currentComment.getPostId(), commentsListPerPost);
                } else {
                    List<Comment> commentsListPerPost = new ArrayList<>();
                    commentsList.add(currentComment);
                    commentsMap.put(currentComment.getPostId(), commentsListPerPost);
                }
            }
        }
        return commentsMap;
    }

    /**
     * Returns users as a map to reduce search time
     *
     * @param users
     * @return
     */
    HashMap getUsersMap(ApiResponse users) {
        HashMap usersMap = new HashMap();
        if (users.getStatus().equals(Status.SUCCESS)) {
            List<User> usersList = (List<User>) users.getData();
            for (int i = 0; i < usersList.size(); i++) {
                User currentUser = usersList.get(i);
                if (!usersMap.containsKey(currentUser.getId())) {
                    usersMap.put(currentUser.getId(), currentUser);
                }
            }
        }
        return usersMap;
    }
}
