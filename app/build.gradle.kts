plugins {
    alias(libs.plugins.custom.android.application)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.uandcode.messenger"

    defaultConfig {
        applicationId = "com.uandcode.messenger"
        versionCode = 1
        versionName = "1.0"
    }
}

dependencies {
    implementation(project(":templates:android-library"))
    implementation(project(":templates:kotlin-library"))
    implementation(project(":templates:feature:domain"))
    implementation(project(":templates:feature:presentation"))
    implementation(project(":core:essentials"))
    implementation(project(":core:common:android"))
    implementation(project(":features:init:presentation"))
    implementation(project(":navigation"))

    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}