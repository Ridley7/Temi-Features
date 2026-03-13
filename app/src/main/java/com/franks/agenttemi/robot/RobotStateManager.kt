import com.franks.agenttemi.domain.model.enums.AvatarState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AvatarStateManager {
    private val _state = MutableStateFlow(AvatarState.IDLE)
    val state : StateFlow<AvatarState> = _state

    fun setState(newState: AvatarState){
        _state.value = newState
    }
}