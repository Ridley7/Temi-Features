import com.franks.agenttemi.domain.model.EnvironmentData
import kotlinx.coroutines.flow.Flow

class ObserveEnvironmentUseCase(
    private val repository: EnvironmentRepository
) {

    operator fun invoke(): Flow<EnvironmentData> {
        return repository.observeEnvironment()
    }

}