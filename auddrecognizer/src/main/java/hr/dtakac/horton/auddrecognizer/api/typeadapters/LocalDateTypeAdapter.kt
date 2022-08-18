package hr.dtakac.horton.auddrecognizer.api.typeadapters

import com.google.gson.*
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class LocalDateTypeAdapter : TypeAdapter<LocalDate>() {
    override fun write(out: JsonWriter, value: LocalDate) {
        out.value(FORMATTER.format(value))
    }

    override fun read(input: JsonReader): LocalDate = LocalDate.parse(input.nextString(), FORMATTER)

    companion object {
        private val FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE
    }
}