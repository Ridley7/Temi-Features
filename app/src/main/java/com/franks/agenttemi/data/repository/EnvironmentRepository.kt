import com.franks.agenttemi.domain.model.EnvironmentData
import kotlinx.coroutines.flow.Flow

interface EnvironmentRepository {
    fun observeEnvironment(): Flow<EnvironmentData>
}