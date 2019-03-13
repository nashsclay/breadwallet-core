package com.breadwallet.crypto.api;

import com.breadwallet.crypto.api.bitcoin.BitcoinMasterPubKey;
import com.breadwallet.crypto.api.provider.AccountProvider;

public interface Account {

    AccountProvider FACTORY = CryptoApi.cryptoApiProvider().accountProvider();

    BitcoinMasterPubKey masterPublicKey();
}
