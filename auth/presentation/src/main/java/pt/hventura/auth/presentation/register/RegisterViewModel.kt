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
import pt.hventura.auth.domain.UserDataValidator

class RegisterViewModel(
    private val userDataValidator: UserDataValidator,
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
            RegisterAction.OnRegisterClick -> Unit
            RegisterAction.OnTogglePasswordVisibilityClick -> Unit
            else -> Unit
        }
    }
}