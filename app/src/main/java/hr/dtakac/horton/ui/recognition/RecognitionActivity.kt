package hr.dtakac.horton.ui.recognition

import android.Manifest
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import hr.dtakac.horton.ui.theme.AppTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import dagger.hilt.android.AndroidEntryPoint
import hr.dtakac.horton.presentation.recognition.RecognitionViewModel
import hr.dtakac.horton.ui.recognition.composables.RecognitionScreen
import hr.dtakac.horton.ui.recognition.composables.RecordingPermissionRationaleDialog

@AndroidEntryPoint
class RecognitionActivity : ComponentActivity() {
    private val viewModel: RecognitionViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Content()
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
    @Composable
    private fun Content() {
        val state by viewModel.state

        var showRecordingPermissionRationale by rememberSaveable { mutableStateOf(false) }
        var startRecordingWhenPermissionGranted by rememberSaveable { mutableStateOf(false) }
        val recordingPermissionState = rememberPermissionState(Manifest.permission.RECORD_AUDIO)

        if (startRecordingWhenPermissionGranted && recordingPermissionState.hasPermission) {
            viewModel.startRecording()
            startRecordingWhenPermissionGranted = false
        }

        if (showRecordingPermissionRationale) {
            RecordingPermissionRationaleDialog(
                onGrantClick = {
                    showRecordingPermissionRationale = false
                    startRecordingWhenPermissionGranted = true
                    recordingPermissionState.launchPermissionRequest()
                },
                onDismiss = {
                    showRecordingPermissionRationale = false
                }
            )
        }

        RecognitionScreen(
            progressBarState = state.progressBarState,
            recognitionState = state.recognitionState,
            statusState = state.statusState,
            recordButtonEnabled = state.buttonEnabled,
            onRecordButtonClick = {
                when {
                    recordingPermissionState.hasPermission -> {
                        viewModel.startRecording()
                    }
                    recordingPermissionState.shouldShowRationale -> {
                        showRecordingPermissionRationale = true
                    }
                    else -> {
                        startRecordingWhenPermissionGranted = true
                        recordingPermissionState.launchPermissionRequest()
                    }
                }
            },
            onHistoryButtonClick = { navigateToHistory() }
        )
    }

    private fun navigateToHistory() {
        startActivity(Intent(this, hr.dtakac.horton.ui.history.HistoryActivity::class.java))
    }
}