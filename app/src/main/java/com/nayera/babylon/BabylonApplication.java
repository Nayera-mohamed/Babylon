package com.nayera.babylon;

import android.app.Application;

import com.nayera.babylon.di.AppModule;
import com.nayera.babylon.di.ApplicationComponent;
import com.nayera.babylon.di.DaggerApplicationComponent;
import com.nayera.babylon.di.RetrofitModule;

public class BabylonApplication extends Application {

    ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerApplicationComponent.builder()
                .retrofitModule(new RetrofitModule())
                .appModule(new AppModule(this))
                .build();

    }

    public ApplicationComponent getApplicationComponent() {
        return component;
    }
}
