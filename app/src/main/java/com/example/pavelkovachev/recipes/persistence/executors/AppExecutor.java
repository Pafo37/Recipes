package com.example.pavelkovachev.recipes.persistence.executors;

import java.util.concurrent.Executor;

public class AppExecutor {

    private final Executor executor;

    public AppExecutor(Executor executor) {
        this.executor = executor;
    }
}
