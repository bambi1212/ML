plugins {
    alias(libs.plugins.androidApplication)
}

android {
    namespace = "com.example.ml"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.ml"
        minSdk = 33
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)

    // Object detection & tracking feature
    //implementation("com.google.mlkit:object-detection-custom:17.0.1")
    //implementation("com.google.mlkit:object-detection:17.0.1")

    //image classification
    implementation("com.google.mlkit:image-labeling:17.0.8")

    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
        //added by me




}

