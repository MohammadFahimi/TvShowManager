object BuildsPlugin {
    const val androidApplication = "com.android.application"
    const val kotlinAndroid = "kotlin-android"
    const val androidLibrary = "com.android.library"
    const val javaLibrary = "java-library"
    const val kotlin = "kotlin"
    const val kapt = "kapt"
    const val jvm = "jvm"
//    const val navigationSageArg = "androidx.navigation.safeargs.kotlin"
    const val navigationSageArg = "androidx.navigation.safeargs"

    const val androidBuildTool = "com.android.tools.build:gradle:${Versions.ANDROID_GRADLE_PLUGIN}"
    const val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KOTLIN}"
    const val navigationSafeArgGradle = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.NAVIGATION}"
}