package pt.hventura.analytics.data.di

import pt.hventura.analytics.data.RoomAnalyticsRepository
import pt.hventura.analytics.domain.AnalyticsRepository
import pt.hventura.core.database.RunDatabase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val analyticsModule = module {
    singleOf(::RoomAnalyticsRepository).bind<AnalyticsRepository>()
    single {
        get<RunDatabase>().analyticsDao
    }
}