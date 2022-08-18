package hr.dtakac.horton.ui.recognition.recorder

import android.content.Context
import android.media.AudioManager
import android.media.MediaRecorder
import android.os.Build

class AndroidRecorder(private val context: Context) : Recorder {
    // Using context constructor on S and up, not sure why linter complains
    @Suppress("DEPRECATION")
    private val mediaRecorder by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            MediaRecorder(context)
        } else {
            MediaRecorder()
        }
    }

    override fun start() {
        mediaRecorder.run {
            setAudioSource(getAudioSource())
            setOutputFormat(MediaRecorder.OutputFormat.AAC_ADTS)
            setOutputFile(getOutputFilePath())
            setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
            prepare()
            start()
        }
    }

    override fun stop(): String {
        mediaRecorder.run {
            stop()
            reset()
        }
        return getOutputFilePath()
    }

    private fun getAudioSource(): Int {
        // Unprocessed audio is better for recognition purposes because processing removes
        //  background noise, but oftentimes the music we want to recognize IS the background noise.
        //  See https://developer.android.com/guide/topics/media/mediarecorder#audiocapture/ for more info.
        val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as? AudioManager
        return if (audioManager?.getProperty(AudioManager.PROPERTY_SUPPORT_AUDIO_SOURCE_UNPROCESSED) != null) {
            MediaRecorder.AudioSource.UNPROCESSED
        } else {
            MediaRecorder.AudioSource.VOICE_COMMUNICATION
        }
    }

    private fun getOutputFilePath(): String {
        return "${context.filesDir.absolutePath}/recorded_sound"
    }
}