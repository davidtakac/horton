package hr.dtakac.horton.ui.history.model

import java.time.Instant
import java.time.LocalDate

data class HistoryEntryUiModel(
    val title: String,
    val subtitle: String?,
    val artUrl: String?,
    val recognitionTimestamp: Instant,
    val releaseDate: LocalDate?
)