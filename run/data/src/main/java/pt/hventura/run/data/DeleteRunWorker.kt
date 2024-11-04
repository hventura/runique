package pt.hventura.run.data

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import pt.hventura.core.database.dao.RunPendingSyncDao
import pt.hventura.core.domain.run.RemoteRunDataSource
import pt.hventura.core.domain.util.Result as RuniqueResult

class DeleteRunWorker(
    context: Context,
    private val params: WorkerParameters,
    private val remoteRunDataSource: RemoteRunDataSource,
    private val pendingSyncDao: RunPendingSyncDao
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        if (runAttemptCount >= 5) {
            return Result.failure()
        }

        val runId = params.inputData.getString(RUN_ID) ?: return Result.failure()
        return when (val result = remoteRunDataSource.deleteRun(runId)) {
            is RuniqueResult.Error -> {
                result.error.toWorkerResult()
            }

            is RuniqueResult.Success -> {
                pendingSyncDao.deleteDeletedRunSyncEntity(runId)
                Result.success()
            }
        }
    }

    companion object {
        const val RUN_ID = "RUN_ID"
    }
}