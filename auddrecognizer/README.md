# auddrecognizer

This module implements the `SongRecognizer` interface from the *domain* module by communicating with 
Audd.io's API. 

## Instructions

1. Register on Audd.io
2. Get an API key
3. Create a new file in the `api` directory named `AuddApiKey.kt`
4. Inside it, add the following line: 
```kt
internal const val AUDD_API_KEY = "your_api_key_here"
```
5. Go to `app/di/ViewModelModule.kt`
6. In the `provideRecognizeSongUseCase` method, replace `@Named(SHAZAM)` with `@Named(AUDD)`.
7. Run the app and enjoy song recognition with Audd.io