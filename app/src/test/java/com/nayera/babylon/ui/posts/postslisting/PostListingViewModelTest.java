package com.nayera.babylon.ui.posts.postslisting;

import com.google.common.collect.Lists;
import com.nayera.babylon.BabylonApplication;
import com.nayera.babylon.data.models.ApiResponse;
import com.nayera.babylon.data.models.Status;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;

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


    @Before
    public void setup() {

        MockitoAnnotations.initMocks(this);

        mPostsListingViewModel = new PostsListingViewModel(mPostsRepository);
        mProgressIndicatorSubscriber = new TestObserver<>();
        mStatusSubscriber = new TestObserver<>();

    }

    @Test
    public void progressIndicator_emits_whenSubscribedToGetPosts() {
        when(mPostsRepository.getPosts()).thenReturn(Observable.error(new Exception()));
        when(mPostsRepository.getUsers()).thenReturn(Observable.error(new Exception()));
        when(mPostsRepository.getComments()).thenReturn(Observable.error(new Exception()));
        mPostsListingViewModel.getLoadingStatus().subscribe(mProgressIndicatorSubscriber);

        mPostsListingViewModel.getPostsData().subscribe();

        mProgressIndicatorSubscriber.assertValues(true, false);
    }


    @Test
    public void error_emits_whenError_whenRetrievingPosts() {
        when(mPostsRepository.getPosts()).thenReturn(Observable.error(new RuntimeException()));
        when(mPostsRepository.getUsers()).thenReturn(Observable.just(new ApiResponse(Status.SUCCESS)));
        when(mPostsRepository.getComments()).thenReturn(Observable.just(new ApiResponse(Status.SUCCESS)));

        mPostsListingViewModel.getErrors().subscribe(mStatusSubscriber);

        mPostsListingViewModel.getPostsData().subscribe();

        mStatusSubscriber.assertValue(Status.ERROR);
    }

}
