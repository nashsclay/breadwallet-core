package com.breadwallet.crypto.api.provider;

public interface CryptoApiProvider {

    AccountProvider accountProvider();

    WalletManagerProvider walletManagerProvider();

    NetworkProvider networkProvider();
}
