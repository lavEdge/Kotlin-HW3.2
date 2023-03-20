import kotlin.math.roundToInt

fun main() {
    val remittanceOnMonth = 0
    val remittance = 17500
    val cardOwnership = "VK Pay"
    println(
        """Сумма перевода: $remittance 
        |Переведено в этом месяце: $remittanceOnMonth
        |Карта: $cardOwnership
        |Комиссия за перевод составит: ${comission(remittance, remittanceOnMonth, cardOwnership)}
        |Сумма перевода с комиссией: ${remittance+comission(remittance, remittanceOnMonth, cardOwnership)}""".trimMargin()
    )
}

fun comission(remittance: Int, remittanceOnMonth: Int, cardOwnership: String): Int {

    if (cardOwnership == "Mastercard" || cardOwnership == "Maestro") {
        if (remittance > 300 && remittanceOnMonth < 75000) {
            return 0
        } else {
            return (remittance * 0.006 + 20).roundToInt()
        }
    } else if (cardOwnership == "Visa" || cardOwnership == "Mir") {
        if (remittance * 0.0075 < 35) {
            return 35
        } else {
            return (remittance * 0.0075).roundToInt()
        }
    } else if (cardOwnership == "VK Pay") {
        return 0
    }
    return TODO("Provide the return value")
}