package com.breadwallet.crypto.core.jni;

// TODO: Review visibility (for class, methods, fields, etc.)
public class Bip39 {

    public static native byte[] deriveKey(String phrase);
}
