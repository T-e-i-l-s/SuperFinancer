plugins {
    id("kotlin-kapt")
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
    id("com.android.library")
    id("org.jetbrains.kotlin.plugin.compose") version "2.1.0"
    alias(libs.plugins.jetbrains.kotlin.android)
    kotlin("plugin.serialization")
}

android {
    namespace = "com.mustafin.main_menu_feature"
    compileSdk = 35

    defaultConfig {
        minSdk = 26
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":ui-components"))
    implementation(project(":web-view-feature"))
    implementation(project(":feed-feature"))
    implementation(project(":network"))
    implementation(project(":analytics"))

    // Serialization dependency
    implementation(libs.kotlinx.serialization.json)

    // Room dependencies
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.core.i18n)
    ksp(libs.androidx.room.compiler)

    // Coil dependencies
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)

    // Dagger Hilt dependency
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    implementation(libs.androidx.hilt.navigation.compose)

    // AndroidX core and lifecycle
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.lifecycle.runtime.compose)

    // Compose dependencies
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.compose)

    // Test dependencies
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}