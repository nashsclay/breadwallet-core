package com.breadwallet.crypto.api;

import com.breadwallet.crypto.api.bitcoin.BitcoinBackendClient;
import com.breadwallet.crypto.api.bitcoin.BitcoinPersistenceClient;
import com.breadwallet.crypto.api.bitcoin.BitcoinWalletManagerListener;

import java.util.concurrent.Executor;

public final class WalletManagerFactory {

    public static WalletManager create(BitcoinWalletManagerListener listener,
                         Account account,
                         Network network,
                         WalletManager.Mode mode,
                         int earliestKeyTime,
                         String storagePath,
                         BitcoinPersistenceClient persistenceClient,
                         BitcoinBackendClient backendClient) {
        return CryptoApi
                .getCryptoApiProvider()
                .bitcoinWalletManagerProvider()
                .create(listener,
                        account,
                        network,
                        mode,
                        earliestKeyTime,
                        storagePath,
                        persistenceClient,
                        backendClient);
    }

    public static WalletManager create(Executor executor,
                         BitcoinWalletManagerListener listener,
                         Account account,
                         Network network,
                         WalletManager.Mode mode,
                         int earliestKeyTime,
                         String storagePath,
                         BitcoinPersistenceClient persistenceClient,
                         BitcoinBackendClient backendClient) {
        return CryptoApi
                .getCryptoApiProvider()
                .bitcoinWalletManagerProvider()
                .create(executor,
                        listener,
                        account,
                        network,
                        mode,
                        earliestKeyTime,
                        storagePath,
                        persistenceClient,
                        backendClient);
    }
}
