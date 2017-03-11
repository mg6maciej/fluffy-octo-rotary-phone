package pl.mg6.likeornot.infrastructure.retrofit

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import pl.mg6.likeornot.BuildConfig
import pl.mg6.likeornot.commons.Provider
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

abstract class AbstractRetrofitProvider(baseUrl: String) : Provider<Retrofit>({
    Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(OkHttpClient.Builder()
                    .apply { if (BuildConfig.DEBUG) addInterceptor(HttpLoggingInterceptor().setLevel(BODY)) }
                    .build())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
})
