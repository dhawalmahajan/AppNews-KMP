package com.example.appnews.utils

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class AppPreference(
    private val dataStore: DataStore<Preferences>
) {
    private val themeKey = stringPreferencesKey("theme")

    suspend fun getTheme(): String? =
        dataStore.data.map {
            it[themeKey] ?: Theme.DARK_MODE.name
        }.first()

    suspend fun changeThemeMode(value: String) =
        dataStore.edit {
            it[themeKey] = value
        }


}