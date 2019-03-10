package com.breadwallet.crypto.api.ethereum;

import com.breadwallet.crypto.api.Amount;
import com.breadwallet.crypto.api.Currency;
import com.breadwallet.crypto.api.WalletManager;

public class Wallet extends com.breadwallet.crypto.api.Wallet {
    long core;

    /// Balance

    protected Amount balance;

    @Override
    public Amount getBalance() {
        return balance;
    }

    protected void setBalance(Amount balance) {
        this.balance = balance;
    }

    /// Transfers
    protected Transfer[] transfers;

    public Transfer[] getTransfers() {
        return transfers;
    }

    public void setTransfers(Transfer[] transfers) {
        this.transfers = transfers;
    }


    public Wallet(long core, WalletManager manager, Currency currency, String name, Amount balance, Transfer[] transfers) {
        super(manager, currency, name);
        this.core = core;
        this.balance = balance;
        this.transfers = transfers;
    }

    public Wallet (WalletManager manager, Currency currency, long core) {
        this (core, manager, currency, currency.name,
                new Amount (0, currency.baseUnit),
                new Transfer[]{});
    }
}
