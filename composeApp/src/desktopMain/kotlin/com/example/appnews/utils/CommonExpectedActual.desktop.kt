package com.example.appnews.utils

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import java.awt.Toolkit
import java.awt.datatransfer.StringSelection
import java.util.UUID

actual fun getType(): com.example.appnews.utils.Type {
    return Type.Desktop
}

actual fun getRandomId(): String {
    return UUID.randomUUID().toString()
}

actual fun shareLink(url: String) {
    // Implement sharing functionality for desktop if needed
    val clipboard = Toolkit.getDefaultToolkit().systemClipboard
    clipboard.setContents(StringSelection(url), null)
}

actual fun dataStorePreference(): DataStore<Preferences> {
    return AppSettings.getDataStore {
        dataStoreFileName
    }
}