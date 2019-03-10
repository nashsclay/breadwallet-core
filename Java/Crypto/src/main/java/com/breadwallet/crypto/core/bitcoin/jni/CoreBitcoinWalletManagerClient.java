package com.breadwallet.crypto.core.bitcoin.jni;

// TODO: Add parameters to callbacks
public interface CoreBitcoinWalletManagerClient {

    void handleTransactionEvent();

    void handleWalletEvent();

    void handleWalletManagerEvent();
}
