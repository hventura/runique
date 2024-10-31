package pt.hventura.auth.presentation.register

sealed interface RegisterAction {
    data object OnTogglePasswordVisibilityClick: RegisterAction
    data object OnSignInClick: RegisterAction
    data object OnRegisterClick: RegisterAction
}