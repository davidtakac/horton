package hr.dtakac.horton.auddrecognizer

import hr.dtakac.horton.auddrecognizer.api.AUDD_API_KEY
import hr.dtakac.horton.auddrecognizer.api.AuddApiInterface
import hr.dtakac.horton.auddrecognizer.mappers.mapToEntity
import hr.dtakac.horton.domain.recognizer.SongRecognizer
import hr.dtakac.horton.domain.usecases.recognizesong.RecognizeSongResult
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.lang.Exception
import java.time.Duration

class AuddSongRecognizer(
    private val apiInterface: AuddApiInterface
) : SongRecognizer {
    override val sampleDuration: Duration = Duration.ofSeconds(5L)

    override suspend fun recognize(songFilePath: String): RecognizeSongResult {
        val response = try {
            apiInterface.recognize(buildAuddRequestBody(songFilePath))
        } catch (e: Exception) {
            return RecognizeSongResult.NetworkError(e)
        }
        return if (response.result == null) {
            RecognizeSongResult.NotRecognized
        } else {
            RecognizeSongResult.Success(response.result.mapToEntity())
        }
    }

    private fun buildAuddRequestBody(songFilePath: String): RequestBody {
        val file = File(songFilePath)
        return MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("api_token", AUDD_API_KEY)
            .addFormDataPart(
                "file",
                file.name,
                file.asRequestBody("audio/aac".toMediaType())
            )
            .addFormDataPart("return", "apple_music,spotify")
            .build()
    }
}