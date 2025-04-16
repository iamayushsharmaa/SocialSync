import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinxSerialization)
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }
    
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    
    jvm()
    
    sourceSets {
        androidMain.dependencies {
            implementation(libs.koin.android)
            implementation (libs.koin.androidx.compose)
            implementation(libs.ktor.client.android)
            implementation( libs.androidx.lifecycle.runtime.ktx)
            implementation (libs.kotlinx.coroutines.android)
            implementation(libs.ktor.client.okhttp)

        }
        commonMain.dependencies {
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.auth)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.ktor.client.logging)
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)
            implementation(libs.lifecycle.viewmodel)
            implementation(libs.navigation.compose)
            api(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)
            implementation(libs.lifecycle.viewmodel)
            implementation(libs.navigation.compose)
            api(libs.permissions)
            api(libs.permissions.gallery)
            api(libs.permissions.compose)
            implementation(libs.kotlinx.datetime)

            implementation(libs.cmp.image.pick.n.crop)
            implementation(libs.calf.file.picker)
            implementation(libs.calf.file.picker.coil)
        }

        val jvmMain by getting {
            dependencies {
                implementation(libs.kotlinx.coroutines.core)
            }
        }
    }
}

android {
    namespace = "org.example.socialsync.shared"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}
