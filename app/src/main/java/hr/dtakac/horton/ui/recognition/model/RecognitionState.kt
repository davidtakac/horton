package hr.dtakac.horton.ui.recognition.model

sealed class RecognitionState {
    object Idle : RecognitionState()
    data class Recognized(val recognizedSong: RecognizedSongUiModel) : RecognitionState()
}

enum class StatusState {
    HIDDEN, NOT_RECOGNIZED
}

sealed class ProgressBarState {
    object Idle : ProgressBarState()
    data class Progress(val value: Float) : ProgressBarState()
    object Indeterminate : ProgressBarState()
}

data class RecognitionScreenState(
    val recognitionState: RecognitionState = RecognitionState.Idle,
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val statusState: StatusState = StatusState.HIDDEN,
    val buttonEnabled: Boolean = true
)
