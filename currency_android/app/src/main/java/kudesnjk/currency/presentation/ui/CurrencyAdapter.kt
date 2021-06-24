package kudesnjk.currency.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kudesnjk.currency.R

class CurrencyAdapter(private val currencyValueListener: CurrencyValueListener) :
    RecyclerView.Adapter<CurrencyViewHolder>() {
    private var currencies: List<CurrencyViewData> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.component_currency, parent, false)
        return CurrencyViewHolder(view, currencyValueListener)
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.bind(currencies[position])
    }

    override fun getItemCount(): Int {
        return currencies.size
    }

    fun updateData(currencies: List<CurrencyViewData>) {
        val diff = DiffUtil.calculateDiff(UpdateDataCalculator(this.currencies, currencies))
        diff.dispatchUpdatesTo(this)
        this.currencies = currencies
    }
}
