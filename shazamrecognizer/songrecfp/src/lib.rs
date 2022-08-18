mod fingerprinting {
    pub mod algorithm;
    pub mod signature_format;
    mod hanning;
}

use jni::{objects::{JClass, JString}, JNIEnv,};
use crate::fingerprinting::algorithm::SignatureGenerator;

#[no_mangle]
pub extern "system" fn Java_hr_dtakac_horton_shazamrecognizer_fingerprint_library_NativeShazamFingerprintLibrary_getFingerprintJson<'a>(
    env: JNIEnv<'a>,
    _class: JClass,
    path: JString
) -> JString<'a> {
    let path_rust = String::from(env.get_string(path).unwrap());
    let json = match SignatureGenerator::make_signature_from_file(&path_rust) {
        Ok(signature) => format!(
            "{{\"uri\":\"{}\", \"samplems\":{}}}", 
            signature.encode_to_uri().unwrap(), 
            (signature.number_samples as f32 / signature.sample_rate_hz as f32 * 1000.) as u32
        ),
        Err(e) => format!(
            "{{\"error\":\"{:?}\"}}",
            e
        ),
    };
    return env.new_string(json).unwrap();
}