package kudesnjk.currency.presentation

import android.os.Handler
import android.os.Looper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import kudesnjk.currency.domain.CurrencyEntity
import kudesnjk.currency.domain.Data
import kudesnjk.currency.presentation.ui.CurrencyViewData
import javax.inject.Inject

class CurrencyPresenterImpl
@Inject constructor(private val currencyInteractor: CurrencyInteractor) : CurrencyPresenter {
    override fun onAttach(view: CurrencyView) {
        currencyInteractor.getCurrencies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableObserver<Data<List<CurrencyEntity>>>() {
                override fun onNext(t: Data<List<CurrencyEntity>>) {
                    when (t) {
                        is Data.Content -> view.setCurrencies(t.content.map { it.toViewData() }
                            .sortedBy { it.value })
                        is Data.Error -> t.throwable.localizedMessage?.let { view.nofifyError(it) }
                    }
                }

                override fun onError(e: Throwable) {
                    e.localizedMessage?.let { view.nofifyError(it) }
                }

                override fun onComplete() {

                }

            })
    }

    override fun computeValues(
        oldCurrencies: List<CurrencyViewData>,
        currencyShortName: String,
        currencyNewValue: Float
    ): List<CurrencyViewData> {
        return currencyInteractor.computeValues(
            oldCurrencies.map { it.toDomain() },
            currencyShortName,
            currencyNewValue
        ).map {
            it.toViewData()
        }
    }
}

fun CurrencyEntity.toViewData() =
    CurrencyViewData(
        this.shortName,
        this.name,
        this.value,
        this.image,
    )

fun CurrencyViewData.toDomain() =
    CurrencyEntity(
        this.shortName,
        this.name,
        this.value,
        this.image,
    )