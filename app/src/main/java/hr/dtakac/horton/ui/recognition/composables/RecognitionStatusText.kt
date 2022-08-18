package hr.dtakac.horton.ui.recognition.composables

import androidx.compose.animation.Crossfade
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import hr.dtakac.horton.R
import hr.dtakac.horton.ui.recognition.model.StatusState

@Composable
fun RecognitionStatusText(statusState: StatusState) {
    Crossfade(targetState = statusState) { targetStatusState ->
        if (targetStatusState == StatusState.NOT_RECOGNIZED) {
            Text(text = stringResource(id = R.string.status_not_recognized))
        }
    }
}
