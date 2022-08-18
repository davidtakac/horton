package hr.dtakac.horton.roompersistence.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SongDao {
    @Query("SELECT * FROM recognized_songs")
    suspend fun getAll(): List<RecognizedSongRow>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(recognizedSong: RecognizedSongRow)
}