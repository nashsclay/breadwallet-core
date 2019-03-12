package com.breadwallet.crypto.core.bitcoin.jni;

import com.breadwallet.crypto.core.common.jni.JniReference;

// TODO: Review visibility (for class, methods, fields, etc.)
public class CoreBitcoinChainParams
        extends JniReference {

    public static CoreBitcoinChainParams TESTNET = new CoreBitcoinChainParams(createTestnetChainParams());

    private static native long createTestnetChainParams();

    private CoreBitcoinChainParams(long jniReferenceAddress) {
        super(jniReferenceAddress);
    }
}
