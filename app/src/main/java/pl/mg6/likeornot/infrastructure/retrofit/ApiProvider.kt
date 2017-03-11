package pl.mg6.likeornot.infrastructure.retrofit

import pl.mg6.likeornot.commons.Provider

abstract class ApiProvider<T>(clazz: Class<T>) : Provider<T>({ RetrofitProvider.get().create(clazz) })
