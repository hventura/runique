package pt.hventura.analytics.presentation

data class AnalyticsDashboardState(
    val totalDistanceRun: String,
    val totalTimeRun: String,
    val fastestEverRun: String,
    val avgDistance: String,
    val avgPace: String
)