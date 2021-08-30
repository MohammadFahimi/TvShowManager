plugins {
    id(BuildsPlugin.androidApplication)
    id(BuildsPlugin.kotlinAndroid)
    id(BuildsPlugin.apollo).version(Versions.apollo)
    kotlin(BuildsPlugin.kapt)
    id(BuildsPlugin.navigationSageArg)
}

android {
    compileSdkVersion(Versions.COMPILE_SDK)
    defaultConfig {
        applicationId = "com.mfahimi.tvshowmanager"
        minSdkVersion(Versions.MIN_SDK)
        targetSdkVersion(Versions.TARGET_SDK)
        versionCode = Versions.versionCodeMobile
        versionName = Versions.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        getByName("debug") {
            versionNameSuffix = "-debug"
            applicationIdSuffix = ".debug"
            isDebuggable = true
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        val options = this as org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions
        options.jvmTarget = "1.8"
    }
    buildFeatures.viewBinding = true

    flavorDimensions(Flavor.DIMENSION_MARKET, Flavor.DIMENSION_VERSION)
    productFlavors {
        create("demo") {
            dimension = Flavor.DIMENSION_VERSION
            buildConfigField("String", "BASE_URL", "${Config.DEMO_BASE_URL}")
        }
        create("product") {
            dimension = Flavor.DIMENSION_VERSION
            buildConfigField("String", "BASE_URL", "${Config.PRODUCT_BASE_URL}")
        }
        create("playStore") {
            dimension = Flavor.DIMENSION_MARKET
        }
    }
}

dependencies {

    implementation(Libs.KOTLINX_COROUTINE_ANDROID)

    implementation(Libs.KOIN_ANDROID)
    implementation(Libs.KOIN_ANDROID_VIEWMODEL)

    implementation(Libs.CORE_KTX)
    implementation(Libs.APPCOMPAT)
    implementation(Libs.MATERIAL)
    implementation(Libs.CONSTRAINT_LAYOUT)
    implementation(Libs.FRAGMENT_KTX)
    implementation(Libs.RECYCLERVIEW)

    implementation(Libs.NAVIGATION)
    implementation(Libs.NAVIGATION_UI)

}
apollo {
    generateKotlinModels.set(true)
}
