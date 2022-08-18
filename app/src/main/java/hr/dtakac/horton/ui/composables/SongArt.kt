package hr.dtakac.horton.ui.composables

import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil.compose.rememberImagePainter
import coil.size.Scale
import hr.dtakac.horton.R

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SongArt(
    modifier: Modifier = Modifier,
    artUrl: String?,
    placeholder: @Composable () -> Unit,
    actual: @Composable (String) -> Unit
) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Crossfade(targetState = artUrl) { targetArtUrl ->
            if (targetArtUrl == null) {
                placeholder()
            } else {
                actual(targetArtUrl)
            }
        }
    }
}

@Composable
fun RecognizedSongArtPlaceholder(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.ic_album),
        contentDescription = stringResource(id = R.string.a11y_song_art),
        modifier = modifier,
        alignment = Alignment.Center,
        colorFilter = ColorFilter.tint(placeholderColor())
    )
}

@Composable
fun RecognizedSongActualArt(
    modifier: Modifier,
    artUrl: String
) {
    Image(
        painter = rememberImagePainter(
            data = artUrl,
            builder = {
                crossfade(enable = true)
                scale(Scale.FILL)
            }
        ),
        contentDescription = stringResource(id = R.string.a11y_song_art),
        modifier = modifier
    )
}