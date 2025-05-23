plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
}

android {
    namespace = "ru.effectivemobile.thousandscourses"
    compileSdk = 35

    defaultConfig {
        applicationId = "ru.effectivemobile.thousandscourses"
        minSdk = 28
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.datastore)

    implementation(libs.hilt)
    ksp(libs.hilt.compiler)

    implementation(libs.androidx.navigation)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    implementation(project(":core:feature-api"))
    implementation(project(":core:datastore"))
    implementation(project(":feature:onboarding:onboarding-impl"))
    implementation(project(":feature:onboarding:onboarding-api"))
    implementation(project(":feature:authorization:authorization-impl"))
    implementation(project(":feature:authorization:authorization-api"))
    implementation(project(":feature:menu:menu-api"))
    implementation(project(":feature:menu:menu-impl"))
    implementation(project(":feature:main:main-api"))
    implementation(project(":feature:main:main-impl"))
    implementation(project(":feature:favorites:favorites-api"))
    implementation(project(":feature:favorites:favorites-impl"))
    implementation(project(":feature:account:account-api"))
    implementation(project(":feature:account:account-impl"))
    implementation(project(":feature:course-description:course-description-api"))
    implementation(project(":feature:course-description:course-description-impl"))

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}