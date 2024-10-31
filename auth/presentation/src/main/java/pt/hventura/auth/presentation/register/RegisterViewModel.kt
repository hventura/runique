package pt.hventura.auth.presentation.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import pt.hventura.auth.domain.AuthRepository
import pt.hventura.auth.domain.UserDataValidator
import pt.hventura.auth.presentation.R
import pt.hventura.core.domain.util.DataError
import pt.hventura.core.domain.util.Result
import pt.hventura.core.presentation.ui.UiText
import pt.hventura.core.presentation.ui.asUiText

class RegisterViewModel(
    private val userDataValidator: UserDataValidator,
    private val repository: AuthRepository
) : ViewModel() {

    var state by mutableStateOf(RegisterState())
        private set

    private val eventChannel = Channel<RegisterEvent>()
    val events = eventChannel.receiveAsFlow()

    init {
        viewModelScope.launch {
            snapshotFlow { state.email.text }
                .collect { email ->
                    val isValidEmail = userDataValidator.isValidEmail(email.toString())
                    state = state.copy(
                        isEmailValid = isValidEmail,
                        canRegister = isValidEmail && state.passwordValidationState.isValidPassword
                                && !state.isRegistering
                    )
                }
        }

        viewModelScope.launch {
            snapshotFlow { state.password.text }
                .collect { password ->
                    val passwordValidationState =
                        userDataValidator.validatePassword(password.toString())
                    state = state.copy(
                        passwordValidationState = passwordValidationState,
                        canRegister = state.isEmailValid && passwordValidationState.isValidPassword
                                && !state.isRegistering
                    )
                }
        }
    }

    fun onAction(action: RegisterAction) {
        when (action) {
            RegisterAction.OnRegisterClick -> register()
            RegisterAction.OnTogglePasswordVisibilityClick -> {
                state = state.copy(
                    isPasswordVisible = !state.isPasswordVisible
                )
            }

            else -> Unit
        }
    }

    private fun register() {
        viewModelScope.launch {
            state = state.copy(isRegistering = true)
            val result = repository.register(
                email = state.email.text.toString().trim(),
                password = state.password.text.toString()
            )
            state = state.copy(isRegistering = false)

            when(result) {
                is Result.Error -> {
                    if(result.error == DataError.Network.CONFLICT) {
                        eventChannel.send(RegisterEvent.Error(
                            UiText.StringResource(R.string.error_email_exists)
                        ))
                    } else {
                        eventChannel.send(RegisterEvent.Error(result.error.asUiText()))
                    }
                }
                is Result.Success -> {
                    eventChannel.send(RegisterEvent.RegistrationSuccess)
                }
            }
        }
    }
}