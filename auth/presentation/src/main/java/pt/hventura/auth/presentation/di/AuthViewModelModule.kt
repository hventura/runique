package pt.hventura.auth.presentation.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import pt.hventura.auth.presentation.login.LoginViewModel
import pt.hventura.auth.presentation.register.RegisterViewModel

val authViewModelModule = module {
    viewModelOf(::RegisterViewModel)
    viewModelOf(::LoginViewModel)
}