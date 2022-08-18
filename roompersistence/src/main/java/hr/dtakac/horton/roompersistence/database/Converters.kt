package hr.dtakac.horton.roompersistence.database

import androidx.room.TypeConverter
import java.time.Instant
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Instant? {
        return value?.let { epochMilli ->
            Instant.ofEpochMilli(epochMilli)
        }
    }

    @TypeConverter
    fun instantToTimestamp(value: Instant?): Long? {
        return value?.toEpochMilli()
    }

    @TypeConverter
    fun fromString(value: String?): LocalDate? {
        return value?.let { v -> FORMATTER.parse(v, LocalDate::from) }
    }

    @TypeConverter
    fun localDateToString(value: LocalDate?): String? {
        return value?.let { v -> FORMATTER.format(v) }
    }

    private val FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE
}