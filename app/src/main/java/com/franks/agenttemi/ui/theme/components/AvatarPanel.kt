
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.franks.agenttemi.R
import com.franks.agenttemi.domain.model.enums.AvatarState
@Composable
fun AvatarPanel(
    state: AvatarState,
    modifier: Modifier = Modifier,
    sentinelMode: Boolean,
    onSentinelChange: (Boolean) -> Unit
){
    val animationRes = when(state){
        AvatarState.IDLE -> R.raw.idle
        AvatarState.ALERT -> R.raw.alert
        AvatarState.TALKING -> R.raw.speaker
        AvatarState.THINKING -> R.raw.thinking
    }

    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(animationRes)
    )

    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever
    )

    Box(
        modifier = modifier
    ) {

        // 🔹 Animación del avatar (fondo)
        LottieAnimation(
            composition = composition,
            progress = { progress },
            modifier = Modifier.fillMaxSize()
        )

        // 🔹 Toggle arriba izquierda
        Row(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Switch(
                checked = sentinelMode,
                onCheckedChange = onSentinelChange
                //checked = true,
                //onCheckedChange = {}
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "Modo centinela",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}