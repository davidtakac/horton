package hr.dtakac.horton.auddrecognizer.api

import hr.dtakac.horton.auddrecognizer.api.response.AuddResponse
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST

interface AuddApiInterface {
    @POST("/")
    suspend fun recognize(@Body requestBody: RequestBody): AuddResponse
}