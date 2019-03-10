package com.breadwallet.crypto.api;

import java.util.Map;

public interface WalletManagerPersistenceClient {

    void savePeers(WalletManager walletManager, Map<String, String> data);

    void saveBlocks(WalletManager walletManager, Map<String, String> data);

    // TODO: Add persistence change type
}
