package hr.dtakac.horton.shazamrecognizer.fingerprint

import hr.dtakac.horton.shazamrecognizer.fingerprint.library.ShazamFingerprintData

sealed class ShazamFingerprintResult {
    data class Success(val data: ShazamFingerprintData) : ShazamFingerprintResult()
    data class Error(val cause: Throwable?) : ShazamFingerprintResult()
}