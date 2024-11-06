package pt.hventura.run.presentation.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import pt.hventura.run.domain.RunningTracker
import pt.hventura.run.presentation.active_run.ActiveRunViewModel
import pt.hventura.run.presentation.run_overview.RunOverviewViewModel

val runPresentationModule = module {
    singleOf(::RunningTracker)

    viewModelOf(::RunOverviewViewModel)
    viewModelOf(::ActiveRunViewModel)
}