package kudesnjk.currency.domain

data class CurrencyEntity(
    val shortName: String,
    val name: String,
    val value: Float,
    val image: String,
)
