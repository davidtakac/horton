package hr.dtakac.horton.domain.usecases

import hr.dtakac.horton.domain.entities.RecognizedSong
import hr.dtakac.horton.domain.persistence.SongGetter

class HistoryUseCase(private val songGetter: SongGetter) {
    suspend fun get(): List<RecognizedSong> {
        return songGetter.getAll()
    }
}