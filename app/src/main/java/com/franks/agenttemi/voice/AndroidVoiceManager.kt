import android.content.Context
import android.speech.tts.TextToSpeech
import java.util.Locale

class AndroidVoiceManager(
    context: Context
): VoiceManager, TextToSpeech.OnInitListener{

    private var tts: TextToSpeech? = null;
    private var ready = false

    init {
        tts = TextToSpeech(context, this)
    }

    override fun onInit(status: Int) {
        if(status == TextToSpeech.SUCCESS){
            val result = tts?.setLanguage(Locale("es", "ES"))

            ready = result != TextToSpeech.LANG_MISSING_DATA &&
                    result != TextToSpeech.LANG_NOT_SUPPORTED
        }
    }

    override fun speak(text: String) {
        if(!ready) return

        tts?.speak(
            text,
            TextToSpeech.QUEUE_FLUSH,
            null,
            "agentTemiSpeech"
        )
    }

    override fun shutdown() {
        tts?.stop()
        tts?.shutdown()
    }
}
