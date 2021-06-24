package kudesnjk.currency.presentation.ui

import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kudesnjk.currency.R

interface CurrencyValueListener {
    fun onCurrencyValueChanged(currencyShortName: String, currencyNewValue: Float)
    fun onCurrencyValueFocusChanged(currency: CurrencyViewData)
}

class CurrencyViewHolder(
    itemView: View,
    private val currencyValueListener: CurrencyValueListener
) : RecyclerView.ViewHolder(itemView) {
    private val currencyImageView = itemView.findViewById<ImageView>(R.id.currency_image)
    private val shortNameTextView = itemView.findViewById<TextView>(R.id.currency_short_name)
    private val nameTextView = itemView.findViewById<TextView>(R.id.currency_name)
    private val valueEditText = itemView.findViewById<EditText>(R.id.currency_value)
    var calledTimes = 0

    fun bind(currency: CurrencyViewData) {
        shortNameTextView.text = currency.shortName
        nameTextView.text = currency.name
        valueEditText.setText(currency.value.toString())
        valueEditText.doAfterTextChanged { text ->
            if (text != null) {
                val newValue = text.toString().toFloat()
                if (newValue != currency.value && calledTimes == 0) {
                    currencyValueListener.onCurrencyValueChanged(currency.shortName, newValue)
                    calledTimes = 1
                }
            }
        }

        valueEditText.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                currencyValueListener.onCurrencyValueFocusChanged(currency)
            }
        }

        Glide
            .with(itemView.context)
            .load(currency.image)
            .into(currencyImageView)
    }
}