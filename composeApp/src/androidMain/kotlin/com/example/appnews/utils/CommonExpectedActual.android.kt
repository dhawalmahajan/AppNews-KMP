package com.example.appnews.utils

import android.app.Activity
import android.content.Intent
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import java.util.UUID
import kotlin.uuid.ExperimentalUuidApi

actual fun getType(): Type {
    return Type.Mobile
}

@OptIn(ExperimentalUuidApi::class)
actual fun getRandomId(): String {
    return UUID.randomUUID().toString()
}

actual fun shareLink(url: String) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, url)
    }
    val intentChooser = Intent.createChooser(intent, "Share Link")
    activityProvider.invoke().startActivity(intentChooser)

}

private var activityProvider: () -> Activity = {
    throw IllegalStateException("Not initialized yet")
}

fun setActivityProvider(provider: () -> Activity) {
    activityProvider = provider
}

actual fun dataStorePreference(): DataStore<Preferences> {
    return AppSettings.getDataStore(producerPath = {
        activityProvider.invoke().filesDir
            .resolve(dataStoreFileName)
            .absolutePath
    })
}