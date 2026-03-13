
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
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
    modifier: Modifier = Modifier
){
    val animationRes = when(state){
        AvatarState.IDLE -> R.raw.idle
        AvatarState.ALERT -> R.raw.alert
        AvatarState.TALKING -> R.raw.alert
        AvatarState.THINKING -> R.raw.speaker
    }

    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(animationRes)
    )

    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever
    )

    LottieAnimation(
        composition = composition,
        progress = { progress },
        modifier = modifier
    )
}
