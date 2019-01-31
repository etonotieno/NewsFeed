/*
 *  Copyright (C) 2018 Eton Otieno Oboch
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
}

android {
    compileSdkVersion(28)
    buildToolsVersion = "28.0.3"
    defaultConfig {
        applicationId = "com.edoubletech.newsfeed"
        minSdkVersion(16)
        targetSdkVersion(28)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        forEach {
            it.buildConfigField("String", "GUARDIAN_API_KEY",
                    "${project.properties["GUARDIAN_API_KEY"]}")
        }
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(project(":guardian"))

    // Kotlin
    implementation(kotlin("stdlib-jdk8"))
    
    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.1.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.1.1")

    // Koin for Android
    implementation("org.koin:koin-android:1.0.1")
    implementation("org.koin:koin-androidx-scope:1.0.1")
    implementation("org.koin:koin-androidx-viewmodel:1.0.1")

    implementation("com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2")
    implementation("ru.gildor.coroutines:kotlin-coroutines-retrofit:0.13.0-eap13")

    // Support Libraries
    implementation("androidx.appcompat:appcompat:1.0.2")
    implementation("androidx.cardview:cardview:1.0.0")
    implementation("com.google.android.material:material:1.0.0")
    implementation("androidx.recyclerview:recyclerview:1.0.0")
    implementation("androidx.browser:browser:1.0.0")

    // ViewModel and LiveData
    implementation("androidx.lifecycle:lifecycle-extensions:2.0.0")

    // Paging
    implementation("androidx.paging:paging-runtime-ktx:2.1.0-rc01")

    // Room
    implementation("androidx.room:room-runtime:2.1.0-alpha03")
    kapt("androidx.room:room-compiler:2.1.0-alpha03")

    // WorkManager
    implementation("android.arch.work:work-runtime-ktx:1.0.0-beta02")

    // ConstraintLayout
    implementation("androidx.constraintlayout:constraintlayout:2.0.0-alpha3")

    // Picasso
    implementation("com.squareup.picasso:picasso:2.71828")

    // Timber for logging events
    implementation("com.jakewharton.timber:timber:4.7.1")

    // Retrofit for making the network calls
    implementation("com.squareup.retrofit2:retrofit:2.5.0")
    implementation("com.squareup.retrofit2:converter-gson:2.5.0")
    implementation("com.squareup.okhttp3:logging-interceptor:3.10.0")
    implementation("com.google.code.gson:gson:2.8.5")

    // Joda Time
    implementation("net.danlew:android.joda:2.9.9.3")
}
