plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.hoanganhdangcode.budgetchecker"
    compileSdk {
        version = release(36)
    }

    signingConfigs {
        create("release") {
            storeFile = file("../android.keystore")
            storePassword = System.getenv("KEYSTORE_PASSWORD") ?: "123456Aa@"
            keyAlias = System.getenv("KEY_ALIAS") ?: "android"
            keyPassword = System.getenv("KEY_PASSWORD") ?: "123456Aa@"
        }
    }
    defaultConfig {
        applicationId = "com.hoanganhdangcode.budgetchecker"
        minSdk = 24
        targetSdk = 36
        versionCode = System.getenv("GITHUB_RUN_NUMBER")?.toIntOrNull() ?: 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            // FIX QUAN TRỌNG: Gắn cấu hình ký vào bản build release
            signingConfig = signingConfigs.getByName("release")

            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}