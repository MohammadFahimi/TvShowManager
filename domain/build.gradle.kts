plugins {
    id(BuildsPlugin.javaLibrary)
    id(BuildsPlugin.kotlin)
    kotlin(BuildsPlugin.kapt)
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    api(Libs.KOTLIN)
    api(Libs.KOTLINX_COROUTINE)
}