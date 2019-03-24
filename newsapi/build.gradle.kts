plugins {
    kotlin("jvm")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    api(project(":data"))
    implementation("com.google.code.gson:gson:2.8.5")
    implementation("com.squareup.retrofit2:retrofit:2.5.1-SNAPSHOT")
    implementation("com.squareup.retrofit2:converter-gson:2.5.1-SNAPSHOT")
}
