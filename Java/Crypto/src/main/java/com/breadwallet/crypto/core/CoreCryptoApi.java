package com.breadwallet.crypto.core;

import com.breadwallet.crypto.api.*;
import com.breadwallet.crypto.api.Account;
import com.breadwallet.crypto.api.bitcoin.BitcoinBackendClient;
import com.breadwallet.crypto.api.bitcoin.BitcoinPersistenceClient;
import com.breadwallet.crypto.api.bitcoin.BitcoinWalletManagerListener;
import com.breadwallet.crypto.api.provider.AccountProvider;
import com.breadwallet.crypto.api.provider.BitcoinNetworkProvider;
import com.breadwallet.crypto.api.provider.BitcoinWalletManagerProvider;
import com.breadwallet.crypto.api.provider.CryptoApiProvider;
import com.breadwallet.crypto.core.bitcoin.BitcoinChainParams;
import com.breadwallet.crypto.core.bitcoin.BitcoinWalletManager;

import java.util.concurrent.Executor;

// TODO: Add guard around initialization occuring once
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
    public BitcoinWalletManagerProvider bitcoinWalletManagerProvider() {
        return new BitcoinWalletManagerProvider() {
            @Override
            public WalletManager create(BitcoinWalletManagerListener listener,
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
            public WalletManager create(Executor executor,
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
    public BitcoinNetworkProvider bitcoinNetworkProvider() {
        return new BitcoinNetworkProvider() {
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
