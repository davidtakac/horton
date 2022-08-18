package hr.dtakac.horton.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hr.dtakac.horton.auddrecognizer.AuddSongRecognizer
import hr.dtakac.horton.auddrecognizer.api.AuddApiConstants
import hr.dtakac.horton.auddrecognizer.api.AuddApiInterface
import hr.dtakac.horton.auddrecognizer.api.typeadapters.LocalDateTypeAdapter
import hr.dtakac.horton.domain.recognizer.SongRecognizer
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDate
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AuddRecognizerModule {
    @Provides
    @Singleton
    fun provideAuddApiInterface(
        @Named(AUDD) gson: Gson
    ): AuddApiInterface {
        val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val okHttpClient = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
        return Retrofit.Builder()
            .baseUrl(AuddApiConstants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(AuddApiInterface::class.java)
    }

    @Provides
    @Singleton
    @Named(AUDD)
    fun provideGson(): Gson {
        return GsonBuilder()
            .registerTypeAdapter(LocalDate::class.java, LocalDateTypeAdapter().nullSafe())
            .create()
    }

    @Provides
    @Named(AUDD)
    fun provideAuddSongRecognizer(apiInterface: AuddApiInterface): SongRecognizer {
        return AuddSongRecognizer(apiInterface)
    }
}