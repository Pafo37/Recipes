package com.example.pavelkovachev.recipes;

import android.app.Application;

import com.example.pavelkovachev.recipes.dagger.component.AppComponent;
import com.example.pavelkovachev.recipes.dagger.component.DaggerAppComponent;
import com.example.pavelkovachev.recipes.dagger.modules.ApplicationModule;
import com.example.pavelkovachev.recipes.dagger.modules.NetworkModule;
import com.example.pavelkovachev.recipes.dagger.modules.RoomModule;

public class App extends Application {

    private static App instance;

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static App getInstance() {
        return instance;
    }

    public AppComponent getAppComponent() {
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .roomModule(new RoomModule(this))
                    .networkModule(new NetworkModule())
                    .build();
        }
        return appComponent;
    }
}