package kudesnjk.currency.presentation

import android.view.View
import kudesnjk.currency.domain.CurrencyEntity
import kudesnjk.currency.presentation.ui.CurrencyViewData

interface CurrencyPresenter {
    fun onAttach(view: CurrencyView)
    fun computeValues(
        oldCurrencies: List<CurrencyViewData>,
        currencyShortName: String,
        currencyNewValue: Float
    ): List<CurrencyViewData>
}

interface CurrencyView {
    fun setCurrencies(currencies: List<CurrencyViewData>)
    fun nofifyError(errorText: String)
}