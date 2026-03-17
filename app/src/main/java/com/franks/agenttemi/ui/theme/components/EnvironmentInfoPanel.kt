import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.franks.agenttemi.domain.model.EnvironmentData

@Composable
fun EnvironmentInfoPanel(
    environment: EnvironmentData
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ){
        SensorItem(
            label = "Temperatura",
            value = "${environment.temperature} °C"
        )

        Spacer(modifier = Modifier.height(8.dp))

        SensorItem(
            label = "Humedad",
            value = "${environment.humidity} %"
        )

        Spacer(modifier = Modifier.height(8.dp))

        SensorItem(
            label = "Luminosidad",
            value = "${environment.luminosity} lx"
        )

        Spacer(modifier = Modifier.height(8.dp))

        SensorItem(
            label = "Sonido",
            value = "${environment.sound} dB"
        )

        Spacer(modifier = Modifier.height(8.dp))

        SensorItem(
            label = "Gases inflamables",
            value = "${environment.flammablesGas} ppm"
        )

        Spacer(modifier = Modifier.height(8.dp))

        SensorItem(
            label = "Alcohol",
            value = "${environment.alcohol} ppm"
        )

        Spacer(modifier = Modifier.height(8.dp))

        SensorItem(
            label = "Monóxido de Carbono CO",
            value = "${environment.carbonMonoxide} ppm"
        )

        Spacer(modifier = Modifier.height(8.dp))

        SensorItem(
            label = "Humo",
            value = "${environment.smoke} ppm"
        )

        Spacer(modifier = Modifier.height(8.dp))

        SensorItem(
            label = "Vapores volátiles",
            value = "${environment.volatileVapors} ppm"
        )


    }
}
