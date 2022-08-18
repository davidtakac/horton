package hr.dtakac.horton.ui.recognition.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.FilledTonalButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import hr.dtakac.horton.R
import hr.dtakac.horton.ui.composables.ButtonContent

@Composable
fun RecognitionButtons(
    modifier: Modifier,
    recordButtonEnabled: Boolean,
    onRecordButtonClick: () -> Unit,
    onHistoryButtonClick: () -> Unit
) {
    Row(modifier = modifier) {
        RecordButton(
            onClick = onRecordButtonClick,
            enabled = recordButtonEnabled
        )
        Spacer(modifier = Modifier.width(12.dp))
        HistoryButton(onClick = onHistoryButtonClick)
    }
}

@Composable
private fun RowScope.RecordButton(
    onClick: () -> Unit,
    enabled: Boolean
) {
    Button(
        onClick = onClick,
        modifier = Modifier.weight(1f),
        enabled = enabled
    ) {
        ButtonContent(
            label = stringResource(id = R.string.label_record),
            iconPainter = painterResource(R.drawable.ic_mic)
        )
    }
}

@Composable
private fun RowScope.HistoryButton(onClick: () -> Unit) {
    FilledTonalButton(
        onClick = onClick,
        modifier = Modifier.weight(1f)
    ) {
        ButtonContent(
            label = stringResource(id = R.string.label_history),
            iconPainter = painterResource(R.drawable.ic_history)
        )
    }
}