package com.breadwallet.crypto.core.bitcoin;

import com.breadwallet.crypto.api.bitcoin.BitcoinMasterPubKey;

// TODO: Review visibility (for class, methods, fields, etc.)
/* package */ class BitcoinMasterPubKeyAdapter {

    public static com.breadwallet.crypto.core.bitcoin.BitcoinMasterPubKey from(BitcoinMasterPubKey mbk) {
        if (mbk instanceof com.breadwallet.crypto.core.bitcoin.BitcoinMasterPubKey) {
            return (com.breadwallet.crypto.core.bitcoin.BitcoinMasterPubKey) mbk;
        }
        throw new IllegalArgumentException("Unsupported BitcoinMasterPubKey implementation");
    }
}
