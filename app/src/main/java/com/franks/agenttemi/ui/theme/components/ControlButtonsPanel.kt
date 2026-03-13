import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.franks.agenttemi.domain.model.EnvironmentData

@Composable
fun ControlButtonsPanel(
    environment: EnvironmentData,
    onRecommendation: () -> String,
    onSpeakEnvironment: () -> Unit
){
    Column{
        Button(
            onClick = { onSpeakEnvironment() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Mi entorno")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {onRecommendation()},
            modifier = Modifier.fillMaxWidth()
        ){
            Text("Mis recomendaciones")
        }


    }
}
