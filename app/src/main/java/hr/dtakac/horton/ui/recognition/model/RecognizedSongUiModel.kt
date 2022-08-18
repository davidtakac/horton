package hr.dtakac.horton.ui.recognition.model

import java.time.Instant
import java.time.LocalDate

data class RecognizedSongUiModel(
    val title: String,
    val subtitle: String?,
    val artUrl: String?,
    val recognitionTimestamp: Instant,
    val releaseDate: LocalDate?
)