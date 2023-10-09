package id.android.codebase.di

import id.android.codebase.BuildConfig

val appComponent = listOf(createRemoteModule(BuildConfig.BASE_URL, BuildConfig.ACCESS_TOKEN), repositoryModule, localModule, featuresModule)
