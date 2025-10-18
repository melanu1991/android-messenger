plugins {
    alias(libs.plugins.custom.kotlin.library)
}

dependencies {
    implementation(libs.javax.inject)
    api(libs.coroutines.core)
    api(libs.container)
    testImplementation(libs.junit)
}
