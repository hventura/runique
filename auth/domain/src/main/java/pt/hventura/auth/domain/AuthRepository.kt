package pt.hventura.auth.domain

import pt.hventura.core.domain.util.DataError
import pt.hventura.core.domain.util.EmptyResult

interface AuthRepository {
//    suspend fun login(email: String, password: String): EmptyResult<DataError.Network>
    suspend fun register(email: String, password: String): EmptyResult<DataError.Network>
}