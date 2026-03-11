import com.franks.agenttemi.domain.model.EnvironmentData
import kotlinx.coroutines.flow.Flow

interface EnvironmentDataSource{
    fun observeEnvironment(): Flow<EnvironmentData>
}