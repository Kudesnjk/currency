package kudesnjk.currency.presentation.ui

import androidx.recyclerview.widget.DiffUtil


class UpdateDataCalculator(
    private val oldCurrencies: List<CurrencyViewData>,
    private val newCurrencies: List<CurrencyViewData>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldCurrencies.size
    }

    override fun getNewListSize(): Int {
        return newCurrencies.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldCurrencies[oldItemPosition].shortName ==
                newCurrencies[newItemPosition].shortName
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldCurrencies[oldItemPosition] ==
                newCurrencies[newItemPosition]
    }
}