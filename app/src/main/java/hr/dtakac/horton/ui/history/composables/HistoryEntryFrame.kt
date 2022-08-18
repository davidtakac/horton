package hr.dtakac.horton.ui.history.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HistoryEntryFrame(
    modifier: Modifier = Modifier,
    contentModifier: Modifier = Modifier,
    art: @Composable () -> Unit = {},
    title: @Composable () -> Unit = {},
    subtitle: @Composable () -> Unit = {},
    info: @Composable () -> Unit = {}
) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        art()
        Column(
            modifier = contentModifier,
            verticalArrangement = Arrangement.Center
        ) {
            CompositionLocalProvider(LocalTextStyle provides MaterialTheme.typography.titleMedium) {
                title()
            }
            CompositionLocalProvider(LocalTextStyle provides MaterialTheme.typography.bodyMedium) {
                Spacer(modifier = Modifier.height(4.dp))
                subtitle()
                Spacer(modifier = Modifier.height(4.dp))
                info()
            }
        }
    }
}