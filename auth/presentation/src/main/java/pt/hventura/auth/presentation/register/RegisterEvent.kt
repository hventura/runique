package pt.hventura.auth.presentation.register

import pt.hventura.core.presentation.ui.UiText

sealed interface RegisterEvent {
    data object RegistrationSuccess: RegisterEvent
    data object OnSignInEvent: RegisterEvent
    data class Error(val error: UiText): RegisterEvent
}