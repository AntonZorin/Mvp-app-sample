package com.az.template.di.modules;

import android.content.Context;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.az.template.common.Constants;
import com.az.template.di.qualifiers.ApplicationContext;

import java.io.File;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.az.template.common.Constants.DISK_CACHE_SIZE;

/**
 * Created: Zorin A.
 * Date: 30.01.2017.
 */
@Module
public class ApiModule {
    @Provides
    Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .baseUrl(Constants.BASE_URL)
                .build();

    }

    @Provides
    OkHttpClient provideOkHttpClient(@ApplicationContext Context context) {
        return new OkHttpClient.Builder()
                .cache(new Cache(new File(context.getCacheDir(), "http"), DISK_CACHE_SIZE))
                .build();
    }
}
