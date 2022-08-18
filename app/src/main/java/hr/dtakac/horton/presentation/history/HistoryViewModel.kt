package hr.dtakac.horton.presentation.history

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hr.dtakac.horton.domain.coroutines.DispatcherProvider
import hr.dtakac.horton.ui.history.model.HistoryEntriesByDay
import hr.dtakac.horton.ui.history.model.HistoryState
import hr.dtakac.horton.domain.entities.RecognizedSong
import hr.dtakac.horton.domain.usecases.HistoryUseCase
import hr.dtakac.horton.presentation.mappers.mapToHistoryEntryUiModel
import hr.dtakac.horton.ui.history.model.HistoryEntryUiModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.temporal.ChronoUnit
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val historyUseCase: HistoryUseCase,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {
    private val mutableState: MutableState<HistoryState> = mutableStateOf(HistoryState.Loading)
    val state: State<HistoryState> get() = mutableState

    init {
        getHistory()
    }

    fun getHistory() {
        viewModelScope.launch {
            mutableState.value = HistoryState.Loading

            val result = historyUseCase.get()
            mutableState.value = if (result.isEmpty()) {
                HistoryState.Empty
            } else {
                HistoryState.Success(groupRecognizedSongsByDay(result))
            }
        }
    }

    private suspend fun groupRecognizedSongsByDay(recognizedSongs: List<RecognizedSong>): List<HistoryEntriesByDay> {
        return withContext(dispatcherProvider.compute) {
            recognizedSongs
                .map { entity ->
                    entity.mapToHistoryEntryUiModel()
                }
                .groupBy { entry ->
                    ZonedDateTime
                        .ofInstant(entry.recognitionTimestamp, ZoneId.systemDefault())
                        .toLocalDate()
                }
                .map { entriesGroupedByDay ->
                    HistoryEntriesByDay(
                        day = entriesGroupedByDay.key.atStartOfDay(ZoneId.systemDefault())
                            .toInstant(),
                        entries = entriesGroupedByDay.value.sortedByDescending { entry ->
                            entry.recognitionTimestamp
                        }
                    )
                }
                .sortedByDescending { historyEntriesByDay ->
                    historyEntriesByDay.day
                }
        }
    }

    private fun dummySuccess(): HistoryState.Success {
        val artUrl = "https://via.placeholder.com/400"
        val releaseDate = LocalDate.of(2019, 1, 22)
        return HistoryState.Success(
            entries = listOf(
                HistoryEntriesByDay(
                    day = Instant.now(),
                    entries = mutableListOf<HistoryEntryUiModel>().apply {
                        for (i in 1..9) {
                            add(
                                HistoryEntryUiModel(
                                    title = "Song $i",
                                    subtitle = "Artist $i",
                                    artUrl = artUrl,
                                    recognitionTimestamp = Instant.now().minus(2L * i, ChronoUnit.MINUTES),
                                    releaseDate = releaseDate
                                )
                            )
                        }
                    }
                ),
                HistoryEntriesByDay(
                    day = Instant.now().minus(1, ChronoUnit.DAYS),
                    entries = mutableListOf<HistoryEntryUiModel>().apply {
                        for (i in 1..5) {
                            add(
                                HistoryEntryUiModel(
                                    title = "Song $i",
                                    subtitle = "Artist $i",
                                    artUrl = artUrl,
                                    recognitionTimestamp = Instant.now().minus(2L * i, ChronoUnit.MINUTES),
                                    releaseDate = releaseDate
                                )
                            )
                        }
                    }
                ),
                HistoryEntriesByDay(
                    day = Instant.now().minus(4, ChronoUnit.DAYS),
                    entries = mutableListOf<HistoryEntryUiModel>().apply {
                        for (i in 1..7) {
                            add(
                                HistoryEntryUiModel(
                                    title = "Song $i",
                                    subtitle = "Artist $i",
                                    artUrl = artUrl,
                                    recognitionTimestamp = Instant.now().minus(2L * i, ChronoUnit.MINUTES),
                                    releaseDate = releaseDate
                                )
                            )
                        }
                    }
                )
            )
        )
    }
}