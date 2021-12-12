package dev.saurabhmishra.exoplayersample.extensions

import java.text.DecimalFormat
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import kotlin.math.ln
import kotlin.math.pow

/**
 * @author Saurabh Mishra
 * Converts long value to their abbreviated form
 * For example, 1200 gets converted to 1.2K
 * and so on -> million becomes M, Billion -> B
 */
fun Long?.formatNumberWithAbbr(): String? {
    return this?.let { formatNumberWithAbbreviation(this) }
}

// Dangerous code here, please don't change or even touch it
// Results may shock you 😱
private fun formatNumberWithAbbreviation(count: Long): String {
    if (count < 1000) return "" + count
    val exp = (ln(count.toDouble()) / ln(1000.0)).toInt()
    val format = DecimalFormat("0.#")
    val value = format.format(count / 1000.0.pow(exp.toDouble()))
    return String.format(
        "%s%c",
        value,
        "KMBTPE"[exp - 1]
    )
}

fun Long.asDateTime(): LocalDateTime {
    return Instant.ofEpochMilli(this)
        .atZone(ZoneId.systemDefault())
        .toLocalDateTime()
}

fun Long.formatVideoTime(): String {
    val date = this.asDateTime().toLocalDate()
    val today = LocalDate.now()

    return when {
        date.isEqual(today) -> "Today"
        date.minusDays(1).isEqual(today) -> "Yesterday"
        else -> date.format(DateTimeFormatter.ofPattern("MMM dd, yyyy"))
    }
}