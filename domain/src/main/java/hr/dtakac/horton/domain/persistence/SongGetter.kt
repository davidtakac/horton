package hr.dtakac.horton.domain.persistence

import hr.dtakac.horton.domain.entities.RecognizedSong

interface SongGetter {
    suspend fun getAll(): List<RecognizedSong>
}