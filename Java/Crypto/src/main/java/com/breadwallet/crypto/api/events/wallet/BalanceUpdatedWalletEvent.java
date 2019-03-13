package com.breadwallet.crypto.api.events.wallet;

public class BalanceUpdatedWalletEvent implements WalletEvent {

    private final long balance;

    public BalanceUpdatedWalletEvent(long balance) {
        this.balance = balance;
    }

    public long balance() {
        return balance;
    }
}
