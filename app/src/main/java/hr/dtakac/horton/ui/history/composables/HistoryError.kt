package hr.dtakac.horton.ui.history.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import hr.dtakac.horton.R
import hr.dtakac.horton.ui.composables.ButtonContent
import hr.dtakac.horton.ui.composables.placeholderColor

@Composable
fun HistoryError(
    cause: Throwable?,
    onTryAgainClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            painter = painterResource(id = R.drawable.ic_error),
            contentDescription = stringResource(id = R.string.a11y_error_icon),
            modifier = Modifier.size(64.dp),
            tint = placeholderColor()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = R.string.error_history),
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.width(256.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        TryAgainButton(onClick = onTryAgainClick)
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun TryAgainButton(onClick: () -> Unit) {
    Button(onClick = onClick) {
        ButtonContent(
            label = stringResource(id = R.string.label_try_again),
            iconPainter = painterResource(R.drawable.ic_refresh)
        )
    }
}