package hr.dtakac.horton.shazamrecognizer.fingerprint.library

import com.google.gson.annotations.SerializedName

data class ShazamFingerprintData(
    @SerializedName("uri")
    val uri: String,
    @SerializedName("samplems")
    val sampleMs: Long
)