package com.kyawhut.codetest.order.data.di

import com.kyawhut.codetest.order.BuildConfig
import com.kyawhut.codetest.order.data.network.API
import com.kyawhut.codetest.share.network.interceptor.ConnectionInterceptor
import com.kyawhut.codetest.share.network.utils.NetworkUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

/**
 * @author kyawhtut
 * @date 11/8/21
 */
@Module()
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        )
    }

    @Provides
    @Singleton
    fun provideAPI(
        connectionInterceptor: ConnectionInterceptor,
        loggingInterceptor: HttpLoggingInterceptor,
    ): API {
        return NetworkUtil.createService(
            API::class,
            BuildConfig.BASE_URL,
            listOf(connectionInterceptor, loggingInterceptor)
        )
    }
}
