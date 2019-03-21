package com.example.pavelkovachev.recipes.dagger.modules;

import android.app.Application;

import com.example.pavelkovachev.recipes.persistence.executors.AppExecutor;

import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private final Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    Application provideApplication() {
        return application;
    }

    @Singleton
    @Provides
    AppExecutor provideAppExector() {
        return new AppExecutor(Executors.newSingleThreadExecutor());
    }
}