package kudesnjk.currency.data

import com.google.gson.annotations.SerializedName

data class CurrencyDto(
    @SerializedName("short_name") val shortName: String,
    @SerializedName("name") val name: String,
    @SerializedName("value") val value: Float,
    @SerializedName("image") val image: String,
)
