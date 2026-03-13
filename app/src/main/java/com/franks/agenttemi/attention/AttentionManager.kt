import android.util.Log
import com.franks.agenttemi.domain.model.enums.SpeechPriority

class AttentionManager (
    private val speechManager: SpeechManager
){
    private var currentPriority = 0;
    private val pendingEvents = mutableListOf<AttentionEvent>()

    init {
        speechManager.onSpeechCompleted = {
            releaseAttention()
        }
    }

    fun requestAttention(event: AttentionEvent){

        if(event.priority >= currentPriority){

            currentPriority = event.priority

            speak(event)
        }else{

            pendingEvents.removeAll {
                it.source == event.source
            }

            pendingEvents.add(event)
        }
    }

    private fun speak(event: AttentionEvent){

        Log.d("FLAG", "LLamando al speechManager")

        speechManager.speak(
            SpeechMessage(
                text = event.message,
                priority = mapPriority(event)
            )
        )
    }

    fun releaseAttention() {
        currentPriority = 0

        processPendingEvents()
    }

    private fun processPendingEvents(){
        val nextEvent = pendingEvents.maxByOrNull { it.priority }

        nextEvent?.let {
            pendingEvents.remove(it)
            requestAttention(it)
        }
    }

    private fun mapPriority(event: AttentionEvent): SpeechPriority {

        return when(event.source) {
            AttentionSource.USER -> SpeechPriority.USER_REQUEST
            AttentionSource.ALERT -> SpeechPriority.ALERT
            else -> SpeechPriority.INFO
        }
    }

}
