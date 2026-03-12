import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun AvatarPanel(
    modifier: Modifier = Modifier
){
   Box(
       modifier = modifier,
       contentAlignment = Alignment.Center
   ){
       Text(
           text = "Avatar ",
           style = MaterialTheme.typography.headlineMedium
       )
   }
}

