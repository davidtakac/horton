package hr.dtakac.horton.ui.composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import hr.dtakac.horton.R
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@Composable
fun subtitleText(artist: String?, releaseDate: LocalDate?): String {
    val locale = Locale.getDefault()
    val yearFormatter = remember(locale) { DateTimeFormatter.ofPattern("y") }
    val yearText = releaseDate?.let { localDate -> yearFormatter.format(localDate) }

    val artistText = artist ?: stringResource(R.string.placeholder_song_subtitle)
    return if (yearText == null) {
        artistText
    } else {
        stringResource(id = R.string.template_artist_year, artistText, yearText)
    }
}