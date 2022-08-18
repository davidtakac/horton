package hr.dtakac.horton.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hr.dtakac.horton.domain.coroutines.DispatcherProvider
import hr.dtakac.horton.presentation.coroutines.AndroidDispatcherProvider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideDispatcherProvider(): DispatcherProvider {
        return AndroidDispatcherProvider()
    }
}