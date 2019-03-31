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
import java.io.FileInputStream
import java.util.*

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdkVersion(28)
    buildToolsVersion = "28.0.3"
    defaultConfig {
        applicationId = "io.devbits.newsfeed"
        minSdkVersion(16)
        targetSdkVersion(28)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        val secretsProperties = File("secrets.properties")
        if (secretsProperties.exists()) {
            val secretsFile = rootProject.file("secrets.properties")
            val secrets = Properties()
            secrets.load(FileInputStream(secretsFile))

            buildConfigField("String", "GUARDIAN_API_KEY", secrets.getProperty("GUARDIAN_API_KEY"))
        } else {
            buildConfigField("String", "GUARDIAN_API_KEY", "\"View the README to add your API Key\"")
        }
    }
    buildTypes {
        getByName("debug") {
            isShrinkResources = false
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        targetCompatibility = JavaVersion.VERSION_1_8
        sourceCompatibility = JavaVersion.VERSION_1_8
    }
    // This is a fix for Coroutines 1.2.0-alpha
	packagingOptions {
        exclude("META-INF/atomicfu.kotlin_module")
	}
}

dependencies {
    implementation(project(":guardianapi"))
    implementation(project(":newsapi"))
    implementation(project(":cache"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.3.21")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.2.0-alpha")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.2.0-alpha")
    implementation("org.koin:koin-android:1.0.2")
    implementation("org.koin:koin-androidx-scope:1.0.2")
    implementation("org.koin:koin-androidx-viewmodel:1.0.2")
    implementation("androidx.activity:activity-ktx:1.0.0-alpha05")
    implementation("androidx.fragment:fragment-ktx:1.1.0-alpha05")
    implementation("com.google.android.material:material:1.1.0-alpha04")
    implementation("androidx.recyclerview:recyclerview:1.1.0-alpha03")
    implementation("androidx.browser:browser:1.0.0")
    implementation("androidx.lifecycle:lifecycle-extensions:2.1.0-alpha03")
    kapt("androidx.lifecycle:lifecycle-compiler:2.1.0-alpha03")
    implementation("androidx.paging:paging-runtime-ktx:2.1.0")
    implementation("androidx.room:room-runtime:2.1.0-alpha06")
    implementation("androidx.room:room-ktx:2.1.0-alpha06")
    kapt("androidx.room:room-compiler:2.1.0-alpha06")
    implementation("android.arch.work:work-runtime-ktx:1.0.0")
    implementation("androidx.navigation:navigation-fragment-ktx:2.0.0")
    implementation("androidx.navigation:navigation-ui-ktx:2.0.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.0-alpha3")
	implementation("androidx.viewpager2:viewpager2:1.0.0-alpha02")
    implementation("com.github.bumptech.glide:glide:4.9.0")
    kapt("com.github.bumptech.glide:compiler:4.9.0")
    implementation("com.squareup.retrofit2:retrofit:2.5.0")
    implementation("com.squareup.retrofit2:converter-gson:2.5.0")
    implementation("com.squareup.okhttp3:logging-interceptor:3.13.1")
    implementation("net.danlew:android.joda:2.9.9.3")
    testImplementation("androidx.room:room-testing:2.1.0-alpha06")
}
