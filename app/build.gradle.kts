plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlinx-serialization")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdk = 32

    defaultConfig {
        applicationId = "com.acg.mangalive"
        minSdk = 26
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")

        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(mapOf("path" to ":data")))
    implementation(project(mapOf("path" to ":domain")))
    implementation(project(mapOf("path" to ":share")))

    //noinspection GradleDependency
    implementation("androidx.core:core-ktx:1.8.0")
    implementation("androidx.appcompat:appcompat:1.5.0")
    implementation("androidx.activity:activity-ktx")
    implementation("androidx.fragment:fragment-ktx:1.5.2")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment-ktx:2.5.1")
    implementation("androidx.navigation:navigation-ui-ktx:2.5.1")
    implementation("com.google.android.material:material:1.6.1")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.2.0")

    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.5.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")

    implementation("androidx.paging:paging-runtime-ktx:3.1.1")

    implementation("com.google.dagger:dagger:2.40.5")
    implementation("com.google.dagger:dagger-android:2.40.5")
    implementation("com.google.dagger:dagger-android-support:2.40.5")

    kapt("com.google.dagger:dagger-compiler:2.40.5")
    kapt("com.google.dagger:dagger-android-processor:2.40.5")

    testImplementation("junit:junit:4.13.2")

    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}