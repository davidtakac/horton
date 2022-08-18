package hr.dtakac.horton.presentation.mappers

import hr.dtakac.horton.ui.recognition.model.RecognizedSongUiModel
import hr.dtakac.horton.domain.entities.RecognizedSong
import hr.dtakac.horton.ui.history.model.HistoryEntryUiModel

fun RecognizedSong.mapToSongUiModel(): RecognizedSongUiModel = RecognizedSongUiModel(
    title = title,
    subtitle = subtitle,
    artUrl = art?.highRes,
    recognitionTimestamp = recognitionTimestamp,
    releaseDate = releaseDate
)

fun RecognizedSong.mapToHistoryEntryUiModel(): HistoryEntryUiModel = HistoryEntryUiModel(
    title = title,
    subtitle = subtitle,
    artUrl = art?.thumbnail,
    recognitionTimestamp = recognitionTimestamp,
    releaseDate = releaseDate
)