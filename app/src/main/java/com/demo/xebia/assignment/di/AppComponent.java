package com.demo.xebia.assignment.di;



import com.demo.xebia.assignment.views.news.NewsListFragment;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {AppModule.class, UtilsModule.class})
@Singleton
public interface AppComponent
{
    void doInjection(NewsListFragment newsListFragment);
}
