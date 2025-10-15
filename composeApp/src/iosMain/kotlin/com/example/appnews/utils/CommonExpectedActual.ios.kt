package com.example.appnews.utils

import  platform.Foundation.NSUUID
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