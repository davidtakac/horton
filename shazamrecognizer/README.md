# shazamrecognizer

This module corss-compiles the fingerprint algorithm from [SongRec](https://github.com/marin-m/SongRec) to run on Android. It is written in Rust.

## Description
In spirit, this is an Android port of SongRec. The only thing missing is communication with Shazam's backend, which is not provided here. But it was fun to cross-compile Rust code to run on Android. For instructions and install script, see `songrecfp`. 

## Horton integration instructions
If you want to see the module in action, you can integrate it into Horton yourself. 

1. Extend `SongRecognizer`
2. Use `NativeShazamFingerprintGenerator` to generate a fingeprint from the input audio file
3. Send it to Shazam's backend and map the result to `RecognizeSongResult`

*For an example, see auddrecognizer*

## Valuable resources
- [Mozilla guide](https://mozilla.github.io/firefox-browser-architecture/experiments/2017-09-21-rust-on-android.html)
- [suve's ramblings](https://blog.svgames.pl/article/running-rust-on-android)
