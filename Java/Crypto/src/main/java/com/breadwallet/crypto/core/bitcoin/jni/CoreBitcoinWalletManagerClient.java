package com.breadwallet.crypto.core.bitcoin.jni;

public interface CoreBitcoinWalletManagerClient {

    void handleTransactionAdded(long wid, long tid);
    void handleTransactionUpdated(long wid, long tid);
    void handleTransactionDeleted(long wid, long tid);

    void handleWalletCreated(long wid);
    void handleWalletBalanceUpdated(long wid, long satoshi);
    void handleWalletDeleted(long wid);

    void handleWalletManagerConnected();
    void handleWalletManagerDisconnected();
    void handleWalletManagerSyncStarted();
    void handleWalletManagerSyncStopped(int errorCode);
}
