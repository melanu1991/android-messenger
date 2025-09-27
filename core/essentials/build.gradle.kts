plugins {
    alias(libs.plugins.custom.kotlin.library)
}

dependencies {
    implementation(libs.javax.inject)
    testImplementation(libs.junit)
}
