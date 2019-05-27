package com.nayera.babylon.ui.posts.postslisting;

import com.google.common.collect.Lists;
import com.nayera.babylon.BabylonApplication;
import com.nayera.babylon.data.models.ApiResponse;
import com.nayera.babylon.data.models.Comment;
import com.nayera.babylon.data.models.Post;
import com.nayera.babylon.data.models.Status;
import com.nayera.babylon.data.models.User;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.observers.TestObserver;
import io.reactivex.subscribers.TestSubscriber;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PostListingViewModelTest {


    @Mock
    private PostsListingRepository mPostsRepository;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    PostsListingViewModel mPostsListingViewModel;

    private TestObserver<Boolean> mProgressIndicatorSubscriber;
    private TestObserver<Status> mStatusSubscriber;
    private TestObserver<ApiResponse> mPostsSubscriber;


    @Before
    public void setup() {

        MockitoAnnotations.initMocks(this);

        mPostsListingViewModel = new PostsListingViewModel(mPostsRepository);
        mProgressIndicatorSubscriber = new TestObserver<>();
        mStatusSubscriber = new TestObserver<>();
        mPostsSubscriber = new TestObserver<>();

    }

    @Test
    public void progressIndicator_emits_whenSubscribedToGetPosts() {
        when(mPostsRepository.getPosts()).thenReturn(Observable.error(new Exception()));
        when(mPostsRepository.getUsers()).thenReturn(Observable.error(new Exception()));
        when(mPostsRepository.getComments()).thenReturn(Observable.error(new Exception()));
        mPostsListingViewModel.getLoadingStatus().subscribe(mProgressIndicatorSubscriber);

        mPostsListingViewModel.getPostsData().subscribe();

        mProgressIndicatorSubscriber.assertValues(true, false);

        mProgressIndicatorSubscriber.dispose();
    }


    @Test
    public void noData_emits_whenEmpty_whenRetrievingPosts() {

        List<User> users = new ArrayList();
        users.add(new User());
        when(mPostsRepository.getUsers()).thenReturn(Observable.just(new ApiResponse(Status.SUCCESS), users));


        List<Comment> comments = new ArrayList();
        comments.add(new Comment());
        when(mPostsRepository.getComments()).thenReturn(Observable.just(new ApiResponse(Status.SUCCESS), comments));

        List<Post> posts = new ArrayList();
        posts.add(new Post());
        when(mPostsRepository.getPosts()).thenReturn(Observable.just(new ApiResponse(Status.SUCCESS), posts));


        mPostsListingViewModel.getErrors().subscribe(mStatusSubscriber);
        mPostsListingViewModel.getPostsData().subscribe(mPostsSubscriber);


        mStatusSubscriber.assertValue(Status.NO_DATA);

        mStatusSubscriber.dispose();
    }

    @Test
    public void error_emits_whenError_whenRetrievingPosts() {

        List<User> users = new ArrayList();
        users.add(new User());
        when(mPostsRepository.getUsers()).thenReturn(Observable.just(new ApiResponse(Status.SUCCESS), users));


        List<Comment> comments = new ArrayList();
        comments.add(new Comment());
        when(mPostsRepository.getComments()).thenReturn(Observable.just(new ApiResponse(Status.SUCCESS), comments));

        when(mPostsRepository.getPosts()).thenReturn(Observable.just(new ApiResponse(Status.ERROR)));


        mPostsListingViewModel.getErrors().subscribe(mStatusSubscriber);
        mPostsListingViewModel.getPostsData().subscribe(mPostsSubscriber);


        mStatusSubscriber.assertValue(Status.ERROR);

        mStatusSubscriber.dispose();
    }

}
