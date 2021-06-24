package kudesnjk.currency.data

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

interface ServiceFactory {
    fun <T> createService(clazz: Class<T>) : T
}

class ServiceFactoryImpl @Inject constructor() : ServiceFactory {
    companion object {
        private const val ENDPOINT = "http://10.0.2.2:8080"
    }

    private val gson : Gson by lazy { GsonBuilder().create() }

    private val converterFactory: Converter.Factory by lazy {
        GsonConverterFactory.create(gson)
    }

    private val loggingInterceptor: HttpLoggingInterceptor by lazy {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    private val callAdapter : CallAdapter.Factory by lazy {
        RxJava2CallAdapterFactory.create()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(ENDPOINT)
            .addCallAdapterFactory(callAdapter)
            .addConverterFactory(converterFactory)
            .client(okHttpClient)
            .build()
    }

    override fun <T> createService(clazz: Class<T>): T =
        retrofit.create(clazz)
}