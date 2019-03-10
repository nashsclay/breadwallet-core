package com.breadwallet.crypto.api.ethereum;

import com.breadwallet.crypto.api.Address;
import com.breadwallet.crypto.api.Amount;
import com.breadwallet.crypto.api.Wallet;

class Transfer extends com.breadwallet.crypto.api.Transfer {
    long core;

    private Transfer(long core, Wallet wallet, Address source, Address target, Amount amount, Amount fee) {
        super(wallet, source, target, amount, fee);
        this.core = core;
    }

    protected Transfer (Wallet wallet, long core) {
        this (core, wallet, null, null, null, null);
    }

}
