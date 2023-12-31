package id.android.codebase.di

import id.android.codebase.data.repository.AppDispatchers
import id.android.codebase.data.repository.MovieRepository
import id.android.codebase.data.repository.MovieRepositoryImpl
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val repositoryModule = module {
    factory { AppDispatchers(Dispatchers.Main, Dispatchers.IO) }
    factory<MovieRepository> { MovieRepositoryImpl(get(), get()) }

}
