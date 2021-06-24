package kudesnjk.currency.presentation

import io.reactivex.Observable
import kudesnjk.currency.domain.CurrencyEntity
import kudesnjk.currency.domain.Data

interface CurrencyInteractor {
    fun getCurrencies() : Observable<Data<List<CurrencyEntity>>>
    fun computeValues(
        oldCurrencies: List<CurrencyEntity>,
        currencyShortName: String,
        currencyNewValue: Float
    ): List<CurrencyEntity>
}