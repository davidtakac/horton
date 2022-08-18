package hr.dtakac.horton.ui.history.model

sealed class HistoryState {

    object Loading : HistoryState()

    object Empty : HistoryState()

    data class Success(
        val entries: List<HistoryEntriesByDay>
    ) : HistoryState()

    data class Error(
        val why: Throwable?
    ) : HistoryState()
}
