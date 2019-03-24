plugins {
    kotlin("jvm")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("com.google.code.gson:gson:2.8.5")
    implementation("com.squareup.retrofit2:retrofit:2.5.1-SNAPSHOT")
    implementation("com.squareup.retrofit2:converter-gson:2.5.1-SNAPSHOT")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.1.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.1.1")
}
