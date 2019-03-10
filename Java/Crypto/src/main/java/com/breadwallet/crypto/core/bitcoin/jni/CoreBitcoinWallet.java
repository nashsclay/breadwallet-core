package com.breadwallet.crypto.core.bitcoin.jni;

import com.breadwallet.crypto.core.common.jni.JniReference;

// TODO: Review visibility (for class, methods, fields, etc.)
public class CoreBitcoinWallet extends JniReference {

    protected CoreBitcoinWallet(long jniReferenceAddress) {
        super(jniReferenceAddress);
    }

    protected native void disposeNative();
}
