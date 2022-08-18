package hr.dtakac.horton.roompersistence.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [RecognizedSongRow::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class SongDatabase : RoomDatabase() {
    abstract fun songDao(): SongDao
}