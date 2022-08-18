package hr.dtakac.horton.ui.recognition.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecognitionCardFrame(
    modifier: Modifier = Modifier,
    art: @Composable () -> Unit = {},
    title: @Composable () -> Unit = {},
    subtitle: @Composable () -> Unit? = {},
    info: @Composable () -> Unit = {},
    progressBar: @Composable () -> Unit = {}
) {
    Card(modifier = modifier.fillMaxWidth()) {
        art()
        Column(modifier = Modifier.padding(16.dp)) {
            CompositionLocalProvider(LocalTextStyle provides MaterialTheme.typography.titleLarge) {
                title()
            }
            CompositionLocalProvider(LocalTextStyle provides MaterialTheme.typography.bodyLarge) {
                Spacer(modifier = Modifier.height(4.dp))
                subtitle()
                Spacer(modifier = Modifier.height(4.dp))
                info()
            }
        }
        progressBar()
    }
}
