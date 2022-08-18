package hr.dtakac.horton.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import hr.dtakac.horton.domain.persistence.SongGetter
import hr.dtakac.horton.domain.persistence.SongSaver
import hr.dtakac.horton.domain.recognizer.SongRecognizer
import hr.dtakac.horton.domain.usecases.HistoryUseCase
import hr.dtakac.horton.domain.usecases.recognizesong.RecognizeSongUseCase
import hr.dtakac.horton.ui.recognition.recorder.AndroidRecorder
import hr.dtakac.horton.ui.recognition.recorder.Recorder
import javax.inject.Named

@Module
@InstallIn(ViewModelComponent::class)
class ViewModelModule {
    @Provides
    @ViewModelScoped
    fun provideRecorder(@ApplicationContext context: Context): Recorder {
        return AndroidRecorder(context)
    }

    @Provides
    @ViewModelScoped
    fun provideRecognizeSongUseCase(
        @Named(SHAZAM) songRecognizer: SongRecognizer,
        songSaver: SongSaver
    ): RecognizeSongUseCase {
        return RecognizeSongUseCase(songRecognizer, songSaver)
    }

    @Provides
    @ViewModelScoped
    fun provideHistoryUseCase(songGetter: SongGetter): HistoryUseCase {
        return HistoryUseCase(songGetter)
    }
}