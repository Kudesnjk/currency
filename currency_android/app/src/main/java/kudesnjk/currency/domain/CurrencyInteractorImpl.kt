package kudesnjk.currency.domain

import android.util.Log
import io.reactivex.Observable
import kudesnjk.currency.presentation.CurrencyInteractor
import javax.inject.Inject

class CurrencyInteractorImpl
@Inject constructor(private val currencyRepository: CurrencyRepository) :
    CurrencyInteractor {
    override fun getCurrencies() = currencyRepository.getCurrencies()

    override fun computeValues(
        oldCurrencies: List<CurrencyEntity>,
        currencyShortName: String,
        currencyNewValue: Float
    ): List<CurrencyEntity> {
        val oldCurrency = oldCurrencies.find { it.shortName == currencyShortName }
        if (oldCurrency != null) {
            val coefficient = currencyNewValue / oldCurrency.value
            val newList = mutableListOf<CurrencyEntity>()
            oldCurrencies.forEach {
                newList.add(
                    CurrencyEntity(
                        it.shortName,
                        it.name,
                        it.value * coefficient,
                        it.image,
                    )
                )
            }
            return newList
        }
        return oldCurrencies
    }
}