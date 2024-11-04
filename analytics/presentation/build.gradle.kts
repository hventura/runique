plugins {
    alias(libs.plugins.runique.android.feature.ui)
}

android {
    namespace = "pt.hventura.analytics.presentation"
}

dependencies {
    implementation(projects.analytics.domain)
}