package hr.dtakac.horton.ui.history

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import hr.dtakac.horton.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint
import hr.dtakac.horton.presentation.history.HistoryViewModel
import hr.dtakac.horton.ui.history.composables.HistoryScreen

@AndroidEntryPoint
class HistoryActivity : ComponentActivity() {
    private val viewModel: HistoryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Content()
            }
        }
    }

    @Composable
    fun Content() {
        val state by viewModel.state
        HistoryScreen(
            state = state,
            onBackClick = { navigateBack() },
            onTryAgainClick = { viewModel.getHistory() }
        )
    }

    private fun navigateBack() {
        finish()
    }
}