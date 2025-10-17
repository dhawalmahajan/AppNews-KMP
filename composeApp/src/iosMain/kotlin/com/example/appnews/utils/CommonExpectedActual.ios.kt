package com.example.appnews.utils

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.appnews.data.database.NewsDatabase
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSHomeDirectory
import platform.Foundation.NSUUID
import platform.Foundation.NSUserDomainMask
import platform.UIKit.UIActivityViewController
import platform.UIKit.UIApplication

actual fun getType(): Type {
    return Type.Mobile
}

actual fun getRandomId(): String {
    return NSUUID().UUIDString()
}

actual fun shareLink(url: String) {
    val currentViewController = UIApplication.sharedApplication().keyWindow?.rootViewController
    val activityViewController = UIActivityViewController(listOf(url), null)
    currentViewController?.presentViewController(
        viewControllerToPresent = activityViewController,
        animated = true,
        completion = null
    )
}

@OptIn(ExperimentalForeignApi::class)
actual fun dataStorePreference(): DataStore<Preferences> {
    return AppSettings.getDataStore(
        producerPath = {
            val documentDirectory = NSFileManager.defaultManager.URLForDirectory(
                directory = NSDocumentDirectory,
                inDomain = NSUserDomainMask,
                appropriateForURL = null,
                create = false,
                error = null
            )
            requireNotNull(documentDirectory).path + "/$dataStoreFileName"
        }
    )
}

actual fun getDatabaseBuilder(): RoomDatabase.Builder<NewsDatabase> {
    val dbFilePath = NSHomeDirectory() + "/$DB_NAME"
    return Room.databaseBuilder<NewsDatabase>(
        name = dbFilePath,
    )
}