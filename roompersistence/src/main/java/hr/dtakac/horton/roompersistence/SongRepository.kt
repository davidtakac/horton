package hr.dtakac.horton.roompersistence

import hr.dtakac.horton.domain.entities.RecognizedSong
import hr.dtakac.horton.domain.persistence.SongGetter
import hr.dtakac.horton.domain.persistence.SongSaver
import hr.dtakac.horton.roompersistence.database.SongDao
import hr.dtakac.horton.roompersistence.mappers.mapToEntity
import hr.dtakac.horton.roompersistence.mappers.mapToRow

class SongRepository(
    private val songDao: SongDao
) : SongSaver, SongGetter {
    override suspend fun getAll(): List<RecognizedSong> {
        return songDao
            .getAll()
            .map { row -> row.mapToEntity() }
    }

    override suspend fun save(recognizedSong: RecognizedSong) {
        songDao.save(recognizedSong.mapToRow())
    }
}