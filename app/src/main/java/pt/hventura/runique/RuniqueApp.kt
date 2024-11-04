package pt.hventura.runique

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.workmanager.koin.workManagerFactory
import org.koin.core.context.startKoin
import pt.hventura.auth.data.di.authDataModule
import pt.hventura.auth.presentation.di.authViewModelModule
import pt.hventura.core.data.di.coreDataModule
import pt.hventura.core.database.di.databaseModule
import pt.hventura.run.data.di.runDataModule
import pt.hventura.run.location.di.locationModule
import pt.hventura.run.network.di.networkModule
import pt.hventura.run.presentation.di.runPresentationModule
import pt.hventura.runique.di.appModule
import timber.log.Timber

class RuniqueApp: Application() {

    val applicationScope = CoroutineScope(SupervisorJob())
    
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidLogger()
            androidContext(this@RuniqueApp)
            workManagerFactory()
            modules(
                appModule,
                coreDataModule,
                authDataModule,
                authViewModelModule,
                locationModule,
                runPresentationModule,
                databaseModule,
                networkModule,
                runDataModule
            )
        }
    }

}