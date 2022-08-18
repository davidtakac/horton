package hr.dtakac.horton.auddrecognizer.api.response

import com.google.gson.annotations.SerializedName

data class AppleMusic(
    @SerializedName("artwork")
    val artwork: AppleMusicArtwork?
)

data class AppleMusicArtwork(
    @SerializedName("url")
    val url: String,
    @SerializedName("width")
    val width: Int,
    @SerializedName("height")
    val height: Int
)