plugins {
    id(BuildsPlugin.javaLibrary)
    id(BuildsPlugin.kotlin)
    id(BuildsPlugin.apollo).version(Versions.apollo)
    kotlin(BuildsPlugin.kapt)
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    api(project(":domain"))

    implementation(Libs.KOIN)
    implementation(Libs.APOLLO_RUNTIME)
    implementation(Libs.APOLLO_COROUTINE)
    implementation(Libs.OKHTTP_LOGGING_INTERCEPTOR)
}
apollo {
    generateKotlinModels.set(true)
}