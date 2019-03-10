package com.breadwallet.crypto.core.bitcoin.jni;

import com.breadwallet.crypto.core.common.jni.JniReference;

public class CoreBitcoinPeerManager extends JniReference {
    protected CoreBitcoinPeerManager(long jniReferenceAddress) {
        super(jniReferenceAddress);
    }

    public native void connect();
}
