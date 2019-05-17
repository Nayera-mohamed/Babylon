package com.nayera.babylon.ui.common;


import java.util.concurrent.Callable;

import io.reactivex.Observable;

public class BaseRepository<T> {

    public Observable createObservable(T object)
    {
        return Observable.fromCallable(new Callable<T>() {
            @Override
            public T call() throws Exception {
                return object;
            }
        });
    }

}
