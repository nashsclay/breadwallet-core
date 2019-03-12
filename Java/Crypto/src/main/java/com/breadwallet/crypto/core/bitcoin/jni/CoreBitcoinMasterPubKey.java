package com.breadwallet.crypto.core.bitcoin.jni;

import com.breadwallet.crypto.api.bitcoin.BitcoinMasterPubKey;
import com.breadwallet.crypto.core.common.jni.JniReference;

// TODO: Review visibility (for class, methods, fields, etc.)
public class CoreBitcoinMasterPubKey
        extends JniReference implements BitcoinMasterPubKey {

    private static native long createBitcoinMasterPubKey (byte[] seed);

    public CoreBitcoinMasterPubKey(byte[] seed) {
        this(createBitcoinMasterPubKey(seed));
    }

    private CoreBitcoinMasterPubKey(long jniReferenceAddress) {
        super(jniReferenceAddress);
    }
}
