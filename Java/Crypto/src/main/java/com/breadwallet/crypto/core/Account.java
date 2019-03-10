package com.breadwallet.crypto.core;

import com.breadwallet.crypto.api.bitcoin.BitcoinMasterPubKey;
import com.breadwallet.crypto.core.bitcoin.jni.CoreBitcoinMasterPubKey;
import com.breadwallet.crypto.core.jni.BIP39;

public class Account implements com.breadwallet.crypto.api.Account {

    private final BitcoinMasterPubKey masterPublicKey;

    public Account(String phrase) {
        this(BIP39.deriveKey(phrase));
    }

    public Account(byte[] seed) {
        this.masterPublicKey = new CoreBitcoinMasterPubKey(seed);
    }

    @Override
    public BitcoinMasterPubKey masterPublicKey() {
        return masterPublicKey;
    }
}
