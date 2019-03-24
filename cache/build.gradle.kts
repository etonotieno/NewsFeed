plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    compileSdkVersion(28)
    buildToolsVersion = "28.0.3"
    defaultConfig {
        minSdkVersion(16)
        targetSdkVersion(28)
    }

    compileOptions {
        targetCompatibility = JavaVersion.VERSION_1_8
        sourceCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation(project(":data"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.3.21")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.1.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.1.1")
    implementation("org.koin:koin-android:1.0.2")
    implementation("org.koin:koin-androidx-scope:1.0.2")
    implementation("org.koin:koin-androidx-viewmodel:1.0.2")
    implementation("androidx.lifecycle:lifecycle-extensions:2.1.0-alpha03")
    kapt("androidx.lifecycle:lifecycle-compiler:2.1.0-alpha03")
    implementation("androidx.room:room-runtime:2.1.0-alpha05")
    implementation("androidx.room:room-ktx:2.1.0-alpha05")
    kapt("androidx.room:room-compiler:2.1.0-alpha05")
    implementation("android.arch.work:work-runtime-ktx:1.0.0")
    testImplementation("androidx.room:room-testing:2.1.0-alpha05")
}