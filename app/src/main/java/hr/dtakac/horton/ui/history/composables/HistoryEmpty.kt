package hr.dtakac.horton.ui.history.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import hr.dtakac.horton.R

@Composable
fun HistoryEmpty() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = stringResource(id = R.string.lenny))
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = stringResource(id = R.string.placeholder_history), textAlign = TextAlign.Center)
    }
}
