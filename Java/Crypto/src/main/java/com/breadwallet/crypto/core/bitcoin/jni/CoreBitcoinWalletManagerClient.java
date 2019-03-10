package com.breadwallet.crypto.core.bitcoin.jni;

public interface CoreBitcoinWalletManagerClient {

    void handleTransactionEvent();

    void handleWalletEvent();

    void handleWalletManagerEvent();
}
