package com.breadwallet.crypto.api;

import com.breadwallet.crypto.api.bitcoin.BitcoinMasterPubKey;
import com.breadwallet.crypto.api.factories.AccountFactory;

public interface Account {

    AccountFactory FACTORY = CryptoApi.provider().accountFactory();

    BitcoinMasterPubKey masterPublicKey();
}
