package pl.mg6.likeornot.infrastructure.retrofit

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.schedulers.Schedulers
import pl.mg6.likeornot.commons.Provider
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitProvider : Provider<Retrofit>({
    Retrofit.Builder()
            .baseUrl("https://raw.githubusercontent.com/mg6maciej/fluffy-octo-rotary-phone/master/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
})
