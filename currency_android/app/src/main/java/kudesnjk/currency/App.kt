package kudesnjk.currency

import android.app.Application
import android.os.Looper
import kudesnjk.currency.di.ApplicationComponent
import kudesnjk.currency.di.DaggerApplicationComponent

class App : Application() {
    companion object {
        lateinit var appComponent: ApplicationComponent
    }

    private fun initializeDagger() {
        appComponent = DaggerApplicationComponent.create()
    }

    override fun onCreate() {
        super.onCreate()
        initializeDagger()
    }
}