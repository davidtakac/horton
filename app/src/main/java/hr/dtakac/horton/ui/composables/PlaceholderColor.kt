package hr.dtakac.horton.ui.composables

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun placeholderColor(): Color {
    return MaterialTheme.colorScheme.secondary.copy(alpha = 0.6f)
}