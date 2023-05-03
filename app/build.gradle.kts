//apply("$rootDir/config.gradle")

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "by.vfedorenko.mvistarter"
    compileSdk = 33

    defaultConfig {
        applicationId = "by.vfedorenko.mvistarter"
        minSdk = 26
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        val release by getting  {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
//        jvmTarget = '1.8'
        freeCompilerArgs = freeCompilerArgs.plus(
            listOf(
//                "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
//                "-opt-in=kotlinx.coroutines.FlowPreview",
//                "-opt-in=androidx.compose.ui.ExperimentalComposeUiApi",
                "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api",
//                "-opt-in=androidx.compose.foundation.ExperimentalFoundationApi",
//                "-opt-in=com.google.accompanist.permissions.ExperimentalPermissionsApi",
//                "-opt-in=androidx.compose.animation.ExperimentalAnimationApi",
            )
        )
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.6"
    }
    packaging {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
}

dependencies {
    implementation(project(":starter"))

    implementation(libs.accompanist.systemuicontroller)
    implementation(libs.androidx.core.splashscreen)

    implementation(libs.androidx.core.splashscreen)
    implementation(libs.bundles.core)
    implementation(libs.bundles.compose)
    implementation(libs.bundles.network)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.hilt.navigation.compose)

    implementation(libs.coil.kt)
    implementation(libs.timber)
}