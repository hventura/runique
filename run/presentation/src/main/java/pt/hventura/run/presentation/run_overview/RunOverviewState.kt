package pt.hventura.run.presentation.run_overview

import pt.hventura.run.presentation.run_overview.model.RunUi

data class RunOverviewState(
    val runs: List<RunUi> = emptyList()
)