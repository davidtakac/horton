package hr.dtakac.horton.ui.recognition.composables

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import hr.dtakac.horton.ui.recognition.model.ProgressBarState

@Composable
fun RecognizedSongProgressBar(
    modifier: Modifier = Modifier,
    progressBarState: ProgressBarState
) {
    when (progressBarState) {
        is ProgressBarState.Progress -> {
            val animatedProgress by animateFloatAsState(targetValue = progressBarState.value)
            LinearProgressIndicator(
                progress = animatedProgress,
                modifier = modifier,
                trackColor = Color.Transparent
            )
        }
        ProgressBarState.Idle -> LinearProgressIndicator(
            progress = 0f,
            modifier = modifier,
            trackColor = Color.Transparent
        )
        ProgressBarState.Indeterminate -> LinearProgressIndicator(
            modifier = modifier
        )
    }
}
