package pt.hventura.analytics.analytics_feature

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.android.play.core.splitcompat.SplitCompat
import pt.hventura.analytics.data.di.analyticsModule
import pt.hventura.core.presentation.designsystem.RuniqueTheme
import org.koin.core.context.loadKoinModules
import pt.hventura.analytics.presentation.AnalyticsDashboardScreenRoot
import pt.hventura.analytics.presentation.di.analyticsPresentationModule

class AnalyticsActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadKoinModules(
            listOf(
                analyticsModule,
                analyticsPresentationModule
            )
        )
        SplitCompat.installActivity(this)

        setContent {
            RuniqueTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "analytics_dashboard"
                ) {
                    composable("analytics_dashboard") {
                        AnalyticsDashboardScreenRoot(
                            onBackClick = { finish() }
                        )
                    }
                }
            }
        }
    }
}