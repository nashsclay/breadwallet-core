package com.breadwallet.crypto.api.event;

public class WalletEventBalanceUpdated implements WalletEvent {

    private final long balance;

    public WalletEventBalanceUpdated(long balance) {
        this.balance = balance;
    }

    public long balance() {
        return balance;
    }
}
