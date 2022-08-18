package hr.dtakac.horton.auddrecognizer.mappers

import hr.dtakac.horton.auddrecognizer.api.response.AppleMusic
import hr.dtakac.horton.auddrecognizer.api.response.AuddResult
import hr.dtakac.horton.auddrecognizer.api.response.Spotify
import hr.dtakac.horton.domain.entities.Art
import hr.dtakac.horton.domain.entities.RecognizedSong
import java.time.Instant

fun AuddResult.mapToEntity() = RecognizedSong(
    title = title,
    subtitle = artist,
    art = findArt(this),
    recognitionTimestamp = Instant.now(),
    releaseDate = releaseDate
)

private fun findArt(result: AuddResult): Art? {
    return getAppleMusicArt(appleMusic = result.appleMusic) ?: getSpotifyArt(spotify = result.spotify)
}

private fun getAppleMusicArt(appleMusic: AppleMusic?): Art? {
    return if (appleMusic?.artwork == null) {
        null
    } else {
        val highResDimen = "1000"
        val thumbnailDimen = "400"
        Art(
            highRes = appleMusic.artwork.url
                .replace("{w}", highResDimen)
                .replace("{h}", highResDimen),
            thumbnail = appleMusic.artwork.url
                .replace("{w}", thumbnailDimen)
                .replace("{h}", thumbnailDimen)
        )
    }
}

private fun getSpotifyArt(spotify: Spotify?): Art? {
    return if (spotify?.album?.images == null) {
        null
    } else {
        val maxRes = spotify.album.images.maxByOrNull { image -> image.height * image.width }?.url
        val lowRes = spotify.album.images.filterNot { image -> image.url == maxRes }.maxByOrNull { image -> image.height * image.width }?.url

        val highRes = maxRes ?: lowRes
        val thumbnail = lowRes ?: highRes

        if (highRes == null || thumbnail == null) {
            null
        } else {
            Art(highRes = highRes, thumbnail = thumbnail)
        }
    }
}