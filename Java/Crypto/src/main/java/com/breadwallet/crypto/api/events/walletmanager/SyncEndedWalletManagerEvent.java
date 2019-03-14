package com.breadwallet.crypto.api.events.walletmanager;

public class SyncEndedWalletManagerEvent implements WalletManagerEvent {

    private final String error;

    public SyncEndedWalletManagerEvent(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return String.format("SyncEndedWalletManagerEvent(err: %s)", error);
    }
}
