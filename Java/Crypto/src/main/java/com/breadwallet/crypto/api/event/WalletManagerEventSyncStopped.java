package com.breadwallet.crypto.api.event;

public class WalletManagerEventSyncStopped implements WalletManagerEvent {

    private final int errorCode;

    public WalletManagerEventSyncStopped(int errorCode) {
        this.errorCode = errorCode;
    }

    public int errorCode() {
        return errorCode;
    }
}
