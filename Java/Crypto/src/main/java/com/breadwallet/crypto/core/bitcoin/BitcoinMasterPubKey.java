package com.breadwallet.crypto.core.bitcoin;

import com.breadwallet.crypto.core.bitcoin.jni.CoreBitcoinMasterPubKey;

public class BitcoinMasterPubKey implements com.breadwallet.crypto.api.bitcoin.BitcoinMasterPubKey {

    /* package */ final CoreBitcoinMasterPubKey masterPubKey;

    public BitcoinMasterPubKey(byte[] seed) {
        this(new CoreBitcoinMasterPubKey(seed));
    }

    public BitcoinMasterPubKey(CoreBitcoinMasterPubKey masterPubKey) {
        this.masterPubKey = masterPubKey;
    }
}
