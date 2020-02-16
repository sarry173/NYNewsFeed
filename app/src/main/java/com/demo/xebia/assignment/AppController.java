package com.demo.xebia.assignment;

import android.app.Application;
import android.content.Context;

import com.demo.xebia.assignment.di.AppComponent;
import com.demo.xebia.assignment.di.AppModule;
import com.demo.xebia.assignment.di.DaggerAppComponent;
import com.demo.xebia.assignment.di.UtilsModule;


public class AppController extends Application
{
    AppComponent appComponent;
    Context context;
    private static AppController mInstance;

    public static AppController getmInstance()
    {
        return mInstance;
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        mInstance = this;
        context = this;
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).utilsModule(new UtilsModule()).build();
    }

    public AppComponent getAppComponent()
    {
        if (appComponent==null)
        {
            appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).utilsModule(new UtilsModule()).build();
        }
        return appComponent;
    }

    @Override
    protected void attachBaseContext(Context context)
    {
        super.attachBaseContext(context);
    }
}

