package com.demo.xebia.assignment.datasource.apis;

import com.demo.xebia.assignment.datasource.tools.ToStringConverterFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import static com.demo.xebia.assignment.utils.Consts.BASE_URL;

@Module
public class RetrofitInstance
{
    private static Retrofit retrofit = null;

    @Provides
    public static RestApiService getApiService() {

        if (retrofit == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(new ToStringConverterFactory())
                    .build();
        }
        return retrofit.create(RestApiService.class);
    }
}