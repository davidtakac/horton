package hr.dtakac.horton.auddrecognizer.api.response

import com.google.gson.annotations.SerializedName

data class Spotify(
    @SerializedName("album")
    val album: SpotifyAlbum?
)

data class SpotifyAlbum(
    @SerializedName("images")
    val images: List<SpotifyImage>?
)

data class SpotifyImage(
    @SerializedName("height")
    val height: Int,
    @SerializedName("width")
    val width: Int,
    @SerializedName("url")
    val url: String
)