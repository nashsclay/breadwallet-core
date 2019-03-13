package com.breadwallet.crypto.api.provider;

public interface CryptoApiProvider {

    AccountProvider accountProvider();

    BitcoinWalletManagerProvider bitcoinWalletManagerProvider();

    BitcoinNetworkProvider bitcoinNetworkProvider();
}
