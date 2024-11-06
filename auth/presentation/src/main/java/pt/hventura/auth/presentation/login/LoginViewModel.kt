package pt.hventura.auth.presentation.login

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

class LoginViewModel(
    private val authRepository: AuthRepository,
    private val userDataValidator: UserDataValidator
) : ViewModel() {

    var state by mutableStateOf(LoginState())
        private set

    private val eventChannel = Channel<LoginEvent>()
    val events = eventChannel.receiveAsFlow()

    init {
        viewModelScope.launch {
            snapshotFlow { state.email.text }
                .collect { email ->
                    state = state.copy(
                        canLogin = userDataValidator.isValidEmail(
                            email = email.toString().trim()
                        ) && state.password.text.isNotEmpty()
                    )
                }
        }

        viewModelScope.launch {
            snapshotFlow { state.password.text }
                .collect { password ->
                    state = state.copy(
                        canLogin = userDataValidator.isValidEmail(
                            email = state.email.toString().trim()
                        ) && password.isNotEmpty()
                    )
                }
        }

    }

    fun onAction(action: LoginAction) {
        when (action) {
            LoginAction.OnLoginClick -> login()
            LoginAction.OnTogglePasswordVisibility -> {
                state = state.copy(
                    isPasswordVisible = !state.isPasswordVisible
                )
            }

            else -> Unit
        }
    }

    private fun login() {
        viewModelScope.launch {
            state = state.copy(isLoggingIn = true)
            val result = authRepository.login(
                email = state.email.text.toString().trim(),
                password = state.password.text.toString()
            )
            state = state.copy(isLoggingIn = false)

            when (result) {
                is Result.Error -> {
                    if (result.error == DataError.Network.UNAUTHORIZED) {
                        eventChannel.send(
                            LoginEvent.Error(
                                UiText.StringResource(R.string.error_email_password_incorrect)
                            )
                        )
                    } else {
                        eventChannel.send(LoginEvent.Error(result.error.asUiText()))
                    }
                }

                is Result.Success -> {
                    eventChannel.send(LoginEvent.LoginSuccess)
                }
            }
        }
    }
}