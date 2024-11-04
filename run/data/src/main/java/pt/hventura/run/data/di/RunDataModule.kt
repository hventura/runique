package pt.hventura.run.data.di

import org.koin.androidx.workmanager.dsl.workerOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import pt.hventura.core.domain.run.SyncRunScheduler
import pt.hventura.run.data.CreateRunWorker
import pt.hventura.run.data.DeleteRunWorker
import pt.hventura.run.data.FetchRunsWorker
import pt.hventura.run.data.SyncRunWorkerScheduler

val runDataModule = module {
    workerOf(::CreateRunWorker)
    workerOf(::FetchRunsWorker)
    workerOf(::DeleteRunWorker)

    singleOf(::SyncRunWorkerScheduler).bind<SyncRunScheduler>()
}