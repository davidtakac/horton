package hr.dtakac.horton.shazamrecognizer.fingerprint.library;

public class NativeShazamFingerprintLibrary {
    static {
        System.loadLibrary("songrecfp");
    }

    public static native String getFingerprintJson(String filePath);
}
