package hr.dtakac.horton.domain.usecases.recognizesong

import hr.dtakac.horton.domain.entities.RecognizedSong

sealed class RecognizeSongResult {
    data class Success(
        val recognizedSong: RecognizedSong
    ) : RecognizeSongResult()

    object NotRecognized : RecognizeSongResult()

    data class NetworkError(
        val cause: Throwable?
    ) : RecognizeSongResult()

    data class OtherError(
        val cause: Throwable?
    ) : RecognizeSongResult()
}