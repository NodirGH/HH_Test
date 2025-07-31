package app.market.toolkit.string_ext

import android.text.Html
import android.widget.TextView

fun TextView.setTextWithHtml(text: String) {
    this.setText(
        Html.fromHtml(
            text,
            Html.FROM_HTML_OPTION_USE_CSS_COLORS
        ), TextView.BufferType.SPANNABLE
    )
}