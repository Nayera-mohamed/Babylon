package com.nayera.babylon.ui.posts.postslisting;

import android.content.Context;

import com.nayera.babylon.data.models.ApiResponse;
import com.nayera.babylon.data.models.Comment;
import com.nayera.babylon.data.models.Post;
import com.nayera.babylon.data.models.Status;
import com.nayera.babylon.data.models.User;
import com.nayera.babylon.data.remote.RemoteDataSource;
import com.nayera.babylon.helpers.Utils;
import com.nayera.babylon.ui.common.BaseRepository;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class PostsListingRepository<T> extends BaseRepository {


    Context mContext;
    RemoteDataSource mRemoteDataSource;


    @Inject
    public PostsListingRepository(Context context, RemoteDataSource remoteDataSource) {
        mContext = context;
        mRemoteDataSource = remoteDataSource;
    }

    public Observable<ApiResponse> getPosts() {
        if (Utils.isNetworkAvailable(mContext)) {
            return mRemoteDataSource.getPosts().map(new Function<List<Post>, ApiResponse>() {
                @Override
                public ApiResponse apply(List<Post> posts) throws Exception {
                    if (posts != null && posts.size() > 0) {
                        return new ApiResponse(posts, Status.SUCCESS);
                    } else {
                        return new ApiResponse(null, Status.NO_DATA);
                    }
                }
            });
        } else {
            return createObservable(new ApiResponse<T>(Status.NO_NETWORK));
        }
    }

    public Observable<ApiResponse> getUsers() {
        if (Utils.isNetworkAvailable(mContext)) {
            return mRemoteDataSource.getUsers().map(new Function<List<User>, ApiResponse>() {
                @Override
                public ApiResponse apply(List<User> users) throws Exception {
                    if (users != null && users.size() > 0) {
                        return new ApiResponse(users, Status.SUCCESS);
                    } else {
                        return new ApiResponse(null, Status.NO_DATA);
                    }
                }
            });
        } else {
            return createObservable(new ApiResponse<T>(Status.NO_NETWORK));
        }
    }

    public Observable<ApiResponse> getComments() {
        if (Utils.isNetworkAvailable(mContext)) {
            return mRemoteDataSource.getComments().map(new Function<List<Comment>, ApiResponse>() {
                @Override
                public ApiResponse apply(List<Comment> comments) throws Exception {
                    if (comments != null && comments.size() > 0) {
                        return new ApiResponse(comments, Status.SUCCESS);
                    } else {
                        return new ApiResponse(null, Status.NO_DATA);
                    }
                }
            });
        } else {
            return createObservable(new ApiResponse<T>(Status.NO_NETWORK));
        }
    }


}
