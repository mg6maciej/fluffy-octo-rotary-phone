package pl.mg6.likeornot

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitProvider {

    private val retrofit by lazy(this::createRetrofit)
    var override: (() -> Nothing)? = null

    private fun createRetrofit(): Retrofit = Retrofit.Builder()
            .baseUrl("https://raw.githubusercontent.com/mg6maciej/fluffy-octo-rotary-phone/master/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun get() = override?.invoke() ?: retrofit
}
