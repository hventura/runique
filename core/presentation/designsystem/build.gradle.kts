plugins {
    alias(libs.plugins.runique.android.library.compose)
}

android {
    namespace = "pt.hventura.core.presentation.designsystem"
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    api(libs.androidx.material3)

    debugImplementation(libs.androidx.ui.tooling)
}