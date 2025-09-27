plugins {
    alias(libs.plugins.custom.kotlin.library)
}

dependencies {
    implementation(project(":core:essentials"))
}