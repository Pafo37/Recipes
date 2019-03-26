package com.example.pavelkovachev.recipes.persistence.executors;

import java.util.concurrent.Executor;

import javax.inject.Singleton;

@Singleton
public class AppExecutor implements Executor {

    private Executor executor;

    public AppExecutor(Executor executor) {
        this.executor = executor;
    }

    @Override
    public void execute(Runnable command) {
        executor.execute(command);
    }
}