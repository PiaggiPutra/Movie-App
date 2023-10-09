package id.android.codebase.di

import id.android.codebase.data.remote.ApiService
import id.android.codebase.data.remote.MovieDataSource
import id.android.codebase.utils.OAuthInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.chuckerteam.chucker.api.ChuckerInterceptor.Builder as ChuckerBuilder

fun createRemoteModule(baseUrl: String, accessToken : String) = module {

    factory<Interceptor> {
        HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.HEADERS)
    }

    factory {
        OkHttpClient.Builder()
            .addInterceptor(OAuthInterceptor("Bearer",accessToken))
            .addInterceptor(get<Interceptor>())
            .addInterceptor(ChuckerBuilder(androidApplication().applicationContext).build())
            .build()
    }

    single {
        Retrofit.Builder()
            .client(get())
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    factory { get<Retrofit>().create(ApiService::class.java) }

    factory { MovieDataSource(get()) }
}
