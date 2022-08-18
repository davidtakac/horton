package hr.dtakac.horton.di

import android.app.Application
import androidx.room.Room
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hr.dtakac.horton.domain.persistence.SongGetter
import hr.dtakac.horton.domain.persistence.SongSaver
import hr.dtakac.horton.roompersistence.SongRepository
import hr.dtakac.horton.roompersistence.database.SongDao
import hr.dtakac.horton.roompersistence.database.SongDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RoomPersistenceModule {
    companion object {
        @Provides
        @Singleton
        fun provideSongDatabase(application: Application): SongDatabase {
            return Room.databaseBuilder(
                application.applicationContext,
                SongDatabase::class.java,
                "song_database"
            ).build()
        }

        @Provides
        @Singleton
        fun provideSongDao(songDatabase: SongDatabase): SongDao {
            return songDatabase.songDao()
        }

        @Provides
        fun provideSongRepository(songDao: SongDao): SongRepository {
            return SongRepository(songDao)
        }
    }

    @Binds
    abstract fun bindSongSaver(songRepository: SongRepository): SongSaver

    @Binds
    abstract fun bindSongGetter(songRepository: SongRepository): SongGetter
}