package org.example.socialsync

import android.os.Build
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.okhttp.OkHttp
import org.example.socialsync.app.Platform

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"

}

//actual fun httpClient(config: HttpClientConfig<*>.() -> Unit): HttpClient {
//    return HttpClient(OkHttp) {
//        config()
//        // Add common configuration here if needed
//    }
//}
//actual fun getPlatform(): Platform = AndroidPlatform()