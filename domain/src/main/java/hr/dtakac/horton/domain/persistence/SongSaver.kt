package hr.dtakac.horton.domain.persistence

import hr.dtakac.horton.domain.entities.RecognizedSong

interface SongSaver {
    suspend fun save(recognizedSong: RecognizedSong)
}