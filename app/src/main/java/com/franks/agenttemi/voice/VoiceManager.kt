import android.content.Context
import android.speech.tts.TextToSpeech

class VoiceManager(context: Context) {

    private val tts = TextToSpeech(context) {}

    fun speak(text: String) {
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
    }

}