package com.nayera.babylon.di;

import com.nayera.babylon.api.APIInterface;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, RetrofitModule.class})
public interface ApplicationComponent {
    public APIInterface getApiInterface();
}
