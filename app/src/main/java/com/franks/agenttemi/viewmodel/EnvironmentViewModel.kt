import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.franks.agenttemi.domain.model.EnvironmentData
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class EnvironmentViewModel(
    private val observeEnvironmentUseCase: ObserveEnvironmentUseCase,
    private val recommendationUseCase: GetEnvironmentRecommendationUseCase
) : ViewModel() {

    val environment = observeEnvironmentUseCase()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            null
        )

    fun getRecommendation(data: EnvironmentData): String {
        return recommendationUseCase.execute(data)
    }
}
