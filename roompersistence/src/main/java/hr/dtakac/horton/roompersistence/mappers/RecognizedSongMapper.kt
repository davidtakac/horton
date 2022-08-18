package hr.dtakac.horton.roompersistence.mappers

import hr.dtakac.horton.domain.entities.Art
import hr.dtakac.horton.domain.entities.RecognizedSong
import hr.dtakac.horton.roompersistence.database.ArtEmbedded
import hr.dtakac.horton.roompersistence.database.RecognizedSongRow

fun RecognizedSongRow.mapToEntity() = RecognizedSong(
    title = title,
    subtitle = subtitle,
    art = art?.let { a -> Art(highRes = a.highRes, thumbnail = a.thumbnail) },
    recognitionTimestamp = recognitionTimestamp,
    releaseDate = releaseDate
)

fun RecognizedSong.mapToRow() = RecognizedSongRow(
    title = title,
    subtitle = subtitle,
    art = art?.let { a -> ArtEmbedded(highRes = a.highRes, thumbnail = a.thumbnail) },
    recognitionTimestamp = recognitionTimestamp,
    releaseDate = releaseDate
)