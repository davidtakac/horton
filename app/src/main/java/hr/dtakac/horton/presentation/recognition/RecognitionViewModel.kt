package hr.dtakac.horton.presentation.recognition

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hr.dtakac.horton.domain.usecases.recognizesong.RecognizeSongResult
import hr.dtakac.horton.ui.recognition.recorder.Recorder
import hr.dtakac.horton.domain.usecases.recognizesong.RecognizeSongUseCase
import hr.dtakac.horton.presentation.mappers.mapToSongUiModel
import hr.dtakac.horton.ui.recognition.model.ProgressBarState
import hr.dtakac.horton.ui.recognition.model.RecognitionScreenState
import hr.dtakac.horton.ui.recognition.model.RecognitionState
import hr.dtakac.horton.ui.recognition.model.StatusState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecognitionViewModel @Inject constructor(
    private val recognizeUseCase: RecognizeSongUseCase,
    private val recorder: Recorder
) : ViewModel() {
    private val mutableState = mutableStateOf(RecognitionScreenState())
    val state: State<RecognitionScreenState> get() = mutableState

    fun startRecording() {
        viewModelScope.launch {
            mutableState.value = mutableState.value.copy(buttonEnabled = false)
            recorder.start()
            recordingCountdown()
            recognize(filePath = recorder.stop())
        }
    }

    private suspend fun CoroutineScope.recordingCountdown() {
        var currentProgress = 0f
        while (currentProgress < 1f && isActive) {
            currentProgress += progressIncrement
            mutableState.value = mutableState.value.copy(
                progressBarState = ProgressBarState.Progress(value = currentProgress)
            )
            delay(MILLIS_BETWEEN_TICKS)
        }
    }

    private fun recognize(filePath: String) {
        viewModelScope.launch {
            mutableState.value = mutableState.value.copy(
                progressBarState = ProgressBarState.Indeterminate
            )

            val result = recognizeUseCase.recognize(filePath)
            if (result is RecognizeSongResult.Success) {
                mutableState.value = mutableState.value.copy(
                    statusState = StatusState.HIDDEN,
                    recognitionState = RecognitionState.Recognized(result.recognizedSong.mapToSongUiModel())
                )
            } else {
                mutableState.value = mutableState.value.copy(
                    statusState = StatusState.NOT_RECOGNIZED,
                )
            }

            mutableState.value = RecognitionScreenState(
                recognitionState = mutableState.value.recognitionState,
                statusState = mutableState.value.statusState
            )
        }
    }

    private val progressIncrement: Float by lazy {
        MILLIS_BETWEEN_TICKS / recognizeUseCase.sampleDuration.toMillis().toFloat()
    }

    companion object {
        private const val MILLIS_BETWEEN_TICKS = 20L
    }
}