enum class Severity{
    LOW, MEDIUM, HIGH
}

data class AIRecommendation(
    val alert: Boolean,
    val severity: Severity,
    val message: String
)