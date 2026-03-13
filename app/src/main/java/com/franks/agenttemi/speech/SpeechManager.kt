import com.franks.agenttemi.domain.model.enums.SpeechPriority
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SpeechManager (
    private val voiceManager: VoiceManager
){
    private val queue = ArrayDeque<SpeechMessage>()
    private var speaking = false;

    fun speak(message: SpeechMessage){
        if(!speaking){
            //Procesamos el mensaje metiendolo en la cola
            process(message)
        }else{
            if(message.priority == SpeechPriority.USER_REQUEST){
                queue.addFirst(message)
            } else {
                queue.addLast(message)
            }
        }
    }

    fun process(message: SpeechMessage){

        speaking = true;
        voiceManager.speak(message.text)

        //Estimación simple de la duración
        GlobalScope.launch {
            delay(estimateSpeechDuration(message.text))
            speaking = false

            queue.removeFirstOrNull()?.let {
                process(it)
            }
        }

    }

    private fun estimateSpeechDuration(text: String) : Long{
         val words = text.split(" ").size
        return words * 400L
    }
}
