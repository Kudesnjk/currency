package kudesnjk.currency.presentation

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kudesnjk.currency.App
import kudesnjk.currency.R
import kudesnjk.currency.presentation.ui.CurrencyAdapter
import kudesnjk.currency.presentation.ui.CurrencyValueListener
import kudesnjk.currency.presentation.ui.CurrencyViewData
import javax.inject.Inject

class CurrencyFragment : Fragment(), CurrencyValueListener, CurrencyView {

    @Inject
    lateinit var currencyPresenter: CurrencyPresenter

    lateinit var adapter: CurrencyAdapter

    private var currencies: MutableList<CurrencyViewData> = mutableListOf()

    private var recyclerView: RecyclerView? = null

    companion object {
        fun newInstance() = CurrencyFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.main_fragment, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.currency_recycler)
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = CurrencyAdapter(this)
        recyclerView.adapter = adapter
        this.recyclerView = recyclerView
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        App.appComponent.inject(this)
        currencyPresenter.onAttach(this)
    }

    override fun setCurrencies(currencies: List<CurrencyViewData>) {
        adapter.updateData(currencies)
        this.currencies = mutableListOf(*currencies.toTypedArray())
    }

    override fun nofifyError(errorText: String) {
        Toast.makeText(context, errorText, Toast.LENGTH_SHORT).show()
    }

    override fun onCurrencyValueChanged(currencyShortName: String, currencyNewValue: Float) {
        val newList = currencyPresenter.computeValues(
            currencies,
            currencyShortName,
            currencyNewValue
        )
        recyclerView?.post {
            adapter.updateData(newList)
        }
        currencies = newList.toMutableList()
    }

    override fun onCurrencyValueFocusChanged(currency: CurrencyViewData) {
        val oldPosition = currencies.indexOfFirst { it.shortName == currency.shortName }
        val newList = currencies.swapAndCopy(oldPosition, 0)
        adapter.updateData(newList)
        recyclerView?.scrollToPosition(0)
        currencies = newList
    }
}

fun <T> MutableList<T>.swapAndCopy(oldPosition: Int, newPosition: Int): MutableList<T> {
    val newList = this.toMutableList()
    val temp = newList[oldPosition]
    newList[oldPosition] = newList[newPosition]
    newList[newPosition] = temp
    return newList
}