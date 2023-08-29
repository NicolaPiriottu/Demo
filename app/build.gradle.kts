plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    //navigations
    id("androidx.navigation.safeargs")
    //hilt
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt")
}

android {
    namespace = "it.nicolapiriottu.demo"
    compileSdk = 34
    buildToolsVersion = "33.0.1"

    defaultConfig {
        applicationId = "it.nicolapiriottu.demo"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        debug {
            isMinifyEnabled = false
        }

        flavorDimensions += ("buildTypes")

        productFlavors {
            create("development") {
                applicationIdSuffix = ".dev"
                buildConfigField("String", "BASE_URL", "\"https\"")
            }
            create("production") {
                applicationIdSuffix = ".pro"
                buildConfigField("String", "BASE_URL", "\"https\"")
            }
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }

    // Allow build variants
    buildFeatures {
        buildConfig = true
    }

    // Allow references to generated code
    kapt {
        correctErrorTypes = true
    }

    packaging {
        resources {
            excludes += ("META-INF/*.kotlin_module")
            /*In Android gradle construction, it is not allowed to include the same file with the same path in the output multiple times.
             In your construction, there are two meta-inf/dependencies files from different places.
             Since your application does not need this file at all,
            the easiest way is to tell the construction system to ignore it completely, which is the role of the exclude instruction*/
            excludes += ("META-INF/gradle/incremental.annotation.processors")
        }
    }

}

dependencies {

    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.1")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.1")

    // Timber
    api("com.jakewharton.timber:timber:5.0.1")
    //Hilt
    implementation("com.google.dagger:hilt-android:2.47")
    kapt("com.google.dagger:hilt-compiler:2.47")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}