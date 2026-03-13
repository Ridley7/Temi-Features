import com.robotemi.sdk.Robot
import com.robotemi.sdk.TtsRequest

class TemiVoiceManager(
    private val robot: Robot
): VoiceManager{
    override fun speak(text: String) {

        val ttsRequest = TtsRequest.create(
            speech = text,
            isShowOnConversationLayer = false,
            language = TtsRequest.Language.ES_ES
        )

        robot.speak(ttsRequest)
    }

    override fun shutdown() {
        TODO("Not yet implemented")
    }
}