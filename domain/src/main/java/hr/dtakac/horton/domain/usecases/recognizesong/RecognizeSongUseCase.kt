package hr.dtakac.horton.domain.usecases.recognizesong

import hr.dtakac.horton.domain.persistence.SongSaver
import hr.dtakac.horton.domain.recognizer.SongRecognizer
import java.lang.Exception
import java.time.Duration

class RecognizeSongUseCase(
    private val songRecognizer: SongRecognizer,
    private val songSaver: SongSaver
) {
    val sampleDuration: Duration = songRecognizer.sampleDuration

    suspend fun recognize(songFilePath: String): RecognizeSongResult {
        val result = songRecognizer.recognize(songFilePath)
        if (result is RecognizeSongResult.Success) {
            try {
                songSaver.save(result.recognizedSong)
            } catch (e: Exception) {
                // Ignore this because saving to history is not critical
            }
        }
        return result
    }
}