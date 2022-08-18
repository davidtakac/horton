package hr.dtakac.horton.domain.entities

import java.time.Instant
import java.time.LocalDate

data class RecognizedSong(
    val title: String,
    val subtitle: String?,
    val art: Art?,
    val recognitionTimestamp: Instant,
    val releaseDate: LocalDate?
)

data class Art(
    val highRes: String,
    val thumbnail: String
)