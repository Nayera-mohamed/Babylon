package com.nayera.babylon.di;

import com.nayera.babylon.api.APIInterface;
import com.nayera.babylon.ui.posts.postslisting.PostsListingFragment;
import com.nayera.babylon.ui.posts.postslisting.PostsListingModule;
import com.nayera.babylon.ui.posts.postslisting.PostsListingRepository;
import com.nayera.babylon.ui.posts.postslisting.PostsListingViewModel;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class,
        RetrofitModule.class,
        PostsListingModule.class
})
public interface ApplicationComponent {

    void inject(PostsListingFragment postsListingFragment);


}
