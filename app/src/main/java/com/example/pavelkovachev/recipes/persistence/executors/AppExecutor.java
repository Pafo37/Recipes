package com.example.pavelkovachev.recipes.persistence.executors;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppExecutor {

    private static Executor executor = null;
    private static final Object LOCK = new Object();

    public synchronized static Executor getInstance() {
        if (executor == null) {
            synchronized (LOCK) {
                if (executor == null) {
                    executor = Executors.newSingleThreadExecutor();
                }
            }
        }
        return executor;
    }
}