package pt.hventura.core.domain.run

import pt.hventura.core.domain.util.DataError
import pt.hventura.core.domain.util.EmptyResult
import pt.hventura.core.domain.util.Result

interface RemoteRunDataSource {
    suspend fun getRuns(): Result<List<Run>, DataError.Network>
    suspend fun postRun(run: Run, mapPicture: ByteArray): Result<Run, DataError.Network>
    suspend fun deleteRun(id: String): EmptyResult<DataError.Network>
}