plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.android.library) apply false
    id("com.google.dagger.hilt.android") version "2.51.1" apply false
    id("org.jetbrains.kotlin.plugin.compose") version "2.1.0"
    id("org.jetbrains.kotlin.plugin.serialization") version "2.1.10" apply false
    id("com.google.devtools.ksp") version "2.0.21-1.0.27" apply false
}