package org.example.socialsync

import android.os.Build
import org.example.socialsync.app.Platform

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

//actual fun getPlatform(): Platform = AndroidPlatform()