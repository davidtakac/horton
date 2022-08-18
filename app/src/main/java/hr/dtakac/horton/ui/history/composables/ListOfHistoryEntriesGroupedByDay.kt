package hr.dtakac.horton.ui.history.composables

import android.content.res.Configuration
import android.text.format.DateUtils
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.size.Scale
import coil.transform.RoundedCornersTransformation
import hr.dtakac.horton.R
import hr.dtakac.horton.ui.composables.placeholderColor
import hr.dtakac.horton.ui.history.model.HistoryEntriesByDay
import hr.dtakac.horton.ui.history.model.HistoryEntryUiModel
import hr.dtakac.horton.ui.composables.SongArt
import hr.dtakac.horton.ui.composables.subtitleText
import java.time.Instant
import java.time.LocalDate

@Composable
fun ListOfHistoryEntriesGroupedByDay(data: List<HistoryEntriesByDay>) {
    if (LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        VerticalGrid(data)
    } else {
        VerticalList(data)
    }
}

@Composable
private fun VerticalList(data: List<HistoryEntriesByDay>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp)
    ) {
        for (i in data.indices) {
            val historyEntriesByDay = data[i]

            item {
                Spacer(modifier = Modifier.height(8.dp))
                GroupingHeader(day = historyEntriesByDay.day)
                Spacer(modifier = Modifier.height(12.dp))
            }

            for (j in historyEntriesByDay.entries.indices) {
                item {
                    val showDivider = remember(j) {
                        j != historyEntriesByDay.entries.lastIndex
                    }
                    PortraitHistoryEntry(
                        entry = historyEntriesByDay.entries[j],
                        showDivider = showDivider
                    )
                    if (!showDivider) {
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun VerticalGrid(data: List<HistoryEntriesByDay>) {
    val itemSpacing = remember { 12.dp }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        for (i in data.indices) {
            val historyEntriesByDay = data[i]

            item {
                Spacer(modifier = Modifier.height(8.dp))
                GroupingHeader(day = historyEntriesByDay.day)
                Spacer(modifier = Modifier.height(12.dp))
            }

            for (j in historyEntriesByDay.entries.indices step 3) {
                val first = historyEntriesByDay.entries[j]
                val second = historyEntriesByDay.entries.getOrNull(j + 1)
                val third = historyEntriesByDay.entries.getOrNull(j + 2)
                item {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        LandscapeHistoryEntry(modifier = Modifier.weight(1f), entry = first)
                        if (second != null) {
                            Spacer(modifier = Modifier.width(itemSpacing))
                            LandscapeHistoryEntry(modifier = Modifier.weight(1f), entry = second)
                        }
                        if (third != null) {
                            Spacer(modifier = Modifier.width(itemSpacing))
                            LandscapeHistoryEntry(modifier = Modifier.weight(1f), entry = third)
                        }
                    }
                    Spacer(modifier = Modifier.height(itemSpacing))
                }
            }
        }
    }
}

@Composable
private fun PortraitHistoryEntry(
    entry: HistoryEntryUiModel,
    showDivider: Boolean = false,
) {
    val artSize = remember { 96.dp }
    val contentStartPadding = remember { 16.dp }
    Column(modifier = Modifier.fillMaxWidth()) {
        HistoryEntryFrame(
            modifier = Modifier.fillMaxWidth(),
            contentModifier = Modifier.padding(start = contentStartPadding),
            art = { Art(size = artSize, url = entry.artUrl) },
            title = { Title(text = entry.title) },
            subtitle = { Subtitle(subtitle = entry.subtitle, releaseDate = entry.releaseDate) },
            info = { RecognizedAt(timestamp = entry.recognitionTimestamp) }
        )
        if (showDivider) {
            Spacer(modifier = Modifier.height(8.dp))
            Divider(modifier = Modifier.padding(start = artSize + contentStartPadding))
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LandscapeHistoryEntry(
    modifier: Modifier,
    entry: HistoryEntryUiModel
) {
    val artSize = remember { 96.dp }
    val horizontalPadding = remember { 16.dp }
    val verticalPadding = remember { 8.dp }
    Card(modifier = modifier) {
        HistoryEntryFrame(
            modifier = Modifier.fillMaxWidth(),
            contentModifier = Modifier.padding(horizontal = horizontalPadding, vertical = verticalPadding),
            art = { Art(size = artSize, url = entry.artUrl) },
            title = { Title(text = entry.title, maxLines = 1) },
            subtitle = { Subtitle(subtitle = entry.subtitle, releaseDate = entry.releaseDate) },
            info = { RecognizedAt(timestamp = entry.recognitionTimestamp) }
        )
    }
}

@Composable
private fun Art(size: Dp, url: String?) {
    SongArt(
        artUrl = url,
        placeholder = {
            Image(
                painter = painterResource(id = R.drawable.ic_album),
                contentDescription = stringResource(id = R.string.a11y_song_art),
                modifier = Modifier.size(size),
                colorFilter = ColorFilter.tint(placeholderColor())
            )
        },
        actual = { urlNotNull ->
            Image(
                painter = rememberImagePainter(
                    data = urlNotNull,
                    builder = {
                        transformations(RoundedCornersTransformation(12.dp.value))
                        scale(Scale.FILL)
                    }
                ),
                contentDescription = stringResource(id = R.string.a11y_song_art),
                modifier = Modifier.size(size)
            )
        }
    )
}

@Composable
private fun Title(text: String, maxLines: Int = 2) {
    Text(
        text = text,
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
private fun Subtitle(subtitle: String?, releaseDate: LocalDate?, maxLines: Int = 1) {
    Text(
        text = subtitleText(artist = subtitle, releaseDate = releaseDate),
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
private fun RecognizedAt(timestamp: Instant) {
    Text(
        text = stringResource(
            id = R.string.template_recognition_time,
            DateUtils.formatDateTime(
                LocalContext.current,
                timestamp.toEpochMilli(),
                DateUtils.FORMAT_SHOW_TIME
            )
        ),
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
private fun GroupingHeader(day: Instant) {
    Text(
        text = DateUtils.getRelativeTimeSpanString(
            day.toEpochMilli(),
            Instant.now().toEpochMilli(),
            DateUtils.DAY_IN_MILLIS
        ).toString(),
        style = MaterialTheme.typography.titleMedium
    )
}
