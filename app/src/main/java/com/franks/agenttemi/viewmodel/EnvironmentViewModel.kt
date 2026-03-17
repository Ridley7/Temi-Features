import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.franks.agenttemi.domain.model.enums.AvatarState
import com.franks.agenttemi.domain.model.EnvironmentData
import com.franks.agenttemi.domain.model.enums.SpeechPriority
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class EnvironmentViewModel(
    private val observeEnvironmentUseCase: ObserveEnvironmentUseCase,
    private val recommendationUseCase: GetEnvironmentRecommendationUseCase,
    private val attentionManager: AttentionManager,
    private val avatarManager: AvatarStateManager
) : ViewModel() {

    val avatarState : StateFlow<AvatarState> = avatarManager.state
    val environment = observeEnvironmentUseCase()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            null
        )
    private val _sentinelMode = MutableStateFlow(false)
    val sentinelModel: StateFlow<Boolean> = _sentinelMode

    init {
        viewModelScope.launch {

            combine(
                observeEnvironmentUseCase(),
                sentinelModel
            ){
                environment,
                    isActive -> Pair(environment, isActive)
            }
                .collect{
                    (environment, isActive) ->

                    if(!isActive || environment == null ) return@collect

                    checkEnvironmentAlerts(environment)
                }
        }
    }

    private fun checkEnvironmentAlerts(data: EnvironmentData) {

        when {

            data.temperature < 10 -> {
                triggerAlert(
                    message = "La temperatura es muy baja. Se recomienda encender la calefacción."
                )
            }

            data.luminosity < 450 -> {

                triggerAlert(
                    message = "Los niveles de luz son bajos. Se recomienda encender las luces o abrir ventanas."
                )
            }

            data.carbonMonoxide > 800 -> {
                triggerAlert(
                    message = "Atención. Niveles altos de monóxido de carbono detectados."
                )
            }
        }
    }

    private val lastAlertTime = mutableMapOf<String, Long>()
    private val ALERT_COOLDOWN = 15000L

    private fun triggerAlert(message: String) {

        val now = System.currentTimeMillis()
        val lastTime = lastAlertTime[message] ?: 0L

        if(now - lastTime < ALERT_COOLDOWN) return

        avatarManager.setState(AvatarState.ALERT)

        attentionManager.requestAttention(
            AttentionEvent(
                source = AttentionSource.ALERT,
                message = message,
                priority = 8
            )
        )
    }


    fun getRecommendation(data: EnvironmentData) {

        avatarManager.setState(AvatarState.THINKING)

        viewModelScope.launch {
            val recommendation = recommendationUseCase.execute(data)

            //Avatar segun la severidad
            when(recommendation.severity){
                Severity.HIGH -> avatarManager.setState(AvatarState.ALERT)
                Severity.MEDIUM -> avatarManager.setState(AvatarState.TALKING)
                Severity.LOW -> avatarManager.setState(AvatarState.TALKING)
            }

            attentionManager.requestAttention(
                AttentionEvent(
                    source = AttentionSource.SYSTEM,
                    message = recommendation.message,
                    priority = when(recommendation.severity){
                        Severity.HIGH -> 10
                        Severity.MEDIUM -> 7
                        Severity.LOW -> 5
                    }
                )
            )
        }

    }

    //Boton mi entorno
    fun speakEnviroment(data: EnvironmentData){

        avatarManager.setState(AvatarState.TALKING)

        val message = """
        La temperatura actual es ${data.temperature} grados.
        El nivel de humedad es ${data.humidity}.
    """.trimIndent()

        attentionManager.requestAttention(
            AttentionEvent(
                source = AttentionSource.SYSTEM,
                message = message,
                priority = 5
            )
        )
    }

    fun toggleSentinelMode(sentinel: Boolean){
        _sentinelMode.value = !_sentinelMode.value
    }
}

