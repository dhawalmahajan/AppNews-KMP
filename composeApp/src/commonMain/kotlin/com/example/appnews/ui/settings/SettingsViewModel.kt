package com.example.appnews.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appnews.utils.AppPreference
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class SettingsViewModel(private val appPreference: AppPreference) : ViewModel() {
    private val _currentTheme: MutableStateFlow<String?> = MutableStateFlow(null)
    val currentTheme = _currentTheme.asStateFlow()

    init {
        currentThemeGet()
    }

    private fun currentThemeGet() = runBlocking {
        _currentTheme.update { appPreference.getTheme() }
    }

    fun changeThemeMode(value: String) {
        viewModelScope.launch(Dispatchers.IO) {
            appPreference.changeThemeMode(value)
            _currentTheme.update { value }
        }

    }
}