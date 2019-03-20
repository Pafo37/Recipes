package com.example.pavelkovachev.recipes.dagger.component;

import com.example.pavelkovachev.recipes.dagger.modules.ApplicationModule;
import com.example.pavelkovachev.recipes.dagger.modules.NetworkModule;
import com.example.pavelkovachev.recipes.dagger.modules.RoomModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {RoomModule.class, ApplicationModule.class, NetworkModule.class})
public interface AppComponent {

}