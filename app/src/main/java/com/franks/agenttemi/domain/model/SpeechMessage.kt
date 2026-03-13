import com.franks.agenttemi.domain.model.enums.SpeechPriority

data class SpeechMessage(
    val text: String,
    val priority: SpeechPriority
)