package hr.dtakac.horton.auddrecognizer.api.response

import com.google.gson.annotations.SerializedName
import java.time.LocalDate

data class AuddResponse(
    @SerializedName("status")
    val status: String,
    @SerializedName("result")
    val result: AuddResult?
)

data class AuddResult(
    @SerializedName("artist")
    val artist: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("release_date")
    val releaseDate: LocalDate,
    @SerializedName("apple_music")
    val appleMusic: AppleMusic?,
    @SerializedName("spotify")
    val spotify: Spotify?
)
