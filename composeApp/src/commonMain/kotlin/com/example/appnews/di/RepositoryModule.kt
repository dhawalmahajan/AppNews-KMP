package com.example.appnews.di

import com.example.appnews.data.database.NewsDatabase
import com.example.appnews.data.repository.LocalNewsRepository
import com.example.appnews.data.repository.OnlineNewsRepository
import org.koin.dsl.module

val repositoryModule = module {
    single {
        OnlineNewsRepository(get())
    }
    single {
        LocalNewsRepository(get<NewsDatabase>().newsDao())
    }
}