package kudesnjk.currency.di

import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides
import kudesnjk.currency.MainActivity
import kudesnjk.currency.data.*
import kudesnjk.currency.domain.CurrencyInteractorImpl
import kudesnjk.currency.domain.CurrencyRepository
import kudesnjk.currency.presentation.CurrencyFragment
import kudesnjk.currency.presentation.CurrencyInteractor
import kudesnjk.currency.presentation.CurrencyPresenter
import kudesnjk.currency.presentation.CurrencyPresenterImpl
import kudesnjk.currency.presentation.ui.CurrencyValueListener

@Component(
    modules = [ApplicationBindsModule::class, ApplicationProvidesModule::class]
)
interface ApplicationComponent {
    fun inject(fragment: CurrencyFragment)
}

@Module
interface ApplicationBindsModule {
    @Binds
    fun bindServiceFactory(impl: ServiceFactoryImpl): ServiceFactory

    @Binds
    fun bindCurrencyRepository(impl: CurrencyRepositoryImpl): CurrencyRepository

    @Binds
    fun bindCurrencyInteractor(impl: CurrencyInteractorImpl): CurrencyInteractor

    @Binds
    fun bindCurrencyPresenter(impl: CurrencyPresenterImpl): CurrencyPresenter

    @Binds
    fun bindCurrencyAdapter(impl: CurrencyFragment) : CurrencyValueListener
}

@Module
class ApplicationProvidesModule {
    @Provides
    fun provideCurrencyService(serviceFactory: ServiceFactory): CurrencyService =
        serviceFactory.createService(CurrencyService::class.java)
}