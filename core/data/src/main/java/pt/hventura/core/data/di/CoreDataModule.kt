package pt.hventura.core.data.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import pt.hventura.core.data.auth.EncryptedSessionStorage
import pt.hventura.core.data.networking.HttpClientFactory
import pt.hventura.core.data.run.OfflineFirstRunRepository
import pt.hventura.core.domain.SessionStorage
import pt.hventura.core.domain.run.RunRepository

val coreDataModule = module {
    single {
        HttpClientFactory(get()).build()
    }
    singleOf(::EncryptedSessionStorage).bind<SessionStorage>()
    singleOf(::OfflineFirstRunRepository).bind<RunRepository>()

}