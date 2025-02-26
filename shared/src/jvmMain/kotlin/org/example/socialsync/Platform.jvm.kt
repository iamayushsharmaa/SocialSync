package org.example.socialsync

import org.example.socialsync.app.Platform

class JVMPlatform: Platform {
    override val name: String = "Java ${System.getProperty("java.version")}"
}

//actual fun getPlatform(): Platform = JVMPlatform()