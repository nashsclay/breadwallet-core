package com.breadwallet.crypto.core.bitcoin;

import com.breadwallet.crypto.core.bitcoin.jni.CoreBitcoinMasterPubKey;

// TODO: Add parameter validation
// TODO: Review visibility (for class, methods, fields, etc.)
public class BitcoinMasterPubKey implements com.breadwallet.crypto.api.bitcoin.BitcoinMasterPubKey {

    /* package */ final CoreBitcoinMasterPubKey masterPubKey;

    public BitcoinMasterPubKey(byte[] seed) {
        this(new CoreBitcoinMasterPubKey(seed));
    }

    private BitcoinMasterPubKey(CoreBitcoinMasterPubKey masterPubKey) {
        this.masterPubKey = masterPubKey;
    }
}
