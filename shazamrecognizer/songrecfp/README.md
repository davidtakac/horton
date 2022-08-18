# songrecfp
Rust implementation of Shazam's audio fingerprinting algorithm. Taken from 
[SongRec](https://github.com/marin-m/SongRec) and modified in the following ways:

1. Replaced stack-allocated arrays with Vecs to avoid segmentation faults on Android device background threads.
2. Pruned dependencies and source files to include only the algorithm that turns an audio file to a fingerprint that Shazam recognizes.
3. Added `lib.rs` which serves as glue between Rust and Android code.
4. Added the `install` script which handles cross-compilation for Android devices.

## Build instructions (Linux and MacOS)
1. [Install Rust](https://www.rust-lang.org/tools/install).
2. Install the `unzip` utility.
3. Navigate to this directory.
4. Run `install`. This will download the required version of Android NDK, cross-compile the library
for Android and copy the compiled files to the `jniLibs` directory.