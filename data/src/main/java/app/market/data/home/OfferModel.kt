package app.market.data.home

data class OfferResponse(
    val id: String?,
    val title: String?,
    val link: String?,
    val button: Button? = null
)

data class Button(
    val text: String?
)