import kotlin.math.roundToInt

fun main() {
    val remittanceOnMonth = 0
    val remittance = 11_000
    val cardOwnership = "VK Pay"

    if (limits(remittance, remittanceOnMonth, cardOwnership)) {
        println(
            """Сумма перевода: $remittance 
        |Переведено в этом месяце: $remittanceOnMonth
        |Карта: $cardOwnership
        |Комиссия за перевод составит: ${comission(remittance, remittanceOnMonth, cardOwnership)}
        |Сумма перевода с комиссией: ${
                remittance + comission(
                    remittance,
                    remittanceOnMonth,
                    cardOwnership
                )
            }""".trimMargin()
        )
    } else {
        println("Вы не сможете выполнить перевод, т.к. превышены лимиты")
    }
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
    return 0
}

fun limits(remittance: Int, remittanceOnMonth: Int, cardOwnership: String): Boolean {

    if (cardOwnership == "VK Pay") {
        return !(remittance > 15_000 || (remittanceOnMonth+remittance) > 40_000)
    } else {
        return !(remittance > 150_000 || (remittance+remittanceOnMonth) > 600_000)
    }
}