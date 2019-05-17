package com.nayera.babylon.di;

import android.app.Application;
import android.content.Context;

import com.nayera.babylon.BuildConfig;
import com.nayera.babylon.api.APIInterface;
import com.nayera.babylon.data.remote.RemoteDataSource;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {

    Application mApplication;

    public AppModule(Application application) {
        mApplication = application;
    }

    @Provides
    Context provideContext() {
        return mApplication.getApplicationContext();
    }

    @Provides
    RemoteDataSource provideRemoteDataSource(APIInterface apiInterface) {
        return new RemoteDataSource(apiInterface);
    }


}
