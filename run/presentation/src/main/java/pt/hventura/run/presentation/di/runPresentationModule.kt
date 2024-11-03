package pt.hventura.run.presentation.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import pt.hventura.run.presentation.run_overview.RunOverviewViewModel

val runPresentationModule = module {
    viewModelOf(::RunOverviewViewModel)
}