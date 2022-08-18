package hr.dtakac.horton.ui.recognition.composables

import android.content.res.Configuration
import android.text.format.DateUtils
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import hr.dtakac.horton.ui.composables.*
import hr.dtakac.horton.ui.recognition.model.ProgressBarState
import hr.dtakac.horton.ui.recognition.model.RecognitionState
import hr.dtakac.horton.ui.recognition.model.StatusState
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecognitionScreen(
    progressBarState: ProgressBarState,
    recognitionState: RecognitionState,
    statusState: StatusState,
    recordButtonEnabled: Boolean,
    onRecordButtonClick: () -> Unit,
    onHistoryButtonClick: () -> Unit
) {
    Scaffold(modifier = Modifier.fillMaxSize()) {
        if (LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            LandscapeRecognitionScreen(
                progressBarState = progressBarState,
                recognitionState = recognitionState,
                statusState = statusState,
                recordButtonEnabled = recordButtonEnabled,
                onRecordButtonClick = onRecordButtonClick,
                onHistoryButtonClick = onHistoryButtonClick
            )
        } else {
            PortraitRecognitionScreen(
                progressBarState = progressBarState,
                recognitionState = recognitionState,
                statusState = statusState,
                recordButtonEnabled = recordButtonEnabled,
                onRecordButtonClick = onRecordButtonClick,
                onHistoryButtonClick = onHistoryButtonClick
            )
        }
    }
}

@Composable
fun PortraitRecognitionScreen(
    progressBarState: ProgressBarState,
    recognitionState: RecognitionState,
    statusState: StatusState,
    recordButtonEnabled: Boolean,
    onRecordButtonClick: () -> Unit,
    onHistoryButtonClick: () -> Unit
) {
    Column(
        modifier = Modifier
            // Bottom padding here is a workaround because RecognitionButtons have an implicit
            // bottom padding of 4.dp, which is most likely a Material3 bug.
            .padding(
                top = 24.dp,
                start = 24.dp,
                end = 24.dp,
                bottom = 20.dp
            )
            .fillMaxSize()
    ) {
        RecognitionCard(
            progressBarState = progressBarState,
            recognitionState = recognitionState
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            RecognitionStatusText(statusState = statusState)
        }

        RecognitionButtons(
            modifier = Modifier.fillMaxWidth(),
            recordButtonEnabled = recordButtonEnabled,
            onRecordButtonClick = onRecordButtonClick,
            onHistoryButtonClick = onHistoryButtonClick
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LandscapeRecognitionScreen(
    progressBarState: ProgressBarState,
    recognitionState: RecognitionState,
    statusState: StatusState,
    recordButtonEnabled: Boolean,
    onRecordButtonClick: () -> Unit,
    onHistoryButtonClick: () -> Unit
) {
    Row(modifier = Modifier.fillMaxSize()) {
        Card(
            modifier = Modifier
                .padding(
                    top = 24.dp,
                    bottom = 24.dp,
                    start = 24.dp,
                    end = 36.dp
                )
                .fillMaxHeight()
                .aspectRatio(1f)
        ) {
            SongArt(
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(1f),
                artUrl = (recognitionState as? RecognitionState.Recognized)?.recognizedSong?.artUrl,
                placeholder = {
                    RecognizedSongArtPlaceholder(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(32.dp)
                    )
                },
                actual = { url ->
                    RecognizedSongActualArt(
                        modifier = Modifier.fillMaxSize(),
                        artUrl = url
                    )
                }
            )
        }
        Column(
            modifier = Modifier
                // Bottom padding here is a workaround because RecognitionButtons have an implicit
                // bottom padding of 4.dp, which is most likely a Material3 bug.
                .padding(
                    top = 24.dp,
                    end = 24.dp,
                    bottom = 20.dp
                )
                .fillMaxSize()
        ) {
            RecognitionCard(
                showArt = false,
                progressBarState = progressBarState,
                recognitionState = recognitionState
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                RecognitionStatusText(statusState = statusState)
            }

            RecognitionButtons(
                modifier = Modifier.fillMaxWidth(),
                recordButtonEnabled = recordButtonEnabled,
                onRecordButtonClick = onRecordButtonClick,
                onHistoryButtonClick = onHistoryButtonClick
            )
        }
    }
}

@Composable
private fun RecognitionCard(
    showArt: Boolean = true,
    progressBarState: ProgressBarState,
    recognitionState: RecognitionState,
) {
    RecognitionCardFrame(
        art = {
            if (showArt) {
                SongArt(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f),
                    artUrl = (recognitionState as? RecognitionState.Recognized)?.recognizedSong?.artUrl,
                    placeholder = {
                        RecognizedSongArtPlaceholder(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(64.dp)
                        )
                    },
                    actual = { url ->
                        RecognizedSongActualArt(
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp)),
                            artUrl = url
                        )
                    }
                )
            }
        },
        title = {
            val title = when (recognitionState) {
                is RecognitionState.Recognized -> recognitionState.recognizedSong.title
                else -> null
            }
            RecognizedSongText(
                text = title,
                placeholder = {
                    val placeholderWidth = remember { Random.nextInt(180, 220).dp }
                    TextPlaceholder(modifier = Modifier.width(placeholderWidth))
                },
                actual = { t ->
                    Text(
                        text = t,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            )
        },
        subtitle = {
            val subtitle = when (recognitionState) {
                is RecognitionState.Recognized -> subtitleText(
                    artist = recognitionState.recognizedSong.subtitle,
                    releaseDate = recognitionState.recognizedSong.releaseDate
                )
                else -> null
            }
            RecognizedSongText(
                text = subtitle,
                placeholder = {
                    val placeholderWidth = remember { Random.nextInt(150, 230).dp }
                    TextPlaceholder(modifier = Modifier.width(placeholderWidth))
                },
                actual = { t ->
                    Text(
                        text = t,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            )
        },
        info = {
            val timestamp = when (recognitionState) {
                is RecognitionState.Recognized -> {
                    DateUtils.getRelativeTimeSpanString(
                        LocalContext.current,
                        recognitionState.recognizedSong.recognitionTimestamp.toEpochMilli(),
                        true
                    ).toString()
                }
                else -> null
            }
            RecognizedSongText(
                text = timestamp,
                placeholder = {
                    val placeholderWidth = remember { Random.nextInt(86, 110).dp }
                    TextPlaceholder(modifier = Modifier.width(placeholderWidth))
                },
                actual = { t ->
                    Text(
                        text = t,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            )
        },
        progressBar = {
            RecognizedSongProgressBar(
                modifier = Modifier.fillMaxWidth(),
                progressBarState = progressBarState
            )
        }
    )
}