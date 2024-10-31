package pt.hventura.auth.data

import io.ktor.client.HttpClient
import pt.hventura.auth.domain.AuthRepository
import pt.hventura.core.data.networking.post
import pt.hventura.core.domain.AuthInfo
import pt.hventura.core.domain.SessionStorage
import pt.hventura.core.domain.util.DataError
import pt.hventura.core.domain.util.EmptyResult
import pt.hventura.core.domain.util.Result
import pt.hventura.core.domain.util.asEmptyDataResult

class AuthRepositoryImpl(
    private val httpClient: HttpClient,
    private val sessionStorage: SessionStorage
): AuthRepository {

    override suspend fun login(email: String, password: String): EmptyResult<DataError.Network> {
        val result = httpClient.post<LoginRequest, LoginResponse>(
            route = "/login",
            body = LoginRequest(
                email = email,
                password = password
            )
        )
        if(result is Result.Success) {
            sessionStorage.set(
                AuthInfo(
                    accessToken = result.data.accessToken,
                    refreshToken = result.data.refreshToken,
                    userId = result.data.userId
                )
            )
        }
        return result.asEmptyDataResult()
    }

    override suspend fun register(email: String, password: String): EmptyResult<DataError.Network> {
        return httpClient.post<RegisterRequest, Unit>(
            route = "/register",
            body = RegisterRequest(
                email = email,
                password = password
            )
        )
    }
}