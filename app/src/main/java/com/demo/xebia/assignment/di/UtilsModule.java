package com.demo.xebia.assignment.di;


import androidx.lifecycle.ViewModelProvider;

import com.demo.xebia.assignment.datasource.apis.RestApiService;
import com.demo.xebia.assignment.datasource.tools.ToStringConverterFactory;
import com.demo.xebia.assignment.utils.ViewModelFactory;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.concurrent.TimeUnit;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import static com.demo.xebia.assignment.utils.Consts.BASE_URL;

@Module
public class UtilsModule
{

    @Provides
    @Singleton
    Gson provideGson()
    {
        GsonBuilder builder =
                new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return builder.setLenient().create();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient)
    {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .addConverterFactory(new ToStringConverterFactory())
                .build();
    }

    @Provides
    @Singleton
    RestApiService getApiCallInterface(Retrofit retrofit) {
        return retrofit.create(RestApiService.class);
    }

    @Provides
    @Singleton
    OkHttpClient getRequestHeader()
    {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(chain -> {
            Request original = chain.request();
            Request request = original.newBuilder().build();
                     return chain.proceed(request);})
                .connectTimeout(100, TimeUnit.SECONDS)
                .writeTimeout(100, TimeUnit.SECONDS)
                .readTimeout(300, TimeUnit.SECONDS);
        return httpClient.build();
    }

    @Provides
    @Singleton
    ViewModelProvider.Factory getViewModelFactory(RestApiService myRepository)
    {
        return new ViewModelFactory(myRepository);
    }
}