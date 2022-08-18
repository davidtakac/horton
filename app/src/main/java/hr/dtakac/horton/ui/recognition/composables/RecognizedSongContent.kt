package hr.dtakac.horton.ui.recognition.composables

import androidx.compose.animation.Crossfade
import androidx.compose.runtime.Composable

@Composable
fun RecognizedSongText(
    text: String?,
    placeholder: @Composable () -> Unit,
    actual: @Composable (String) -> Unit
) {
    Crossfade(targetState = text) { t ->
        if (t == null) {
            placeholder()
        } else {
            actual(t)
        }
    }
}
