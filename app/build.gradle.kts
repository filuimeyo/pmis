

/*

plugins {
    id ("com.android.application")
    id ("org.jetbrains.kotlin.android")
    id ("kotlin-kapt")
    id ("com.google.dagger.hilt.android")
}


android {
    namespace = "com.example.nikakudirko.myapplication"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.nikakudirko.myapplication"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_1_9
        targetCompatibility = JavaVersion.VERSION_1_9
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)


    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    implementation ("com.google.android.material:material:1.9.0")
    implementation("androidx.navigation:navigation-compose:2.7.3")

    implementation ("androidx.compose.material:material-icons-extended:1.5.2")

    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
    implementation ("com.google.dagger:hilt-android:2.44.2")

    kapt ("com.google.dagger:hilt-compiler:2.44.2")


    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")

    //collectAsStateWithLifecycle()
    implementation ("androidx.lifecycle:lifecycle-runtime-compose:2.6.1")

    //COMPOSE NAVIGATION
    implementation ("androidx.hilt:hilt-navigation-compose:1.0.0")
    implementation ("androidx.navigation:navigation-compose:2.6.0")
    implementation ("androidx.navigation:navigation-runtime-ktx:2.6.0")

//debugImplementation(libs.compose.tooling) // androidx.compose.ui:ui-tooling
}

*/


plugins {
    id ("com.android.application")
    id ("org.jetbrains.kotlin.android")
    id ("kotlin-kapt")
    id ("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.nikakudirko.myapplication"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.nikakudirko.myapplication"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "0.2.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        viewBinding = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.6"
    }

}


dependencies {
    /* implementation("androidx.hilt:hilt-navigation-fragment:1.0.0")
     annotationProcessor("androidx.hilt:hilt-compiler:1.0.0")
     implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.2")
     implementation("androidx.compose.runtime:runtime-livedata:1.3.2")
     implementation ("androidx.room:room-runtime:2.5.1")
     implementation ("androidx.room:room-ktx:2.5.1")
     implementation ("androidx.navigation:navigation-runtime-ktx:2.6.0")
     implementation(libs.core.ktx)
     implementation(libs.lifecycle.runtime.ktx)
     implementation(libs.activity.compose)
     implementation(platform(libs.compose.bom))
     implementation(libs.ui)
     implementation(libs.ui.graphics)
     implementation(libs.ui.tooling.preview)
     testImplementation(libs.junit)
     androidTestImplementation(libs.androidx.test.ext.junit)
     androidTestImplementation(libs.espresso.core)
     androidTestImplementation(platform(libs.compose.bom))
     androidTestImplementation(libs.ui.test.junit4)
     debugImplementation(libs.ui.tooling)
     debugImplementation(libs.ui.test.manifest)
     implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
     implementation("androidx.activity:activity-compose:1.7.2")
     implementation(platform("androidx.compose:compose-bom:2023.03.00"))
     implementation("androidx.compose.ui:ui")
     implementation("androidx.compose.ui:ui-graphics")
     implementation("androidx.compose.ui:ui-tooling-preview")
     implementation("androidx.compose.material3:material3")
     implementation("androidx.core:core-ktx:1.12.0")
     implementation ("com.google.dagger:hilt-android:2.44.2")*/
    // implementation(libs.compose.destinations)
    // Navigation
    // implementation ("androidx.navigation:navigation-fragment-ktx:2.3.0-beta01")
    //  implementation ("androidx.navigation:navigation-ui-ktx:2.3.0-beta01")
    // Dynamic Feature Module Support
    //  implementation ("androidx.navigation:navigation-dynamic-features-fragment:2.3.0-beta01")
    // Testing Navigation
    // androidTestImplementation ("androidx.navigation:navigation-testing:2.3.0-beta01")

    //TryByHabrGoogle
    //implementation("androidx.navigation:navigation-compose:2.4.0-beta02")
    //TryBySomeYouTube
    val nav_version = "2.7.3"

    implementation("androidx.navigation:navigation-compose:$nav_version")
    implementation ("com.google.android.material:material:1.3.0-alpha02")
    /* val appcompat_version = "1.6.1"

     implementation("androidx.appcompat:appcompat:$appcompat_version")
     implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
     testImplementation("junit:junit:4.13.2")
     androidTestImplementation("androidx.test.ext:junit:1.1.5")
     androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
     androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
     androidTestImplementation("androidx.compose.ui:ui-test-junit4")
     debugImplementation("androidx.compose.ui:ui-tooling")
     debugImplementation("androidx.compose.ui:ui-test-manifest")
     kapt ("com.google.dagger:hilt-android-compiler:2.28-alpha")
     kapt ("androidx.room:room-compiler:2.5.2")*/
    //kapt ("androidx.room:room-compiler:2.5.1")






    //Test
    implementation ("androidx.core:core-ktx:1.10.1")
    implementation ("androidx.appcompat:appcompat:1.6.1")
    implementation ("com.google.android.material:material:1.9.0")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
    implementation(libs.material3)
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")

    testImplementation ("junit:junit:4.13.2")
    androidTestImplementation ("androidx.test.ext:junit:1.1.5")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.5.1")

    implementation ("com.google.dagger:hilt-android:2.44.2")
    kapt ("com.google.dagger:hilt-compiler:2.44.2")

    //Room
    implementation ("androidx.room:room-runtime:2.5.1")
    kapt ("androidx.room:room-compiler:2.5.1")
    implementation ("androidx.room:room-ktx:2.5.1")

    val composeBom = platform("androidx.compose:compose-bom:2023.04.01")
    implementation (composeBom)
    androidTestImplementation (composeBom)

    //COMPOSE
    // Material Design 2
    implementation ("androidx.compose.material:material")

    // Android Studio Preview support
    implementation ("androidx.compose.ui:ui-tooling-preview")
    debugImplementation ("androidx.compose.ui:ui-tooling")

    // UI Tests
    androidTestImplementation ("androidx.compose.ui:ui-test-junit4")
    debugImplementation ("androidx.compose.ui:ui-test-manifest")

    // Optional - Integration with ViewModels
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")

    //collectAsStateWithLifecycle()
    implementation ("androidx.lifecycle:lifecycle-runtime-compose:2.6.1")

    //COMPOSE NAVIGATION
    implementation ("androidx.hilt:hilt-navigation-compose:1.0.0")
    implementation ("androidx.navigation:navigation-compose:2.6.0")
    implementation ("androidx.navigation:navigation-runtime-ktx:2.6.0")


    //icons
    implementation ("androidx.compose.material:material-icons-extended:1.5.2")




}
hilt {
    enableAggregatingTask = true
}