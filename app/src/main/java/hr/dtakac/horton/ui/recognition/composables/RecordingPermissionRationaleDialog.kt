package hr.dtakac.horton.ui.recognition.composables

import android.content.res.Configuration
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import hr.dtakac.horton.R

@Composable
fun RecordingPermissionRationaleDialog(
    onGrantClick: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        icon = if (LocalConfiguration.current.orientation != Configuration.ORIENTATION_LANDSCAPE) {
            { Icon(painter = painterResource(id = R.drawable.ic_mic), contentDescription = null) }
        } else {
            null
        },
        title = {
            Text(
                text = stringResource(id = R.string.recording_permission_rationale_title),
                textAlign = TextAlign.Center
            )
        },
        text = {
            if (LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                LazyColumn(modifier = Modifier.height(86.dp)) {
                    item {
                        RationaleText()
                    }
                }
            } else {
                RationaleText()
            }
        },
        confirmButton = {
            TextButton(onClick = onGrantClick) {
                Text(text = stringResource(id = R.string.recording_permission_grant_action))
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(text = stringResource(id = R.string.recording_permission_dismiss_action))
            }
        }
    )
}

@Composable
private fun RationaleText() {
    Text(
        text = stringResource(id = R.string.recording_permission_rationale_text),
    )
}