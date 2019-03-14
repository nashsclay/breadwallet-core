package com.breadwallet.crypto.api;

import com.breadwallet.crypto.api.factories.WalletManagerFactory;

public interface WalletManager {

    WalletManagerFactory FACTORY = CryptoApi.provider().walletManagerFactory();

    enum Mode { API_ONLY, API_WITH_P2P_SUBMIT, P2P_WITH_API_SYNC, P2P_ONLY, }

    enum State { CREATED, DISCONNECTED, CONNECTED, SYNCING, DELETED }

    void connect();

    void disconnect();
}
