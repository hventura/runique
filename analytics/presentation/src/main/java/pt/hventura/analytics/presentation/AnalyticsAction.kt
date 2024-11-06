package pt.hventura.analytics.presentation

sealed interface AnalyticsAction {
    data object OnBackClick: AnalyticsAction
}