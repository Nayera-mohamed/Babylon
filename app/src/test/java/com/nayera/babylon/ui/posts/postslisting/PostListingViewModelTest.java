package com.nayera.babylon.ui.posts.postslisting;

import com.google.common.collect.Lists;
import com.nayera.babylon.BabylonApplication;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.*;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.observers.TestObserver;
import io.reactivex.subscribers.TestSubscriber;

import static org.mockito.Mockito.when;

public class PostListingViewModelTest {


    @Mock
    private PostsListingRepository mPostsRepository;

    @Mock
    private BabylonApplication babylonApplication;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    PostsListingViewModel mPostsListingViewModel;
    private TestObserver<Boolean> mProgressIndicatorSubscriber;


    @Before
    public void setupTasksPresenter() {
        mPostsListingViewModel = new PostsListingViewModel(babylonApplication);
        mProgressIndicatorSubscriber = new TestObserver<>();

    }

    @Test
    public void progressIndicator_emits_whenSubscribedToTasks() {
        when(mPostsRepository.getPosts()).thenReturn(Observable.never());
        mPostsListingViewModel.getLoadingStatus().subscribe(mProgressIndicatorSubscriber);

        mPostsListingViewModel.getPostsData().subscribe();

        // The progress indicator emits initially false and then true
        mProgressIndicatorSubscriber.assertValues(false, true);
    }

}
