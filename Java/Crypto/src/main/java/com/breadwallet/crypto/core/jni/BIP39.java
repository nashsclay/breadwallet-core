package com.breadwallet.crypto.core.jni;

public class BIP39 {
    public static native byte[] deriveKey(String phrase);
}
