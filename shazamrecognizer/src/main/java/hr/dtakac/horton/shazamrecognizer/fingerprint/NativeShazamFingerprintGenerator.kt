package hr.dtakac.horton.shazamrecognizer.fingerprint

import com.google.gson.Gson
import hr.dtakac.horton.domain.coroutines.DispatcherProvider
import hr.dtakac.horton.shazamrecognizer.fingerprint.library.NativeShazamFingerprintLibrary
import hr.dtakac.horton.shazamrecognizer.fingerprint.library.ShazamFingerprintData
import kotlinx.coroutines.withContext
import java.lang.Exception
import kotlin.coroutines.suspendCoroutine

class NativeShazamFingerprintGenerator(
    private val gson: Gson,
    private val dispatcherProvider: DispatcherProvider
) {
    suspend fun getFingerprint(filePath: String): ShazamFingerprintResult {
        return withContext(dispatcherProvider.compute) {
            suspendCoroutine { continuation ->
                val fingerprintJson = NativeShazamFingerprintLibrary.getFingerprintJson(filePath)
                continuation.resumeWith(
                    try {
                        val data = gson.fromJson(fingerprintJson, ShazamFingerprintData::class.java)
                        Result.success(ShazamFingerprintResult.Success(data))
                    } catch (e: Exception) {
                        Result.success(ShazamFingerprintResult.Error(e))
                    }
                )
            }
        }
    }
}