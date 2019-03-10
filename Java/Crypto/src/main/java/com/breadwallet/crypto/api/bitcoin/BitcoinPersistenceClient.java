package com.breadwallet.crypto.api.bitcoin;

import com.breadwallet.crypto.api.WalletManagerPersistenceClient;
import com.breadwallet.crypto.api.WalletManager;

import java.util.Map;

public interface BitcoinPersistenceClient extends WalletManagerPersistenceClient {

    @Override
    default void savePeers(WalletManager walletManager, Map<String, String> data) {
        System.out.println("TST: BTC: savePeers");
    }

    @Override
    default void saveBlocks(WalletManager walletManager, Map<String, String> data) {
        System.out.println("TST: BTC: saveBlocks");
    }

    // TODO: Add persistence change type
}
