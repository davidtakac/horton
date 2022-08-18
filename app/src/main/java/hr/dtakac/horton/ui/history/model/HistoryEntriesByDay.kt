package hr.dtakac.horton.ui.history.model

import java.time.Instant

data class HistoryEntriesByDay(
    val day: Instant,
    val entries: List<HistoryEntryUiModel>
)