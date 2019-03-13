package com.breadwallet.crypto.api.provider;

import com.breadwallet.crypto.api.Network;

public interface BitcoinNetworkProvider {

    Network testnet();

    Network mainnet();
}
