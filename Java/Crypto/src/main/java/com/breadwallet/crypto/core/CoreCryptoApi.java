package com.breadwallet.crypto.core;

import com.breadwallet.crypto.api.*;
import com.breadwallet.crypto.api.Account;
import com.breadwallet.crypto.api.bitcoin.BitcoinBackendClient;
import com.breadwallet.crypto.api.bitcoin.BitcoinPersistenceClient;
import com.breadwallet.crypto.api.bitcoin.BitcoinWalletManagerListener;
import com.breadwallet.crypto.api.provider.AccountProvider;
import com.breadwallet.crypto.api.provider.NetworkProvider;
import com.breadwallet.crypto.api.provider.WalletManagerProvider;
import com.breadwallet.crypto.api.provider.CryptoApiProvider;
import com.breadwallet.crypto.core.bitcoin.BitcoinChainParams;
import com.breadwallet.crypto.core.bitcoin.BitcoinWalletManager;

import java.util.concurrent.Executor;

// TODO: Init providers once, instead of on each invocation
// TODO: Review visibility (for class, methods, fields, etc.)
public class CoreCryptoApi implements CryptoApiProvider {

    static {
        System.loadLibrary("crypto");
    }

    @Override
    public AccountProvider accountProvider() {
        return new AccountProvider() {
            @Override
            public Account create(String phrase) {
                return new com.breadwallet.crypto.core.Account(phrase);
            }

            @Override
            public Account create(byte[] seed) {
                return new com.breadwallet.crypto.core.Account(seed);
            }
        };
    }

    @Override
    public WalletManagerProvider walletManagerProvider() {
        return new WalletManagerProvider() {
            @Override
            public WalletManager createBitcoinWalletManager(BitcoinWalletManagerListener listener,
                                                            Account account,
                                                            Network network,
                                                            WalletManager.Mode mode,
                                                            int earliestKeyTime,
                                                            String storagePath,
                                                            BitcoinPersistenceClient persistenceClient,
                                                            BitcoinBackendClient backendClient) {
                return new BitcoinWalletManager(listener,
                        account,
                        network,
                        mode,
                        earliestKeyTime,
                        storagePath,
                        persistenceClient,
                        backendClient);
            }

            @Override
            public WalletManager createBitcoinWalletManager(Executor executor,
                                                            BitcoinWalletManagerListener listener,
                                                            Account account,
                                                            Network network,
                                                            WalletManager.Mode mode,
                                                            int earliestKeyTime,
                                                            String storagePath,
                                                            BitcoinPersistenceClient persistenceClient,
                                                            BitcoinBackendClient backendClient) {
                return new BitcoinWalletManager(executor,
                        listener,
                        account,
                        network,
                        mode,
                        earliestKeyTime,
                        storagePath,
                        persistenceClient,
                        backendClient);
            }
        };
    }

    @Override
    public NetworkProvider networkProvider() {
        return new NetworkProvider() {
            @Override
            public Network testnet() {
                return new Network(new Network.Bitcoin("BTC Testnet", 0x40, BitcoinChainParams.TESTNET));
            }

            @Override
            public Network mainnet() {
                // TODO: Implement this
                return null;
            }
        };
    }
}
