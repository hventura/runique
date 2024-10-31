package pt.hventura.auth.data.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import pt.hventura.auth.data.AuthRepositoryImpl
import pt.hventura.auth.data.EmailPatternValidator
import pt.hventura.auth.domain.AuthRepository
import pt.hventura.auth.domain.PatternValidator
import pt.hventura.auth.domain.UserDataValidator

val authDataModule = module {
    single<PatternValidator> {
        EmailPatternValidator
    }
    singleOf(::UserDataValidator)
    singleOf(::AuthRepositoryImpl).bind<AuthRepository>()
}