package hr.dtakac.horton.roompersistence.database

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant
import java.time.LocalDate

@Entity(tableName = "recognized_songs")
data class RecognizedSongRow(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int? = null,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "subtitle")
    val subtitle: String?,

    @Embedded(prefix = "art")
    val art: ArtEmbedded?,

    @ColumnInfo(name = "recognition_timestamp")
    val recognitionTimestamp: Instant,

    @ColumnInfo(name = "release_date")
    val releaseDate: LocalDate?
)

data class ArtEmbedded(
    @ColumnInfo(name = "high_res")
    val highRes: String,

    @ColumnInfo(name = "thumbnail")
    val thumbnail: String
)