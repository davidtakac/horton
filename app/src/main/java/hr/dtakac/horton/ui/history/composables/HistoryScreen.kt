package hr.dtakac.horton.ui.history.composables

import androidx.compose.animation.core.DecayAnimationSpec
import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import hr.dtakac.horton.R
import hr.dtakac.horton.ui.history.model.HistoryState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(
    state: HistoryState,
    onBackClick: () -> Unit,
    onTryAgainClick: () -> Unit
) {
    val decayAnimationSpec: DecayAnimationSpec<Float> = rememberSplineBasedDecay()
    val scrollBehavior = remember {
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(decayAnimationSpec = decayAnimationSpec)
    }
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            MediumTopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.label_history))
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_back),
                            contentDescription = stringResource(id = R.string.a11y_back_from_history)
                        )
                    }
                },
                scrollBehavior = scrollBehavior
            )
        }
    ) {
        when (state) {
            is HistoryState.Success -> ListOfHistoryEntriesGroupedByDay(data = state.entries)
            is HistoryState.Empty -> HistoryEmpty()
            is HistoryState.Loading -> HistoryLoading()
            is HistoryState.Error -> HistoryError(
                cause = state.why,
                onTryAgainClick = onTryAgainClick
            )
        }
    }
}