package com.example.appnews.di

import com.example.appnews.ui.articleDetail.ArticleDetailViewModel
import com.example.appnews.ui.bookmark.BookmarkViewModel
import com.example.appnews.ui.headline.HeadlineViewModel
import com.example.appnews.ui.search.SearchViewModel
import com.example.appnews.ui.settings.SettingsViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

actual val viewModelModule = module {

    factoryOf(::HeadlineViewModel)
    factoryOf(::SearchViewModel)
    factoryOf(::BookmarkViewModel)
    factoryOf(::ArticleDetailViewModel)
    factoryOf(::SettingsViewModel)


}