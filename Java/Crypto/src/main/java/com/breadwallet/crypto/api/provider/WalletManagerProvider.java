package com.breadwallet.crypto.api.provider;

import com.breadwallet.crypto.api.Account;
import com.breadwallet.crypto.api.Network;
import com.breadwallet.crypto.api.WalletManager;
import com.breadwallet.crypto.api.bitcoin.BitcoinBackendClient;
import com.breadwallet.crypto.api.bitcoin.BitcoinPersistenceClient;
import com.breadwallet.crypto.api.bitcoin.BitcoinWalletManagerListener;

import java.util.concurrent.Executor;

public interface WalletManagerProvider {

    WalletManager createBitcoinWalletManager(BitcoinWalletManagerListener listener,
                                             Account account,
                                             Network network,
                                             WalletManager.Mode mode,
                                             int earliestKeyTime,
                                             String storagePath,
                                             BitcoinPersistenceClient persistenceClient,
                                             BitcoinBackendClient backendClient);

    WalletManager createBitcoinWalletManager(Executor listenerExecutor,
                                             BitcoinWalletManagerListener listener,
                                             Account account,
                                             Network network,
                                             WalletManager.Mode mode,
                                             int earliestKeyTime,
                                             String storagePath,
                                             BitcoinPersistenceClient persistenceClient,
                                             BitcoinBackendClient backendClient);
}
