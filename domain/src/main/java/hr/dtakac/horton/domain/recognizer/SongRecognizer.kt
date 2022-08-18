package hr.dtakac.horton.domain.recognizer

import hr.dtakac.horton.domain.usecases.recognizesong.RecognizeSongResult
import java.time.Duration

interface SongRecognizer {
    val sampleDuration: Duration
    suspend fun recognize(songFilePath: String) : RecognizeSongResult
}