package com.example.appnews.di

import com.example.appnews.utils.AppPreference
import com.example.appnews.utils.dataStorePreference
import com.example.appnews.utils.getDatabaseBuilder
import com.example.appnews.utils.getRoomDatabase
import org.koin.dsl.module

val databseModule = module {
    single {
        getRoomDatabase(getDatabaseBuilder())
    }
    single {
        AppPreference(dataStorePreference())
    }
}