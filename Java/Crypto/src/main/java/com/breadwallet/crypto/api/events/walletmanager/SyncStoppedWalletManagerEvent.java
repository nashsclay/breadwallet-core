package com.breadwallet.crypto.api.events.walletmanager;

public class SyncStoppedWalletManagerEvent implements WalletManagerEvent {

    private final int errorCode;

    public SyncStoppedWalletManagerEvent(int errorCode) {
        this.errorCode = errorCode;
    }

    public int errorCode() {
        return errorCode;
    }
}
