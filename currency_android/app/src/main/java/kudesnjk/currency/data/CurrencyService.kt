package kudesnjk.currency.data

import io.reactivex.Observable
import retrofit2.http.GET

interface CurrencyService {

    @GET("/")
    fun getCurrencies() : Observable<List<CurrencyDto>>
}