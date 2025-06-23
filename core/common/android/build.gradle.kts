plugins {
    alias(libs.plugins.custom.android.library)
}

android {
    namespace = "com.uandcode.messenger.common.android"
}

dependencies {
    api(project(":core:essentials"))
    implementation(libs.timber)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}