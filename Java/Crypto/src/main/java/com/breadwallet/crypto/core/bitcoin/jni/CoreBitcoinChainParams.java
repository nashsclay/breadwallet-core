package com.breadwallet.crypto.core.bitcoin.jni;

import com.breadwallet.crypto.core.common.jni.JniReference;

public class CoreBitcoinChainParams
        extends JniReference {

    public static CoreBitcoinChainParams TESTNET = new CoreBitcoinChainParams(getTestnetParams());

    private static native long getTestnetParams();

    protected CoreBitcoinChainParams(long jniReferenceAddress) {
        super(jniReferenceAddress);
    }
}
