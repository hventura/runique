package pt.hventura.run.network.di

import pt.hventura.core.domain.run.RemoteRunDataSource
import pt.hventura.run.network.KtorRemoteRunDataSource
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val networkModule = module {
    singleOf(::KtorRemoteRunDataSource).bind<RemoteRunDataSource>()
}