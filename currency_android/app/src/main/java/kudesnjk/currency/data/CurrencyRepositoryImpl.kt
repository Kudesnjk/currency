package kudesnjk.currency.data

import io.reactivex.Observable
import kudesnjk.currency.domain.CurrencyEntity
import kudesnjk.currency.domain.CurrencyRepository
import kudesnjk.currency.domain.Data
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(private val currencyService: CurrencyService) : CurrencyRepository {
    override fun getCurrencies() : Observable<Data<List<CurrencyEntity>>> =
        currencyService.getCurrencies()
            .map { it.map { dto ->  dto.toDomain() } }
            .map { Data.content(it) }
}

fun CurrencyDto.toDomain() = CurrencyEntity(
    this.shortName,
    this.name,
    this.value,
    this.image
)