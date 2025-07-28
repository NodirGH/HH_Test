package app.market.data.home

import app.market.data.local.DisplayableItem

data class HeadersModel(
    val id: String = "",
    val title: String = "",
    val link: String = "",
    val buttonText: String = "",
) : DisplayableItem