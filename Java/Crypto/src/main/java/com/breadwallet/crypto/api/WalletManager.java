package com.breadwallet.crypto.api;

public interface WalletManager {

    enum Mode { API_ONLY, API_WITH_P2P_SUBMIT, P2P_WITH_API_SYNC, P2P_ONLY, }

    enum State { CREATED, DISCONNECTED, CONNECTED, SYNCING, DELETED }

    void connect();

    void disconnect();
}
