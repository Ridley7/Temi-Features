import com.franks.agenttemi.domain.model.enums.AvatarState
import com.franks.agenttemi.domain.model.enums.SpeechPriority
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.selects.select

class SpeechManager (
    private val voiceManager: VoiceManager,
    private val avatarStateManager: AvatarStateManager
){

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
    private val highPriorityChannel = Channel<SpeechMessage>(Channel.UNLIMITED)
    private val normalChannel = Channel<SpeechMessage>(Channel.UNLIMITED)
    var onSpeechCompleted: (() -> Unit)? = null

    init {
        startWorker()
    }


    fun speak(message: SpeechMessage){
        scope.launch {

            if(message.priority == SpeechPriority.USER_REQUEST){
                highPriorityChannel.send(message)
            }else{
                normalChannel.send(message)
            }

        }
    }

    private fun startWorker(){
        scope.launch {

            while(isActive){
                //val message = highPriorityChannel.tryReceive().getOrNull() ?: normalChannel.receive()

                val message = select<SpeechMessage> {
                    highPriorityChannel.onReceive{
                        it
                    }

                    normalChannel.onReceive{
                        it
                    }
                }

                avatarStateManager.setState(AvatarState.TALKING)
                voiceManager.speak(message.text)
                delay(estimateSpeechDuration(message.text))
                avatarStateManager.setState(AvatarState.IDLE)
                onSpeechCompleted?.invoke()
            }

        }
    }


    private fun estimateSpeechDuration(text: String) : Long{
         val words = text.split(" ").size
        return words * 480L
    }
}
