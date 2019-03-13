package com.breadwallet.crypto.api.provider;

import com.breadwallet.crypto.api.Network;

public interface NetworkProvider {

    Network testnet();

    Network mainnet();
}
