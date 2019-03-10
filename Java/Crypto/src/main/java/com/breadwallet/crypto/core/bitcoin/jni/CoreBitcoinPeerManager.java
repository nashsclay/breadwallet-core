package com.breadwallet.crypto.core.bitcoin.jni;

import com.breadwallet.crypto.core.common.jni.JniReference;

// TODO: Review visibility (for class, methods, fields, etc.)
public class CoreBitcoinPeerManager extends JniReference {

    protected CoreBitcoinPeerManager(long jniReferenceAddress) {
        super(jniReferenceAddress);
    }

    public native void connect();

    protected native void disposeNative();
}
