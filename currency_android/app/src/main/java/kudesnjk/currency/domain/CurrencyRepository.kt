package kudesnjk.currency.domain

import io.reactivex.Observable

interface CurrencyRepository {
    fun getCurrencies() : Observable<Data<List<CurrencyEntity>>>
}