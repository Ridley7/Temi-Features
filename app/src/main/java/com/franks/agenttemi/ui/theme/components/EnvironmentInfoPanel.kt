import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.franks.agenttemi.domain.model.EnvironmentData

@Composable
fun EnvironmentInfoPanel(
    environment: EnvironmentData
){
    Column{
        SensorItem(
            label = "Temperatura",
            value = "${environment.temperature} °C"
        )

        Spacer(modifier = Modifier.height(8.dp))

        SensorItem(
            label = "Metano",
            value = "${environment.methane} ppm"
        )
    }
}
