package com.sungbin.kakaoemoticonparser.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton


/**
 * Created by SungBin on 2020-08-13.
 */

@Module
@InstallIn(ApplicationComponent::class)
object EmoticonModule {

    private var defaultOkHttpClient = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.MINUTES)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)

    @Singleton
    @Named("SEARCH")
    @Provides
    fun provideSearchInstance() = Retrofit.Builder()
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .baseUrl("https://e.kakao.com/")
        .client(defaultOkHttpClient.build())
        .build()
}
