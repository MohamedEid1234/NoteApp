plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id ("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
    alias(libs.plugins.kotlin.compose)



}

android {
    namespace = "er.codeforegypt.noteappmvvm"
    compileSdk = 35

    defaultConfig {
        applicationId = "er.codeforegypt.noteappmvvm"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "er.codeforegypt.noteappmvvm.HiltTestRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
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
    buildFeatures {
        compose = true
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.support.annotations)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    ksp(libs.hilt.android.compiler)
    ksp(libs.androidx.hilt.compiler)
    implementation(libs.hilt.android)
    //room dependency
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)
    //COROUTINE DEPENDENCY
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.androidx.navigation.compose)

    //compose dependencies
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.hilt.navigation.compose)
//i want dependency of scaffold state
    implementation(libs.androidx.material)
    implementation(libs.androidx.material.icons.extended)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    // Local unit tests
    testImplementation("androidx.test:core:1.6.1")
    testImplementation(libs.junit) //test framwork responsible of running test cases
    testImplementation("androidx.arch.core:core-testing:2.2.0")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.9.0")
    testImplementation("com.google.truth:truth:1.1.3")
    testImplementation("com.squareup.okhttp3:mockwebserver:4.9.1")
    testImplementation("io.mockk:mockk:1.13.2")
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.7.6")

// Instrumentation tests
    androidTestImplementation("com.google.dagger:hilt-android-testing:2.37")
    kspAndroidTest(libs.hilt.android.compiler)
    androidTestImplementation(libs.junit)
    androidTestImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.9.0")
    androidTestImplementation("androidx.arch.core:core-testing:2.2.0")
    androidTestImplementation("com.google.truth:truth:1.1.3")
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation("androidx.test:core-ktx:1.6.1")
    androidTestImplementation("com.squareup.okhttp3:mockwebserver:4.9.1")
    androidTestImplementation("io.mockk:mockk-android:1.10.5")
    androidTestImplementation("androidx.test:runner:1.6.2")
    androidTestImplementation ("com.linkedin.dexmaker:dexmaker:2.28.1") // Latest available version
    androidTestImplementation ("com.linkedin.dexmaker:dexmaker-mockito:2.28.1")

/*
* // Local unit tests
testImplementation("androidx.test:core:1.4.0")
testImplementation("junit:junit:4.13.2")
testImplementation("androidx.arch.core:core-testing:2.1.0")
testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.5.1")
testImplementation("com.google.truth:truth:1.1.3")
testImplementation("com.squareup.okhttp3:mockwebserver:4.9.1")
testImplementation("io.mockk:mockk:1.10.5")
debugImplementation("androidx.compose.ui:ui-test-manifest:1.1.0-alpha04")

// Instrumentation tests
androidTestImplementation("com.google.dagger:hilt-android-testing:2.37")
kaptAndroidTest("com.google.dagger:hilt-android-compiler:2.37")
androidTestImplementation("junit:junit:4.13.2")
androidTestImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.5.1")
androidTestImplementation("androidx.arch.core:core-testing:2.1.0")
androidTestImplementation("com.google.truth:truth:1.1.3")
androidTestImplementation("androidx.test.ext:junit:1.1.3")
androidTestImplementation("androidx.test:core-ktx:1.4.0")
androidTestImplementation("com.squareup.okhttp3:mockwebserver:4.9.1")
androidTestImplementation("io.mockk:mockk-android:1.10.5")
androidTestImplementation("androidx.test:runner:1.4.0")
*/


}





