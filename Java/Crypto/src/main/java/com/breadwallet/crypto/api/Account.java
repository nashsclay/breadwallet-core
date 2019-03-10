package com.breadwallet.crypto.api;

import com.breadwallet.crypto.api.bitcoin.BitcoinMasterPubKey;

public interface Account {

    BitcoinMasterPubKey masterPublicKey();
}
