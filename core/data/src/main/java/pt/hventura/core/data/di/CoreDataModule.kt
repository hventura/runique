package pt.hventura.core.data.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import pt.hventura.core.data.auth.EncryptedSessionStorage
import pt.hventura.core.data.networking.HttpClientFactory
import pt.hventura.core.domain.SessionStorage

val coreDataModule = module {
    single {
        HttpClientFactory(get()).build()
    }
    singleOf(::EncryptedSessionStorage).bind<SessionStorage>()
}