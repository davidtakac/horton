package hr.dtakac.horton.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TextPlaceholder(modifier: Modifier) {
    Text(
        text = "",
        modifier = modifier.background(color = placeholderColor(), shape = RoundedCornerShape(4.dp))
    )
}