package hr.dtakac.horton.ui.recognition.recorder

interface Recorder {
    fun start()
    fun stop(): String
}